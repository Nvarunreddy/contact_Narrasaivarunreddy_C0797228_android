package com.app.contact_narrasaivarunreddy_c0797228_android;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("select * from Contact")
    List<Contact> getContactList();

    @Insert
    void saveContact(Contact contact);

    @Update
    void updateContact(Contact contact);

    @Delete
    void deleteContact(Contact contact);



}
