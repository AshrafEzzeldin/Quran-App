package com.example.quranApp.service;


import com.example.quranApp.error.AyaNotFoundException;
import com.example.quranApp.model.Aya;
import com.example.quranApp.repository.AyaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Aya getAyaById(Long id) throws AyaNotFoundException {
        Optional<Aya> aya=ayaRepository.findById(id);
        if(!aya.isPresent())
            throw new AyaNotFoundException("Aya not found");
        else
            return aya.get();
    }

    public Aya createAya(Aya aya) {
        return ayaRepository.save(aya);
    }
}