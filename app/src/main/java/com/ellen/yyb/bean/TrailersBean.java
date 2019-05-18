package com.ellen.yyb.bean;


import java.util.List;

public  class TrailersBean {
    /**
     * id : 69826
     * movieName : 《起跑线》定档预告
     * coverImg : http://img5.mtime.cn/mg/2018/03/08/110652.95637891_120X90X4.jpg
     * movieId : 251494
     * url : http://vfx.mtime.cn/Video/2018/03/08/mp4/180308110531052852.mp4
     * hightUrl : http://vfx.mtime.cn/Video/2018/03/08/mp4/180308110531052852.mp4
     * videoTitle : 起跑线 定档预告
     * videoLength : 8
     * rating : 7.9
     * type : ["剧情","喜剧"]
     * summary : 印度择校话题引发热议
     */

    //不能用int
    private Long ID;
    private int id;
    private String movieName;
    private String coverImg;
    private int movieId;
    private String url;
    private String hightUrl;
    private String videoTitle;
    private int videoLength;
    private double rating;
    private String summary;
    private List<String> type;

    public TrailersBean(Long ID, int id, String movieName, String coverImg,
                        int movieId, String url, String hightUrl, String videoTitle,
                        int videoLength, double rating, String summary) {
        this.ID = ID;
        this.id = id;
        this.movieName = movieName;
        this.coverImg = coverImg;
        this.movieId = movieId;
        this.url = url;
        this.hightUrl = hightUrl;
        this.videoTitle = videoTitle;
        this.videoLength = videoLength;
        this.rating = rating;
        this.summary = summary;
    }

    public TrailersBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHightUrl() {
        return hightUrl;
    }

    public void setHightUrl(String hightUrl) {
        this.hightUrl = hightUrl;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public int getVideoLength() {
        return videoLength;
    }

    public void setVideoLength(int videoLength) {
        this.videoLength = videoLength;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public Long getID() {
        return this.ID;
    }


    public void setID(Long ID) {
        this.ID = ID;
    }
}