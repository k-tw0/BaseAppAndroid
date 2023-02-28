package com.example.leanyrz_learningandcreatelanguaje;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

public class ListOne extends Fragment {

    RecyclerViewDragAndDropAdapter recyclerViewDragAndDropAdapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // De esta manera podremos llamar variables desde los fragmentos
        // Comentamos lo anterior y ponemos estas dos lineas de codigo con el return.
        //return inflater.inflate(R.layout.fragment_list_one, container, false);
        View v = inflater.inflate(R.layout.fragment_list_one,container,false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        recyclerViewDragAndDropAdapter = new RecyclerViewDragAndDropAdapter(getContext(), recyclerView);
        return v;

    }
}