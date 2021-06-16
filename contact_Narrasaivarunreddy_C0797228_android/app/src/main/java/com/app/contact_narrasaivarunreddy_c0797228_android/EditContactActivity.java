package com.app.contact_narrasaivarunreddy_c0797228_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class EditContactActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextFName;
    EditText editTextLName;
    EditText editTextAddress;
    EditText editTextPhone;
    EditText editTextEmail;
    MaterialButton btnUpdateContact;
    int contactId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        editTextAddress = findViewById(R.id.etAddress);
        editTextEmail = findViewById(R.id.etEmail);
        editTextFName = findViewById(R.id.etFName);
        editTextLName = findViewById(R.id.etLName);
        editTextPhone = findViewById(R.id.etPhone);

        btnUpdateContact = findViewById(R.id.btnUpdate);

        btnUpdateContact.setOnClickListener(this);
        setData();
    }

    @Override
    public void onClick(View v) {
        ContactDatabase contactDatabase = ContactDatabase.getInstance(getApplicationContext());
        Contact contact = new Contact(editTextFName.getText().toString(), editTextLName.getText().toString(),
                editTextEmail.getText().toString(), editTextPhone.getText().toString(), editTextAddress.getText().toString());
        contact.setId(contactId);
        if (isValid()) {
            contactDatabase.contactDao().updateContact(contact);
            finish();
        }
    }

    private boolean isValid() {
        if (!TextUtils.isEmpty(editTextAddress.getText()) && !TextUtils.isEmpty(editTextPhone.getText()) &&
                !TextUtils.isEmpty(editTextFName.getText()) && !TextUtils.isEmpty(editTextLName.getText()) &&
                !TextUtils.isEmpty(editTextEmail.getText())) {
            return true;
        }
        Toast.makeText(this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
        return false;

    }

    private void setData(){
        if(getIntent().hasExtra("DATA")){
            Contact contact = (Contact) getIntent().getSerializableExtra("DATA");
            editTextFName.setText(contact.getFName());
            editTextLName.setText(contact.getLName());
            editTextAddress.setText(contact.getAddress());
            editTextPhone.setText(contact.getPhoneNumber());
            editTextEmail.setText(contact.getEmailAddress());
            contactId = contact.getId();
        }
    }
}