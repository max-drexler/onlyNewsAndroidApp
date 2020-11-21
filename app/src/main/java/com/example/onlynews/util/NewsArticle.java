package com.example.onlynews.util;

//TODO: Finish implementing getters/setters/constructors

/**
 * {
 *
 *     "status": "ok",
 *     "totalResults": 38,
 *     -
 *     "articles": [
 *         -
 *         {
 *             -
 *             "source": {
 *                 "id": null,
 *                 "name": "New York Post"
 *             },
 *             "author": "Natalie O'Neill",
 *             "title": "Scientist behind Pfizer's COVID-19 vaccine makes bold claim: It can stop pandemic - New York Post ",
 *             "description": "A billionaire scientist behind Pfizer and BioNTech’s breakthrough COVID-19 vaccine says he expects it to “bash the virus over the head” and ultimately end the pandemic, according to a report. Follo…",
 *             "url": "https://nypost.com/2020/11/13/man-behind-covid-19-vaccine-says-it-will-bash-the-virus-over-head/",
 *             "urlToImage": "https://nypost.com/wp-content/uploads/sites/2/2020/11/biotech.jpg?quality=90&strip=all&w=1200",
 *             "publishedAt": "2020-11-13T23:57:00Z",
 *             "content": "A billionaire scientist behind Pfizer and BioNTechs breakthrough COVID-19 vaccine says he expects it to bash the virus over the head and ultimately end the pandemic, according to a report.\r\nFollowing… [+1743 chars]"
 *         },
 */
public class NewsArticle {
    private String mSource;
    private String mAuthor;
    private String mTitle;
    private String mDescription;
    private String mUrl;
    private String mUrlToImage;
    private String mPublishedAt;
    private String mContent;

    public NewsArticle(String source, String author, String title, String description, String url,
                       String urlToImage, String publishedAt, String content){
        this.mAuthor = author;
        this.mContent = content;
        this.mDescription = description;
        this.mSource = source;
        this.mTitle = title;
        this.mUrl = url;
        this.mUrlToImage = urlToImage;
        this.mPublishedAt = publishedAt;
    }

    public String getSource() {
        return mSource;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getUrlToImage() {
        return mUrlToImage;
    }

    public String getPublishedAt() {
        return mPublishedAt;
    }

    public String getContent() {
        return mContent;
    }
}
