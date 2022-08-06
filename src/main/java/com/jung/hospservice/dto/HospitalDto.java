package com.jung.hospservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HospitalDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    // 주소
    private String addr;

    // 운영 시작 일자
    private Integer mgtStaDd;

    // pcr 가능 여부
    private String pcrPsblYn;

    // rat(신속 항원 검사) 가능 여부
    private String ratPsblYn;

    // 요양 종별 코드 (종합병원 11, 병원 21, 의원 31)
    private Integer recuClCd;

    //
    private Integer rnum;

    // 호흡기 전담 클리닉 여부
    private String rprtWorpClicFndtTgtYn;

    // 시군구명
    private String sgguCdNm;

    // 시도명
    private String sidoCdNm;

    // 전화번호
    private String telno;

    // x 좌표
    private Integer xPos;

    // 세계지구 x 좌표
    private Double xPosWgs84;

    // y 좌표
    private Integer yPos;

    // 세계지구 y 좌표
    private Double yPosWgs84;

    // 병원명
    private String yadmNm;

    // 암호화 된 요양 기호
    private String ykihoEnc;
}
