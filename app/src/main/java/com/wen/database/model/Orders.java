package com.wen.database.model;

import java.util.Date;

/**
 * Created by Wen on 7/27/2015.
 */
public class Orders {

    private Integer id;
    private boolean fulfilled;
    private boolean current;
    private Date createTime;

    public Orders() {
    }

    public Orders(Integer id, Date createTime) {
        this.id = id;
        fulfilled = false;
        current = true;
        this.createTime = createTime;
    }

    public Integer getId(){ return id; }

    public void setId(Integer id) { this.id = id; }

    public boolean isFulfilled() {
        return fulfilled;
    }

    public void setFulfilled(boolean fulfilled) {
        this.fulfilled = fulfilled;
    }

    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
