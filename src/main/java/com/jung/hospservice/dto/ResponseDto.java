package com.jung.hospservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
class Body {

    private Items items;
    private Integer numOfRows;
    private Integer pageNo;
    private Integer totalCount;

}

@Data
class Header {

    private String resultCode;
    private String resultMsg;

}

@Data
class Item {

    private String addr;
    private Integer mgtStaDd;
    private String pcrPsblYn;
    private String ratPsblYn;
    private Integer recuClCd;
    private Integer rnum;
    private String rprtWorpClicFndtTgtYn;
    private String sgguCdNm;
    private String sidoCdNm;
    private String telno;
    private Integer xPos;
    private Double xPosWgs84;
    private Integer yPos;
    private Double yPosWgs84;
    private String yadmNm;
    private String ykihoEnc;

}

@Data
class Items {

    private List<Item> item = null;

}

@Data
class Response {

    private Header header;
    private Body body;

}

@Data
public class ResponseDto {

    private Response response;

}