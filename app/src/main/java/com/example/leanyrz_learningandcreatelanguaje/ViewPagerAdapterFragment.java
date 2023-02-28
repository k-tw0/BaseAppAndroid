package com.example.leanyrz_learningandcreatelanguaje;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapterFragment extends FragmentStateAdapter {

    //TITULO CONTENIDO DINAMICO DE LOS FRAGMENTS
    private String[] titles = new String[] {"Home", "Config", "Config"};

    public ViewPagerAdapterFragment(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    //retornar Los Menus
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new Home();
            case 1:
                return new Config();
            case 2:
                return new ListOne();
        }
        return new Home();
    }

    @Override
    public int getItemCount() {
        //Obtener numero de string del objeto titles
        return titles.length;
    }
}
