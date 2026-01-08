package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Rewards;


@Repository 
public interface RewardsRepository extends JpaRepository<Rewards, Integer> {
    
}
