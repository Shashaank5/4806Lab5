package com.example.accessingdatajpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
@SpringBootTest
public class AddressBookTest {

    @Autowired
    AddressBookRepository repo;
    @Test
    public void TestBook(){
        AddressBook a1 = new AddressBook();
        a1.addInfo(new BuddyInfo("Shawshank", "613"));
        a1.addInfo(new BuddyInfo("bobby", "not a number hehe"));
        assertEquals("AddressBook{buddyList=[BuddyInfo{name='Shawshank', phone='613'}, BuddyInfo{name='bobby', phone='not a number hehe'}]}", a1.toString());
    }

    @Test
    public void TestBookAlternate(){
        AddressBook a1 = new AddressBook();
        a1.addInfo(new BuddyInfo("bob", "613-737-1111"));
        a1.addInfo(new BuddyInfo("Johnyy", "+1(91)123-4567"));
        System.out.println(a1);
        assertEquals("AddressBook{buddyList=[BuddyInfo{name='bob', phone='613-737-1111'}, BuddyInfo{name='Johnyy', phone='+1(91)123-4567'}]}", a1.toString());
    }

    @Test
    public void testGetter(){
        AddressBook a1 = new AddressBook();
        ArrayList<BuddyInfo> b1 = new ArrayList<BuddyInfo>();
        BuddyInfo b2 = new BuddyInfo("bob", "613-737-1111");
        BuddyInfo b3 = new BuddyInfo("Johnyy", "+1(91)123-4567");
        b1.add(b2);
        b1.add(b3);
        a1.addInfo(b2);
        a1.addInfo(b3);
        assertEquals(b1, a1.getBuddyList());
    }

    @Test
    public void testRemove(){
        AddressBook a1 = new AddressBook();
        ArrayList<BuddyInfo> b1 = new ArrayList<BuddyInfo>();
        BuddyInfo b2 = new BuddyInfo("bob", "613-737-1111");
        BuddyInfo b3 = new BuddyInfo("Johnyy", "+1(91)123-4567");
        b1.add(b2);
        b1.add(b3);
        a1.addInfo(b2);
        a1.addInfo(b3);
        assertEquals(b1, a1.getBuddyList());
        a1.removeInfo(b2);
        assertEquals("AddressBook{buddyList=[BuddyInfo{name='Johnyy', phone='+1(91)123-4567'}]}", a1.toString());
    }

    @Test
    public void testAddressBookJPA(){
        AddressBook a1 = new AddressBook();
        BuddyInfo b2 = new BuddyInfo("bob", "613-737-1111");
        BuddyInfo b3 = new BuddyInfo("Johnyy", "+1(91)123-4567");
        a1.addInfo(b2);
        a1.addInfo(b3);
        repo.save(a1);
        System.out.println(a1);
        System.out.println(repo.findById(a1.getId()).get());
        assertEquals(a1.getBuddyList().size(), repo.findById(a1.getId()).get().getBuddyList().size());
    }
}