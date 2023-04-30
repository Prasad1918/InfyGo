package com.infosys.irs.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.infosys.irs.entity.FlightEntity;
import com.infosys.irs.exception.PassengerDetailNotFoundException;
import com.infosys.irs.exception.SeatsNotAvailableException;
import com.infosys.irs.model.Booking;
import com.infosys.irs.model.Passenger;
import com.infosys.irs.model.PassengerListContainer;
import com.infosys.irs.model.SearchFlights;
import com.infosys.irs.repository.FlightRepository;
import com.infosys.irs.utility.CalendarUtility;

@Service
public class BookingService {

	@Autowired
	private FlightRepository flightRepository;

	@SuppressWarnings("deprecation")
	public SearchFlights getFlightDetails(String flightId)  {

		SearchFlights result = new SearchFlights();
		FlightEntity flight = flightRepository.getOne(flightId);
		//Optional<FlightEntity> flight = flightRepository.findById(flightId);
		result.setAirlines(flight.getAirlines());
		result.setDepartureTime(flight.getDepartureTime());
		result.setDestination(flight.getDestination());
		result.setFare(flight.getFare().toString());
		result.setSeatCount(flight.getSeatCount().toString());
		result.setSource(flight.getSource());
		result.setFlightAvailableDate(CalendarUtility.getStringFromCalendar(flight.getFlightAvailableDate()));
		return result;

	}

	public Booking bookTicket(Booking booking, PassengerListContainer passengerListContainer)
			throws PassengerDetailNotFoundException, SeatsNotAvailableException{

		if (passengerListContainer == null) {
			throw new PassengerDetailNotFoundException("BookingService.PASSENGER_NOT_ADDED");
		}
		List<Passenger> passengerList = passengerListContainer.getPassengerList();
		if (passengerList == null || passengerList.isEmpty()) {
			throw new PassengerDetailNotFoundException("BookingService.PASSENGER_NOT_ADDED");
		}
		FlightEntity flightEntity = flightRepository.getOne(booking.getFlightId());

		if (flightEntity.getSeatCount() - passengerList.size() <= 0)
			throw new SeatsNotAvailableException("BookingService.SEATS_NOT_AVAILABLE");
		booking.setSeats(passengerList.size());
		Double totalFare = Double.parseDouble(booking.getFare()) * booking.getSeats();
		booking.setFare(totalFare.toString());
		Integer pnr = new Random().nextInt() * 1858955;
		booking.setPnr(pnr);

		return booking;
	}

}
