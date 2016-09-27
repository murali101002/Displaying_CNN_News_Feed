package android.com.inclass06;

import android.app.ProgressDialog;
import android.com.inclass06.Feed;
import android.os.AsyncTask;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by murali101002 on 9/26/2016.
 */
public class GetRSSFeed extends AsyncTask<String,Void,ArrayList<Feed>> {

    MainActivity activity;
    ProgressDialog progressDialog;

    public GetRSSFeed(MainActivity mainActivity) {
        this.activity=mainActivity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(activity);
        progressDialog.setTitle("Loading News...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected ArrayList<Feed> doInBackground(String... params) {
        InputStream in = null;
        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statusCode = con.getResponseCode();
            if(statusCode == HttpURLConnection.HTTP_OK){
                in = con.getInputStream();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return FeedXMLParser.FeedSAXParser.feedParser(in);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Feed> feeds) {
        super.onPostExecute(feeds);
        progressDialog.dismiss();
    }
}
