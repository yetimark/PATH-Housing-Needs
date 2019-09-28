package com.example.pathhousingneeds;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class ResourceBlock {
    public TextView Title, Address, County, PhoneContact, Website, EmailContact;
    public Button DetailsBtn;
    public LinearLayout vertLayout;
    public String details;
    private Context myContext;

    public ResourceBlock(String title, String address, String county, String phone, String web, String email, Context context, String Details, Boolean MakeBtn){
        System.out.println("Let's make views");
        this.Title = new TextView(context); this.Title.setText(title);
        this.Address = new TextView(context); this.Address.setText(address);
        this.County = new TextView(context); this.County.setText(county);
        this.PhoneContact = new TextView(context); this.PhoneContact.setText(phone);
        this.Website = new TextView(context); this.Website.setText(web);
        if(MakeBtn) {
            this.DetailsBtn = new Button(context);
            this.DetailsBtn.setText("Details");
            this.DetailsBtn.setBackgroundColor(Color.parseColor("#f0b06d"));
            this.DetailsBtn.setOnClickListener(new View.OnClickListener(){public void onClick(View v){
                PressBtn(v);
            }});
        }
        this.vertLayout = new LinearLayout(context);
        this.vertLayout.setOrientation(LinearLayout.VERTICAL);

        System.out.println("We refine views");
        Title.setTextSize(30f); Title.setTypeface(Typeface.DEFAULT_BOLD); Title.setGravity(Gravity.CENTER);
        Address.setTextSize(18f); County.setTextSize(14f); PhoneContact.setTextSize(18f);
        Website.setTextSize(18f); EmailContact.setTextSize(18f);
        System.out.println("We note our details");
        this.myContext = context;
    }

    public ResourceBlock(JSONObject jObj, Boolean MakeBtn, Context context) throws JSONException {
        this.Title = new TextView(context); this.Title.setText(jObj.getString("Title"));
        this.Address = new TextView(context); this.Address.setText(jObj.getString("Address"));
        this.County = new TextView(context); this.County.setText(jObj.getString("County"));
        this.PhoneContact = new TextView(context); this.PhoneContact.setText(jObj.getString("Phone"));
        this.Website = new TextView(context); this.Website.setText(jObj.getString("Website"));
        if(MakeBtn) {
            this.DetailsBtn = new Button(context);
            this.DetailsBtn.setText("Details");
            this.DetailsBtn.setBackgroundColor(Color.parseColor("#f0b06d"));
            //this.DetailsBtn.setOnClickListener(new View.OnClickListener(){public void onClick(View v){
            //    PressBtn(v);
            //}});
        }

        this.vertLayout = new LinearLayout(context);
        this.vertLayout.setOrientation(LinearLayout.VERTICAL);

        System.out.println("We refine views");
        Title.setTextSize(30f); Title.setTypeface(Typeface.DEFAULT_BOLD); Title.setGravity(Gravity.CENTER);
        Address.setTextSize(18f); County.setTextSize(14f); PhoneContact.setTextSize(18f);
        Website.setTextSize(18f);
        System.out.println("We note our details");
        this.myContext = context;
    }

    public void ShowStuff(){
        vertLayout.addView(Title); vertLayout.addView(Address); vertLayout.addView(County);
        vertLayout.addView(PhoneContact); vertLayout.addView(Website);
        if(DetailsBtn != null) {
            vertLayout.addView(DetailsBtn);
        }
    }

    private void PressBtn(View v){
        Intent i = new Intent(myContext, Details.class);
        System.out.println("I've got an intent");
        i.putExtra("Title", Title.getText()); i.putExtra("Address", Address.getText());
        i.putExtra("County", County.getText()); i.putExtra("Phone", PhoneContact.getText());
        i.putExtra("Website", Website.getText()); i.putExtra("Email", EmailContact.getText());
        i.putExtra("Details", details);
        System.out.println("I've got extras");
        myContext.startActivity(i);
    }
}
