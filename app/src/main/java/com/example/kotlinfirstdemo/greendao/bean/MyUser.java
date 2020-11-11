package com.example.kotlinfirstdemo.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class MyUser {
    @Id(autoincrement = true)
    private Long id ;
    @Property(nameInDb = "NAME")
    private String  name;
    @Transient
    private int  tempUsageCount;

    @Property
    private boolean isCollect;

    public MyUser(Long id, String name, int tempUsageCount) {
        this.id = id;
        this.name = name;
        this.tempUsageCount = tempUsageCount;
    }

    public MyUser() {
    }

    @Generated(hash = 302792846)
    public MyUser(Long id, String name, boolean isCollect) {
        this.id = id;
        this.name = name;
        this.isCollect = isCollect;
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

    public int getTempUsageCount() {
        return tempUsageCount;
    }

    public void setTempUsageCount(int tempUsageCount) {
        this.tempUsageCount = tempUsageCount;
    }

    public boolean isCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    public boolean getIsCollect() {
        return this.isCollect;
    }

    public void setIsCollect(boolean isCollect) {
        this.isCollect = isCollect;
    }

    @Override
    public String toString() {
        return "MyUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tempUsageCount=" + tempUsageCount +
                ", isCollect=" + isCollect +
                '}';
    }
}
