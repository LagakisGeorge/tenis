package com.example.tenis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteAccessPermException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class addPel extends AppCompatActivity {

public  String message="";

    public   List<String> values=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pel);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        final   EditText mKOD;

        mKOD = (EditText) findViewById(R.id.KOD);

        // patav ton kvdiko kai enter
        mKOD.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // adding();
                    EditText mKOD, mONO;
                    TextView t;
                    mKOD = (EditText) findViewById(R.id.KOD);
                    mONO = (EditText) findViewById(R.id.ONO);

                    t = (TextView) findViewById(R.id.textView);
                    t.setText(mONO.getText());
                    // Perform action on key press
                    Toast.makeText(addPel.this, mKOD.getText().toString(), Toast.LENGTH_SHORT).show();

                    Button update=(Button)findViewById(R.id.update);
                    //when play is clicked show stop button and hide play button
                    update.setVisibility(View.GONE);
                    // stopButton.setVisibility(View.VISIBLE);

                    //==========================================
                    SQLiteDatabase mydatabase = null;
                    mydatabase = openOrCreateDatabase("pelates", MODE_PRIVATE, null);
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
                        mONO.setText("");
                        t.setText("δεν υπαρχει");
                        mydatabase.close();
                    } else {
                        // Button update=(Button)findViewById(R.id.update);
                        //when play is clicked show stop button and hide play button
                        update.setVisibility(View.VISIBLE);

                        mydatabase.close();
                        t.setText(mName);
                        mONO.setText(mName);
                        SHOW_KINISI(n);
                    }

                    return true;
                }
                return false;
            }
        });







       /* mKOD.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

              if (s.toString().length()==4) {
                  // adding();
              }
              //  Toast.makeText(getApplicationContext(), "editable "+s.toString(), Toast.LENGTH_SHORT).show();

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                This method is called to notify you that, within s, the count characters beginning at start are about to be replaced by new text with length after. It is an error to attempt to make changes to s from this callback.
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) { }});
        */
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


