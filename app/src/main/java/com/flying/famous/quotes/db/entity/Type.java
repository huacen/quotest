package com.flying.famous.quotes.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Type {
    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private String name;
    private String iconUrl;
    private String bg;
    @Generated(hash = 2079582951)
    public Type(Long id, @NotNull String name, String iconUrl, String bg) {
        this.id = id;
        this.name = name;
        this.iconUrl = iconUrl;
        this.bg = bg;
    }
    @Generated(hash = 1782799822)
    public Type() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIconUrl() {
        return this.iconUrl;
    }
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
    public String getBg() {
        return this.bg;
    }
    public void setBg(String bg) {
        this.bg = bg;
    }
}
