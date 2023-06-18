package com.example.newinventoryapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.models.ItemModel;

import java.util.concurrent.atomic.AtomicReference;

public class AddItemActivity extends AppCompatActivity {

    String newItem;
    String newQTY;
    EditText newItemInput;
    EditText newQTYInput;
    Button cancelBTN;
    Button addBTN;
    Boolean empty;
    ImageButton increase;
    ImageButton decrease;
    DBInventory SQLdb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity_layout);

        newItemInput = findViewById(R.id.newItemField);
        newQTYInput = findViewById(R.id.newItemQTY);
        increase = findViewById(R.id.itemQtyIncrease);
        decrease = findViewById(R.id.itemQtyDecrease);
        cancelBTN = findViewById(R.id.cancelBTN);
        addBTN = findViewById(R.id.addBTN);

        AtomicReference<Intent> Intent = new AtomicReference<>(getIntent());

        increase.setOnClickListener(view -> {
            int input = 0, total;
            String value = newQTYInput.getText().toString().trim();
            if (!value.isEmpty()) {
                input = Integer.parseInt(value);
            }
            total = input + 1;
            newQTYInput.setText(String.valueOf(total));
        });

        decrease.setOnClickListener(view -> {
            int input, total;

            String qty = newQTYInput.getText().toString().trim();
            if (qty.equals("0")) {
                Toast.makeText(this, "Item quantity is 0", Toast.LENGTH_LONG).show();
            } else {
                input = Integer.parseInt(qty);
                total = input - 1;
                newQTYInput.setText(String.valueOf(total));
            }
        });

        cancelBTN.setOnClickListener(view -> {
            Intent add = new Intent();
            setResult(0, add);
            this.finish();
        });

        addBTN.setOnClickListener(view -> insertToInventory());

    }

        public void insertToInventory() {
            String message = inputValidator();

            if (!empty) {
                String newItemName = newItem;
                String qty = newQTY;

                ItemModel newItem = new ItemModel(newItemName, newQTY);
                SQLdb.addNewItem(newItem);

                Toast.makeText(this, "Added", Toast.LENGTH_LONG).show();

                Intent add = new Intent();
                setResult(RESULT_OK, add);
                this.finish();
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }

        public String inputValidator() {
            String message = "";
            newItem = newItemInput.getText().toString().trim();
            newQTY = newQTYInput.getText().toString().trim();

            if (newItem.isEmpty()) {
                newItemInput.requestFocus();
                empty = true;
                message = "Specify the item name.";
            } else if (newQTY.isEmpty()) {
                newQTYInput.requestFocus();
                empty = true;
                message = "Specify a quantity.";
            } else {
                empty = false;
            }
            return message;
        }
}
