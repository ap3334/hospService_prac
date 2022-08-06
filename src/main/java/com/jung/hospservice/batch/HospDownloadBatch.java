package com.jung.hospservice.batch;

// 하루에 한 번씩 다운로드해서 DB 변경

import com.jung.hospservice.dto.HospitalDto;
import com.jung.hospservice.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class HospDownloadBatch {

    private final HospitalRepository hospitalRepository;

    // cron = 초 분 시 일 월 주 (년)"
    @Scheduled(cron = "0 13 * * * *", zone = "Asia/Seoul")
    public void startBatch() {

        RestTemplate rt = new RestTemplate();

        // 초기 numOfRows의 값이 1이면 컬렉션이 아니라 파싱이 안돼 오류가 발생함
        String url = "http://apis.data.go.kr/B551182/rprtHospService/getRprtHospService?serviceKey=/7bUQRDAVeOz8OUi2pk6QLOFXx/gJTQMKFoym8SP18azscytoTJqYPSPj7TpDNy7gRyJNRIaqR4l0tp9vgPuFw==&pageNo=1&numOfRows=2&_type=json";
        ResponseDto responseDto = rt.getForObject(url, ResponseDto.class);

        int totalCnt = responseDto.getResponse().getBody().getTotalCount();

        String newUrl = "http://apis.data.go.kr/B551182/rprtHospService/getRprtHospService?serviceKey=/7bUQRDAVeOz8OUi2pk6QLOFXx/gJTQMKFoym8SP18azscytoTJqYPSPj7TpDNy7gRyJNRIaqR4l0tp9vgPuFw==&pageNo=1&numOfRows="
                + totalCnt + "&_type=json";

        ResponseDto newResponseDto = rt.getForObject(newUrl, ResponseDto.class);

        List<Item> items = newResponseDto.getResponse().getBody().getItems().getItem();

        List<HospitalDto> hospitals = items.stream().map((e) -> {
            return HospitalDto.builder()
                    .addr(e.getAddr())
                    .mgtStaDd(e.getMgtStaDd())
                    .pcrPsblYn(e.getPcrPsblYn())
                    .ratPsblYn(e.getRatPsblYn())
                    .recuClCd(e.getRecuClCd())
                    .sgguCdNm(e.getSgguCdNm())
                    .sidoCdNm(e.getSidoCdNm())
                    .xPos(e.getXPos())
                    .yPos(e.getYPos())
                    .xPosWgs84(e.getXPosWgs84())
                    .yPosWgs84(e.getYPosWgs84())
                    .yadmNm(e.getYadmNm())
                    .ykihoEnc(e.getYkihoEnc())
                    .build();
        }).collect(Collectors.toList());

        hospitalRepository.deleteAll();

        hospitalRepository.saveAll(hospitals);
    }

}

