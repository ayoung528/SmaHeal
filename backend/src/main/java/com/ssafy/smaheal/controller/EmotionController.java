package com.ssafy.smaheal.controller;

import java.io.ByteArrayOutputStream;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/emotion")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmotionController {

	private static final String SUCCESS = "success";
	
	@GetMapping("/video")
	public ResponseEntity<String> execVideo() {
		
		System.out.println("Python Call");
        String[] command = new String[4];
        command[0] = "python";
        command[1] = "C:\\AI108\\s03p22b108\\backend\\test.py";
        command[2] = "10";
        command[3] = "20";
        
        try {
        	
        	CommandLine commandLine = CommandLine.parse(command[0]);
            for (int i = 1, n = command.length; i < n; i++) {
                commandLine.addArgument(command[i]);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(outputStream);
            DefaultExecutor executor = new DefaultExecutor();
            executor.setStreamHandler(pumpStreamHandler);
            int result = executor.execute(commandLine);
            System.out.println("result: " + result);
            System.out.println("output: " + outputStream.toString());
            
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
            
        } catch (Exception e) {
            e.printStackTrace();
            
            return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
        }
        
	}

}
