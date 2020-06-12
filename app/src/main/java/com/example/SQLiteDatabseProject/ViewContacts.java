package com.example.SQLiteDatabseProject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

public class ViewContacts extends AppCompatActivity {

    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    UserDataBaseHelper userDataBaseHelper;
    Cursor cursor;

    ListDataAdapter listDataAdapter;
    DataProvider dataProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contacts);

        listView = findViewById(R.id.ListViewId);
        listDataAdapter = new ListDataAdapter(getApplicationContext(),R.layout.row_layout);
        listView.setAdapter(listDataAdapter);

        userDataBaseHelper = new UserDataBaseHelper(getApplicationContext());
        sqLiteDatabase = userDataBaseHelper.getReadableDatabase();
        cursor = userDataBaseHelper.getInformation(sqLiteDatabase);

        if (cursor.moveToFirst()){
            do {
                String name,email,number;
                name = cursor.getString(0);
                email = cursor.getString(1);
                number = cursor.getString(2);

                dataProvider = new DataProvider(name,email,number);
                listDataAdapter.add(dataProvider);
            }while (cursor.moveToNext());
        }
    }
}
