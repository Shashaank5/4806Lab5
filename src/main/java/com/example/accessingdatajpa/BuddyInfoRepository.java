package com.example.accessingdatajpa;

import org.springframework.data.repository.CrudRepository;

public interface BuddyInfoRepository extends CrudRepository<BuddyInfo, Integer> {

    BuddyInfo findByName(String name);
}