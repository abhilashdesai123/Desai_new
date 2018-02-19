package com.try1.abhilashdesai.desai;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class ThirdFragment extends Fragment {
    ImageView onion, potato, maize;
    ListView list;
    View myview;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myview = inflater.inflate(R.layout.third_layout, container, false);
        final String[] web = {
                "Google Plus",
                "Twitter",
                "Windows",
                "Bing",
                "Itunes",
                "Wordpress",
                "Drupal"
        };

        ///

        try {
           final SQLiteDatabase db;
            final SQLiteDatabase db2 ;
//
            db=getActivity().openOrCreateDatabase("myDB1",android.content.Context.MODE_PRIVATE,null);
           // db.execSQL("CREATE TABLE IF NOT EXISTS employee(name VARCHAR PRIMARY KEY, gender VARCHAR, pic VARCHAR,bal integer);");
            //

            final Cursor cu = db.rawQuery("SELECT * FROM employees;", null);
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


            custom_payment adapter = new
                    custom_payment(getActivity(), array, imageId);
            list = (ListView)myview.findViewById(R.id.paylist);
            list.setAdapter(adapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                      Toast.makeText(getContext(), "You Clicked at " + array[+position], Toast.LENGTH_SHORT).show();
                   // db2=getActivity().openOrCreateDatabase("myDB1",android.content.Context.MODE_PRIVATE,null);
                    Cursor c=db.rawQuery("SELECT bal FROM employees where name like '"+array[+position]+"' ", null);
                    StringBuffer buffer=new StringBuffer();
                    try {
                        while (c.moveToNext()) {

                            buffer.append("Rs." + c.getString(0));
                        }
                        showMessage(" Balance", buffer.toString());
                    }
                    catch (Exception xx)
                    {
                        Toast.makeText(getContext(),xx.getMessage(),Toast.LENGTH_LONG).show();
                    }
////



                    ////
                }
            });

        }
        catch (Exception e)
        {
            Toast.makeText(getContext(),"No employees added !",Toast.LENGTH_LONG).show();
        }


        ///
        return myview;
    }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("OK",null);
        builder.show();
    }
}