package com.org.creditcardprocessing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.creditcardprocessing.dto.CreditCard;
import com.org.creditcardprocessing.exception.CardNotValidException;
import com.org.creditcardprocessing.service.CreditCardService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CreditCardController.class)
class CreditCardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreditCardService creditCardService;


    @Test
    void getAllCardsTest() throws Exception {

        CreditCard card1 = new CreditCard(1L,"TestName1", "1111222233334444",1000L, 0.0);
        CreditCard card2 = new CreditCard(2L,"TestName2", "8873449329997775",2000L, 0.0);

        Mockito.when(creditCardService.getAllCards()).thenReturn(Arrays.asList(card1, card2));

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/cards")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is("TestName1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cardNumber", is("1111222233334444")))
                .andDo(print());
    }

    @Test
    void add_card_with_valid_details() throws Exception {
        CreditCard card = new CreditCard(1L,"TestName1", "1111222233334444",1000L, 0.0);

        Mockito.when(creditCardService.addCard(Mockito.any(CreditCard.class))).thenReturn(card);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/cards")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(card))
                        .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    void add_card_with_invalid_card_details() throws Exception {
        CreditCard card = new CreditCard(1L,"TestName1", "1234567891234567",1000L, 0.0);

        Mockito.when(creditCardService.addCard(Mockito.any(CreditCard.class))).thenThrow(CardNotValidException.class);

        assertThrows(CardNotValidException.class, () -> creditCardService.addCard(card));
    }
}