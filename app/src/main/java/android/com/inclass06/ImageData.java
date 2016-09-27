package android.com.inclass06;

import android.widget.ImageView;

/**
 * Created by murali101002 on 9/26/2016.
 */
public class ImageData {
    public String url;
    public ImageView view;

    public ImageData(String url, ImageView view) {
        this.url = url;
        this.view = view;
    }

    public String getUrl() {
        return url;
    }

    public ImageView getView() {
        return view;
    }
}
