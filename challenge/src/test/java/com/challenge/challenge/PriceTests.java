package com.challenge.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.challenge.entities.OysterCard;
import com.challenge.entities.Price;
import com.challenge.entities.Trip;
import com.challenge.enums.StationsAndZones;
import com.challenge.enums.TransportTypes;


public class PriceTests {

    @Test
	public void testValidateBus() {

		OysterCard card = new OysterCard(Price.BUS_FARE - 1f);
		Price price = new Price();
		price.validateCardByTransport(TransportTypes.BUS, card);
	}

	@Test
	public void testValidateTubePrice() {

		OysterCard card = new OysterCard(Price.MAX_FARE - 1f);
		Price price = new Price();
		price.validateCardByTransport(TransportTypes.TUBE, card);
	}

	@Test
	public void testChargeMaxTube() {
		OysterCard card = new OysterCard(Price.MAX_FARE);
		Price price = new Price();
		price.applyMaxCharge(TransportTypes.TUBE, card);
		assertEquals(0f, card.getCurrentBalance(), 0.0f);
	}

	@Test
	public void testChargeMaxBus() {
		OysterCard card = new OysterCard(Price.BUS_FARE);
		Price price = new Price();
		price.applyMaxCharge(TransportTypes.BUS, card);
		assertEquals(0f, card.getCurrentBalance(), 0.0f);
	}

	@Test
	public void testChargeBus() {
		OysterCard card = new OysterCard(Price.BUS_FARE);
		Price price = new Price();
		Trip jorneyBusEarlToChelsea = new Trip();
		jorneyBusEarlToChelsea.setStart(TransportTypes.BUS, null, card);
		jorneyBusEarlToChelsea.setEnd(null);
		price.applyCharge(TransportTypes.BUS,jorneyBusEarlToChelsea, card);
		assertEquals(0f, card.getCurrentBalance(), 0.0f);
	}
	
	@Test
	public void testChargeTubeZoneOne() {
		OysterCard card = new OysterCard(Price.MAX_FARE);
		Trip jorneyTubeZoneOne = new Trip();
		jorneyTubeZoneOne.setStart(TransportTypes.TUBE, StationsAndZones.HOLBORN, card);
		jorneyTubeZoneOne.setEnd(StationsAndZones.EARLSCOURT);
		assertEquals(Price.MAX_FARE - Price.ZONE_1_FARE, card.getCurrentBalance(), 0.001f);
	}
	
	@Test
	public void testChargeTubeAnyZoneOutSideZoneOne() {
		OysterCard card = new OysterCard(Price.MAX_FARE);
		Trip jorneyTubeOutsideZoneOne = new Trip();
		jorneyTubeOutsideZoneOne.setStart(TransportTypes.TUBE, StationsAndZones.HAMMERSMITH, card);
		jorneyTubeOutsideZoneOne.setEnd(StationsAndZones.EARLSCOURT);
	}
	
	@Test
	public void testChargeTubeZoneTwoInZoneOne() {
		OysterCard card = new OysterCard(Price.MAX_FARE);
		Trip jorneyTubeZoneTwoZoneOne = new Trip();
		jorneyTubeZoneTwoZoneOne.setStart(TransportTypes.TUBE, StationsAndZones.HAMMERSMITH, card);
		jorneyTubeZoneTwoZoneOne.setEnd(StationsAndZones.HOLBORN);
		assertEquals(Price.MAX_FARE - Price.TWO_ZONES_INC_ZONE_1_FARE, card.getCurrentBalance(), 0.001f);
	}
	
	@Test
	public void testChargeTubeThreeZones() {
		OysterCard card = new OysterCard(Price.MAX_FARE);
		Trip jorneyTubeThreeZones = new Trip();
		jorneyTubeThreeZones.setStart(TransportTypes.TUBE, StationsAndZones.HOLBORN, card);
		jorneyTubeThreeZones.setEnd(StationsAndZones.WIMBLEDON);
		assertEquals(Price.MAX_FARE - Price.THREE_ZONES_FARE, card.getCurrentBalance(), 0.001f);
	}
    
}
