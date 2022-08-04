package com.jung.hospservice.batch;

import com.jung.hospservice.dto.HospitalDto;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class HospDownloadBatchTest {

    @Test
    public void 공공데이터_다운로드_테스트() {
        RestTemplate rt = new RestTemplate();
        // 테스트시에는 인증키 인코딩 안해주어도 됨 + PKIX 오류 발생으로 인해 임시적으로 https -> http로 수정
        String url = "http://apis.data.go.kr/B551182/rprtHospService/getRprtHospService?serviceKey=/7bUQRDAVeOz8OUi2pk6QLOFXx/gJTQMKFoym8SP18azscytoTJqYPSPj7TpDNy7gRyJNRIaqR4l0tp9vgPuFw==&pageNo=1&numOfRows=10&_type=json";
        ResponseDto responseDto = rt.getForObject(url, ResponseDto.class);
        System.out.println("responseDto = " + responseDto);

        List<Item> hospitals = responseDto.getResponse().getBody().getItems().getItem();
        for (Item item : hospitals) {
            System.out.println(item.getYadmNm());
            System.out.println(item.getPcrPsblYn());
        }

    }

    @Test
    public void 공공데이터_다운로드_전체_담기_테스트() {

        List<HospitalDto> all = new ArrayList<>();

        RestTemplate rt = new RestTemplate();

        // 초기 numOfRows의 값이 1이면 컬렉션이 아니라 파싱이 안돼 오류가 발생함
        String url = "http://apis.data.go.kr/B551182/rprtHospService/getRprtHospService?serviceKey=/7bUQRDAVeOz8OUi2pk6QLOFXx/gJTQMKFoym8SP18azscytoTJqYPSPj7TpDNy7gRyJNRIaqR4l0tp9vgPuFw==&pageNo=1&numOfRows=2&_type=json";
        ResponseDto responseDto = rt.getForObject(url, ResponseDto.class);

        int totalCnt = responseDto.getResponse().getBody().getTotalCount();
        int page = 1;

        String newUrl = "http://apis.data.go.kr/B551182/rprtHospService/getRprtHospService?serviceKey=/7bUQRDAVeOz8OUi2pk6QLOFXx/gJTQMKFoym8SP18azscytoTJqYPSPj7TpDNy7gRyJNRIaqR4l0tp9vgPuFw==&pageNo=1&numOfRows="
                + totalCnt + "&_type=json";

        ResponseDto newResponseDto = rt.getForObject(newUrl, ResponseDto.class);

        List<Item> hospitals = newResponseDto.getResponse().getBody().getItems().getItem();

        assertThat(hospitals.size()).isEqualTo(totalCnt);

    }

}