package com.example.splash2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.format.TextStyle;

public class rel2 extends AppCompatActivity {

    ImageView mainimageView;
    TextView dis,des;

    String data1,data2;
    int myImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rel2);

        mainimageView = findViewById(R.id.mainimageView);
        dis = findViewById(R.id.dis);
        des = findViewById(R.id.des);

        getData();
        setData();
    }

    private void getData(){
        if (getIntent().hasExtra("myImage")&& getIntent().hasExtra("data1")&&getIntent().hasExtra("data2")){

            data1=getIntent().getStringExtra("data1");
            data2=getIntent().getStringExtra("data2");
            myImage = getIntent().getIntExtra("myImage",1);
        }
        else{
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }

    }
    private void setData(){
        dis.setText(data1);
        des.setText(data2);
        mainimageView.setImageResource(myImage);

    }
}
