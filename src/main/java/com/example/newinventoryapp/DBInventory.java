package com.example.newinventoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.models.ItemModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DBInventory extends SQLiteOpenHelper {

    private static final String DBNAME = "InventoryDB.db";
    private static final String TABLE_NAME = "Inventory";

    private static final String ITEM_ID = "id";
    private static final String ITEM_NAME = "name";
    private static final String ITEM_QUANTITY = "quantity";

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
            ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            ITEM_NAME + " VARCHAR, " +
            ITEM_QUANTITY + " VARCHAR" + ");";


    public DBInventory(Context context) {

        super(context, DBNAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase SQLdb) {

        SQLdb.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase SQLdb, int i, int i1) {

        SQLdb.execSQL("drop Table if exists inventory");

    }

    // Get items in inventory

    public List<ItemModel> getItems(){
        List<ItemModel> itemList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase SQLdb = this.getWritableDatabase();
        Cursor cursor = SQLdb.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ItemModel itemModel = new ItemModel();
                itemModel.setId(Integer.parseInt(cursor.getString(0)));
                itemModel.setName(cursor.getString(1));
                itemModel.setQuantity(cursor.getString(2));
                itemList.add(itemModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return itemList;
    }

    public void deleteAll(){
        SQLiteDatabase SQLdb = this.getWritableDatabase();
        SQLdb.delete(TABLE_NAME, null, null);
        SQLdb.close();
    }
    public void addNewItem(ItemModel itemModel) {

        SQLiteDatabase SQLdb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ITEM_NAME, itemModel.getName());
        contentValues.put(ITEM_QUANTITY, itemModel.getQuantity());

        SQLdb.insert(TABLE_NAME, null, contentValues);
        SQLdb.close();
    }

    public Boolean updateData(ItemModel itemModel) {

        SQLiteDatabase SQLdb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ITEM_NAME, itemModel.getName());
        contentValues.put(ITEM_QUANTITY, itemModel.getQuantity());

            long result = SQLdb.update(TABLE_NAME, contentValues, ITEM_ID + " = ?", new String[]{String.valueOf(itemModel.getId())});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
    }

    public Boolean deleteData(ItemModel itemModel) {

        SQLiteDatabase SQLdb = this.getWritableDatabase();

            long result = SQLdb.delete(TABLE_NAME, ITEM_ID + " = ?", new String[]{String.valueOf(itemModel.getId())});
            if (result == -1) {
                return false;
            } else {
                return true;
            }

    }

    public ItemModel getData(int id) {

        SQLiteDatabase SQLdb = this.getWritableDatabase();

        Cursor cursor = SQLdb.query(TABLE_NAME, new String[] {
                ITEM_ID, ITEM_NAME, ITEM_QUANTITY },
                ITEM_ID + " = ?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
            ItemModel itemModel = new ItemModel(Integer.parseInt(Objects.requireNonNull(cursor).getString(0)), cursor.getString(1), cursor.getString(2));
            cursor.close();
            return itemModel;
        };


    public int getItemCount() {
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase SQLdb = this.getReadableDatabase();
        Cursor cursor = SQLdb.rawQuery(countQuery, null);
        int totalItems = cursor.getCount();
        cursor.close();

        return totalItems;
    }

    }