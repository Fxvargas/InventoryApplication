package com.example.newinventoryapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.models.ItemModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainScreen extends AppCompatActivity {

    ArrayList<ItemModel> itemList;
    Button addBtn;
    int itemCount;
    FloatingActionButton addBTN;
    DBInventory SQLdb;
    ListView InventoryList;
    ItemListAdapter itemListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        addBTN = findViewById(R.id.addBTN);
        SQLdb = new DBInventory(this);
        itemList = (ArrayList<ItemModel>) SQLdb.getItems();
        itemCount = SQLdb.getItemCount();

        String tempName;
        String tempQTY;
        tempName = "testnameone";
        tempQTY = "5";

        ItemModel testItem = new ItemModel(tempName, tempQTY);
        SQLdb.addNewItem(testItem);

        if (itemCount > 0) {
            itemListAdapter = new ItemListAdapter(this, itemList, SQLdb);
            InventoryList.setAdapter(itemListAdapter);
        } else {
            Toast.makeText(this, "Database is empty", Toast.LENGTH_LONG).show();
        }
        addBTN.setOnClickListener(view -> {
            Intent add = new Intent(this, AddItemActivity.class);
            startActivityForResult(add, 1);
        });
    }
}
        /**
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameTXT = itemName.getText().toString();
                String qtyTXT = itemQty.getText().toString();

                if (nameTXT.equals("") || qtyTXT.equals("")) {
                    Toast.makeText(MainScreen.this, "Fill out blank fields", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkAddData = SQLdb.addData(nameTXT, qtyTXT);
                    if (checkAddData == true) {
                        Toast.makeText(MainScreen.this, "Item added", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainScreen.this, "Item add failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = itemName.getText().toString();
                String qtyTXT = itemQty.getText().toString();
                if (nameTXT.equals("") || qtyTXT.equals("")) {
                    Toast.makeText(MainScreen.this, "Fill out blank fields", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkUpdateData = SQLdb.updateData(nameTXT, qtyTXT);
                    if (checkUpdateData == true) {
                        Toast.makeText(MainScreen.this, "Item updated", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainScreen.this, "Item update failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = itemName.getText().toString();
                if (nameTXT.equals("")) {
                    Toast.makeText(MainScreen.this, "Specify item name", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkDeleteData = SQLdb.deleteData(nameTXT);
                    if (checkDeleteData == true) {
                        Toast.makeText(MainScreen.this, "Item deleted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainScreen.this, "Item delete failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        viewBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){

                Cursor res = SQLdb.getData();

                if(res.getCount() == 0) {

                    Toast.makeText(MainScreen.this, "Inventory is empty", Toast.LENGTH_SHORT).show();
                    return;

                }

                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()) {

                    buffer.append("Name: " + res.getString(0) + "\n");
                    buffer.append("Quantity: " + res.getString(1) + "\n\n");

                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainScreen.this);
                builder.setCancelable(true);
                builder.setTitle("Inventory");
                builder.setMessage(buffer.toString());
                builder.show();



            }

        });

    }
}
         **/