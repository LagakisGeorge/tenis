package com.example.tenis;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class kiniseis extends AppCompatActivity {

    public String message="";
    public CalendarView calendarView;
    public TextView t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiniseis);

       final CalendarView view = new CalendarView(this);
        setContentView(view);

        view.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView arg0, int year, int month,
                                            int date) {
               Toast.makeText(getApplicationContext(),date+ "/"+month+"/"+year,Toast.LENGTH_SHORT ).show();

                 t3=(TextView)findViewById(R.id.apoHmer);
                // TextView t5=(TextView)findViewById(R.id.textView5);
               String apo =date+ "/"+month+"/"+year;
              // t3.setText(apo);


/*
                if (t3.length()==0) {
                         t3.setText(date + "/" + month + "/" + year);
                } else{
                    t5.setText(date + "/" + month + "/" + year);
                   // view.setVisibility(View.INVISIBLE  );

                };
             */







            }
        });





    }


    public void hmeromhnia(View view){
        calendarView=(CalendarView) findViewById(R.id.calendarView);

                TextView t3=(TextView)findViewById(R.id.apoHmer);
                TextView t5=(TextView)findViewById(R.id.textView5);

                if (t3.length()==0) {
               //     t3.setText(dayOfMonth + "/" + month + "/" + year);
                } else{
                 //   t5.setText(dayOfMonth + "/" + month + "/" + year);
                    calendarView.setVisibility(View.INVISIBLE  );

                };





    }


    public void send_email(View view){


       // Intent intent = new Intent(view.getContext(), kiniseis.class);
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
      //  kiniseis.this.startActivity(intent);






        File folder = new File(Environment.getExternalStorageDirectory()
                + "/lagakis2");

        boolean var = false;
        if (!folder.exists()) {
            var = folder.mkdir();
        }
        else{
            var=true;
        }
        final String filename = folder.toString() + "/" + "Test.csv";

        // show waiting screen
        CharSequence contentTitle = getString(R.string.app_name);
        final ProgressDialog progDailog = ProgressDialog.show(
                kiniseis.this, contentTitle, "even geduld aub...",
                true);//please wait
        @SuppressLint("HandlerLeak") final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
            }
        };

        new Thread() {
            public void run() {
                try {

                    FileWriter fw = new FileWriter(filename);
                    SQLiteDatabase db = null;
                    db = openOrCreateDatabase("pelates", MODE_PRIVATE, null);

                    Cursor cursor = db.rawQuery("select IDBARDIA,CH1,CH2  from  parousies  order by CH1 desc", null);  //+ " order by CH1 desc"

                    // Cursor cursor = db.selectAll();
                    fw.append("ONOMA");
                    fw.append(',');
                    fw.append("CODE");
                    fw.append(',');
                    fw.append("TIME/DATE");
                    fw.append(',');
                    fw.append('\n');

                    if (cursor.moveToFirst()) {
                        do {
                            //NAME
                            fw.append(cursor.getString(2));
                            fw.append(',');
                            //ID
                            // fw.append(Float.toString(cursor.getFloat(2)));
                            fw.append(Float.toString(cursor.getInt(0)));
                            fw.append(',');
                            // DATE
                            fw.append(cursor.getString(1));
                            fw.append(',');
                             /*  fw.append(cursor.getString(10));
                                  fw.append(',');
                             */
                            fw.append('\n');

                            message=message+cursor.getString(2)+","+cursor.getString(0)+","+cursor.getString(1)+"\n";

                        } while (cursor.moveToNext());
                    }
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }

                    // fw.flush();
                    fw.close();
                    db.close();
                } catch (Exception e) {
                }
                handler.sendEmptyMessage(0);
                progDailog.dismiss();
            }
        }.start();









        message="";
        try {


            SQLiteDatabase db = null;
            db = openOrCreateDatabase("pelates", MODE_PRIVATE, null);

            Cursor cursor = db.rawQuery("select IDBARDIA,CH1,CH2  from  parousies  order by CH1 desc", null);  //+ " order by CH1 desc"

            if (cursor.moveToFirst()) {
                do {
                    message=message+cursor.getString(2)+","+cursor.getString(0)+","+cursor.getString(1)+"\n";

                } while (cursor.moveToNext());
            }
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            db.close();

        } catch (Exception e) {
        }









        //  email----------------------------

        Intent i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "glagakis@gmail.com", null));
        //  intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Test Subject");

        //  context.startActivity(Intent.createChooser(intent, "Send mail..."));




        String to = "glagakis@gmail.com";  // textTo.getText().toString();
        String subject = "κινησεις" ; //textSubject.getText().toString();
        //String message = "zzzzvvvvvvv"; // textMessage.getText().toString();

        //   Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("plain/text");
        File data = null;
        try {
            Date dateVal = new Date();
            //  String filename2 = dateVal.toString();
            data =File.createTempFile("Report", ".csv");
            FileWriter out = (FileWriter) GenerateCsv.generateCsvFile(
                    data, "Name,Data1");



            File file = new File("/lagakis2/Test.csv");
            i.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file)); // data));
            i.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
            i.putExtra(Intent.EXTRA_SUBJECT, message);
            i.putExtra(Intent.EXTRA_TEXT, message);
            i.setData(Uri.parse("mailto:"));

            // i.setType( "message/rfc822");
            startActivity(Intent.createChooser(i, "e-mail"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}