package com.try1.abhilashdesai.desai;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

import java.util.Date;

public class onion_main extends Activity {
    ListView list;
    String gend;
Button chk;

//int av=0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onion_main);
        chk=(Button)findViewById(R.id.chk);
        Date d = new Date();
        CharSequence s  = DateFormat.format("MMMM d, yyyy ", d.getTime());
        final String da=s.toString();

        final SQLiteDatabase db2 = openOrCreateDatabase("myDB1", Context.MODE_PRIVATE, null);
        db2.execSQL("CREATE TABLE IF NOT EXISTS works(type VARCHAR, date VARCHAR,naam VARCHAR,FOREIGN KEY(naam) REFERENCES employee(name));");
        chk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c=db2.rawQuery("SELECT * FROM works where type like 'Onion'", null);
                StringBuffer buffer=new StringBuffer();
                while(c.moveToNext())
                {
                    buffer.append("Date: "+c.getString(1)+"\n");
                    buffer.append("Name: "+c.getString(2)+"\n");

                }
                showMessage("Onion Workers", buffer.toString());
            }
        });
        final String[] web = {
                "Google Plus",
                "Twitter",
                "Windows",
                "Bing",
                "Itunes",
                "Wordpress",
                "Drupal"
        };
        try {
            SQLiteDatabase db = openOrCreateDatabase("myDB1", Context.MODE_PRIVATE, null);


            final Cursor cu = db.rawQuery("SELECT * FROM employees where name not in (select naam from works where date like '"+da+"')", null);
            final String[] array = new String[cu.getCount()];
            final String[] imageId = new String[cu.getCount()];
            int i = 0;
            while (cu.moveToNext()) {
                String uname = cu.getString(cu.getColumnIndex("name"));
                String uname1 = cu.getString(cu.getColumnIndex("pic"));
                array[i] = uname;
                imageId[i] = uname1;

                i++;
            }


            custom_onion adapter = new
                    custom_onion(onion_main.this, array, imageId);
            list = (ListView)findViewById(R.id.list);
            list.setAdapter(adapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                  //  Toast.makeText(onion_main.this, "You Clicked at " + array[+position], Toast.LENGTH_SHORT).show();
////
                    try {
                        Cursor c=db2.rawQuery("SELECT gender FROM employees where name like '"+array[+position]+"' ", null);

                        while(c.moveToNext())
                        {
                            gend=c.getString(0);


                        }

                        db2.execSQL("INSERT INTO works VALUES('Onion','" + da + "','" + array[+position] + "' );");
                        if(gend.equals("Male"))
                        { db2.execSQL("UPDATE employees SET bal=bal+400 WHERE name like '"+array[+position]+"';");}
                        else
                        {
                            db2.execSQL("UPDATE employees SET bal=bal+200 WHERE name like '"+array[+position]+"';");
                        }
                        Toast.makeText(getApplicationContext(), array[+position] + " Assigned to onion", Toast.LENGTH_SHORT).show();
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

                    }


                    ////
                }
            });

        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"No employees added !",Toast.LENGTH_LONG).show();
        }
    }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK",null);
        builder.show();
    }
}