package com.example.socket.smarthealthdetector;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by HP-User on 4/9/2017.
 */


public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "SmartHealth";

    // Contacts table name
    private static final String TABLE_DOCTORS = "doctors";

    // Contacts Table Columns names
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";
    private static final String KEY_SPECIAlITY = "speciality";
    Context mContext;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_DOCTORS + "("
                + KEY_ADDRESS + " TEXT," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT," + KEY_SPECIAlITY + " TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOCTORS);

        // Create tables again
        onCreate(db);
    }

    void addDoctor(Doctor doctor) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, doctor.getName()); // Contact Name
        values.put(KEY_PH_NO, doctor.getContactNo()); // Contact Phone
        values.put(KEY_ADDRESS, doctor.getAddress());
        values.put(KEY_SPECIAlITY, doctor.getSpeciality());
        // Inserting Row
        long rowInserted = db.insert(TABLE_DOCTORS, null, values);
        if (rowInserted != -1)
            Toast.makeText(mContext, "New row added, row id: " + rowInserted, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(mContext, "Something wrong", Toast.LENGTH_SHORT).show();
        db.close(); // Closing database connection
    }

}