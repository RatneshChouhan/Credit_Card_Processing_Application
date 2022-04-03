package com.org.creditcardprocessing.service;

import com.org.creditcardprocessing.dto.CreditCard;
import org.springframework.stereotype.Component;

@Component
public interface CardValidator {
    public boolean validateCard(CreditCard cardDetails);
}
