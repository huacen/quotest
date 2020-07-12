package com.flying.famous.quotes.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Like {
    @Id(autoincrement = true)
    private Long id;
    private long qid;
    private String uuid;
    @Generated(hash = 924760505)
    public Like(Long id, long qid, String uuid) {
        this.id = id;
        this.qid = qid;
        this.uuid = uuid;
    }
    @Generated(hash = 763251169)
    public Like() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public long getQid() {
        return this.qid;
    }
    public void setQid(long qid) {
        this.qid = qid;
    }
    public String getUuid() {
        return this.uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
