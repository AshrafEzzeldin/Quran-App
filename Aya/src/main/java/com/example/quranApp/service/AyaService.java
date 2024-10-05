package com.example.quranApp.service;

import com.example.quranApp.error.AyaNotFoundException;
import com.example.quranApp.model.Aya;

import java.util.List;

public interface AyaService {


    public List<Aya> getAllAyas();

    public Aya getAyaById(Long id) throws AyaNotFoundException;
    public Aya createAya(Aya aya);

}
