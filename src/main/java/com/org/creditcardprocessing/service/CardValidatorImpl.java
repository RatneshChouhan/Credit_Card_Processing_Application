package com.org.creditcardprocessing.service;

import com.org.creditcardprocessing.dto.CreditCard;
import org.springframework.stereotype.Component;

@Component
public class CardValidatorImpl implements CardValidator{
    @Override
    public boolean validateCard(CreditCard cardDetails) {
        String cardNumber = cardDetails.getCardNumber();
        int [] a =  {cardNumber.length() % 2 == 0 ? 1 : 2};

        return cardNumber.chars()   // Converting to IntStream
                .map(num -> num - '0')         // converting each char value to equivalent int value
                .map(num -> num * (a[0] = a[0] == 1 ? 2 : 1)) // multiply by 1, 2 alternating
                .map(num -> num > 9 ? num - 9 : num)         // if sum of digit is > 9
                .sum() % 10 == 0;

    }
}
