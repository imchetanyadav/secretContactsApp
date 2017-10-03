package com.chetan.contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getContactsList().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> contactsList, View item, int position, long id) {
                Contact contact = (Contact) contactsList.getItemAtPosition(position);
                Intent intention = new Intent(MainActivity.this, ProfileActivity.class);
                intention.putExtra("contact", contact);
                startActivity(intention);
            }
        });

        registerForContextMenu(getContactsList());

        Button newContact = (Button) findViewById(R.id.main_new_contact);
        newContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startInsertContact = new Intent(MainActivity.this, InsertContactActivity.class);
                startActivity(startInsertContact);
            }
        });

    }

    private ListView getContactsList() {
        return (ListView) findViewById(R.id.main_list);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadContacts();
    }

    private void loadContacts() {
        ContactDAO dao = new ContactDAO(this);
        List<Contact> contacts = dao.listAll();
        dao.close();

        ArrayAdapter<Contact> adapter = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, contacts);
        getContactsList().setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if(v.equals(getContactsList())) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            final Contact contact = (Contact) getContactsList().getItemAtPosition(info.position);

            MenuItem edit = menu.add("Edit");
            edit.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Intent intention = new Intent(MainActivity.this, InsertContactActivity.class);
                    intention.putExtra("contact", contact);
                    startActivity(intention);
                    return true;
                }
            });

            MenuItem remove = menu.add("Remove");
            remove.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    ContactDAO dao = new ContactDAO(MainActivity.this);
                    dao.remove(contact);
                    dao.close();
                    Toast.makeText(MainActivity.this, "Removed " + contact.getName(), Toast.LENGTH_SHORT).show();
                    loadContacts();
                    return true;
                }
            });
        }
    }
}
