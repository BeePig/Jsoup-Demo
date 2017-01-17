package com.example.beepi.jsoupdemo;

import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ParseUrlAsynTask extends AsyncTask<String, Void, String> {
    public ContentSong mContentSong;

    public ParseUrlAsynTask(ContentSong contentSong) {
        mContentSong = contentSong;
    }

    String urlImge = "";

    @Override
    protected String doInBackground(String... params) {
        try {
            Document document = Jsoup.connect(params[0]).get();
            Log.d("Jsoup", "starting");
            Elements elementClasses = document.getElementsByClass("field-content");
            if (elementClasses != null) {
                Elements elementsHref = elementClasses.select("a");
                if (elementsHref != null) {
                    for (Element element : elementsHref) {
                        String urlVideo = element.attr("href");
                        Elements elementImg = element.select("img");
                        urlImge = urlImge + elementImg.attr("src") + "\n";
                        String title = elementImg.attr("title");
                        mContentSong.setmUrlImage(urlImge);
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mContentSong.getmUrlImage();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

    }
}