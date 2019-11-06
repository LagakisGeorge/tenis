package com.example.tenis;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteAccessPermException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class addPel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pel);
    }

    public void CREATE (View view) {
        SQLiteDatabase mydatabase = null;
        Integer n = 0;
        try {

            mydatabase = openOrCreateDatabase("pelates", MODE_PRIVATE, null);


            mydatabase.execSQL("CREATE TABLE IF NOT EXISTS pel ([ID] integer PRIMARY KEY  ," +
                    "[KOD] [int] ," +
                    "[NUM1] [int] ," +
                    "[NUM2] [int] ," +
                    "[CH1] [varchar](55) ," +
                    "[CH2] [varchar](55) ," +
                    "[ONO] [nvarchar](55)  ); ");


            String c = "CREATE TABLE IF NOT EXISTS parousies(" +
                    "[IDPEL] [nvarchar](55) ," +
                    "[HME] [datetime] ," +
                    "[IDBARDIA] [int] ," +
                    "[AJIA] [real] ," +
                    "[TROPOS] [int] ," +
                    "[NUM1] [int] ," +
                    "[NUM2] [int] ," +
                    "[CH1] [varchar](55) ," +
                    "[CH2] [varchar](55) ," +
                    "[ID] integer PRIMARY KEY )";

            mydatabase.execSQL(c);


        } catch (
                SQLiteAccessPermException e2) {
            e2.printStackTrace();
        }


    }

}
