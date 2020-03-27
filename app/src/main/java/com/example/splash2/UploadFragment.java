package com.example.splash2;

import android.Manifest;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import static android.app.Activity.RESULT_OK;


public class UploadFragment extends Fragment  {

    private static final int IMAGE_CAPTURE_CODE = 1001;
    private static final Object TAG = 78;
    ImageView mImageView;
    Button gal;
    Button cam;
    Button iden;
    Button disea;

    private static final int CAM_REQUEST = 1313;

    private static final int IMAG_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    Uri image_uri;
    int k = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upload,container,false) ;
        mImageView = view.findViewById(R.id.post_image);
        gal = view.findViewById(R.id.gallery);
        cam = view.findViewById(R.id.camera);
        iden = view.findViewById(R.id.leafClass);
        disea = view.findViewById(R.id.disidentify);

        gal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                k=2;
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){


                    if (ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){

                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE,};

                        requestPermissions(permissions,PERMISSION_CODE);

                    }
                    else{

                        pickImageFromGallery();
                    }
                }
                else{
                    pickImageFromGallery();

                }


            }
        });

        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                k=1;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


                    if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED || ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {

                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

                        requestPermissions(permissions, PERMISSION_CODE);

                    } else {

                        openCamera();
                    }
                } else {
                    openCamera();

                }
            }


        });

        iden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(k==2||k==1){
                Intent leafintent = new Intent(getActivity(),leafidentification.class);
                startActivity(leafintent);
                }
                else{
                    Toast.makeText(getActivity(),"Upload Picture First",Toast.LENGTH_SHORT).show();
                }
            }
        });

        disea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(k==2||k==1){
                Intent disintent = new Intent(getActivity(),disdetect.class);
                startActivity(disintent);
            }
            else{
                    Toast.makeText(getActivity(),"Upload Picture First",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    private void pickImageFromGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAG_PICK_CODE);

    }

    private void openCamera(){

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"New picture");
        values.put(MediaStore.Images.Media.DESCRIPTION,"From camera");
        image_uri = getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);

        Intent cameraIntent = new  Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,image_uri);
        startActivityForResult(cameraIntent,IMAGE_CAPTURE_CODE);

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(k==1){
            switch (requestCode){
                case PERMISSION_CODE:{
                    if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                        openCamera();
                    }
                    else{
                        Toast.makeText(getActivity(),"Permission Denied",Toast.LENGTH_SHORT).show();
                    }
                }
            }}
        else{
            switch (requestCode){
                case PERMISSION_CODE:{
                    if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                        pickImageFromGallery();
                    }
                    else{
                        Toast.makeText(getActivity(),"Permission Denied",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(k==1){
            if(resultCode == RESULT_OK){
                mImageView.setImageURI(image_uri);
            }
        }
        else{
            if(resultCode == RESULT_OK && requestCode == IMAG_PICK_CODE){
                mImageView.setImageURI(data.getData());
            }
        }
    }}
