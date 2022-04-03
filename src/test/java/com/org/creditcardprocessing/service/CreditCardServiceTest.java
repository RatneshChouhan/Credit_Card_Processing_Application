package com.org.creditcardprocessing.service;

import com.org.creditcardprocessing.dto.CreditCard;
import com.org.creditcardprocessing.exception.CardNotValidException;
import com.org.creditcardprocessing.repository.CreditCardRepository;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
class CreditCardServiceTest {

    @Mock
    private CreditCardRepository repository;

    @Mock
    private CardValidator validator;

    @InjectMocks
    CreditCardService service;

  /*  @BeforeEach
    void setUp() {
        validator = new CardValidatorImpl();
        service = new CreditCardService(repository, validator);
    }
*/
    @Test
    @DisplayName("Get All Cards")
    void shouldReturnAllCardsTest() {

        List<CreditCard> cards = new ArrayList<>();
        cards.add(new CreditCard(1L,"TestName1", "1111222233334444",1000L, 0.0));
        cards.add(new CreditCard(2L,"TestName2", "8873449329997775",2000L, 0.0));

        Mockito.when(service.getAllCards()).thenReturn(cards);

        List<CreditCard> expected = service.getAllCards();

        Assert.assertEquals("Find All Cards Test",expected, cards);
    }

    @Test
    @DisplayName("Empty List Test")
    void shouldReturnEmptyListInCaseNoDataFound() {
        Mockito.when(service.getAllCards()).thenReturn(Collections.emptyList());
        List<CreditCard> expected = service.getAllCards();
        assertTrue(expected.isEmpty());
    }

    @Test
    @DisplayName("Add Card With Valid Credit Card Details")
    void shouldAddCardWithValidDetails() {
        CreditCard card = new CreditCard(1L,"TestName1", "1111222233334444",1000L, 0.0);
        Mockito.when(validator.validateCard(Mockito.any(CreditCard.class))).thenReturn(true);
        Mockito.when(repository.save(Mockito.any(CreditCard.class))).thenReturn(card);
        CreditCard expected = service.addCard(card);
        Assert.assertEquals(expected,card);
    }

    @Test
    @DisplayName("Add Card With Valid Credit Card Details")
    void shouldThrowExceptionWithValidDetails() {
        CreditCard card = new CreditCard(1L,"TestName1", "1111222256334444",1000L, 0.0);
       /* Mockito.when(validator.validateCard(Mockito.any(CreditCard.class))).thenReturn(false);
        Mockito.when(repository.save(Mockito.any(CreditCard.class))).thenReturn(card);
        CreditCard expected = service.addCard(card);*/
        assertThrows(CardNotValidException.class,() -> service.addCard(card));
    }

}