/*
    private void exportDB(View view) {

        File dbFile=getDatabasePath("MyDBName.db");
        DBHelper dbhelper = new DBHelper(getApplicationContext());
        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists())
        {
            exportDir.mkdirs();
        }

        File file = new File(exportDir, "csvname.csv");
        try
        {
            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            Cursor curCSV = db.rawQuery("SELECT * FROM contacts",null);
            csvWrite.writeNext(curCSV.getColumnNames());
            while(curCSV.moveToNext())
            {
                //Which column you want to exprort
                String arrStr[] ={curCSV.getString(0),curCSV.getString(1), curCSV.getString(2)};
                csvWrite.writeNext(arrStr);
            }
            csvWrite.close();
            curCSV.close();
        }
        catch(Exception sqlEx)
        {
            Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
        }
    }
*/


    public void update_pel(View view) {


        SQLiteDatabase mydatabase = null;
        mydatabase = openOrCreateDatabase("pelates", MODE_PRIVATE, null);
        EditText mKOD, mONO;
        TextView t;
        mKOD = (EditText) findViewById(R.id.KOD);
        mONO = (EditText) findViewById(R.id.ONO);

        t = (TextView) findViewById(R.id.textView);
        t.setText(mONO.getText());

        mydatabase.execSQL("update pel set ONO='" + mONO.getText() + "' where KOD="+mKOD.getText()+";");
        mydatabase.close();





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
        t.setText("");
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
            mydatabase.close();
        } else {


            mydatabase.close();
            t.setText(mName);
            SHOW_KINISI(n);
        }




    }


    public void SHOW_KINISI(int kod ){
         int n=0;

        values=new ArrayList<>();  // μηδενιζω την λιστα
        try{
            SQLiteDatabase mydatabase = null;
            mydatabase = openOrCreateDatabase("pelates", MODE_PRIVATE, null);

            Cursor cursor2 = mydatabase.rawQuery("select IDBARDIA,CH1  from  parousies where IDBARDIA="+kod+" order by CH1 desc", null);  //+ " order by CH1 desc"
            String kat="";
            String syn="";
            if (cursor2.moveToFirst()) {
                do {
                    n++;
                    kat="";
                    syn="";
                    if (cursor2.getShort(0)>0){
                       // values.add( cursor2.getShort(0));
                        values.add( cursor2.getString(1));

                    }

                 //   arrIdParagg[n-1]=Long.toString(cursor2.getLong(2));

                } while (cursor2.moveToNext());
            }
            mydatabase.close();
            ArrayAdapter<String> arrayAdapter =
                    new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, values);

            GridView moviesList;
            moviesList=(GridView)findViewById(R.id.grid);
            moviesList.setAdapter(arrayAdapter);

            // TrapeziaList.setAdapter(arrayAdapter);

            //final MyAdapter adapter = new MyAdapter();
            //  rv.setAdapter(adapter);
            //   GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);
            //  rv.setLayoutManager(mLayoutManager);

// set up the RecyclerView
            //   RecyclerView recyclerView = findViewById(R.id.rvAnimals);
            //    TrapeziaList.setLayoutManager(new LinearLayoutManager(this));
            //   adapter = new MyRecyclerViewAdapter(this, animalNames);
            //   adapter.setClickListener(this);
            //   recyclerView.setAdapter(adapter);

        } catch (SQLiteAccessPermException e) {
            e.printStackTrace();
        }






    }



    public void show_Paragg(View view){
        SQLiteDatabase mydatabase=null;
        Integer n=0;
        GridView moviesList;
        moviesList=(GridView)findViewById(R.id.grid);
        //recyclerView=(RecyclerView) findViewById(R.id.grid2);
        final List<String> values=new ArrayList<>();

     /*   "[IDERGAZ] [int] ,"+
                "[HME] [datetime] ,"+
                "[IDBARDIA] [int] ,"+
                "[AJIA] [real] ,"+
                "[TROPOS] [int] ,"+
                "[NUM1] [int] ,"+
                "[NUM2] [int] ,"+
                "[CH1] [nvarchar](255) ,"+
                "[CH2] [nvarchar](255) )");

                System.out.print("ID: "+rs.getInt("ID")+", ");
      System.out.print("Name: "+rs.getString("First_Name")+", ");
      System.out.print("Age: "+rs.getString("Last_Name")+", ");
      System.out.print("Salary: "+rs.getDate("Date_Of_Birth")+", ");



     */
        try{
            values.add("ΕΙΔΟΣ");
            values.add("ΠΡΟΣΘ");
            values.add("ΠΟΣΟΤΗΤΑ");
            values.add("ΤΙΜΗ");
            values.add("ID ΠΑΡΑΓΓ");
            mydatabase = openOrCreateDatabase("pelates",MODE_PRIVATE,null);
            Cursor cursor2 = mydatabase.rawQuery("select kod,ono from  pel order by idparagg desc", null);

            if (cursor2.moveToFirst()) {
                do {

                    for(int i = 0; i<5;i++)  {

                        //    int index = c.getColumnIndex("description");
                        String str = cursor2.getString(i);
                        if (str == null || str.isEmpty() || str.equalsIgnoreCase("null")) {
                            values.add("");
                        } else {
                            values.add(str);
                        }




                        // values.add(cursor2.getString(i));
                        // values.add(cursor2.getString(1));
                        // values.add(cursor2.getString(2));
                        // values.add(cursor2.getString(3));
                        // values.add(cursor2.getString(4));



                    }

                } while (cursor2.moveToNext());
            }
            mydatabase.close();
            ArrayAdapter<String> arrayAdapter =
                    new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, values)
                            //-------------------- arxh  αυτο το κομματι βαζει πλαισια στο gridview
                    {
                        public View getView(int position, View convertView, ViewGroup parent) {

                            // Return the GridView current item as a View
                            View view = super.getView(position,convertView,parent);

                            // Convert the view as a TextView widget
                            TextView tv = (TextView) view;

                            //tv.setTextColor(Color.DKGRAY);

                            // Set the layout parameters for TextView widget
                            RelativeLayout.LayoutParams lp =  new RelativeLayout.LayoutParams(
                                    RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT
                            );
                            tv.setLayoutParams(lp);

                            // Get the TextView LayoutParams
                            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)tv.getLayoutParams();

                            // Set the width of TextView widget (item of GridView)
                /*
                    IMPORTANT
                        Adjust the TextView widget width depending
                        on GridView width and number of columns.

                        GridView width / Number of columns = TextView width.

                        Also calculate the GridView padding, margins, vertical spacing
                        and horizontal spacing.
                 */


                            Resources r = addPel.this.getResources();
                            int  px = (int) (TypedValue.applyDimension(
                                    TypedValue.COMPLEX_UNIT_DIP, 100, r.getDisplayMetrics()));
                            // tv.setLayoutParams(new GridView.LayoutParams((width/10)*6, 50));

                            // if (position==0 || position==5) {
                            // params.width = px/2;  // getPixelsFromDPs(EpiloghEid.this,168);
                            //   tv.setLayoutParams(new GridView.LayoutParams((px*6), 100));
                            // }else{
                            params.width = px;  // getPixelsFromDPs(EpiloghEid.this,168);
                            // }


                            // Set the TextView layout parameters
                            tv.setLayoutParams(params);

                            // Display TextView text in center position
                            tv.setGravity(Gravity.CENTER);

                            // Set the TextView text font family and text size
                            tv.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);

                            // Set the TextView text (GridView item text)
                            tv.setText(values.get(position));

                            // Set the TextView background color
                            tv.setBackgroundColor(Color.parseColor("#d9d5dc"));

                            // Return the TextView widget as GridView item
                            return tv;
                        }
                    };
            ;
            moviesList.setAdapter(arrayAdapter);

        } catch (SQLiteAccessPermException e) {
            e.printStackTrace();
        }
    }

    // το κομματι αυτο χρησιμοποιείται στο test
    public void toemail(View v) {


        Intent i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "glagakis@gmail.com", null));
      //  intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Test Subject");

      //  context.startActivity(Intent.createChooser(intent, "Send mail..."));




        String to = "glagakis@gmail.com";  // textTo.getText().toString();
        String subject = "asdfghjklqwertyuio" ; //textSubject.getText().toString();
        String message = "zzzzvvvvvvv"; // textMessage.getText().toString();

     //   Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("plain/text");
        File data = null;
        try {
            Date dateVal = new Date();
            String filename = dateVal.toString();
            data =File.createTempFile("Report", ".csv");
            FileWriter out = (FileWriter) GenerateCsv.generateCsvFile(
                    data, "Name,Data1");



            File file = new File("/lagakis2/Test.csv");
            i.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file)); // data));
            i.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
            i.putExtra(Intent.EXTRA_SUBJECT, subject);
            i.putExtra(Intent.EXTRA_TEXT, message);
            i.setData(Uri.parse("mailto:"));

           // i.setType( "message/rfc822");
            startActivity(Intent.createChooser(i, "e-mail"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void test(View view){
        Intent intent = new Intent(view.getContext(), kiniseis.class);
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
        addPel.this.startActivity(intent);
    }











}