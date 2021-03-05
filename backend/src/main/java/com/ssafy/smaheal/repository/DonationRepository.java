package com.ssafy.smaheal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import com.ssafy.smaheal.model.Donation;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    Donation findByDonationid(Long donationid);

    List<Donation> findByApprovalAndTempOrderByCreatedateDesc(int approval, int temp);

    List<Donation> findByApprovalAndTempOrderByCreatedateDesc(int approval, int temp, Pageable page);

    List<Donation> findByTempAndWriterOrderByCreatedateDesc(int temp, String writer, Pageable page);

    List<Donation> findByCategoryAndApprovalAndTempOrderByCreatedateDesc(String category, int approval, int temp, Pageable page);

    List<Donation> findByApprovalAndTempAndTitleContainingOrderByCreatedateDesc(int approval, int temp, String title);
    
    Donation findByTemplate(String template);
    
    List<Donation> findByWriterAndTempOrderByCreatedateDesc(String writer, int temp, Pageable page);
    List<Donation> findByWriterAndTemp(String writer, int temp);
    List<Donation> findByTempAndWriter(int temp, String writer);
}