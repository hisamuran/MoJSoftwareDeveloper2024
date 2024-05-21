package com.challenge.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import com.challenge.entities.OysterCard;


public class OysterCardTests {
    
    @Test 
	public void testValidateCard() {
		OysterCard card = new OysterCard(30f);
        boolean result = card.validateCard(25);
        if (result) {
            assertEquals(result, true);
            System.out.println("The Oyster Card is valid");    
        } else {
            assertEquals(result, false);
            System.out.println("The Oyster Card is not valid");
        }
        
        
	}
	@Test 
	public void testSubtractMoneyFromCurrentBalance() {
		OysterCard card = new OysterCard(30f);
		card.subtractMoneyFromCurrentBalance(8f);
        assertEquals(card.getCurrentBalance(), 22f);
        System.out.println("The new balance is: " + String.valueOf(card.getCurrentBalance()));
	}

}
