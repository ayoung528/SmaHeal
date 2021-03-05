package com.ssafy.smaheal.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
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
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long donationid;

    private String title;
    private String writer;
    private LocalDate sdate;
    private LocalDate edate;
    private String category;
    private int nowcnt;
    private int maxcnt;
    private String receiver;
    private String address;
    private String content;
    private String img;
    private int approval;
    private int temp;
    @Column(insertable = false, updatable = false)
    private LocalDateTime createdate;
    private String template;
}
