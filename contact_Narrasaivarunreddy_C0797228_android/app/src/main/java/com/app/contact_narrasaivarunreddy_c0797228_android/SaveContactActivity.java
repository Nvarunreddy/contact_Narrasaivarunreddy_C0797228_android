package com.app.contact_narrasaivarunreddy_c0797228_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class SaveContactActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextFName;
    EditText editTextLName;
    EditText editTextAddress;
    EditText editTextPhone;
    EditText editTextEmail;
    MaterialButton btnSaveContact;
    MaterialButton btnShowList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_contact);
        editTextAddress = findViewById(R.id.etAddress);
        editTextEmail = findViewById(R.id.etEmail);
        editTextFName = findViewById(R.id.etFName);
        editTextLName = findViewById(R.id.etLName);
        editTextPhone = findViewById(R.id.etPhone);

        btnSaveContact = findViewById(R.id.btnSave);
        btnShowList = findViewById(R.id.btnShowList);

        btnShowList.setOnClickListener(this);
        btnSaveContact.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSave) {
            ContactDatabase contactDatabase = ContactDatabase.getInstance(getApplicationContext());
            Contact contact = new Contact(editTextFName.getText().toString(), editTextLName.getText().toString(),
                    editTextEmail.getText().toString(), editTextPhone.getText().toString(), editTextAddress.getText().toString());
            if (isValid()) {
                contactDatabase.contactDao().saveContact(contact);
                editTextPhone.setText("");
                editTextEmail.setText("");
                editTextFName.setText("");
                editTextLName.setText("");
                editTextAddress.setText("");
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
            }
        } else {
            startActivity(new Intent(this, UserListActivity.class));
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
}