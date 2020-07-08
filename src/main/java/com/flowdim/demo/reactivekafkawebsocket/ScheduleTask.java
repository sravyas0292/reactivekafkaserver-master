package com.flowdim.demo.reactivekafkawebsocket;

import com.flowdim.demo.reactivekafkawebsocket.DTO.AgeDTO;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduleTask {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
            "MM/dd/yyyy HH:mm:ss");

//    @Scheduled(fixedRate = 10000)
//    public void performTask() {
//
//        System.out.println("Regular task performed at "
//                + dateFormat.format(new Date()));
//
//    }
//
//    @Scheduled(initialDelay = 1000, fixedRate = 10000)
//    public void performDelayedTask() {
//
//        System.out.println("Delayed Regular task performed at "
//                + dateFormat.format(new Date()));
//
//    }

    @Scheduled(cron = "*/5 * * * * *")
    public void performTaskUsingCron() throws InterruptedException, IOException {

        System.out.println("Regular task performed using Cron at "
                + dateFormat.format(new Date()));

       FileAgeCalculator fileAge = new FileAgeCalculator();
        List<AgeDTO> dto = fileAge.getFileCountList();

        System.out.println("updated dto ::: " + dto);
//
//        int count = 20;
//        CountDownLatch latch = new CountDownLatch(count);
//        SampleProducer producer = new SampleProducer();
//        producer.sendMessage("chartTopic",dto);
//        producer.sendMessages("chartTopic", count, latch);
//        latch.await(10, TimeUnit.SECONDS);
//        producer.close();

    }
}

