package com.example.leanyrz_learningandcreatelanguaje;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewListAdapter extends RecyclerView.Adapter<ViewListAdapter.ViewHolder> {

    Context context;
    List<Integer> mList;

    public ViewListAdapter(Context context, List<Integer> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.iv_item_layout,parent,false);

        return new ViewHolder(view);
    }
    //      Metodo responsable de setear los valores de la lista.
    @Override
    public void onBindViewHolder(@NonNull ViewListAdapter.ViewHolder holder, int position) {
        if(mList.size() > 0 && mList != null){
            holder.textViewList.setText("Value " + (position + 1));
            //holder.textViewList.setText(title);
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewList;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewList = itemView.findViewById(R.id.textViewList);
        }
    }
}
