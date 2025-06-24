package problems.parkinglot;

import problems.parkinglot.vehicleType.Vehicle;

import java.util.Date;


public class Ticket {
    private final String ticketId;
    private final ParkingSpot spot;
    private final Vehicle vehicle;
    private final Long entryTimeStamp;
    private Long exitTimeStamp;
    public Ticket(String ticketId, ParkingSpot spot, Vehicle vehicle) {
        this.ticketId = ticketId;
        this.spot = spot;
        this.vehicle = vehicle;
        this.entryTimeStamp = new Date().getTime();
    }

    public String getTicketId() {return ticketId;}
    public ParkingSpot getSpot() {return spot;}
    public Long getEntryTimeStamp() {return entryTimeStamp;}
    public Vehicle getVehicle() {return vehicle;}
    public Long getExitTimeStamp() {return exitTimeStamp;}

    public void setExitTimeStamp() {
        this.exitTimeStamp = new Date().getTime();
    }

}
