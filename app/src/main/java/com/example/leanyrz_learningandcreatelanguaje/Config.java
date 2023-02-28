package com.example.leanyrz_learningandcreatelanguaje;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Config extends Fragment {

    Button btnInsert, btnUpdate, btnSearch, btnDelete;
    EditText txtIdMenu, txtNameMenu;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_config, container, false);
        View v = inflater.inflate(R.layout.fragment_config,container,false);

        SQLiteCfg sqLiteCfg = new SQLiteCfg(getContext());

        btnInsert = v.findViewById(R.id.btnInsert);
        btnUpdate = v.findViewById(R.id.btnUpdate);
        btnSearch = v.findViewById(R.id.btnSearch);
        btnDelete = v.findViewById(R.id.btnDelete);

        txtIdMenu = v.findViewById(R.id.editTxtIdMenu);
        txtNameMenu = v.findViewById(R.id.editTextNameMenu);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteCfg.insert(txtIdMenu.getText().toString(), txtNameMenu.getText().toString());
                clear();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteCfg.delete(txtIdMenu.getText().toString());
                clear();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteCfg.update(txtIdMenu.getText().toString(), txtNameMenu.getText().toString());
                clear();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtNameMenu.setText(sqLiteCfg.search(txtIdMenu.getText().toString()));
            }
        });

        return v;

    }

    void clear (){

        txtIdMenu.setText("");
        txtNameMenu.setText("");

    }



}