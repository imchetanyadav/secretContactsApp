package com.chetan.contacts;

import android.app.Activity;
import android.widget.EditText;

public class ContactFormViewHelper {
    private final Activity activity;

    public ContactFormViewHelper(Activity activity) {
        this.activity = activity;
    }

    private String getTextFieldValue(int fieldId){
        EditText field = (EditText) activity.findViewById(fieldId);
        String value = field.getText().toString();
        return value;
    }

    public String getName(){
        return getTextFieldValue(R.id.form_name);
    }

    public String getPhoneNumber(){
        return getTextFieldValue(R.id.form_phoneno);
    }

    public String getEmail(){
        return getTextFieldValue(R.id.form_email);
    }

    public String getAddress(){
        return getTextFieldValue(R.id.form_address);
    }

    public String getMoreInfo(){
        return getTextFieldValue(R.id.form_more_info);
    }

    public Contact createAContact() {
        return new Contact(getName(), getPhoneNumber(), getEmail(), getAddress(), getMoreInfo());
    }

    public void fillInTheForm(Contact contact) {
        fill(R.id.form_name, contact.getName());
        fill(R.id.form_phoneno, contact.getPhoneNumber());
        fill(R.id.form_email, contact.getEmail());
        fill(R.id.form_address, contact.getAddress());
        fill(R.id.form_more_info, contact.getMoreInfo());
    }

    private void fill(int id, String value) {
        EditText field = (EditText) activity.findViewById(id);
        field.setText(value);
    }
}
