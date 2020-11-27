package com.example.crud_mysql;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class User extends Fragment {
    private View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return v = inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        ((TextView)v.findViewById(R.id.nombre_u)).setText(MainActivity.user.get(0));
        ((TextView)v.findViewById(R.id.apellido_u)).setText(MainActivity.user.get(1));
        ((TextView)v.findViewById(R.id.user_u)).setText(MainActivity.user.get(2));
        ((TextView)v.findViewById(R.id.con_u)).setText(MainActivity.user.get(3));
        ((TextView)v.findViewById(R.id.correo_u)).setText(MainActivity.user.get(4));
    }
}
