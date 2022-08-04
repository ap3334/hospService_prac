package com.jung.hospservice.batch;

import com.jung.hospservice.dto.ResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class HospDownloadBatchTest {

    @Test
    public void 공공데이터_다운로드_테스트() {
        RestTemplate rt = new RestTemplate();
        // 테스트시에는 인증키 인코딩 안해주어도 됨 + PKIX 오류 발생으로 인해 임시적으로 https -> http로 수정
        String url = "http://apis.data.go.kr/B551182/rprtHospService/getRprtHospService?serviceKey=/7bUQRDAVeOz8OUi2pk6QLOFXx/gJTQMKFoym8SP18azscytoTJqYPSPj7TpDNy7gRyJNRIaqR4l0tp9vgPuFw==&pageNo=1&numOfRows=10&_type=json";
        ResponseDto responseDto = rt.getForObject(url, ResponseDto.class);
        System.out.println("responseDto = " + responseDto);
    }

}