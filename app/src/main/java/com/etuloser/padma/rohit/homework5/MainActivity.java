package com.etuloser.padma.rohit.homework5;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetAsyncTask.Idata {

    ArrayList<Game> glist=new ArrayList<Game>();
    ProgressDialog pd;
    Button gobutton;
  EditText edtxt;
    RadioGroup rg;

    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gobutton=(Button)findViewById(R.id.butgo);
        edtxt=(EditText)findViewById(R.id.edxsearch);
        ll=(LinearLayout)findViewById(R.id.main_layout);
    }


    @Override
    public void SetGame(ArrayList<Game> aglist) {

        glist=aglist;
        pd.dismiss();
       if(glist!=null) {
         //   Toast.makeText(this, "size of list :" + String.valueOf(glist.size()), Toast.LENGTH_SHORT).show();
            gobutton.setBackgroundColor(getResources().getColor(R.color.customcolor));
            gobutton.setEnabled(true);
            displaydata();
        }
        else
        {
            Toast.makeText(this,"No result for search key",Toast.LENGTH_SHORT).show();
        }

    }

    public void displaydata(){
        LayoutInflater inflater = (LayoutInflater)MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemBox = inflater.inflate(R.layout.childrow, null);
        rg=(RadioGroup)itemBox.findViewById(R.id.radiogrp);
        ll.removeAllViews();

         for(int i=0;i<glist.size()-1;i++)
         {
              Game g=glist.get(i);

             RadioButton rd=new RadioButton(this);
             rd.setId(i+1);
             String TempSet="";
             TempSet=g.getGametitle().toString();
             if(g.getPublisheddata()!=null)
             {
                 TempSet=TempSet+". Release in "+g.getPublisheddata();
             }
             if(g.getPlatform()!=null)
             {
                 TempSet=TempSet+" . Platform:"+g.getPlatform();
             }
             rd.setText(TempSet);
             rg.addView(rd);
             View v = new View(this);
             v.setLayoutParams(new LinearLayout.LayoutParams(
                     LinearLayout.LayoutParams.MATCH_PARENT,
                     5
             ));
             v.setBackgroundColor(Color.parseColor("#B3B3B3"));
             rg.addView(v);
         }
        ll.addView(itemBox);



    }

    public void SearchClick(View v)
    {
        String keysearch=edtxt.getText().toString().trim();

        if(keysearch.length()>0) {
            String url = "http://thegamesdb.net/api/GetGame.php?name=" + keysearch;


            ConnectivityManager cm = (ConnectivityManager) MainActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ni = cm.getActiveNetworkInfo();

            if (ni != null) {

                pd = new ProgressDialog(this);
                pd.setCancelable(false);
                pd.setProgressStyle(pd.STYLE_SPINNER);
                pd.show();
                new GetAsyncTask(this).execute(url);

            } else {

                Toast.makeText(this, "Check your wifi/mobile data connection", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this, "Enter proper search key ", Toast.LENGTH_SHORT).show();
        }

    }


    public void GoClick(View v)
    {

        if(rg.getCheckedRadioButtonId()>0)
        {

            Game q=glist.get(rg.getCheckedRadioButtonId()-1);

            Intent i =new Intent(this,GameDetailsActivity.class);
             i.putExtra("GameObj",q);
             startActivity(i);

        }
        else
        {
            Toast.makeText(this,"Select a option",Toast.LENGTH_SHORT).show();
        }
    }

}
