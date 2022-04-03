package com.org.creditcardprocessing.service;

import com.org.creditcardprocessing.dto.CreditCard;
import com.org.creditcardprocessing.exception.CardNotValidException;
import com.org.creditcardprocessing.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;
    private final CardValidator cardValidator;

    @Autowired
    public CreditCardService(CreditCardRepository creditCardRepository, CardValidator cardValidator) {
        this.creditCardRepository = creditCardRepository;
        this.cardValidator = cardValidator;
    }

    public CreditCard addCard(CreditCard cardDetails){
        if(!cardValidator.validateCard(cardDetails))  {
           throw new CardNotValidException("Card Validation Failed");
        }
        return creditCardRepository.save(cardDetails);
    }

    public List<CreditCard> getAllCards() {
        return creditCardRepository.findAll();
    }
}
