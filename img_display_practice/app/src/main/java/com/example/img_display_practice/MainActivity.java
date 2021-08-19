package com.example.img_display_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    Button button;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnButton();
    }

    public void addListenerOnButton(){

        image = (ImageView) findViewById(R.id.imageView);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View arg0){
                image.setImageResource(R.drawable.obama_sad);
            }
        });
    }
}