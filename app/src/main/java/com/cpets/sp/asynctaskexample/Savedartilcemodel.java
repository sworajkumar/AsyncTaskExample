package com.cpets.sp.asynctaskexample;

public class Savedartilcemodel {
    String did;
    String articleid;
    String articleName;
    String overview;
    String authors;
    String publishDate;
    String articleDescription;
    String categories;
    String subCategories;
    String viewStatus;

    public Savedartilcemodel(String did, String articleid, String articleName, String authors, String publishDate, String categories,String viewStatus) {
        this.did = did;
        this.articleid = articleid;
        this.articleName = articleName;
        this.authors = authors;
        this.publishDate = publishDate;
        this.categories = categories;
        this.viewStatus = viewStatus;
    }

    public Savedartilcemodel() {
    }

    public String getViewStatus() {
        return viewStatus;
    }

    public void setViewStatus(String viewStatus) {
        this.viewStatus = viewStatus;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getArticleid() {
        return articleid;
    }

    public void setArticleid(String articleid) {
        this.articleid = articleid;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getArticleDescription() {
        return articleDescription;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(String subCategories) {
        this.subCategories = subCategories;
    }

}
