package com.chetan.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class InsertContactActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        if(hasIntentionToUpdate()){
            Contact contact = getOriginalContactToUpdate();
            ContactFormViewHelper helper = new ContactFormViewHelper(this);
            helper.fillInTheForm(contact);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.form_save:
                ContactFormViewHelper helper = new ContactFormViewHelper(this);
                Contact contact = helper.createAContact();
                ContactDAO dao = new ContactDAO(this);

                if(hasIntentionToUpdate()){
                    dao.update(contact, getOriginalContactToUpdate().getId());
                }else {
                    dao.insert(contact);
                }
                dao.close();

                Toast.makeText(InsertContactActivity.this, contact.getName() + " was saved!", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.form_cancel:
                finish();
                break;
            case android.R.id.home:
                this.finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private boolean hasIntentionToUpdate() {
        return getIntent().hasExtra("contact");
    }

    private Contact getOriginalContactToUpdate() {
        Intent intent = getIntent();
        Contact contact = (Contact) intent.getSerializableExtra("contact");
        return contact;
    }

}
