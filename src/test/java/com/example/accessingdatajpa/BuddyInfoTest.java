package com.example.accessingdatajpa;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class BuddyInfoTest {

    @Autowired
    BuddyInfoRepository repo;

    @Test
    public void TestBuddy(){
        BuddyInfo b1 = new BuddyInfo("Bobby", "6136136136");
        System.out.println(b1);
        assertEquals("BuddyInfo{name='Bobby', phone='6136136136'}", b1.toString());
    }

    @Test
    public void TestGetName(){
        BuddyInfo b1 = new BuddyInfo("Bobby", "6136136136");
        assertEquals("Bobby", b1.getName());
    }

    @Test
    public void TestGetPhone(){
        BuddyInfo b1 = new BuddyInfo("Bobby", "6136136136");
        assertEquals("6136136136", b1.getPhone());
    }

    @Test
    public void TestSetName(){
        BuddyInfo b1 = new BuddyInfo("Bobby", "6136136136");
        assertEquals("Bobby", b1.getName());
        b1.setName("hello");
        assertEquals("hello", b1.getName());
    }

    @Test
    public void TestSetPhone(){
        BuddyInfo b1 = new BuddyInfo("Bobby", "6136136136");
        assertEquals("6136136136", b1.getPhone());
        b1.setPhone("613");
        assertEquals("613", b1.getPhone());
    }

    @Test
    public void TestBuddyInfoRepository(){
        BuddyInfo b1 = new BuddyInfo("Bobby", "6136136136");
        repo.save(b1);
        assertEquals(b1.getName() , repo.findById(b1.getId()).get().getName());
    }
}