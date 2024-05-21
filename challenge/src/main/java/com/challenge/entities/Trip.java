package com.challenge.entities;

import com.challenge.enums.TransportTypes;
import com.challenge.enums.StationsAndZones;
import com.challenge.exception.PriceException;
import com.challenge.exception.TripException;

public class Trip {
    
    private StationsAndZones start;
	private StationsAndZones end;
	private TransportTypes transport;

	private OysterCard card;

    public Trip(){

    }

    public StationsAndZones getStart() {
        return this.start;
    }

    /**
     * Initialise the starting point of a Trip
     * @param transport
     * @param start
     * @param card
     */
    public void setStart(TransportTypes transport, StationsAndZones start, OysterCard card) {
		try {
			Price.validateCardByTransport(transport, card);
			Price.applyMaxCharge(transport, card);

		} catch (PriceException e) {
			System.out.println(e.getMessage());
		}
		this.transport = transport;
		this.card = card;
		this.start = start;
	}

    public StationsAndZones getEnd() {
        return this.end;
    }

    /**
     * Initialise the end point of a Trip
     * @param end
     * @throws TripException
     */
    public void setEnd( StationsAndZones end)throws TripException {
		this.end = end;
		Price.applyCharge(transport,this, card);
	}
    
}
