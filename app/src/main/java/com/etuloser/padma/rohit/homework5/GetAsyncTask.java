package com.etuloser.padma.rohit.homework5;

import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by Rohit on 2/17/2017.
 */

public class GetAsyncTask extends AsyncTask<String,Void,ArrayList<Game>> {

    Idata act;

    public GetAsyncTask(Idata act)
    {
        this.act=act;
    }


    @Override
    protected ArrayList<Game> doInBackground(String... params) {

        InputStream in=null;
        try {
            URL url=new URL(params[0]);
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int responseCode=con.getResponseCode();

            if(responseCode==con.HTTP_OK)
            {
              in=con.getInputStream();

                return Gameutil.GameutilParser.parseGameObject(in);
            }

        } catch (java.io.IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                if(in!=null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Game> games) {

        super.onPostExecute(games);
        act.SetGame(games);
    }

    public static interface Idata{

        public void SetGame(ArrayList<Game> aglist);
    }

}
