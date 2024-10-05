package com.example.quranApp.service;


import com.example.quranApp.error.AyaNotFoundException;
import com.example.quranApp.model.Aya;
import com.example.quranApp.repository.AyaRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AyaServiceImpl implements AyaService {

    @Autowired
    private  AyaRepository ayaRepository;



    public List<Aya> getAllAyas() {
        return ayaRepository.findAll();
    }

    public Aya getAyaById(Long id) throws AyaNotFoundException {
        Optional<Aya> aya=ayaRepository.findById(id);
        if(aya.isEmpty())
            throw new AyaNotFoundException("Aya not found");
        else
            return aya.get();
    }

    public Aya createAya(Aya aya) {
        return ayaRepository.save(aya);
    }
}