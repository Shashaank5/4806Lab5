package com.example.accessingdatajpa;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class testingWebApp {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AddressBookRepository repo;
    @Autowired
    private BuddyInfoRepository buddyRepo;

    @Test
    public void shouldReturnAddress() throws Exception {
        this.mockMvc.perform(get("/")).andExpect(content().string(containsString("hello")));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getAllAddressBooks() throws Exception {
        AddressBook a1 = new AddressBook();
        BuddyInfo b1 = new BuddyInfo("Shashaank","613415");
        a1.addInfo(b1);
        repo.save(a1);
        this.mockMvc.perform(get("/getadd")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @Order(1)
    public void shouldGetOneAddressBook() throws Exception {
        AddressBook a1 = new AddressBook();
        BuddyInfo b1 = new BuddyInfo("Shashaank","613415");
        a1.addInfo(b1);
        repo.save(a1);
        this.mockMvc.perform(get("/getoneadd?id=" + a1.getId())).andDo(print()).andExpect(content().string(containsString("[BuddyInfo{id=1, name=&#39;Shashaank&#39;, phone=&#39;613415&#39;}]")));
    }


    @Test
    public void getBuddys() throws Exception {
        AddressBook a1 = new AddressBook();
        BuddyInfo b1 = new BuddyInfo("Shashaank","613415");
        a1.addInfo(b1);
        repo.save(a1);
        this.mockMvc.perform(get("/getbud")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void addAddressBook() throws Exception {
        AddressBook a1 = new AddressBook();
        BuddyInfo b1 = new BuddyInfo("Shashaank","613415");
        a1.addInfo(b1);
        String body = asJsonString(a1);
        this.mockMvc.perform(post("/addadd")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void addBuddy() throws Exception {
        AddressBook a1 = new AddressBook();
        BuddyInfo b1 = new BuddyInfo(1, "Shashaank","613415");
        repo.save(a1);
        String body = asJsonString(b1);
        this.mockMvc.perform(post("/addbud")
                .content(asJsonString(b1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void deleteBuddy() throws Exception {
        AddressBook a1 = new AddressBook();
        a1.setId(1);
        BuddyInfo b1 = new BuddyInfo(1, "Shashaank","613415");
        repo.save(a1);
        buddyRepo.save(b1);
        String body = asJsonString(b1);
        this.mockMvc.perform(delete("/delbud?addressId=1&buddyId=1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
    }
}
