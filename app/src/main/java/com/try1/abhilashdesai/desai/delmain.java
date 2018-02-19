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

public class delmain extends Activity {
    ListView list;


//int av=0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delemp);





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


            final Cursor cu = db.rawQuery("SELECT * FROM employee;", null);
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


            customdel adapter = new
                    customdel(delmain.this, array, imageId);
            list = (ListView)findViewById(R.id.listdel);
            list.setAdapter(adapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    //  Toast.makeText(onion_main.this, "You Clicked at " + array[+position], Toast.LENGTH_SHORT).show();
////
                    try {
                        SQLiteDatabase dbx = openOrCreateDatabase("myDB1", Context.MODE_PRIVATE, null);
                        dbx.execSQL("DELETE from employees where name like '" + array[+position] + "';");

                        Toast.makeText(getApplicationContext(), array[+position] + " Deleted", Toast.LENGTH_SHORT).show();
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