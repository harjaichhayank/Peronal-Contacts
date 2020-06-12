package com.example.SQLiteDatabseProject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateContact extends AppCompatActivity {

    EditText searchEditText,searchEmail,searchNumber,SearchName;
    UserDataBaseHelper userDataBaseHelper;
    SQLiteDatabase sqLiteDatabase;
    TextView updateTextView;
    String searchName,newName;

    Button updateContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);

        searchEditText = findViewById(R.id.SearchEditTextId);
        searchEmail = findViewById(R.id.SearchEmailAddressId);
        searchNumber = findViewById(R.id.SearchNumberId);
        SearchName = findViewById(R.id.SearchNameAddressId);

        updateTextView = findViewById(R.id.UpdateTextId);
        updateContact = findViewById(R.id.UpdateSearchedContactId);

        updateTextView.setVisibility(View.INVISIBLE);
        SearchName.setVisibility(View.INVISIBLE);
        searchNumber.setVisibility(View.INVISIBLE);
        searchEmail.setVisibility(View.INVISIBLE);
        updateContact.setVisibility(View.INVISIBLE);
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
                newName = searchName;

                SearchName.setText(newName);
                searchNumber.setText(NUMBER);
                searchEmail.setText(EMAIL);

                updateTextView.setVisibility(View.VISIBLE);
                SearchName.setVisibility(View.VISIBLE);
                searchNumber.setVisibility(View.VISIBLE);
                searchEmail.setVisibility(View.VISIBLE);
                updateContact.setVisibility(View.VISIBLE);
            }while(cursor.moveToNext());
        }
    }

    public void updateContact(View view) {
        userDataBaseHelper = new UserDataBaseHelper(getApplicationContext());
        sqLiteDatabase = userDataBaseHelper.getWritableDatabase();
        String name,email,mobile;
        name = SearchName.getText().toString();
        mobile = searchNumber.getText().toString();
        email = searchEmail.getText().toString();

        int count = userDataBaseHelper.updateInformation(searchName,name,mobile,email,sqLiteDatabase);
        Toast.makeText(getApplicationContext(), count + " updated Contact list", Toast.LENGTH_SHORT).show();
        finish();
    }
}
