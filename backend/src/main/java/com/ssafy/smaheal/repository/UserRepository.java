package com.ssafy.smaheal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.smaheal.model.MemberUser;

@Repository
public interface UserRepository extends JpaRepository<MemberUser, Long> {
    
    Optional<MemberUser> findByNum(Long num);
    
    Optional<MemberUser> findByUserId(String userId);
    
    List<MemberUser> findByNumIn(List<Long> userIds);

    Optional<MemberUser> findByNickName(String nickName);
    
    Boolean existsByUserId(String userId);
    
    Boolean existsByNickName(String nickName);
    
}
