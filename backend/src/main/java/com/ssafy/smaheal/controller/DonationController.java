package com.ssafy.smaheal.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.smaheal.model.Donation;
import com.ssafy.smaheal.model.Smile;
import com.ssafy.smaheal.repository.DonationRepository;
import com.ssafy.smaheal.repository.SmileRepository;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/donation")
public class DonationController {
	@Autowired
	private DonationRepository donationRepository;

	@Autowired
	private SmileRepository smileRepository;

	@GetMapping("/getList/{page}")
	@ApiOperation(value = "기부 게시판 초기 리스트(승인)")
	public Object getList(@PathVariable int page) throws SQLException, IOException {
		try {
			List<List<Donation>> categoryList = new LinkedList<>();
			categoryList
					.add(donationRepository.findByApprovalAndTempOrderByCreatedateDesc(1, 0, PageRequest.of(page, 8)));
			categoryList.add(donationRepository.findByCategoryAndApprovalAndTempOrderByCreatedateDesc("아동,청소년", 1, 0,
					PageRequest.of(page, 8)));
			categoryList.add(donationRepository.findByCategoryAndApprovalAndTempOrderByCreatedateDesc("어르신", 1, 0,
					PageRequest.of(page, 8)));
			categoryList.add(donationRepository.findByCategoryAndApprovalAndTempOrderByCreatedateDesc("장애인", 1, 0,
					PageRequest.of(page, 8)));
			categoryList.add(donationRepository.findByCategoryAndApprovalAndTempOrderByCreatedateDesc("가족,여성", 1, 0,
					PageRequest.of(page, 8)));
			categoryList.add(donationRepository.findByCategoryAndApprovalAndTempOrderByCreatedateDesc("다문화", 1, 0,
					PageRequest.of(page, 8)));
			categoryList.add(donationRepository.findByCategoryAndApprovalAndTempOrderByCreatedateDesc("기타", 1, 0,
					PageRequest.of(page, 8)));
			return new ResponseEntity<>(categoryList, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getCategoryList/{category}/{page}")
	@ApiOperation(value = "기부 게시판 카테고리 별 리스트(승인)")
	public Object getCategoryDonationList(@PathVariable int category, @PathVariable int page)
			throws SQLException, IOException {
		try {
			List<Donation> list = new LinkedList<>();
			if (category == 0)
				list = donationRepository.findByApprovalAndTempOrderByCreatedateDesc(1, 0, PageRequest.of(page, 8));
			else if (category == 1)
				list = donationRepository.findByCategoryAndApprovalAndTempOrderByCreatedateDesc("아동,청소년", 1, 0,
						PageRequest.of(page, 8));
			else if (category == 2)
				list = donationRepository.findByCategoryAndApprovalAndTempOrderByCreatedateDesc("어르신", 1, 0,
						PageRequest.of(page, 8));
			else if (category == 3)
				list = donationRepository.findByCategoryAndApprovalAndTempOrderByCreatedateDesc("장애인", 1, 0,
						PageRequest.of(page, 8));
			else if (category == 4)
				list = donationRepository.findByCategoryAndApprovalAndTempOrderByCreatedateDesc("가족,여성", 1, 0,
						PageRequest.of(page, 8));
			else if (category == 5)
				list = donationRepository.findByCategoryAndApprovalAndTempOrderByCreatedateDesc("다문화", 1, 0,
						PageRequest.of(page, 8));
			else if (category == 6)
				list = donationRepository.findByCategoryAndApprovalAndTempOrderByCreatedateDesc("기타", 1, 0,
						PageRequest.of(page, 8));
			return new ResponseEntity<>(list, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getWaitList/{page}")
	@ApiOperation(value = "기부 게시판 리스트(대기)")
	public Object getWaitDonationList(@PathVariable int page) throws SQLException, IOException {
		try {
			List<Donation> waitList = donationRepository.findByApprovalAndTempOrderByCreatedateDesc(0, 0,
					PageRequest.of(page, 8));
			if (waitList != null) {
				return new ResponseEntity<>(waitList, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getTempList/{writer}/{page}")
	@ApiOperation(value = "기부 게시판 임시저장 리스트")
	public Object getTempDonationList(@PathVariable String writer, @PathVariable int page)
			throws SQLException, IOException {
		try {
			List<Donation> tempDonationList = donationRepository.findByTempAndWriterOrderByCreatedateDesc(1, writer,
					PageRequest.of(page, 8));
			if (tempDonationList != null) {
				return new ResponseEntity<>(tempDonationList, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/regist")
	@ApiOperation(value = "기부 게시물 등록 요청")
	public Object registDonation(@RequestBody Donation request) throws SQLException, IOException {
		try {
			Donation donation = new Donation();
			donation.setTitle(request.getTitle());
			donation.setWriter(request.getWriter());
			donation.setEdate(request.getEdate());
			donation.setCategory(request.getCategory());
			donation.setMaxcnt(request.getMaxcnt());
			donation.setReceiver(request.getReceiver());
			donation.setAddress(request.getAddress());
			donation.setContent(request.getContent());
			donation.setImg(request.getImg());
			donation.setApproval(0); // 아직 승인되지 않은 게시물
			donation.setTemp(0); // 임시저장이 아닌 게시물
			donationRepository.save(donation);
			return new ResponseEntity<>(donation, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/registTemp")
	@ApiOperation(value = "기부 게시물 임시저장")
	public Object registTempDonation(@RequestBody Donation request) throws SQLException, IOException {
		try {
			Donation donation = new Donation();
			donation.setTitle(request.getTitle());
			donation.setWriter(request.getWriter());
			donation.setSdate(request.getSdate());
			donation.setEdate(request.getEdate());
			donation.setCategory(request.getCategory());
			donation.setMaxcnt(request.getMaxcnt());
			donation.setReceiver(request.getReceiver());
			donation.setAddress(request.getAddress());
			donation.setContent(request.getContent());
			donation.setImg(request.getImg());
			donation.setApproval(0); // 아직 승인되지 않은 게시물
			donation.setTemp(1); // 임시저장인 게시물
			donationRepository.save(donation);
			return new ResponseEntity<>(donation, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{donationid}")
	@ApiOperation(value = "기부 게시물 삭제/거절")
	public Object deleteDonation(@PathVariable Long donationid) throws SQLException, IOException {
		try {
			Donation donation = donationRepository.findByDonationid(donationid);
			if (donation != null) {
				donationRepository.delete(donation);
				return new ResponseEntity<>(donation, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update")
	@ApiOperation(value = "기부 게시물 수정")
	public Object updateDonation(@RequestBody Donation request) throws SQLException, IOException {
		System.out.println("@");
		System.out.println(request);
		System.out.println("@");
		try {
			Donation donation = donationRepository.findByDonationid(request.getDonationid());
			if (donation != null) {
				donation.setTitle(request.getTitle());
				donation.setWriter(request.getWriter());
				donation.setSdate(request.getSdate());
				donation.setEdate(request.getEdate());
				donation.setCategory(request.getCategory());
				donation.setMaxcnt(request.getMaxcnt());
				donation.setReceiver(request.getReceiver());
				donation.setAddress(request.getAddress());
				donation.setContent(request.getContent());
				donation.setImg(request.getImg());
				donation.setApproval(0); // 아직 승인되지 않은 게시물
				donation.setTemp(1); // 임시저장인 게시물
				donationRepository.save(donation);
				return new ResponseEntity<>(donation, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(donation, HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/approve/{donationid}")
	@ApiOperation(value = "기부 게시물 승인")
	public Object approveDonation(@PathVariable Long donationid) throws SQLException, IOException {
		try {
			Donation donation = donationRepository.findByDonationid(donationid);
			if (donation != null) {
				donation.setApproval(1);
				donationRepository.save(donation);
				return new ResponseEntity<>(donation, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(donation, HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/detail/{donationid}")
	@ApiOperation(value = "기부 게시물 상세정보")
	public Object detailDonation(@PathVariable Long donationid) throws SQLException, IOException {
		try {
			Donation donation = donationRepository.findByDonationid(donationid);
			if (donation != null) {
				return new ResponseEntity<>(donation, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/searchList/{search}")
	@ApiOperation(value = "기부 게시판 검색(승인)")
	public Object searchList(@PathVariable String search) throws SQLException, IOException {
		try {
			List<Donation> categoryList = donationRepository
					.findByApprovalAndTempAndTitleContainingOrderByCreatedateDesc(1, 0, search);

			return new ResponseEntity<>(categoryList, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/mypage/getList/{writer}/{page}")
	@ApiOperation(value = "마이페이지에서 기부요청 목록")
	public Object mypageGetList(@PathVariable(value = "writer") String writer, @PathVariable(value = "page") int page) {
		try {
			List<Donation> list = donationRepository.findByWriterAndTempOrderByCreatedateDesc(writer, 0,
					PageRequest.of(page, 8));
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/mypage/getListCnt/{writer}")
	@ApiOperation(value = "마이페이지에서 기부요청 목록")
	public Object mypageGetListCnt(@PathVariable(value = "writer") String writer) {
		try {
			List<Donation> list = donationRepository.findByWriterAndTemp(writer, 0);
			return new ResponseEntity<>(list.size(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getTempCnt/{writer}")
	@ApiOperation(value = "기부 게시판 임시저장 개수")
	public Object getTempCnt(@PathVariable String writer) throws SQLException, IOException {
		try {
			List<Donation> tempDonationList = donationRepository.findByTempAndWriter(1, writer);
			if (tempDonationList != null) {
				return new ResponseEntity<>(tempDonationList.size(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getWaitListCnt")
	@ApiOperation("대기 목록 카운트")
	public Object getWaitListCnt() throws SQLException, IOException {
		try {
			int count = donationRepository.findByApprovalAndTempOrderByCreatedateDesc(0, 0).size();
			return new ResponseEntity<>(count, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getTempDetail/{donationid}")
	@ApiOperation("임시저장 디테일")
	public Object getTempDetail(@PathVariable Long donationid) throws SQLException, IOException {
		try {
			Donation temp = donationRepository.findByDonationid(donationid);
			return new ResponseEntity<>(temp, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/template/{donationid}")
	@ApiOperation(value = "마감 후 템플릿 번호 부여")
	public Object endListAndTemplate(@PathVariable Long donationid) throws SQLException, IOException {
		try {
			Donation donation = donationRepository.findByDonationid(donationid);
			if (donation != null) {
				donation.setApproval(2);
				String uid = UUID.randomUUID().toString();
				donation.setTemplate(uid);
				donationRepository.save(donation);
				return new ResponseEntity<>(donation, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(donation, HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/isDonate/{donationid}/{userId}")
	@ApiOperation(value = "같은 게시물 기부 중복 체크")
	public Object isDonate(@PathVariable(value="donationid") Long donationid, @PathVariable(value="userId") String userId) throws SQLException, IOException {
		try {
			List<Smile> list = smileRepository.findByDonationid(donationid);

			if (list != null) {
				for (Smile smile : list) {
					String user = smile.getUserId();
					if (user.equals(userId)) {
						return new ResponseEntity<>(HttpStatus.OK, HttpStatus.OK);
					}
				}
				return new ResponseEntity<>(HttpStatus.FOUND, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/isDonateSelf/{donationid}/{nickname}")
	@ApiOperation(value = "같은 게시물 기부 중복 체크")
	public Object isDonateSelf(@PathVariable(value="donationid") Long donationid, @PathVariable(value="nickname") String nickname) throws SQLException, IOException {
		try {
			Donation donation = donationRepository.findByDonationid(donationid);

			if (donation.getWriter().equals(nickname)) {
				return new ResponseEntity<>(HttpStatus.OK, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.FOUND, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
