package android.com.inclass06;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by murali101002 on 9/26/2016.
 */
public class Feed  implements Serializable, Comparable<Feed> {

    String title;
    String description;
    String smallImgUrl;
    String largeImgUrl;
    String pubDate;
    String link;

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLargeImgUrl() {
        return largeImgUrl;
    }

    public void setLargeImgUrl(String largeImgUrl) {
        this.largeImgUrl = largeImgUrl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getSmallImgUrl() {
        return smallImgUrl;
    }

    public void setSmallImgUrl(String smallImgUrl) {
        this.smallImgUrl = smallImgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int compareTo(Feed another) {
        Date thisDate = new Date(this.getPubDate());
        Date anotherDate = new Date(another.getPubDate());
        if(thisDate.compareTo(anotherDate)>0) return 1;
        else if(thisDate.compareTo(anotherDate)<0) return -1;
        return 0;
//        if(this.getPubDate().compareToIgnoreCase(another.getPubDate())>0) return 1;
//        else if(this.getPubDate().compareToIgnoreCase(another.getPubDate())<0) return -1;
//        return 0;
    }
}
