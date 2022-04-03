package com.org.creditcardprocessing.controller;

import com.org.creditcardprocessing.dto.CreditCard;
import com.org.creditcardprocessing.dto.CreditCardResponse;
import com.org.creditcardprocessing.service.CreditCardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class CreditCardController {

    private CreditCardService creditCardService;

    @Autowired
    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @Operation(summary = "This is to fetch All the credit cards")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Fetched all the cards",
                            content = {@Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "404", description = "Not Available", content = @Content)
            })
    @GetMapping("/cards")
    public ResponseEntity<List<CreditCard>> getAllCards(){
        return new ResponseEntity<>(creditCardService.getAllCards(), HttpStatus.OK);
    }

    @Operation(summary = "This is to add credit card")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Add card",
                            content = {@Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "404", description = "Not Available", content = @Content)
            })
    @PostMapping("/cards")
    public ResponseEntity<CreditCardResponse>addCard(@Valid @RequestBody CreditCard cardDetails) {

        CreditCard creditCard = creditCardService.addCard(cardDetails);
        final CreditCardResponse response = new CreditCardResponse("Card " + creditCard.getCardNumber() + " Added Successfully",
                "CREATED");
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
}
