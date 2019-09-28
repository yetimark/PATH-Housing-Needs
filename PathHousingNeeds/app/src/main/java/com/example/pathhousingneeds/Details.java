package com.example.pathhousingneeds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Details extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent i = getIntent();
        System.out.println("I'm getting details");
        ResourceBlock myBlock = new ResourceBlock(i.getStringExtra("Title"), i.getStringExtra("Address"),
                i.getStringExtra("County"), i.getStringExtra("Phone"), i.getStringExtra("Website"),
                i.getStringExtra("Email"), this, i.getStringExtra("Details"), false);
        ((LinearLayout)findViewById(R.id.MainLayout)).addView(myBlock.vertLayout);
        myBlock.ShowStuff();
        TextView tv = new TextView(this);
        tv.setText(myBlock.details);
        ((LinearLayout)findViewById(R.id.MainLayout)).addView(tv);
    }
}
