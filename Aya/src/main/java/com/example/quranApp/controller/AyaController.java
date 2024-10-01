package com.example.quranApp.controller;

import com.example.quranApp.error.AyaNotFoundException;
import com.example.quranApp.model.Aya;
import com.example.quranApp.service.AyaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/aya")
@CrossOrigin(origins = "${frontend_url}")

public class AyaController {

    private final AyaService ayaService;

    public AyaController(AyaService ayaService) {
        this.ayaService = ayaService;
    }

    @GetMapping
    public List<Aya> getAllAyas() {
        return ayaService.getAllAyas();
    }

    @GetMapping("/{id}")
    public Aya getAyaById(@PathVariable Long id) throws AyaNotFoundException {
        return ayaService.getAyaById(id);
    }

    @PostMapping
    public Aya createAya(@Valid @RequestBody Aya aya) {
        System.out.println(aya+" "+aya.getAyaText());
        return ayaService.createAya(aya);
    }
}