package com.zx.mvpdemo.model;

public class NewsInfo {
    private String id;
    private String page;
    private String plat;
    private String version;

    public NewsInfo(String id, String page, String plat, String version) {
        this.id = id;
        this.page = page;
        this.plat = plat;
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPlat() {
        return plat;
    }

    public void setPlat(String plat) {
        this.plat = plat;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
