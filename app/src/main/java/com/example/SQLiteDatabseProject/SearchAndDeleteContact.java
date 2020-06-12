package com.example.SQLiteDatabseProject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchAndDeleteContact extends AppCompatActivity {

    EditText searchEditText;
    TextView searchEmail,searchNumber;
    UserDataBaseHelper userDataBaseHelper;
    SQLiteDatabase sqLiteDatabase;

    String searchName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_and_delete_contact);

        searchEditText = findViewById(R.id.SearchEditTextId);
        searchEmail = findViewById(R.id.SearchEmailAddressId);
        searchNumber = findViewById(R.id.SearchNumberId);

        searchNumber.setVisibility(View.INVISIBLE);
        searchEmail.setVisibility(View.INVISIBLE);
    }

    public void searchContactInfo(View view) {
        searchName = searchEditText.getText().toString();
        userDataBaseHelper = new UserDataBaseHelper(getApplicationContext());
        sqLiteDatabase = userDataBaseHelper.getReadableDatabase();
        Cursor cursor = userDataBaseHelper.getContact(searchName,sqLiteDatabase);
        if(cursor.moveToFirst()){
            do {
                String NUMBER = cursor.getString(0);
                String EMAIL = cursor.getString(1);

                searchEmail.setText(EMAIL);
                searchNumber.setText(NUMBER);

                searchNumber.setVisibility(View.VISIBLE);
                searchEmail.setVisibility(View.VISIBLE);
            }while(cursor.moveToNext());
        }
    }

    public void deleteInfo(View view) {
        userDataBaseHelper = new UserDataBaseHelper(getApplicationContext());
        sqLiteDatabase = userDataBaseHelper.getReadableDatabase();
        userDataBaseHelper.deleteInformation(searchName,sqLiteDatabase);
        Toast.makeText(getBaseContext(), "Contact Has Been Deleted", Toast.LENGTH_SHORT).show();
    }
}
