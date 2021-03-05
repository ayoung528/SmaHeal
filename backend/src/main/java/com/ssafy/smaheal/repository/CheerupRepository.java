package com.ssafy.smaheal.repository;

import java.util.List;

import com.ssafy.smaheal.model.Cheerup;
import com.ssafy.smaheal.model.Notices;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CheerupRepository extends JpaRepository<Cheerup, Long> {
	List<Cheerup> findByDonationid(Long donationid, Pageable page);

	Cheerup findByCheerupid(Long cheerupid);

	List<Cheerup> findByDonationid(Long donationid);
}
