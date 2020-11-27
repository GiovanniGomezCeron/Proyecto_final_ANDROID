package com.example.crud_mysql;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Home extends Fragment {
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MainActivity.json.RecibirCategoria();
        return view=inflater.inflate(R.layout.fragment_home2, container, false);
    }
    @Override
    public void onStart(){
        super.onStart();


    }

}
