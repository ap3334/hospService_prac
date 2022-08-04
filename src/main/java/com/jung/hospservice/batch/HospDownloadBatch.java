package com.jung.hospservice.batch;

// 하루에 한 번씩 다운로드해서 DB 변경

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HospDownloadBatch {

    // cron = 초 분 시 일 월 주 (년)"
    @Scheduled(cron = "0 * * * * *", zone = "Asia/Seoul")
    public void startBatch() {
        System.out.println("excute");
    }

}

