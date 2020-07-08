package com.flowdim.demo.reactivekafkawebsocket;

import com.flowdim.demo.reactivekafkawebsocket.DTO.AgeDTO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileAgeCalculator {
    private static long diffInMins;
    public ArrayList<AgeDTO> getFileCountList() {
        ArrayList<AgeDTO> finalList = new ArrayList<>();

        Path path = Paths.get("/Users/abhishekmyadam/Downloads/Emerging Technology");
        try (Stream<Path> subPath = Files.walk(path)) {
            List<String> finale = subPath.filter(Files::isRegularFile).map(x -> x.toString()).collect(Collectors.toList());
            AtomicInteger countThirty = new AtomicInteger();
            AtomicInteger countSixty = new AtomicInteger();
            AtomicInteger countNinty = new AtomicInteger();
            AtomicInteger countHundred = new AtomicInteger();
            AtomicInteger other = new AtomicInteger();

            for (String x : finale) {
                try {
                    FileTime fileTime = Files.getLastModifiedTime(Paths.get(x));
                    System.out.println("fileTime ::::: " + fileTime.toInstant());

                    OffsetDateTime currentTime = OffsetDateTime.now();
                    System.out.println("current time ::::: " + currentTime);

                    diffInMins = Duration.between(fileTime.toInstant(), currentTime).toMinutes();
                    System.out.println("difference in minutes :::: " + diffInMins);
                    if (diffInMins <= 30) {
                        countThirty.getAndIncrement();
                        System.out.println("differ 30  :::::: " + countThirty);
                    } else if (diffInMins <= 60) {
                        countSixty.getAndIncrement();
                        System.out.println("differ 60 :::::: " + countSixty);
                    } else if (diffInMins <= 90) {
                        countNinty.getAndIncrement();
                        System.out.println("differ 90 :::::: " + countNinty);
                    } else if (diffInMins < 120) {
                        countHundred.getAndIncrement();
                        System.out.println("differ 120 :::::: " + countHundred);
                    } else if (diffInMins > 120) {
                        other.getAndIncrement();
                        System.out.println("differ > 120 :::::::: " + other);
                    }

                } catch (IOException e) {
                    System.err.println("Cannot get the last modified time - " + e);
                }
            }
            finalList.add(new AgeDTO(1, countThirty.get()));
            finalList.add(new AgeDTO(2, countSixty.get()));
            finalList.add(new AgeDTO(3, countNinty.get()));
            finalList.add(new AgeDTO(4, countHundred.get()));
            finalList.add(new AgeDTO(5, other.get()));

//            for(AgeDTO student: finalList) {
//                System.out.println(student);
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        for(AgeDTO student: finalList) {
            System.out.println(student);
        }
        return finalList;
    }
}
