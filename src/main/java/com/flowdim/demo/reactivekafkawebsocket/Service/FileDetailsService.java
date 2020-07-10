package com.flowdim.demo.reactivekafkawebsocket.Service;

import com.flowdim.demo.reactivekafkawebsocket.DTO.AgeDTO;
import com.flowdim.demo.reactivekafkawebsocket.DTO.FileDTO;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileDetailsService {
    private static long diffInMins;
    public ArrayList<FileDTO> getFileDetails(Long minutes) {
        ArrayList<FileDTO> filesList = new ArrayList<>();

        Path path = Paths.get("/Users/sravyasarraju/Downloads/TestFolder");
        try (Stream<Path> subPath = Files.walk(path)) {
            List<String> finale = subPath.filter(Files::isRegularFile).map(x -> x.toString()).collect(Collectors.toList());

            for (String x : finale) {
                try {
                    if (diffInMins <= minutes) {
                        FileTime fileTime = Files.getLastModifiedTime(Paths.get(x));
                        Path filePath = Paths.get(x);
                        System.out.println("p.getFileName() :::: " + filePath.getFileName());
                        File selectedFile = new File(x);
                        OffsetDateTime currentTime = OffsetDateTime.now();
                        System.out.println("current time ::::: " + currentTime);
                        diffInMins = Duration.between(fileTime.toInstant(), currentTime).toMinutes();
                        System.out.println("difference in minutes :::: " + diffInMins);
                        System.out.println("fileTime ::::: " + fileTime.toInstant());
                        System.out.println("directory ::: " + selectedFile.getParent());
                        System.out.println("absolute path ::: " + selectedFile.getAbsolutePath());
                        try {
                            BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
                            printBasicAttributes(attributes);
                        } catch (Exception e) {
                        }
                        filesList.add(new FileDTO(filePath.getFileName().toString(),selectedFile.getAbsolutePath(),fileTime));
                    }

                } catch (IOException e) {
                    System.err.println("Cannot get the last modified time - " + e);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        for(FileDTO fileDTO: filesList) {
            System.out.println(fileDTO);
        }
        return filesList;
    }
    private static void printBasicAttributes(BasicFileAttributes attributes) throws Exception {
        System.out.println("-- Some BasicFileAttributes --");
        System.out.printf("creationTime     = %s%n", attributes.creationTime());
        System.out.printf("lastAccessTime   = %s%n", attributes.lastAccessTime());
        System.out.printf("lastModifiedTime = %s%n", attributes.lastModifiedTime());
        System.out.printf("size             = %s%n", attributes.size());
        System.out.printf("directory        = %s%n", attributes.isDirectory());

    }

    private static void printDosAttributes(DosFileAttributes attributes) {
        System.out.println("-- Some DosFileAttributes --");
        System.out.printf("archive  = %s%n", attributes.isArchive());
        System.out.printf("readOnly = %s%n", attributes.isReadOnly());
        System.out.printf("hidden   = %s%n", attributes.isHidden());
    }

}
