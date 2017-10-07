package com.etuloser.padma.rohit.homework5;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SimilarGamesActivity extends AppCompatActivity  implements GetSimilarAsync.Isimilar{

  LinearLayout ls;
    String sgtitle;
    ArrayList<String> Ids=new ArrayList<String>();
    LinearLayout finallout;
    ArrayList<Game> gamelist=new ArrayList<Game>();
    ProgressDialog pd;
    //String URL2="http://thegamesdb.net/api/GetGame.php?id=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similar_games);
        pd=new ProgressDialog(this);
       ls=(LinearLayout)findViewById(R.id.similargameslayout);
        finallout=(LinearLayout)findViewById(R.id.layoutfinal);
        if(getIntent().getExtras()!=null)
        {
          sgtitle=getIntent().getExtras().getString("sgtitle").toString().trim();
            Ids=(ArrayList<String>)(getIntent().getExtras().getStringArrayList("similarids"));
        }

        ((TextView)findViewById(R.id.Sgamestitle)).setText(sgtitle);

        ConnectivityManager cm=(ConnectivityManager)SimilarGamesActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni=cm.getActiveNetworkInfo();

        if(ni!=null  && Ids!=null ) {

            pd.setCancelable(false);
        //    ActionBar.LayoutParams lp=new ActionBar.LayoutParams(600,600);
         //   View vd=new View(this);
          //  vd.setLayoutParams(lp);

           // pd.setProgressStyle(pd.STYLE_SPINNER);
            //pd.setContentView(vd,lp);
           // pd.setView(vd,250,250,200,200);
            //Window window = pd.getWindow();
            //window.setLayout(600,600);
            //window.setBackgroundDrawableResource(R.color.graycolor);
            pd.show();

            try {

                  new GetSimilarAsync(this).execute(Ids);
              }
              catch (Exception e)
              {
                 // Log.d("output",id);
              }


        }
        else
        {
            Toast.makeText(this,"Check your wifi/mobile data connection or they are no proper Ids +",Toast.LENGTH_SHORT).show();
        }


        }


    public void sgFinishClick(View v)
    {
        finish();

    }


    @Override
    public void SetGame(ArrayList<Game> aglist) {
        gamelist=aglist;
        BindData();
        pd.dismiss();
        finallout.setVisibility(View.VISIBLE);


    }

    public void BindData()
    {

        ((TextView)findViewById(R.id.Sgamestitle)).setText(sgtitle);

         ls.removeAllViews();


        LayoutInflater inflater = (LayoutInflater)SimilarGamesActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //LinearLayout slm=(LinearLayout)itemBox.findViewById(R.id.sll);
        for(Game a:gamelist)
        {
            TextView tv=new TextView(this);
            String tp=a.getGametitle();
            if(a.getPublisheddata()!=null)
            {
                tp=tp+". Release in "+a.getPublisheddata();
            }
            if(a.getPlatform()!=null)
            {
                tp=tp+".  Platform:"+a.getPlatform();
            }

            View itemBox = inflater.inflate(R.layout.schildrow, null);
             TextView tv1=(TextView)itemBox.findViewById(R.id.sid);
            //LinearLayout.LayoutParams tvlp=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 50);
            tv1.setText(tp);
            ls.addView(itemBox);



            /*
            View v = new View(this);
            v.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    5
            ));
            v.setBackground(getResources().getDrawable(R.drawable.box));
            v.setBackgroundColor(Color.parseColor("#B3B3B3"));
            */
            //ls.addView(v);


        }


    }
}
