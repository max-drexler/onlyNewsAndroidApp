package com.example.onlynews.util;

import android.os.Build;
import android.util.JsonReader;

import com.example.onlynews.BuildConfig;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
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

    public static ArrayList<NewsArticle> requestTopHeadlines(String country, String category, ArrayList<String> sources, String keyword) throws HTTPRequestException {
        String sourceList = null;
        HttpURLConnection urlConnection = null;
        JsonReader reader = null;
        ArrayList<NewsArticle> articles = null;

        for (String s : sources)
            sourceList += s + ",";
        try {
            URL url = new URL(baseURLPath + "top-headlines?pageSize=100" + (country == null ? "" : "&country=" + country) +
                    (sourceList == null ? "" : "&sources=" + sourceList.substring(0, sourceList.length() - 1)) +
                    (category == null ? "" : "&category=" + category) + (keyword == null ? "" : "&q=" + keyword) + "&apiKey=" + BuildConfig.NEWSAPI_KEY);

            urlConnection = (HttpURLConnection) url.openConnection();
            reader = new JsonReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            articles = parseArticleArray(reader);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            urlConnection.disconnect();
        }

        return articles;
    }

    /**
     * {
     * <p>
     * "status": "ok",
     * "totalResults": 38,
     * -
     * "articles": [
     * -
     * {
     * -
     * "source": {
     * "id": null,
     * "name": "New York Post"
     * },
     * "author": "Natalie O'Neill",
     * "title": "Scientist behind Pfizer's COVID-19 vaccine makes bold claim: It can stop pandemic - New York Post ",
     * "description": "A billionaire scientist behind Pfizer and BioNTech’s breakthrough COVID-19 vaccine says he expects it to “bash the virus over the head” and ultimately end the pandemic, according to a report. Follo…",
     * "url": "https://nypost.com/2020/11/13/man-behind-covid-19-vaccine-says-it-will-bash-the-virus-over-head/",
     * "urlToImage": "https://nypost.com/wp-content/uploads/sites/2/2020/11/biotech.jpg?quality=90&strip=all&w=1200",
     * "publishedAt": "2020-11-13T23:57:00Z",
     * "content": "A billionaire scientist behind Pfizer and BioNTechs breakthrough COVID-19 vaccine says he expects it to bash the virus over the head and ultimately end the pandemic, according to a report.\r\nFollowing… [+1743 chars]"
     * },
     */

    private static NewsArticle parseArticle(JsonReader reader) throws IOException {
        String author = null;
        String title = null;
        String description = null;
        String url = null;
        String urlToImage = null;
        String publishedAt = null;
        String content = null;
        String source = null;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            switch (name) {
                case "author":
                    author = reader.nextString();
                    break;
                case "title":
                    title = reader.nextString();
                    break;
                case "description":
                    description = reader.nextString();
                    break;
                case "url":
                    url = reader.nextString();
                    break;
                case "urlToImage":
                    urlToImage = reader.nextString();
                    break;
                case "publishedAt":
                    publishedAt = reader.nextString();
                    break;
                case "content":
                    content = reader.nextString();
                    break;
                case "source":
                    reader.beginObject();
                    while (reader.hasNext()) {
                        String sTitle = reader.nextName();
                        if (sTitle.equals("name"))
                            source = reader.nextString();
                    }
                    reader.endObject();
                    break;
            }
        }
        reader.endObject();
        return new NewsArticle(source, author, title, description, url, urlToImage, publishedAt, content);
    }

    /**
     * @param
     * @return
     */
    private static ArrayList<NewsArticle> parseArticleArray(JsonReader reader) throws IOException, HTTPRequestException {
        ArrayList<NewsArticle> articles;
        reader.beginObject();
        if (reader.nextString().equals("error")) {
            throw new HTTPRequestException(reader.nextString());
        }
        articles = new ArrayList<>(reader.nextInt());
        reader.beginArray();
        while (reader.hasNext()) {
            articles.add(parseArticle(reader));
        }
        reader.endArray();
        return articles;
    }


    public static JSONObject requestEveryHeadline(String keyword, String keyInTitle, ArrayList<String> sources, ArrayList<String> domains, ArrayList<String> notDomains, String toDate, String fromDate, String language, NEWSApiSortValues val) {
        return null;
    }

    private static String encodeRequest(String URL) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                return URLEncoder.encode(URL, StandardCharsets.UTF_8.toString());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
