package com.ssafy.smaheal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.smaheal.model.Donation;
import com.ssafy.smaheal.model.Smile;
import com.ssafy.smaheal.repository.DonationRepository;
import com.ssafy.smaheal.repository.SmileRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/template")
public class TemplateController {
	@Autowired
	private DonationRepository donationRepository;
	@Autowired
	private SmileRepository smileRepository;

	@GetMapping("/search/{template}")
	public Object getTemplate(@PathVariable(value = "template") String template) {
		try {
			Donation donation = donationRepository.findByTemplate(template);
			Long donationid = donation.getDonationid();
			List<Smile> smileList = smileRepository.findByDonationid(donationid);

			return new ResponseEntity<>(smileList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/totalCount/{template}")
	public Object getTotalCount(@PathVariable(value = "template") String template) {
		try {
			Donation donation = donationRepository.findByTemplate(template);
			Long donationid = donation.getDonationid();
			List<Smile> smileList = smileRepository.findByDonationid(donationid);

			return new ResponseEntity<>(smileList.size(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
