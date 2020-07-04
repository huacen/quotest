package com.flying.famous.quotes.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Quotes {
    @Id(autoincrement = true)
    private Long id;
    //类型id
    private Long tid;
    private String text;
    @Generated(hash = 2095682466)
    public Quotes(Long id, Long tid, String text) {
        this.id = id;
        this.tid = tid;
        this.text = text;
    }
    @Generated(hash = 307010745)
    public Quotes() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getTid() {
        return this.tid;
    }
    public void setTid(Long tid) {
        this.tid = tid;
    }
    public String getText() {
        return this.text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
