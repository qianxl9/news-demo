package com.example.jh.nes_demo.Util;

import android.text.TextUtils;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by jh on 2016/8/27.
 */
public class XmlUtil {

    public static List<String> loadNewsXml(String response) throws XmlPullParserException {
        List<String> list = new ArrayList<String>();

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(response));

            int eventType = xmlPullParser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {

                switch (eventType) {
                    case XmlPullParser.START_TAG: {
                        String nodeName = xmlPullParser.getName();
                        if ("p".equals(nodeName)) {
                            xmlPullParser.getDepth();
                            if (xmlPullParser.getAttributeCount() <= 0) {
                                list.add(xmlPullParser.nextText());
                            }
                        }else if ("img".equals(nodeName)) {
                            list.add(xmlPullParser.getAttributeValue(0));
                        }

                        break;
                    }
                }
                eventType = xmlPullParser.next();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    private static ContentHanlder contentHanlder = new ContentHanlder();

    public static List<String> loadXmlSax(String response) throws SAXException {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            XMLReader  xmlReader= factory.newSAXParser().getXMLReader();
            xmlReader.setContentHandler(contentHanlder);
            xmlReader.parse(new InputSource(new StringReader(response)));
            if (contentHanlder.type == 1)
                return contentHanlder.list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    static class ContentHanlder extends DefaultHandler {

        private List<String> list;
        private String nodeName;
        private int type;

        @Override
        public void startDocument() throws SAXException {
            list = new ArrayList<String>();
            type = 0;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            nodeName = localName;
            if (localName.equals("img")) {
                list.add(attributes.getValue(0));
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if (nodeName.equals("p")) {
                list.add(new String(ch,start,length));
            } else if(nodeName.equals("small")) {
                list.add(new String(ch,start,length));
            }
        }

        @Override
        public void endDocument() throws SAXException {
            type = 1;
        }
    }
}
