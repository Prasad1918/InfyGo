
package com.infosys.irs.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


public class PassengerListContainer implements Serializable
{

	private static final long serialVersionUID = -4820350555841938194L;
	
	//Important. Set this to a default List in order to avoid null pointer exceptions when the list is empty
    private List<Passenger> passengerList = new LinkedList<>();

    public PassengerListContainer() {
    }

    public PassengerListContainer(List<Passenger> passengerList) {
        this.passengerList = passengerList;
    }

    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(List<Passenger> passengerList) {
        this.passengerList = passengerList;
    }
}
