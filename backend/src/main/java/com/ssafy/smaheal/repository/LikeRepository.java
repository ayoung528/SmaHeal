package com.ssafy.smaheal.repository;

import com.ssafy.smaheal.model.Reviewlike;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Reviewlike, Long> {
    Reviewlike findByNicknameAndNum(String nickname, Long num);
}
