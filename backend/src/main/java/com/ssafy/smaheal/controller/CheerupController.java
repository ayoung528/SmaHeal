package com.ssafy.smaheal.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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

import com.ssafy.smaheal.exception.ResourceNotFoundException;
import com.ssafy.smaheal.model.Cheerup;
import com.ssafy.smaheal.model.MemberUser;
import com.ssafy.smaheal.repository.CheerupRepository;
import com.ssafy.smaheal.repository.UserRepository;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/cheerup")
public class CheerupController {
    @Autowired
    private CheerupRepository cheerupRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/getList/{donationid}/{limit}")
    @ApiOperation(value = "기부요청 디테일 응원메세지 리스트")
    public Object getCheerUp(@PathVariable Long donationid, @PathVariable(value="limit") int limit) throws SQLException, IOException {
        try {
            List<Cheerup> cheerupList = cheerupRepository.findByDonationid(donationid,PageRequest.of(limit, 10));
            
            // userid를 닉네임으로 바꿔서 받기 위함
            for (Cheerup cheerup : cheerupList) {
				String userId = cheerup.getUserId();
				MemberUser user = userRepository.findByUserId(userId)
		                .orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));
				String nickName = user.getNickName();
				cheerup.setUserId(nickName);
			}
            
            if(cheerupList != null) {
                return new ResponseEntity<>(cheerupList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getCount/{donationid}")
    @ApiOperation(value = "기부요청 디테일 응원메세지 총 개수")
    public Object getCheerUpCount(@PathVariable Long donationid) throws SQLException, IOException {
        try {
        	List<Cheerup> list = cheerupRepository.findByDonationid(donationid); 
            return list;
        } catch (Exception e) {
            return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/writeMsg")
    @ApiOperation(value = "기부요청 디테일 응원메세지 작성")
    public Object writeMsg(@RequestBody Cheerup request) throws SQLException, IOException {
        try {
            Cheerup msg = new Cheerup();
            msg.setDonationid(request.getDonationid());
            msg.setMsg(request.getMsg());
            msg.setUserId(request.getUserId());
            cheerupRepository.save(msg);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteMsg/{cheerupid}")
    @ApiOperation(value = "기부요청 디테일 응원메세지 삭제")
    public Object deleteMsg(@PathVariable Long cheerupid) throws SQLException, IOException {
        try {
            if(cheerupRepository.findById(cheerupid) != null) {
                cheerupRepository.deleteById(cheerupid);
                return new ResponseEntity<>("delete succ", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("delete fail", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/modifyMsg")
    @ApiOperation(value = "기부요청 디테일 응원메세지 수정")
    public Object modifyMsg(@RequestBody Cheerup request) throws SQLException, IOException {
        try {
            Cheerup cheerup = new Cheerup();
            cheerup = cheerupRepository.findByCheerupid(request.getCheerupid());
            if(cheerup != null) {
                cheerup.setMsg(request.getMsg());
                cheerupRepository.save(cheerup);
                return new ResponseEntity<>(cheerup, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(cheerup, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
