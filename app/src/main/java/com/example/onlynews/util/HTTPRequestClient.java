package com.example.onlynews.util;

import android.os.Build;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

//TODO: find efficient way to process parameters and request from API
//TODO: deal with standard char set not being available for some (android) API levels

public class HTTPRequestClient {
    private static String baseURLPath = "https://newsapi.org/v2/";

    public static JSONObject requestTopHeadlines(String country, String category, ArrayList<String> sources, String keyword) throws IOException {
        URL url = new URL(baseURLPath + (country == null ? "" : "country="+country));
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        } finally {
            urlConnection.disconnect();
        }

        return null;
    }

    public static JSONObject requestEveryHeadline(String keyword, String keyInTitle, ArrayList<String> sources, ArrayList<String> domains, ArrayList<String> notDomains, String toDate, String fromDate, String language, NEWSApiSortValues val){
        return null;
    }

    private static String encodeRequest(String URL){
        try{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                return URLEncoder.encode(URL, StandardCharsets.UTF_8.toString());
            }
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }
}
