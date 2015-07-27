package com.wen.database.model;

import java.util.Date;

/**
 * Created by Wen on 7/27/2015.
 */
public class Orders {

    private Long id;
    private Date createTime;

    public Orders() {
    }

    public Orders(Long id, Date createTime) {
        this.id = id;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
