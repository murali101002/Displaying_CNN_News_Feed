package android.com.inclass06;

import android.app.ProgressDialog;
import android.com.inclass06.MainActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by murali101002 on 9/22/2016.
 */
public class GetImage extends AsyncTask<ImageData,Void,Bitmap>{

    ImageView imageView;

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
       /* progressDialog = new ProgressDialog(activity);
        progressDialog.setTitle("Loading Image");
        progressDialog.setCancelable(false);
        progressDialog.show();*/
    }

    @Override
    protected Bitmap doInBackground(ImageData... params) {
        Bitmap bitmap = null;
        try {
            imageView = (ImageView) params[0].getView();
            URL url = new URL(params[0].getUrl());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int status_code = con.getResponseCode();
            if(status_code == HttpURLConnection.HTTP_OK){
                bitmap = BitmapFactory.decodeStream(con.getInputStream());
            }
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
