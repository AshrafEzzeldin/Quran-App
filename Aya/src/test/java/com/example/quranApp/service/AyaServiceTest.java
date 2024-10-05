package com.example.quranApp.service;

import com.example.quranApp.error.AyaNotFoundException;
import com.example.quranApp.model.Aya;
import com.example.quranApp.repository.AyaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AyaServiceTest {

    @Autowired
    private AyaService ayaService;
    @MockBean
    private AyaRepository ayaRepository;

    private Aya aya;

    @BeforeEach
    void setUp() {
        aya = Aya.builder()
                .ayaText("قل هو ...... أحد")
                .correctWord("الله")
                .ansPath("allah.mp3")
                .id(1L)
                .build();

        Mockito.when(ayaRepository.findById(1L)).
                thenReturn(Optional.ofNullable(aya));

    }

    @Test
    void getAyaByIdSuccessfully() throws AyaNotFoundException {

        Aya retAya = ayaService.getAyaById(1L);

        assertEquals(retAya.getCorrectWord(), "الله");
    }

    @Test
    void createAyaSuccessfully() {
        Aya initAya = Aya.builder()
                .ayaText("قل هو ...... أحد")
                .correctWord("الله")
                .ansPath("allah.mp3")
                .build();

        Mockito.when(ayaRepository.save(initAya)).
                thenReturn(aya);

        Aya retAya = ayaService.createAya(initAya);

        assertEquals(retAya.getId(), 1);

    }

}