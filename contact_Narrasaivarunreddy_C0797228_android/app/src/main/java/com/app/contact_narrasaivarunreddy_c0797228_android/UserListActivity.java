package com.app.contact_narrasaivarunreddy_c0797228_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity implements TextWatcher {

    RecyclerView recyclerView;
    EditText editSearch;
    List<Contact> mContactList = new ArrayList<>();
    ContactDatabase contactDatabase;
    RecyclerListAdapter recyclerListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        recyclerView = findViewById(R.id.recyclerView);
        editSearch =  findViewById(R.id.editSearch);
        contactDatabase = ContactDatabase.getInstance(getApplicationContext());
        recyclerListAdapter = new RecyclerListAdapter(mContactList);
        recyclerView.setAdapter(recyclerListAdapter);

        editSearch.addTextChangedListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mContactList.clear();
        mContactList.addAll(contactDatabase.contactDao().getContactList());
        recyclerListAdapter.notifyDataSetChanged();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if(s.length() == 0){
            recyclerListAdapter.updateList(mContactList);
        }else{
            List<Contact> searchList = new ArrayList<>();
            for(Contact contact: mContactList){
                if(contact.getFName().toLowerCase().contains(s.toString().toLowerCase())){
                    searchList.add(contact);
                }
            }
            recyclerListAdapter.updateList(searchList);
        }
    }
}