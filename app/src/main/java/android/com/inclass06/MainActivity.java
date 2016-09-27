package android.com.inclass06;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    ProgressDialog dialog;
    LinearLayout verticalLayout;
    LinearLayout layoutVertical;
    AsyncTask<String, Void, ArrayList<Feed>> feeds;
    ArrayList<Feed> feedsFetched = new ArrayList<>();
    LayoutInflater inflater;
    View view;
    TextView text;
    ImageView image;
    public final static String FEEDS_KEY = "Feeds";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);
        dialog.setMessage("Loading News ...");
        //dialog.show();

        verticalLayout = (LinearLayout) findViewById(R.id.verticalLayout);

        inflater = LayoutInflater.from(this);

        feeds =  new GetRSSFeed(this).execute("http://rss.cnn.com/rss/cnn_tech.rss");
        try {
            feedsFetched = feeds.get();
            Collections.sort(feedsFetched);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for(Feed f:feedsFetched) {
            view = inflater.inflate(R.layout.new_feed_layout, null);
            view.setPadding(0,5,0,0);
            view.setOnClickListener(new ManageFeed(f));
            text = (TextView)view.findViewById(R.id.textViewFeedText);
            image = (ImageView)view.findViewById(R.id.imageViewFeedImage);
            text.setText(f.getTitle());
            new GetImage().execute(new ImageData(f.getSmallImgUrl(), image));
            verticalLayout.addView(view);
        }

    }
    class ManageFeed implements View.OnClickListener
    {
        Feed feed;

        public ManageFeed(Feed feed) {
            this.feed = feed;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, DetailedActivity.class);
            intent.putExtra(FEEDS_KEY, feed);
            startActivity(intent);
        }
    }
}
