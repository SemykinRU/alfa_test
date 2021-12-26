package ru.semykin.alfa_test.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.semykin.alfa_test.service.GetUrlFromGiphyService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.semykin.alfa_test.util.ApplicationConstants.TEST_UE;

@WebMvcTest(CurrencyController.class)
class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetUrlFromGiphyService getUrlFromGiphyService;

    @Test
    void whenWasCorrectSymbolThenHttpStatus200() throws Exception {

        when(getUrlFromGiphyService
                .responseFromGiphy(TEST_UE))
                .thenReturn(new ResponseEntity<>(HttpStatus.OK));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/currency/" + TEST_UE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void whenWasInvalidSymbolsThenHttpStatusIs5xx() throws Exception {

        when(getUrlFromGiphyService
                .responseFromGiphy(TEST_UE))
                .thenReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/currency/" + TEST_UE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    void whenWasInvalidPageThenHttpStatusIs4xx() throws Exception {

        when(getUrlFromGiphyService
                .responseFromGiphy(TEST_UE))
                .thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/currency/" + TEST_UE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}