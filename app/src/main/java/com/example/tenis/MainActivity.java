package com.example.tenis;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static   String DBname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), addPel.class);
                // intent.putExtra(EXTRA_MESSAGE, o.toString());
                // String ctrapezi;
                // trapezi = (TextView)findViewById(R.id.textView);
                // ctrapezi=trapezi.getText().toString();

               // intent.putExtra("mpel2", TrapeziFull);  // αριθμος τραπεζιου
               // intent.putExtra("WhoCall", "trapezia");  // ποια φορμα καλει
               // intent.putExtra("mpel", pel); // ΣΤΕΛΝΩ ΤΟΝ ΠΙΝΑΚΑ ΜΕ ΤΑ ΤΡΑΠΕΖΙΑ
               // intent.putExtra("mEIDH", EIDH); // ΣΤΕΛΝΩ ΤΟΝ ΠΙΝΑΚΑ ΜΕ ΤΑ EIDH
               // intent.putExtra("mKATHG", KATHG); // ΣΤΕΛΝΩ ΤΟΝ ΠΙΝΑΚΑ ΜΕ ΤΑ EIDH
                // intent.putExtra("mpel", pel); // ΣΤΕΛΝΩ ΤΟΝ ΠΙΝΑΚΑ ΜΕ ΤΑ ΤΡΑΠΕΖΙΑ
                MainActivity.this.startActivity(intent);






             //   Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                     //   .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void getpel (View view) {
    SQLiteDatabase mydatabase = null;
    mydatabase =

    openOrCreateDatabase("pelates",MODE_PRIVATE,null);

  /*  trapezi =(TextView)

    findViewById(R.id.textView);

    String skTrapezi = trapezi.getText().toString();

        if(skTrapezi.substring(0,1).

    equals("#"))

    {
        String[] separated3 = skTrapezi.split("#");
        skTrapezi = separated3[1];
    }

    String[] cTrapeziFull = TrapeziFull.split(";");
    String idpar = cTrapeziFull[1];



        mydatabase.execSQL("UPDATE TABLES SET KATEILHMENO=0,IDPARAGG=0 WHERE ONO='"+skTrapezi +"'");
        mydatabase.execSQL("UPDATE PARAGGMASTER SET CH2= datetime('now','localtime'),AJIA=0 ,TROPOS=2  WHERE ID="+idpar);
  mydatabase.close();

   */

}













    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
