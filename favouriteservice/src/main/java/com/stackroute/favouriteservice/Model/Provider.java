package com.stackroute.favouriteservice.Model;

public class Provider {
	
	

    public Provider(String source, String url, String pubDate) {
		this.source = source;
		this.url = url;
		this.pubDate = pubDate;
	}

	public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }


    private String source;
    private String url;
    private String pubDate;




}
