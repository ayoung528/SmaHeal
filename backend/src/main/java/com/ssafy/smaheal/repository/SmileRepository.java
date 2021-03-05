package com.ssafy.smaheal.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;


import com.ssafy.smaheal.model.Smile;

@Repository
public interface SmileRepository extends JpaRepository<Smile, Long> {
    List<Smile> findByOrderBySmileperDesc();
    List<Smile> findTop3ByOrderBySmileperDesc();
    List<Smile> findByCreatedate(LocalDate createdate);
    List<Smile> findByDonationid(Long donationid);
    List<Smile> findByUserId(String userId);
    List<Smile> findByUserIdOrderBySmileidDesc(String userId, Pageable page);
}