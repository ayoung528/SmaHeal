package com.ssafy.smaheal.model;

import java.time.LocalDate;

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
public class Cheerup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cheerupid;
    private Long donationid;
    private String msg;
    private String userId;
    @Column(insertable = false, updatable = false)
    private LocalDate createdate;
}
