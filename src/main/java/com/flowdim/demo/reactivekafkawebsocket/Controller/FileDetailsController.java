package com.flowdim.demo.reactivekafkawebsocket.Controller;

import com.flowdim.demo.reactivekafkawebsocket.Service.FileDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("files")
public class FileDetailsController {

    @Autowired
    FileDetailsService fileDetailsService;

    @RequestMapping(value = "/fileDetails", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Object> getFileDetails(@RequestParam Long minutes){
        fileDetailsService.getFileDetails(minutes);
       return new ResponseEntity<>("Success", HttpStatus.OK);
    }


}
