package com.ssafy.smaheal.controller;

import java.io.IOException;
import java.sql.SQLException;

import com.ssafy.smaheal.model.Reviewlike;
import com.ssafy.smaheal.repository.LikeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/like")
public class LikeController {
    
    @Autowired
    LikeRepository likeRepository;

    @GetMapping("/getLike/{nickname}/{num}")
    @ApiOperation("사용자 좋아요 목록")
    public Object getLike(@PathVariable String nickname, @PathVariable Long num) throws SQLException, IOException {
        try {
            Reviewlike like = likeRepository.findByNicknameAndNum(nickname, num);
            return new ResponseEntity<>(like, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/pushLike")
    @ApiOperation("좋아요 등록")
    public Object pushLike(@RequestBody Reviewlike request) throws SQLException, IOException {
        try {
            Reviewlike r = new Reviewlike();
            r.setNickname(request.getNickname());
            r.setNum(request.getNum());
            likeRepository.save(r);
            return new ResponseEntity<>(r, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteLike/{nickname}/{num}")
    @ApiOperation("좋아요 취소")
    public Object deleteLike(@PathVariable String nickname, @PathVariable Long num) throws SQLException, IOException {
        try {
            Reviewlike r = likeRepository.findByNicknameAndNum(nickname, num);
            likeRepository.delete(r);
            return new ResponseEntity<>("좋아요 취소", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
