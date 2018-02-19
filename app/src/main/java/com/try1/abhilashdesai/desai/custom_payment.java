package com.try1.abhilashdesai.desai;
import android.app.Activity;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class custom_payment extends ArrayAdapter<String>{

    private final Activity context;
    private final String[] web;
    private final String[] imageId;
    public custom_payment(Activity context,
                        String[] web, String[] imageId) {
        super(context, R.layout.payment_single, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.payment_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txtpay);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.imgpay);
        txtTitle.setText(web[position]);

        imageView.setImageURI(Uri.parse(imageId[position]));
        return rowView;
    }
}