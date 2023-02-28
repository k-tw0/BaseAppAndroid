package com.example.leanyrz_learningandcreatelanguaje;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RecyclerViewDragAndDropAdapter {
    Context context;
    RecyclerView recyclerView;
    ViewListAdapter viewListAdapter;

    //private List<String> valueList(){
    private List<Integer> valueList(){
/*      Metodo antiguo con add list.
        List<String> list = new ArrayList<>();
        list.add("Value 1");
        list.add("Value 10");
*/
        /*
        List<String> list = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9"
        ,"10", "11"));
        List<String> collection = Arrays.asList("12", "13", "14");
        list.addAll(collection);
*/

        List<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(2, 7, 10));

        return list;

    }

    public RecyclerViewDragAndDropAdapter(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;

        setRecyclerView();
    }

    public void setRecyclerView () {
        Log.i("TEAM:: HACKING", String.valueOf(valueList()));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        viewListAdapter = new ViewListAdapter(context, valueList());
        recyclerView.setAdapter(viewListAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallBack);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    ItemTouchHelper.SimpleCallback simpleCallBack = new ItemTouchHelper.
            SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END, 0) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {


            int fromPosition  = viewHolder.getAdapterPosition();
            int toPosition  = target.getAdapterPosition();
            Collections.swap(valueList(), fromPosition, toPosition);
            recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };
}
