package com.org.creditcardprocessing.dto;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "credit_card")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Card holder name is required")
//    @NotNull(message = "Card holder name is required")
    private String name;

   // @NotNull(message = "Card number is required")
    @NotBlank(message = "Card number is required")
    @Length(min = 1, max = 19, message = "min 1 and max 19 digits allowed")
    @Digits(message = "must be a number", integer = 19, fraction = 0)
    private String cardNumber;

    @NotNull
    private long cardLimit;


    private double balance = 0.0;

    public CreditCard() {}

    public CreditCard(Long id, String name, String cardNumber, long cardLimit, double balance) {
        this.id = id;
        this.name = name;
        this.cardNumber = cardNumber;
        this.cardLimit = cardLimit;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public long getCardLimit() {
        return cardLimit;
    }

    public void setCardLimit(long limit) {
        this.cardLimit = limit;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardLimit=" + cardLimit +
                ", balance=" + balance +
                '}';
    }
}
