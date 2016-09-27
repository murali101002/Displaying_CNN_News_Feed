package android.com.inclass06;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailedActivity extends AppCompatActivity  {

    Feed feed = null;

    TextView description;
    TextView date;
    TextView title;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        description = (TextView) findViewById(R.id.textViewDescription);
        date = (TextView) findViewById(R.id.textViewDate);
        title = (TextView) findViewById(R.id.textViewTitle);
        imageView = (ImageView) findViewById(R.id.imageView);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            if (extras.get(MainActivity.FEEDS_KEY) != null) {
                feed = (Feed) extras.get(MainActivity.FEEDS_KEY);
            }
        }

        description.setText(feed.getDescription());
        Date date1 = new Date(feed.getPubDate());
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
        String time1 = sdf.format(date1);
        date.setText(time1);
//        SimpleDateFormat format = new SimpleDateFormat("MM/DD/yyyy hh:mm aa");
//        Date dateVal = null;
//
//        try {
//            dateVal = (Date) format.parse(feed.getPubDate());
//            date.setText(dateVal.toString());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        title.setText(feed.getTitle());
        new GetImage().execute(new ImageData(feed.getLargeImgUrl(), imageView));
    }
}
