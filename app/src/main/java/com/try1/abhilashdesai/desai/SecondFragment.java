package com.try1.abhilashdesai.desai;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import static android.content.ContentValues.TAG;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/**
 * Created by Abhilash Desai on 30-06-2017.
 */
public class SecondFragment extends Fragment {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1888;
    Button button,add,del;
    EditText ed;
    int falgf = 0;
    SQLiteDatabase db;
    ContentValues values;
    String fname,root;
    ImageView imageView;
    RadioGroup radioSexGroup;
     RadioButton radiosex;
     String name,gender;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.second_layout,
                container, false);


        del=(Button)rootView.findViewById(R.id.btndel) ;
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iii = new Intent(getActivity().getApplication(),delmain.class);
                startActivity(iii);


            }
        });
        db=getActivity().openOrCreateDatabase("myDB1",android.content.Context.MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS employees(name VARCHAR PRIMARY KEY, gender VARCHAR,bal INTEGER DEFAULT 0, pic VARCHAR);");


        ///

        radioSexGroup = (RadioGroup) rootView.findViewById(R.id.radioSex);
        ed = (EditText)rootView.findViewById(R.id.ename);
        add = (Button)rootView.findViewById(R.id.badd);
        button = (Button) rootView.findViewById(R.id.btnpick);
        imageView = (ImageView) rootView.findViewById(R.id.imgshow);

        //
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
                 name = ed.getText().toString();
                int selectedId = radioSexGroup.getCheckedRadioButtonId();
// find the radiobutton by returned id
                radiosex = (RadioButton) rootView.findViewById(selectedId);
                gender=radiosex.getText().toString();

                if(name.equals(""))
                {

                    Toast.makeText(getContext(), "Error: Please enter Name", Toast.LENGTH_SHORT).show();
                }
else{

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,
                        CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);}



            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(falgf!=1)
                {

                    Toast.makeText(getContext(), "Error: Please take Picture", Toast.LENGTH_SHORT).show();
                }
                else{
                    final String fpath= root+"/myappimages/"+fname;
                    try {
                        int zero=0;
                        db.execSQL("INSERT INTO employees (name, gender, pic) VALUES('" + name + "','" + gender + "','" + fpath +
                                "');");
                        Toast.makeText(getContext(), "Employee added successfully !", Toast.LENGTH_SHORT).show();
                        ed.setText("");
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.detach(SecondFragment.this).attach(SecondFragment.this).commit();




                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getContext(),"Failed",Toast.LENGTH_LONG).show();
                    }


                }



            }
        });


        return rootView;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {

                if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
                    if (resultCode == Activity.RESULT_OK) {

                        Bitmap bmp = (Bitmap) data.getExtras().get("data");
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();

                        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        byte[] byteArray = stream.toByteArray();
                         values = new ContentValues();
                        values.put("image", byteArray);

                        // convert byte array to Bitmap
///
                        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0,
                                byteArray.length);
                         root = Environment.getExternalStorageDirectory().toString();
                        File myDir = new File(root + "/myappimages");
                        myDir.mkdirs();
                        Random generator = new Random();
                        int n = 10000;
                        n = generator.nextInt(n);
                         fname = "Image-" + n + ".jpg";
                        File file = new File(myDir, fname);
                        Log.i(TAG, "" + file);
                        if (file.exists())
                            file.delete();
                        try {
                            FileOutputStream out = new FileOutputStream(file);
                            bmp.compress(Bitmap.CompressFormat.JPEG, 90, out);
                            out.flush();
                            out.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
///
                        imageView.setImageBitmap(bitmap);
                        falgf++;
                       // Toast.makeText(getContext(), " Picture", Toast.LENGTH_SHORT).show();

                        final Cursor cu = db.rawQuery("SELECT * FROM employee", null);

                        while (cu.moveToNext()) {
                           // Toast.makeText(getContext(),cu.getString(1),Toast.LENGTH_LONG).show();

                        }



                }//
            }
        }
        catch (Exception e)
        {
           // Toast.makeText(getContext(),"hi",Toast.LENGTH_LONG).show();
        }
    }

}