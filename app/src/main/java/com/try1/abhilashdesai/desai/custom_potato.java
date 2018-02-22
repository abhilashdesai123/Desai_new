package com.try1.abhilashdesai.desai;

import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by MahanteshGowda on 19-02-2018.
 */

public class custom_potato extends ArrayAdapter<String> {


    private final Activity context;
    private final String[] web;
    private final String[] imageId;
    public custom_potato(Activity context,
                        String[] web, String[] imageId) {
        super(context, R.layout.potato_single, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.potato_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(web[position]);

        imageView.setImageURI(Uri.parse(imageId[position]));
        return rowView;
    }
}