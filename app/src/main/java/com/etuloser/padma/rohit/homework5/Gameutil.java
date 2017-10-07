package com.etuloser.padma.rohit.homework5;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Rohit on 2/17/2017.
 */

public class Gameutil {

    static public class GameutilParser{


        static public ArrayList<Game> parseGameObject(InputStream in) throws XmlPullParserException, IOException, ParseException {


            XmlPullParser xpp= XmlPullParserFactory.newInstance().newPullParser();
            xpp.setInput(in,"UTF-8");
            Game g=null;
            String Baseimageurl="";
            ArrayList<Game> gamelist=new ArrayList<Game>();
            int event=xpp.getEventType();
            ArrayList<String> tempgenre=new ArrayList<String>();;
            ArrayList<String> tempsimilar=new ArrayList<String>();


            String tempid="";
            while(event!=XmlPullParser.END_DOCUMENT)
            {
                switch (event)
                {
                    case XmlPullParser.START_TAG :

                        if(xpp.getName().equals("baseImgUrl"))
                        {
                        Baseimageurl=xpp.nextText().trim();
                        }
                        else
                        if (xpp.getName().equals("id")) {
                            if(g==null) {
                                tempid = xpp.nextText().trim();
                            }
                        else
                            {
                                tempsimilar.add(xpp.nextText().trim());
                            }
                        }
                        if(xpp.getName().equals("GameTitle") && tempid.length()>0)
                        {
                            g=new Game();
                            g.setGametitle(xpp.nextText().trim());
                            g.setId(tempid);
                            tempid="";
                            tempgenre=new ArrayList<String>();
                            tempsimilar=new ArrayList<String>();
                            }
                        else
                         if (xpp.getName().equals("Platform")) {
                            g.setPlatform(xpp.nextText().trim());
                        } else if(xpp.getName().equals("ReleaseDate"))
                        {

                            String tempdate=xpp.nextText().trim().toString();
                            if(tempdate.length()==10)
                            {
                                String year=tempdate.substring(6,10).toString();
                                if(year!=null)
                                {
                                g.setPublisheddata(year);
                            }
                            }
                            else
                            {
                                g.setPublisheddata(xpp.nextText().trim());
                            }


                        } else if(xpp.getName().equals("Overview"))
                        {
                            g.setOverview(xpp.nextText().trim());
                        }
                        else if(xpp.getName().equals("boxart"))
                        {
                            if(Baseimageurl.length()>0) {
                                g.setImageurl(Baseimageurl + xpp.nextText().trim().toString());
                            }
                        }

                        else if(xpp.getName().equals("Publisher"))
                        {
                            g.setPublisher(xpp.nextText().trim());
                        }
                        else if(xpp.getName().equals("Youtube"))
                        {
                            g.setYoutubelink(xpp.nextText().trim());
                        }
                        else
                         if(xpp.getName().equals("genre"))
                         {
                             tempgenre.add(xpp.nextText().trim());
                         }

             /*           else if(xpp.getName().equals("Similar"))
                        {
                            xpp.next();
                            if(xpp.getName().equals("SimilarCount"))
                            {
                                int k=0;
                                int c=Integer.valueOf(xpp.nextText().trim().toString())>0?Integer.valueOf(xpp.nextText().trim().toString()):0;

                                ArrayList<Integer> temp=new ArrayList<Integer>();
                                if(c>0)
                                {
                                    while(k<c)
                                    {
                                        xpp.next();
                                        if(xpp.getName().equals("game"))
                                        {
                                            xpp.next();
                                            if(xpp.getName().equals("id"))
                                            {
                                                temp.add(Integer.valueOf(xpp.nextText().trim().toString()));
                                            }
                                            xpp.next();
                                        }

                                    }

                                }
                                   g.setSimilarId(temp);
                                temp=null;
                            }

                        }

*/
                        break;
                    case XmlPullParser.END_TAG:

           //             Log.d("demo",xpp.getName());
                        if(xpp.getName().equals("Images")) {

                            gamelist.add(g);
                            g=null;
                            tempgenre=null;
                            tempsimilar=null;
                         }
                        else
                        if(xpp.getName().equals("Genres"))
                        {
                           g.setGenre(tempgenre);
                            tempgenre=new ArrayList<String>();
                        }
                        else if(xpp.getName().equals("Similar"))
                        {
                            g.setSimilarId(tempsimilar);
                            tempsimilar=new ArrayList<String>();
                        }

                        break;

                    default:
                        break;
                }

                event=xpp.next();

            }

            return gamelist;
        }



    }


}
