package com.example.accessingdatajpa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class AddressBook {
    @Id
    @GeneratedValue
    private Integer id = null;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BuddyInfo> buddyList;
    public List<BuddyInfo> getBuddyList() {
        return buddyList;
    }

    public AddressBook(){
        buddyList = new ArrayList<>();
    }

    public void addInfo(BuddyInfo bi){
        buddyList.add(bi);
    }

    public void removeInfo(BuddyInfo bi){
        buddyList.remove(bi);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AddressBook{id=" + id +
                ", buddyList=" + buddyList.toString() +
                '}';
    }

    public static void main(String[] args) {
        AddressBook a1 = new AddressBook();
        a1.addInfo(new BuddyInfo("Shawshank", "613"));
        a1.addInfo(new BuddyInfo("bobby", "not a number hehe"));
        System.out.println(a1);


    }

}
