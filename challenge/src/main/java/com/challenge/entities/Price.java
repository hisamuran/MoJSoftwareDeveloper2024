package com.challenge.entities;

import com.challenge.enums.TransportTypes;
import com.challenge.exception.PriceException;

public class Price {
    
    public static final float ZONE_1_FARE = 2.50f;

	public static final float ANY_ZONE_OUTSIDE_ZONE_1_FARE = 2.00f;

	public static final float TWO_ZONES_INC_ZONE_1_FARE = 3.00f;

	public static final float TWO_ZONES_EXC_ZONE_1_FARE = 2.25f;

	public static final float THREE_ZONES_FARE = 3.20f;

	public static final float BUS_FARE = 1.80f;

	public static final float MAX_FARE = 3.20f;


    public static void validateCardByTransport(TransportTypes transport, OysterCard oyster) throws PriceException {
        
        if (transport.equals(TransportTypes.BUS))
			oyster.validateCard(BUS_FARE);

		if (transport.equals(TransportTypes.TUBE))
			oyster.validateCard(MAX_FARE);

    }

    public static void applyMaxCharge(TransportTypes transport, OysterCard oyster) {
		if (transport.equals(TransportTypes.BUS))
			oyster.subtractMoneyFromCurrentBalance(BUS_FARE);

		if (transport.equals(TransportTypes.TUBE))
        oyster.subtractMoneyFromCurrentBalance(MAX_FARE);

	}

    /**
     * Zones traveled counter, helper function to get the necessary information to charge the customer
     * @param trip
     * @return
     */
    private static int countZones(Trip trip) {
        String[] zonesStart = trip.getStart().getZone().split(",");
        String[] zonesEnd = trip.getEnd().getZone().split(",");
        
        //The below logic is limited to the requirements of the challenge, it might not cater for all possible cases beyond the challenge

        int startZone = 0;
        //If we have more than one zone in the starting group then we will start with the second zone when ending zone is greater or equal than the second starting zone
        //and we will start with the first zone when ending zone is smaller than the second starting zone
        //Example if starting in Earls Court which is 1/2 and end in zone 1, so starting zone would be 1.
        if (zonesStart.length == 2) {
            if (zonesEnd.length == 1 && Integer.parseInt(zonesEnd[0]) >= Integer.parseInt(zonesStart[1])) {            
                startZone = Integer.parseInt(zonesStart[1]);
            } else if (zonesEnd.length == 1 && Integer.parseInt(zonesEnd[0]) < Integer.parseInt(zonesStart[1])) {
                startZone = Integer.parseInt(zonesStart[0]);
            }
        } else {
            startZone = Integer.parseInt(zonesStart[0]);
        }

        int endZone = 0;
        //If we have more than one zone in the ending group then we will end with the second zone when starting zone is greater or equal than the second ending zone
        //and we will end with the first zone when starting zone is smaller than the second ending zone
        //Example if starting in zone 1 and  ending in Earls Court which is 1/2 then we would use 1.
        if (zonesEnd.length == 2) {   
            if (zonesStart.length == 1 && Integer.parseInt(zonesStart[0]) >= Integer.parseInt(zonesEnd[1])) {            
                endZone = Integer.parseInt(zonesEnd[1]);
            } else if (zonesStart.length == 1 && Integer.parseInt(zonesStart[0]) < Integer.parseInt(zonesEnd[1])) {
                endZone = Integer.parseInt(zonesEnd[0]);
            }
        }  else {
            startZone = Integer.parseInt(zonesEnd[0]);
        }

        //Use absolute value function to obtain the number of zones traveled:
        int result = 0;
        result = Math.abs(startZone - endZone);

        return result;
    }  

    /**
     * Find out if a Trip is within zone 2
     * @param journey
     * @return
     */
    private static boolean isZoneTwo(Trip trip) {
		if (trip.getEnd().getZone().contains("2") && trip.getStart().getZone().contains("2"))
			return true;
		return false;
	}

    /**
     * Find out if a Trip is within zone 1
     * @param journey
     * @return
     */
	private static boolean haveZoneOne(Trip trip) {
		if (trip.getEnd().getZone().contains("1") || trip.getStart().getZone().contains("1"))
			return true;
		return false;
	}

    /**
     * Find out if the number of zones traveled is 3 (count equals 2)
     * @param count
     * @return
     */
    private static boolean isThreeZones(int count) {
		if (count == 2)
			return true;
		return false;
	}

    /**
     * Find out if the number of zones traveled is 3 (count equals 1)
     * @param count
     * @return
     */
	private static boolean isTwoZones(int count) {
		if (count == 1)
			return true;
		return false;
	}

     /**
     * Find out if the number of zones traveled is 3 (count equals 1)
     * @param count
     * @return
     */
	private static boolean isOneZones(int count) {
		if (count == 0)
			return true;
		return false;
	}

  
    /**
     * In this method we refund the customer to make sure the customer pays the right fare and not always the maximum
     * As a reminder, in the initial touch in in the tube the customer is charged the max fare, that is why we need to adjust it as the customer travels
     * @param transport
     * @param journey
     * @param card
     */
    public static void applyCharge(TransportTypes transport, Trip journey, OysterCard card) {

		if (transport.equals(TransportTypes.TUBE)) {

			int count = countZones(journey);

			if ( isOneZones(count) && isZoneTwo(journey)) {
				
				card.addMoneyToCurrentBalance(MAX_FARE - ANY_ZONE_OUTSIDE_ZONE_1_FARE);
				
			}else if (haveZoneOne(journey) && isOneZones(count)) {

				card.addMoneyToCurrentBalance(MAX_FARE - ZONE_1_FARE);

			} else if (!haveZoneOne(journey) && isOneZones(count)) {

				card.addMoneyToCurrentBalance(MAX_FARE - ANY_ZONE_OUTSIDE_ZONE_1_FARE);

			} else if (haveZoneOne(journey) && isTwoZones(count)) {

				card.addMoneyToCurrentBalance(MAX_FARE - TWO_ZONES_INC_ZONE_1_FARE);

			} else if (!haveZoneOne(journey) && isTwoZones(count)) {

				card.addMoneyToCurrentBalance(MAX_FARE - TWO_ZONES_EXC_ZONE_1_FARE);

			} else if (isThreeZones(count)) {

				card.addMoneyToCurrentBalance(MAX_FARE - THREE_ZONES_FARE);

			}

		} else if (transport.equals(TransportTypes.BUS)) {
			card.addMoneyToCurrentBalance(0f);
		}
        

	}

}
