package com.example.quranApp.repository;

import com.example.quranApp.model.Aya;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AyaRepository extends JpaRepository<Aya, Long> {
    public  Aya findByAyaText(String ayaText);
}

