package com.example.accessingdatajpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity
public class BuddyInfo {
    @Id
    @GeneratedValue
    private Integer id = null;
    private String name = null;
    private String phone = null;
    public BuddyInfo(String budName, String budPhone){
        name = budName;
        phone = budPhone;
    }

    public BuddyInfo(Integer budId, String budName, String budPhone){
        id = budId;
        name = budName;
        phone = budPhone;
    }

    public BuddyInfo() {
    }

    @Override
    public String toString() {
        return "BuddyInfo{id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
