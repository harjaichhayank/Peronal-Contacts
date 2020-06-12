package com.example.SQLiteDatabseProject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void newUserInfo(View view) {
        startActivity(new Intent(this,UserInfo.class));
    }

    public void viewContact(View view) {
        startActivity(new Intent(this,ViewContacts.class));
    }

    public void searchContact(View view) { startActivity(new Intent(this, SearchAndDeleteContact.class)); }

    public void updateContact(View view) { startActivity(new Intent(this,UpdateContact.class));}
}
