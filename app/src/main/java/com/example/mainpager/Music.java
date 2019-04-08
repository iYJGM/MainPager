package com.example.mainpager;

public class Music {
    private String newsIconUrl;//图片的网址即picSmall
    private String newsTitle;//图片的标题即json中的name属性
    private String newsContent;//图片的内容即json中的description
    private String duration;//歌曲时长



    public Music(String newsIconUrl, String newsTitle, String newsContent,String duration)
    {
        this.newsIconUrl = newsIconUrl;
        this.newsTitle = newsTitle;
        this.newsContent = newsContent;
        this.duration = duration;
    }
    public String getNewsIconUrl() {
        return newsIconUrl;
    }
    public void setNewsIconUrl(String newsIconUrl) {
        this.newsIconUrl = newsIconUrl;
    }
    public String getNewsTitle() {
        return newsTitle;
    }
    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }
    public String getNewsContent() {
        return newsContent;
    }
    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }
    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


}