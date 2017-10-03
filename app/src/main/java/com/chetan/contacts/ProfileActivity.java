package com.chetan.contacts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        final Contact contact = (Contact) intent.getSerializableExtra("contact");

        TextView name = (TextView) findViewById(R.id.profile_name);
        name.setText(contact.getName());

        TextView phoneno = (TextView) findViewById(R.id.profile_phoneno);
        phoneno.setText(contact.getPhoneNumber());

        TextView email = (TextView) findViewById(R.id.profile_email);
        email.setText(contact.getEmail());

        TextView address = (TextView) findViewById(R.id.profile_address);
        address.setText(contact.getAddress());

        TextView moreinfo = (TextView) findViewById(R.id.profile_moreinfo);
        moreinfo.setText(contact.getMoreInfo());

        Button call = (Button) findViewById(R.id.profile_call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String no = contact.getPhoneNumber().toString();
                Toast.makeText(ProfileActivity.this, "Calling " + no, Toast.LENGTH_SHORT).show();
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                phoneIntent.setData(Uri.parse("tel:" + no));
                startActivity(phoneIntent);
            }
        });

        Button share = (Button) findViewById(R.id.profile_share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Contact");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, contact.getName() + "- Phoneno: " + contact.getPhoneNumber() + " Email: " + contact.getEmail());
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
