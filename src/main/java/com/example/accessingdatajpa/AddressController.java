package com.example.accessingdatajpa;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class AddressController {

    AddressBookRepository addressRepo;

    BuddyInfoRepository buddyRepo;

    public AddressController(AddressBookRepository addressRepo, BuddyInfoRepository buddyRepo) {
        this.addressRepo = addressRepo;
        this.buddyRepo = buddyRepo;
    }

    @GetMapping("/getadd")
    public String getAddresses(Model model){
        model.addAttribute("Addresses", addressRepo.findAll().toString());
        return "index";
    }

    @GetMapping("/getoneadd")
    public String getAddress(@RequestParam Integer id,  Model model){
        Optional<AddressBook> a1 = addressRepo.findById(id);
        if (a1.isEmpty()) return null;
        model.addAttribute("BuddyList", a1.get().getBuddyList());
        return "addresses";
    }


    @GetMapping("/getbud")
    public String getBuddies(Model model){
        model.addAttribute("Addresses", buddyRepo.findAll());
        return "addresses";
    }

    @PostMapping("/addadd")
    public String addAddressBook(Model model){
        AddressBook a = new AddressBook();
        addressRepo.save(a);
        model.addAttribute("AddressId", a.getId());
        return "index";
    }

    @PostMapping("/addbud")
    public String addBuddy(@RequestParam Integer id, @RequestParam String name, @RequestParam String phone, Model model){
        Optional<AddressBook> a = addressRepo.findById(id);
        if(a.isEmpty()) return null;
        AddressBook a1 = a.get();
        a1.addInfo(new BuddyInfo(name, phone));
        addressRepo.save(a1);
        return "index";
    }

    @DeleteMapping("/delbud")
    public String removeBuddy(@RequestParam Integer addressId, @RequestParam Integer buddyId){
        Optional<AddressBook> a1 = addressRepo.findById(addressId);
        if(a1.isEmpty()) return null;
        AddressBook a = a1.get();
        Optional<BuddyInfo> b = buddyRepo.findById(buddyId);
        if(b.isEmpty()) return null;
        a.removeInfo(b.get());
        addressRepo.save(a);
        return "index";
    }

    @DeleteMapping("/deladd")
    public String removeAddress(@RequestParam Integer id){
        Optional<AddressBook> a1 = addressRepo.findById(id);
        if(a1.isEmpty())return null;
        addressRepo.deleteById(id);
        return "index";
    }
}
