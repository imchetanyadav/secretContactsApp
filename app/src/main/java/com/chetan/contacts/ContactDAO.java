package com.chetan.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ContactDAO extends SQLiteOpenHelper {
    public ContactDAO(Context context) {
        super(context, "ContactDetails", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE contacts(id INTEGER PRIMARY KEY, name TEXT NOT NULL, phoneno TEXT NOT NULL, email TEXT, address TEXT, moreinfo TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(Contact contact) {
        ContentValues data = contact.toContentValues();
        SQLiteDatabase database = getWritableDatabase();
        database.insert("contacts", null, data);

    }

    public List<Contact> listAll() {

        List<Contact> contacts = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM contacts;", null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String phoneno = cursor.getString(cursor.getColumnIndex("phoneno"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String address = cursor.getString(cursor.getColumnIndex("address"));
            String moreinfo = cursor.getString(cursor.getColumnIndex("moreinfo"));
            Contact contact = new Contact(id, name, phoneno, email, address, moreinfo);
            contacts.add(contact);
        }
        return contacts;
    }

    public void remove(Contact contact) {
        SQLiteDatabase database = getWritableDatabase();
        String[] params = {contact.getId() + ""};
        database.delete("contacts", "id = ?", params);
    }

    public void update(Contact contact, int originalId) {
        ContentValues data = contact.toContentValues();
        SQLiteDatabase database = getWritableDatabase();
        String[] params = {originalId + ""};
        database.update("contacts", data, "id=?", params);
    }
}
