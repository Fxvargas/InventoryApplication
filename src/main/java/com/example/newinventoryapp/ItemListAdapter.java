package com.example.newinventoryapp;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.models.ItemModel;

import java.util.ArrayList;

public class ItemListAdapter extends BaseAdapter {
    private final Activity context;
    private PopupWindow popupWindow;
    ArrayList<ItemModel> items;
    DBInventory SQLdb;

    public ItemListAdapter(Activity context, ArrayList<ItemModel> items, DBInventory SQLdb) {
        this.context = context;
        this.items = items;
        this.SQLdb = SQLdb;
    }

    public static class ViewHolder {
        TextView textViewItemName;
        TextView textViewItemQty;
        ImageButton editBTN;
        ImageButton deleteBTN;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        ViewHolder vh;

        if (convertView == null) {
            vh = new ViewHolder();
            row = inflater.inflate(R.layout.list_view, null, true);

            vh.editBTN = row.findViewById(R.id.editButton);
            vh.textViewItemQty = row.findViewById(R.id.textViewItemQty);
            vh.textViewItemName = row.findViewById(R.id.textViewItemName);
            vh.deleteBTN = row.findViewById(R.id.deleteButton);

            row.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.textViewItemQty.setText(items.get(position).getQuantity());
        vh.textViewItemName.setText(items.get(position).getName());

        final int positionPopup = position;

        vh.editBTN.setOnClickListener(view -> editPopup(positionPopup));
        vh.editBTN.setOnClickListener(view -> {
            SQLdb.deleteData(items.get(positionPopup));
            items = (ArrayList<ItemModel>) SQLdb.getItems();
            notifyDataSetChanged();

            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();

        });

        return row;
    }

    public Object getItem(int position) {
        return position;
    }

    public int getCount() {
        return items.size();
    }

    public long getItemId(int position) {
        return position;
    }


    public void editPopup(final int positionPopup) {
        LayoutInflater inflater = context.getLayoutInflater();
        View layout = inflater.inflate(R.layout.edit_item, context.findViewById(R.id.listViewLayout));

        popupWindow = new PopupWindow(layout, 800, 1000, true);
        popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

        final EditText editItemName = layout.findViewById(R.id.editTextItemUnitPopup);
        final EditText editItemQTY = layout.findViewById(R.id.editTextItemQtyPopup);

        editItemName.setText(items.get(positionPopup).getName());
        editItemQTY.setText(items.get(positionPopup).getQuantity());

        Button save = layout.findViewById(R.id.editSaveButton);
        Button cancel = layout.findViewById(R.id.editCancelBTN);

        save.setOnClickListener(view -> {
            String newItemName = editItemName.getText().toString();
            String newItemQTY = editItemQTY.getText().toString();

            ItemModel item = items.get(positionPopup);
            item.setName(newItemName);
            item.setQuantity(newItemQTY);

            SQLdb.updateData(item);
            items = (ArrayList<ItemModel>) SQLdb.getItems();
            notifyDataSetChanged();

            Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show();

            popupWindow.dismiss();

        });

        cancel.setOnClickListener(view -> {
            Toast.makeText(context, "Canceled", Toast.LENGTH_SHORT).show();
            popupWindow.dismiss();
        });

    }

}
