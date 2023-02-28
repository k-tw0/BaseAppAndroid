package com.example.leanyrz_learningandcreatelanguaje;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.provider.OpenableColumns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPagerAdapterFragment ViewPagerAdapterFragment;
    TabLayout TabLayout;
    ViewPager2 ViewPager2;

    private String[] titles = new String[] {"Home", "Config", "List"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        ViewPager2 = findViewById(R.id.view_pager);
        TabLayout = findViewById(R.id.tab_layout);
        ViewPagerAdapterFragment = new ViewPagerAdapterFragment(this);

        ViewPager2.setAdapter(ViewPagerAdapterFragment);

        new TabLayoutMediator(TabLayout, ViewPager2,((tab, position) -> tab.setText(titles[position]))).attach();

        /*Peticion al inicio de la aplicacion
        Sus parametros son ejectura el permiso de escritura &
        PackageManager.PERMISSION_GRANTED es el permiso otorgado por el Administrador
        doc.
        https://developer.android.com/reference/androidx/core/app/ActivityCompat
        * */
        ActivityCompat.requestPermissions(this, new String[]
                {Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                PackageManager.PERMISSION_GRANTED);
    }

    //Obtener el resultado de una actividad
    ActivityResultLauncher<Intent> sActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Uri uri = data.getData();
                        copyFile(getFileName(uri,getApplicationContext()));
                    }
                }

            }

    );
    // Boton de abrir
    public void openFIleChooser(View view) {
        Intent data = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        data.addCategory(Intent.CATEGORY_OPENABLE);
        data.setType("*/*");
        String[] mimeTypes = {"image/*", "video/*", "audio/*"};
        data.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        data = Intent.createChooser(data, "Choose a file");
        sActivityResultLauncher.launch(data);
    }
    // Obtener nombre de un archivo
    String getFileName (Uri uri, Context context) {
        String res = null;
        if(uri.getScheme().equals("content")){
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            try{
                if (cursor != null && cursor.moveToFirst()){
                    res = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            }catch (Exception ex){
                cursor.close();
            }
            if(res == null){
                res = uri.getPath();
                int cutt = res.lastIndexOf('/');
                if (cutt != -1){
                    res = res.substring(cutt + 1);
                }
            }
        }

        return res;
    }
    //Copiar el archivo y heredando el nombre del archivo
    public void copyFile(String path){

        StorageManager storageManager = (StorageManager) getSystemService(STORAGE_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            List<StorageVolume> storageVolumeList = storageManager.getStorageVolumes();
            StorageVolume storageVolume = storageVolumeList.get(0);

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
                // Obtener direccion para copiar
                File fileSource = new File(storageVolume.getDirectory().getPath()+"/Download/"+path);
                // Dirigir una copia dentro de la carpeta de la aplicacion
                File fileDestination = new File(getExternalFilesDir(null), ""+path);

                try {
                    InputStream inputStream = new FileInputStream(fileSource);
                    OutputStream outputStream = new FileOutputStream(fileDestination);

                    byte[] byteArrayBuffer = new byte[1024];
                    int intLenght;
                    while ((intLenght = inputStream.read(byteArrayBuffer)) > 0) {
                        outputStream.write(byteArrayBuffer, 0, intLenght);
                    }

                    inputStream.close();
                    outputStream.close();

                    Toast.makeText(this, "Name: "+path, Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(this, "Error: "+ e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}