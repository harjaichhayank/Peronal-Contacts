package com.example.SQLiteDatabseProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UserInfo extends AppCompatActivity {

    EditText ContactName,EmailAddress,MobileNumber;
    Context context = this;
    UserDataBaseHelper userDataBaseHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        ContactName = findViewById(R.id.ContactNameId);
        EmailAddress = findViewById(R.id.EmailAddressId);
        MobileNumber = findViewById(R.id.MobileNumberId);
    }

    public void newUserInfo(View view) {
        String name = ContactName.getText().toString();
        String email = EmailAddress.getText().toString();
        String number = MobileNumber.getText().toString();

        userDataBaseHelper = new UserDataBaseHelper(context);
        sqLiteDatabase = userDataBaseHelper.getWritableDatabase();
        userDataBaseHelper.addInformation(name,email,number,sqLiteDatabase);
        Toast.makeText(getBaseContext(), "Data Saved", Toast.LENGTH_SHORT).show();
        userDataBaseHelper.close();
    }
}
