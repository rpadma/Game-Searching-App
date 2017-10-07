package com.etuloser.padma.rohit.homework5;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class GameDetailsActivity extends AppCompatActivity {

    TextView titletxt;
    ImageView iv;
    LinearLayout ll;
    TextView genretxt;
    TextView publishtxt;
    Game ga=new Game();
    ProgressDialog PD;
    LinearLayout lmainout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);
        iv=(ImageView)findViewById(R.id.gameimageview);
        titletxt=(TextView)findViewById(R.id.txttitle);
        genretxt=(TextView)findViewById(R.id.txtgenre);
        lmainout=(LinearLayout)findViewById(R.id.layoutgameview);
        publishtxt=(TextView)findViewById(R.id.txtpublisher);
      //  pb=(ProgressBar)findViewById(R.id.imagepd);
        ll=(LinearLayout)findViewById(R.id.Overviewlayout);
        if(getIntent().getExtras()!=null)
        {
            ga=(Game)(getIntent().getExtras().getSerializable("GameObj"));
        }

        PD=new ProgressDialog(this);
        PD.setCancelable(false);


        Binddata(ga);

    }

    public void Binddata(Game g) {

        PD.show();
        if (g.getImageurl() != null) {
            // pb.setVisibility(View.VISIBLE);
            Picasso.with(this).load(g.getImageurl()).into(iv, new Callback() {
                @Override
                public void onSuccess() {

                    PD.dismiss();
lmainout.setVisibility(View.VISIBLE);
                }

                @Override
                public void onError() {
                    //error

                }
            });
            // pb.setVisibility(View.INVISIBLE);


            TextView tvo=new TextView(this);

            ActionBar.LayoutParams lp=new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tvo.setLayoutParams(lp);
            tvo.setText(g.getOverview());
            ll.addView(tvo);
            titletxt.setText(g.getGametitle());
            String tempg="";
            if(g.getGenre()!=null) {
                for (String t : g.getGenre()) {
                    tempg = tempg + t + ",";
                }
            }
            tempg=tempg.substring(0,tempg.length()-1);
            genretxt.setText("Genre: "+tempg);
            publishtxt.setText("Publisher: "+ (g.getPublisher()!=null? g.getPublisher():""));

        }
    }
    public void SimilarGamesClick (View v)
    {

        ConnectivityManager cm=(ConnectivityManager)GameDetailsActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni=cm.getActiveNetworkInfo();
        if(ni!=null && ga!=null)
        {

            Intent i=new Intent(this,SimilarGamesActivity.class);
             i.putExtra("sgtitle", ga.getGametitle());
            i.putExtra("similarids",ga.getSimilarId());
            startActivity(i);
        }
        else
        {
            Toast.makeText(this,"Check your wifi/mobile data connection / they are no proper Ids ",Toast.LENGTH_SHORT).show();
        }

    }

    public void FinishClick(View v)
    {

        Intent i=new Intent(this,MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    public void PlayTrailerClick (View v)
    {

        if(ga.getYoutubelink()!=null) {
            Intent i = new Intent(this, WebViewActivity.class);
            i.putExtra("titlename",ga.getGametitle()+" "+ga.getPublisheddata());
            i.putExtra("uurl", ga.getYoutubelink());
            startActivity(i);
        }
        else
        {
            Toast.makeText(this,"No Trailer is available",Toast.LENGTH_SHORT).show();
        }
    }
}
