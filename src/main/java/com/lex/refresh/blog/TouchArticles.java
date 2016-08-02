package com.lex.refresh.blog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by caiiishi on 2016/5/28.
 */
public class TouchArticles {
    public static void main(String[] args) {
        URL url;
        Articles articles = new Articles();
        List<String> browserInfoList = new ArrayList<String>();
        browserInfoList.add("Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        browserInfoList.add("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET CLR 2.0.50727; Media Center PC 6.0)");
        browserInfoList.add("Chrome/49.0.2623.110 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET CLR 2.0.50727; Media Center PC 6.0)");
        Iterator<String> art_iterator;
        Iterator<String> br_iterator =  browserInfoList.iterator();
        Integer i = 1000;
        while ( i-- > 0 ){
            for (art_iterator = articles.getArticles().iterator(); art_iterator.hasNext(); ) {
                try {
                    url = new URL(art_iterator.next());
                    URLConnection connection = url.openConnection();
                    if(br_iterator.hasNext()){
                        connection.setRequestProperty("User-Agent", br_iterator.next());
                    }else {
                        br_iterator = browserInfoList.iterator();
                        connection.setRequestProperty("User-Agent", br_iterator.next());
                    }
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                    String nl;
//                    while ((nl = bufferedReader.readLine()) != null) {
//                        System.out.println(nl);
//                    }
                    System.out.println( "this is the round: " + i + "\n");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            Thread.sleep( 3000 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
