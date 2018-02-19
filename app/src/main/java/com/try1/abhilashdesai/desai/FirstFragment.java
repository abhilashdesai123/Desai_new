package com.try1.abhilashdesai.desai;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Abhilash Desai on 30-06-2017.
 */
public class FirstFragment extends Fragment{
    ImageView onion,potato,maize;
    View myview;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myview=inflater.inflate(R.layout.first_layout,container,false);
       // return super.onCreateView(inflater, container, savedInstanceState);
        onion = (ImageView)myview.findViewById(R.id.on);
        potato = (ImageView)myview.findViewById(R.id.po);
        maize = (ImageView)myview.findViewById(R.id.mz);
        onion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplication(),onion_main.class);
                startActivity(i);
            }
        });
        return myview;
    }
}
