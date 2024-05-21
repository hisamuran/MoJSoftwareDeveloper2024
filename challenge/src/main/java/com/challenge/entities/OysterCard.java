package com.challenge.entities;

public class OysterCard {
    
    private float currentBalance;

    public OysterCard() {
        this.currentBalance = 0;
    }

    public OysterCard(float balance) {
        this.currentBalance = balance;
    }

    public float getCurrentBalance() {
        return this.currentBalance;
    }

    public void setCurrentBalance(float newBalance) {
        this.currentBalance = newBalance;
    }

    public void addMoneyToCurrentBalance(float moneyToAdd) {
        this. currentBalance += moneyToAdd;
    }

    public void subtractMoneyFromCurrentBalance(float moneyToSubtract) {
        this.currentBalance -= moneyToSubtract;
    }

    /**
     * A card tap will only be valid if the balance is enough to cover the ticket cost
     * @param ticketCost
     * @return
     */
    public boolean validateCard(float ticketCost) {
        if (this.currentBalance < ticketCost) {
            return false;
        } else {
            return true;
        }
    }
}
