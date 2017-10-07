package com.etuloser.padma.rohit.homework5;

import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by Rohit on 2/17/2017.
 */

public class GetSimilarAsync extends AsyncTask<ArrayList<String>,Void,ArrayList<Game>> {

    ArrayList<String> ids=new ArrayList<String>();
    ArrayList<Game> gl=new ArrayList<Game>();
        Isimilar act;
        int n;
        public GetSimilarAsync(Isimilar act)
        {
        this.act=act;
        }



@Override
protected ArrayList<Game> doInBackground(ArrayList<String>... params) {

         ids=params[0];
        InputStream in=null;
        try
        {
            String urllink="http://thegamesdb.net/api/GetGame.php?id=";
            for(String id:ids) {
                URL url = new URL(urllink+id);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();
                int responseCode = con.getResponseCode();

                if (responseCode == con.HTTP_OK) {
                    in = con.getInputStream();
                    gl.addAll(Gameutil.GameutilParser.parseGameObject(in));
                }
            }
            return gl;
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

    act.SetGame(games);
          super.onPostExecute(games);


        }

public static interface Isimilar{

    public void SetGame(ArrayList<Game> aglist);
}

}