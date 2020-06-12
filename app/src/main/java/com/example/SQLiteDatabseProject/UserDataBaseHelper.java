package com.example.SQLiteDatabseProject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;

public class UserDataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "USHERING.DB";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_QUERY =
            "CREATE TABLE " + UserContract.NewUserInfo.TABLE_NAME + "(" + UserContract.NewUserInfo.USER_NAME + " TEXT," +
                    UserContract.NewUserInfo.USER_NUMBER + " TEXT," + UserContract.NewUserInfo.USER_EMAIL + " TEXT);";

    UserDataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e("DATABASE OPERATIONS","Database created");
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_QUERY);
        Log.e("Database Operations","Table Created...");
    }

    void addInformation(String name, String email, String number, SQLiteDatabase database){
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContract.NewUserInfo.USER_NAME,name);
        contentValues.put(UserContract.NewUserInfo.USER_EMAIL,email);
        contentValues.put(UserContract.NewUserInfo.USER_NUMBER,number);
        database.insert(UserContract.NewUserInfo.TABLE_NAME,null,contentValues);
        Log.e("Database Operations","One Row Inserted....");
    }

    Cursor getInformation(SQLiteDatabase database){
        Cursor cursor;
        String[] projections = {UserContract.NewUserInfo.USER_EMAIL,UserContract.NewUserInfo.USER_NAME,UserContract.NewUserInfo.USER_NUMBER};
        cursor = database.query(UserContract.NewUserInfo.TABLE_NAME,projections,null,null,null,null,null);
        return cursor;
    }

    Cursor getContact(String userName,SQLiteDatabase database){
        Cursor cursor;
        String[] projections = {UserContract.NewUserInfo.USER_NUMBER,UserContract.NewUserInfo.USER_EMAIL};
        String selection = UserContract.NewUserInfo.USER_NAME+" LIKE ?";
        String[] selection_args = {userName};
        cursor = database.query(UserContract.NewUserInfo.TABLE_NAME,projections,selection,selection_args,null,null,null);
        return cursor;
    }

    void deleteInformation(String userName, SQLiteDatabase database){
        String selection = UserContract.NewUserInfo.USER_NAME+" LIKE ?";
        String[] selection_args = {userName};
        database.delete(UserContract.NewUserInfo.TABLE_NAME,selection,selection_args);
    }

    public int updateInformation(String oldName, String newName, String newMobile, String newEmail,SQLiteDatabase sqLiteDatabase){
        int count;
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContract.NewUserInfo.USER_NAME,newName);
        contentValues.put(UserContract.NewUserInfo.USER_EMAIL,newEmail);
        contentValues.put(UserContract.NewUserInfo.USER_NUMBER,newMobile);

        String selection = UserContract.NewUserInfo.USER_NAME+" LIKE ?";
        String[] selection_args = {oldName};
        count = sqLiteDatabase.update(UserContract.NewUserInfo.TABLE_NAME,contentValues,selection,selection_args);
        return count;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }
}
