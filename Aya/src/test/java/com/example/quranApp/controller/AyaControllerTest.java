package com.example.quranApp.controller;

import com.example.quranApp.model.Aya;
import com.example.quranApp.service.AyaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AyaController.class)
class AyaControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AyaService ayaService;

    private Aya aya;

    @BeforeEach
    void setUp() {
        aya = Aya.builder()
                .ayaText("قل هو ...... أحد")
                .correctWord("الله")
                .ansPath("allah.mp3")
                .id(1L)
                .build();
    }

    @Test
    void getAllAyas() throws Exception {

        Mockito.when(ayaService.getAllAyas())
                .thenReturn(Collections.singletonList(aya));

        mockMvc.perform(get("/api/aya")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].ayaText")
                        .value(aya.getAyaText())
                );

    }


    @Test
    void createAya() throws Exception {
        Aya inputAya = Aya.builder()
                .ayaText("قل هو ...... أحد")
                .correctWord("الله")
                .ansPath("allah.mp3")
                .build();

        Mockito.when(ayaService.createAya(inputAya))
                .thenReturn(aya);


        mockMvc.perform(post("/api/aya")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "        \"ayaText\": \"قل هو ...... أحد\",\n" +
                                "    \"correctWord\": \"الله\",\n" +
                                "    \"ansPath\": \"allah.mp3\"\n" +
                                "}"))
                .andExpect(status().isOk())
        ;


    }
}