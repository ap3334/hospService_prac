package com.jung.hospservice.repository;

import com.jung.hospservice.dto.HospitalDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends JpaRepository<HospitalDto, Long> {

    @Query(value = "SELECT * from hospitaldto where sidoCdNm =:sidoCdNm and sgguCdNm = :sgguCdNm", nativeQuery = true)
    List<HospitalDto> mFindHospital(@Param("sidoCdNm") String sidoCdNm, @Param("sgguCdNm") String sgguCdNm);


}
