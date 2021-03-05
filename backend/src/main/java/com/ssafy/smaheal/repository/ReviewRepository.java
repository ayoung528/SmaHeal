package com.ssafy.smaheal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.smaheal.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	@Query(nativeQuery = true, value = "select * from review order by num desc limit :limit, 8")
	List<Review> findByAllPaging(@Param("limit") int limit);
	
	long count();
	
	Optional<Review> findByNum(Long num);
	
	@Transactional
    @Modifying
	void deleteByNum(Long num);

	@Query(nativeQuery = true, value = "SELECT * FROM review as R WHERE R.donationid IN (:donationIds)")
	List<Review> findByDonationid(@Param("donationIds") List<Long> donationIds);
	
	List<Review> findAllByOrderByCreatedAtDesc();
}
