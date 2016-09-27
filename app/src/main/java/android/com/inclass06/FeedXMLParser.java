package android.com.inclass06;

import android.com.inclass06.Feed;
import android.util.Xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by murali101002 on 9/26/2016.
 */
public class FeedXMLParser {
    static public class FeedSAXParser extends DefaultHandler {
        public ArrayList<Feed> getFeedArrayList() {
            return feedArrayList;
        }

        ArrayList<Feed> feedArrayList;
        Feed feed;
        StringBuilder xmlInnerText;
        int flag = 0;

        public static ArrayList<Feed> feedParser(InputStream in) throws IOException, SAXException {
            FeedSAXParser feedSAXParser = new FeedSAXParser();
            Xml.parse(in, Xml.Encoding.UTF_8, feedSAXParser);
            return feedSAXParser.getFeedArrayList();
        }

        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            super.endElement(uri, localName, qName);
            if (localName.equals("item")) {
                feedArrayList.add(feed);
            }
            if (flag == 0) {
                if (localName.equals("title")) {
                    feed.setTitle(xmlInnerText.toString());
                }
                if (localName.equals("description")) {
                    feed.setDescription(xmlInnerText.toString());
                }
                if (localName.equals("pubDate")) {
                    feed.setPubDate(xmlInnerText.toString());
                }
                if (localName.equals("link")) {
                    feed.setLink(xmlInnerText.toString());
                }
            }

            xmlInnerText.setLength(0);
        }

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            feedArrayList = new ArrayList<>();
            xmlInnerText = new StringBuilder();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            super.startElement(uri, localName, qName, attributes);
            if (localName.equals("channel")) {
                flag = 1;
            }
            if (localName.equals("item")) {
                feed = new Feed();
                flag = 0;
            }
            if (qName.equals("media:content")) {
                if (attributes.getValue("height").equals("619"))
                    feed.setLargeImgUrl(attributes.getValue("url"));
                if (attributes.getValue("height").equals("300"))
                    feed.setSmallImgUrl(attributes.getValue("url"));
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            super.characters(ch, start, length);
            xmlInnerText.append(ch, start, length);
        }
    }
}
