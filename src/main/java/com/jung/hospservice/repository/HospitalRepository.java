package com.jung.hospservice.repository;

import com.jung.hospservice.dto.HospitalDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<HospitalDto, Long> {




}
