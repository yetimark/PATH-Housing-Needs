package com.example.pathhousingneeds;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;


public class MainActivity extends AppCompatActivity {
    //In this one, we list the categories
    LinearLayout mainLay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context myCon = this.getBaseContext();
        mainLay = (LinearLayout)findViewById(R.id.MainLayout);

        try {
            JSONObject jObj = new JSONObject(DataString.JString);
            Iterator<String> keys = jObj.keys();
            while(keys.hasNext()){
                Button temp = new Button(this);
                final String tempStr = keys.next();
                temp.setText(tempStr);
                temp.setBackgroundColor(Color.parseColor("#f0b06d"));
                temp.setOnClickListener(new View.OnClickListener(){public void onClick(View v){
                    Intent i = new Intent(myCon, ResourceList.class);
                    i.putExtra("Category", tempStr);
                    startActivity(i);
                }});
                mainLay.addView(temp);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
