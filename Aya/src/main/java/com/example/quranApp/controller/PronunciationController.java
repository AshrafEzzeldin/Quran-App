package com.example.quranApp.controller;


import com.example.quranApp.service.PronunciationService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/pronunciation")
@CrossOrigin(origins = "${frontend_url}")
public class PronunciationController {

    private final PronunciationService pronunciationService;

    public PronunciationController(PronunciationService pronunciationService) {
        this.pronunciationService = pronunciationService;
    }

    // path
//    @PostMapping("/check")
//    public String checkPronunciation(@RequestParam("file") String audioFile) throws IOException, InterruptedException {
////        byte[] audioBytes = audioFile.getBytes();
//        System.out.println(audioFile);
//        byte[] audioBytes= Files.readAllBytes(Paths.get(audioFile));
//        System.out.println("File Size: " + audioBytes.length + " bytes");
//
//        return pronunciationService.checkPronunciation(audioBytes);
//    }


    @PostMapping("/check")
    public String checkPronunciation(@RequestParam("file") MultipartFile audioFile) throws IOException, InterruptedException {
        byte[] audioBytes = audioFile.getBytes();

        return pronunciationService.checkPronunciation(audioBytes);
    }

}