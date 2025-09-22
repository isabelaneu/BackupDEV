package com.example.restapi.model;

public class Photo {
    private String id;
    private String author;
    private long width;
    private long height;
    private String url;
    private String download_url;

    public String getID() { return id; }
    public void setID(String value) { this.id = value; }

    public String getAuthor() { return author; }
    public void setAuthor(String value) { this.author = value; }

    public long getWidth() { return width; }
    public void setWidth(long value) { this.width = value; }

    public long getHeight() { return height; }
    public void setHeight(long value) { this.height = value; }

    public String getURL() { return url; }
    public void setURL(String value) { this.url = value; }

    public String getDownload_url() { return download_url; }
    public void setDownload_url(String value) { this.download_url = value; }
}
