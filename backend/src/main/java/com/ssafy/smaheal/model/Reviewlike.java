package com.ssafy.smaheal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reviewlike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeid;
    private Long num;
    private String nickname;
}
