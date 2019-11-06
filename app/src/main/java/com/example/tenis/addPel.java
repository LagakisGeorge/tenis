package com.example.tenis;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteAccessPermException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class addPel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pel);
        EditText mKOD;

        mKOD = (EditText) findViewById(R.id.KOD);

        mKOD.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

              if (s.toString().length()==4) adding();
              //  Toast.makeText(getApplicationContext(), "editable "+s.toString(), Toast.LENGTH_SHORT).show();

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                /*This method is called to notify you that, within s, the count characters beginning at start are about to be replaced by new text with length after. It is an error to attempt to make changes to s from this callback.*/
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }


        });
    }


   /* @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_6:
                // Don't allow this value to go to 0. It shuts the screen off.
                //if (minBrightness > 1) {
               //     minBrightness -= 1;
               //     updateDisplay();
               //     Util.setActivityBrightness(getWindow(), minBrightness);
               // }
                Toast.makeText(getApplicationContext(), "keycode_6=13", Toast.LENGTH_SHORT).show();
                return true;
            case KeyEvent.KEYCODE_VOLUME_UP:
                Toast.makeText(getApplicationContext(), "volume up", Toast.LENGTH_SHORT).show();
                //if (minBrightness < 255) {
                //    minBrightness += 1;
                //    updateDisplay();
                //    Util.setActivityBrightness(getWindow(), minBrightness);
                //}
                return true;
            default:
                Toast.makeText(getApplicationContext(), "default keycode_6=13", Toast.LENGTH_SHORT).show();
                return super.onKeyDown(keyCode, event);
        }
    }
*/





    public void CREATE(View view) {
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


    public void ADD_PEL(View v) {
        adding();
    }

    public void adding() {
        SQLiteDatabase mydatabase = null;
        mydatabase = openOrCreateDatabase("pelates", MODE_PRIVATE, null);
        EditText mKOD, mONO;
        TextView t;
        mKOD = (EditText) findViewById(R.id.KOD);
        mONO = (EditText) findViewById(R.id.ONO);
        t=(TextView)findViewById(R.id.textView);
        String cmKOD = mKOD.getText().toString();
        String cmONO = mONO.getText().toString();
        Cursor cursor = mydatabase.rawQuery("select KOD,ONO from pel where KOD=" + cmKOD + " ", null);
        int n = 0;
        String mName="";
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                n = cursor.getInt(0);
                // n = Integer.parseInt(cursor.getString(0));
                mName=cursor.getString(1);
            } while (cursor.moveToNext());
        }

        if (n == 0) {
            mydatabase.execSQL("INSERT INTO pel (KOD,ONO) VALUES(" + cmKOD + ",'" + cmONO + "');");
        } else {
            t.setText(mName);
        }


        mydatabase.close();

    }
}