package com.lex.refresh.blog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by caiiishi on 2016/5/28.
 */
public class Articles
{
    public static final String MY_BLOG_ADDRESS = "http://blog.csdn.net/u013613428";
    public static final String CHROME_AGENT =
        "Chrome/49.0.2623.110 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET CLR 2.0.50727; Media Center PC 6.0)";
    private List<String> articles;

    public Articles()
    {
        this.articles = new ArrayList<String>();
    }

    public List<String> getArticles()
    {
        return articles;
    }

    public void retrieveArticleNameFromWeb()
    {
        try
        {
            URL url = new URL( MY_BLOG_ADDRESS );
            Proxy proxy = new Proxy( Proxy.Type.HTTP, new InetSocketAddress("10.144.1.10",8080) );
            URLConnection urlConnection = url.openConnection(proxy);
            urlConnection.addRequestProperty( "user-Agent", CHROME_AGENT );
            BufferedReader bufferedReader =
                new BufferedReader( new InputStreamReader( urlConnection.getInputStream() ) );

            String ln;
            while( (ln = bufferedReader.readLine()) != null )
            {
                if( ln.contains( "<span class=\"link_title\"><a href=\"/u013613428/article/details" ))
                {
                    String articleId = ln.substring( ln.length()-10, ln.length()-2 );
                    this.articles.add(  String.format( "http://blog.csdn.net/u013613428/article/details/%s", articleId )  );
                }
            }
        }
        catch( MalformedURLException e )
        {
            e.printStackTrace();
        }
        catch( IOException e )
        {
            e.printStackTrace();
        }
    }

}
