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
public class Smile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long smileid;

    private String userId;
    private Long donationid;
    private String title;
    private String photo;
    private float smileper;
    private String comment;
    private int agreement;
    @Column(insertable = false, updatable = false)
    private LocalDate createdate;
}
