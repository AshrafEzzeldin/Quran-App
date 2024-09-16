package com.example.quranApp.service;


import com.example.quranApp.model.Aya;
import com.example.quranApp.repository.AyaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AyaService {

    @Autowired
    private final AyaRepository ayaRepository;

    public AyaService(AyaRepository ayaRepository) {
        this.ayaRepository = ayaRepository;
    }

    public List<Aya> getAllAyas() {
        return ayaRepository.findAll();
    }

    public Aya getAyaById(Long id) {
        return ayaRepository.findById(id).orElseThrow(() -> new RuntimeException("Aya not found"));
    }

    public Aya createAya(Aya aya) {
        return ayaRepository.save(aya);
    }
}