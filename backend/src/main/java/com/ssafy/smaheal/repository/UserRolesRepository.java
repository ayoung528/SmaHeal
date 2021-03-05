package com.ssafy.smaheal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.smaheal.model.UserRoles;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {
	UserRoles findByUserNum(Long userNum);
}
