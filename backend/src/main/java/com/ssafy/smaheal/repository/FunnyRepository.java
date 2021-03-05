package com.ssafy.smaheal.repository;

import com.ssafy.smaheal.model.Funny;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FunnyRepository extends JpaRepository<Funny, Integer> {
    
}
