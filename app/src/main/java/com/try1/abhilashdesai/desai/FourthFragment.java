package com.try1.abhilashdesai.desai;

import android.app.AlertDialog;
import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.QuickContactBadge;
import android.widget.Toast;

/**
 * Created by MahanteshGowda on 19-02-2018.
 */

public class FourthFragment extends Fragment {

    View myview;
    Button b1,b2;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myview = inflater.inflate(R.layout.fourth_layout, container, false);
        b1=(Button)myview.findViewById(R.id.current);
        b2=(Button)myview.findViewById(R.id.change);
        try {
            final SQLiteDatabase db;
            final SQLiteDatabase db2;
//
            db = getActivity().openOrCreateDatabase("myDB1", android.content.Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS wages(id INTEGER, Male INTEGER, Female INTEGER);");
            db.execSQL("insert into wages values(1,400,200);");
            //

b1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Cursor c = db.rawQuery("SELECT * FROM wages", null);
        StringBuffer buffer = new StringBuffer();
        try {
            while (c.moveToNext()) {

                buffer.append("Male Wage: Rs." + c.getString(1));
                buffer.append("\nFemale Wage: Rs." + c.getString(2));
                break;
            }
            showMessage("Current Wages", buffer.toString());
        } catch (Exception xx) {
            Toast.makeText(getContext(), xx.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
});


////


            ////

        }
        catch (Exception e)
        {

        }


        return  myview;

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