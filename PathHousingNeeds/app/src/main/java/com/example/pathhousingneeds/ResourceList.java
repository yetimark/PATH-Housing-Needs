package com.example.pathhousingneeds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONObject;

public class ResourceList extends AppCompatActivity {
    //This should be accessed with a category, and thus show the content blocks

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_list);
        Intent i = getIntent();
        String myKey = i.getStringExtra("Category");
        try{
            JSONObject jObj = new JSONObject(DataString.JString);
            JSONArray jArray = jObj.getJSONArray(myKey);
            System.out.println("*****************Got some JSON");
            for(int j = 0; j < jArray.length(); j++){
                System.out.println("*****************Oh Hi There");
                JSONObject tempObj = jArray.getJSONObject(j);
                System.out.println("*****************Oh Hi There 2");
                ResourceBlock tempBlock = new ResourceBlock(tempObj, true, this);
                System.out.println("*****************Oh Hi There 3");
                ((LinearLayout)findViewById(R.id.MainLayout)).addView(tempBlock.vertLayout);
                tempBlock.ShowStuff();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
