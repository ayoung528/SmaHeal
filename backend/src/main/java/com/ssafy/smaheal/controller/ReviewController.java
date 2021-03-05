package com.ssafy.smaheal.controller;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;

import com.ssafy.smaheal.exception.ResourceNotFoundException;
import com.ssafy.smaheal.model.Review;
import com.ssafy.smaheal.model.Smile;
import com.ssafy.smaheal.repository.DonationRepository;
import com.ssafy.smaheal.repository.ReviewRepository;
import com.ssafy.smaheal.repository.SmileRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/review")
public class ReviewController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private SmileRepository smileRepository;

	@PostMapping("/regist")
	public ResponseEntity<String> registReview(@RequestBody Review review) {
		review.setLikeCnt((long) 0);
		review.setVisit((long) 0);
		reviewRepository.save(review);

		return ResponseEntity.ok(SUCCESS);
	}

	@GetMapping("/getList/{limit}")
	public List<Review> findReviewAll(@PathVariable(value = "limit") int limit) {
		List<Review> list = reviewRepository.findByAllPaging(limit);
		return list;
	}

	@GetMapping("/getListCnt")
	public long findReviewCnt() {
		long paging = reviewRepository.count();
		return paging;
	}

	@GetMapping("/get/{num}")
	public Review findReviewByNum(@PathVariable(value = "num") Long num) {
		Review review = reviewRepository.findByNum(num)
				.orElseThrow(() -> new ResourceNotFoundException("Review", "num", num));
		return review;
	}

	@PutMapping("/update/{num}")
	public ResponseEntity<String> modifyReview(@PathVariable(value = "num") Long num, @RequestBody Review review) {
		review.setNum(num);
		reviewRepository.save(review);

		return ResponseEntity.ok(SUCCESS);
	}

	@DeleteMapping("/delete/{num}")
	public ResponseEntity<String> deleteReview(@PathVariable(value = "num") Long num) {
		reviewRepository.deleteByNum(num);

		return ResponseEntity.ok(SUCCESS);
	}

	@PostMapping("/img")
	public ResponseEntity<String> uploadImgs(@RequestPart MultipartFile img) throws Exception {
		String workspacePath = System.getProperty("user.dir");
		String[] pathSplited = workspacePath.split("/");
		if (pathSplited[pathSplited.length - 1].equals("target")) {
			String newPath = pathSplited[0];
			for (int i = 1; i < pathSplited.length - 1; i++) {
				newPath += "/" + pathSplited[i];
			}
			workspacePath = newPath;
		}

		String baseDir = null;
		if (workspacePath.indexOf("backend") != -1) {
			baseDir = workspacePath + "/../frontend/public/reviewImage/";
		} else {
			baseDir = workspacePath + "/frontend/public/reviewImage/";
		}
		String originalFileName = img.getOriginalFilename();
		File dest = new File(baseDir + originalFileName);

		String newName = originalFileName;
		String realName = originalFileName.split("\\.")[0];
		String extension = originalFileName.split("\\.")[1];
		int index = 0;
		
		String hostname = InetAddress.getLocalHost().getHostName();
		
		while (dest.exists()) {
			index++;
			newName = realName + "(" + index + ")." + extension;
			
			if (hostname.substring(0, 7).equals("DESKTOP")) {// local
				dest = new File(baseDir + newName);
	        } else {// aws
	        	dest = new File("/var/lib/jenkins/workspace/Gitlab/frontend/public/reviewImage/" + newName);
	        }
		}

		img.transferTo(dest);

		return ResponseEntity.ok(newName);
	}

	@GetMapping("/getReviewToMe/{userId}")
	public Object getReviewToMe(@PathVariable String userId) {
		try {
			List<Smile> mySmile = smileRepository.findByUserId(userId);
			Set<Long> set = new HashSet<>();
			for (Smile smile : mySmile) {
				set.add(smile.getDonationid());
			}
			List<Long> donationIds = new LinkedList<>(set);
			List<Review> reviews = reviewRepository.findByDonationid(donationIds);
			List<Object> result = new LinkedList<>();
			List<Review> temp = new LinkedList<>();
			for (int i = 0; i < reviews.size(); i++) {
				if (i == 0) {
					temp = new LinkedList<>();
					temp.add(reviews.get(i));
				} else {
					if (i % 4 == 0) {
						result.add(temp);
						temp = new LinkedList<>();
						temp.add(reviews.get(i));
					} else {
						temp.add(reviews.get(i));
					}
				}
			}
			if (temp.size() != 0)
				result.add(temp);
			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/visitPlus/{num}")
	@ApiOperation("방문수 증가")
	public Object visitPlus(@PathVariable Long num) throws SQLException, IOException {
		try {
			Review review = findReviewByNum(num);
			review.setVisit(review.getVisit() + 1);
			reviewRepository.save(review);
			
			return new ResponseEntity<>(num + "visit+", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Autowired
	DonationRepository donationRepo;

	@GetMapping("/categoryCnt")
	@ApiOperation("카테고리별 개수")
	public Object categoryCnt() throws SQLException, IOException {
		try {
			int[] cnts = new int[6];
			List<Review> review = new LinkedList<>();
			review = reviewRepository.findAll();
			for (Review r : review) {
				String cat = donationRepo.findByDonationid(r.getDonationid()).getCategory();
				if(cat.equals("아동,청소년")) {
					cnts[0]++;
				} else if(cat.equals("어르신")) {
					cnts[1]++;
				} else if(cat.equals("장애인")) {
					cnts[2]++;
				} else if(cat.equals("가족,여성")) {
					cnts[3]++;
				} else if(cat.equals("다문화")) {
					cnts[4]++;
				} else if(cat.equals("기타")) {
					cnts[5]++;
				}
			}
			return new ResponseEntity<>(cnts, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/categoryList/{category}")
	@ApiOperation("카테고리별 리스트")
	public Object categoryList(@PathVariable String category) throws SQLException, IOException {
		System.out.println(category);
		try {
			List<Review> list = new LinkedList<>();
			List<Review> review = new LinkedList<>();
			review = reviewRepository.findAllByOrderByCreatedAtDesc();
			for (Review r : review) {
				String cat = donationRepo.findByDonationid(r.getDonationid()).getCategory();
				if(cat.equals(category)) {
					list.add(r);
				}
			}
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
