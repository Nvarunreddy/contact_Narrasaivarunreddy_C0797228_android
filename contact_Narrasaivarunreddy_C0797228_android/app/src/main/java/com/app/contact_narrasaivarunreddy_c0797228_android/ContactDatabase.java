package com.app.contact_narrasaivarunreddy_c0797228_android;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Contact.class}, version = 1)
public abstract class ContactDatabase extends RoomDatabase {

    public abstract ContactDao contactDao();

    private static ContactDatabase instance;

    public static ContactDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, ContactDatabase.class, "database")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;

    }
}
