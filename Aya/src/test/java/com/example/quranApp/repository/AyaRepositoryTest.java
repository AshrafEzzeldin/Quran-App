package com.example.quranApp.repository;

import com.example.quranApp.model.Aya;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AyaRepositoryTest {

    @Autowired
    private AyaRepository ayaRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {


        Aya aya = Aya.builder()
                .ayaText("قل هو ...... أحد")
                .correctWord("الله")
                .ansPath("allah.mp3")
                .build();

        entityManager.persist(aya);
    }

    @Test
    void findByAyaTextSuccessfully() {
        Aya aya = ayaRepository.findByAyaText("قل هو ...... أحد");
        System.out.println(aya);
        assertEquals(aya.getCorrectWord(), "الله");
    }
}