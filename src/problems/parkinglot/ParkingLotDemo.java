package problems.parkinglot;

import problems.parkinglot.fee.VehicleBasedFeeStrategy;
import problems.parkinglot.vehicleType.Bike;
import problems.parkinglot.vehicleType.Car;
import problems.parkinglot.vehicleType.Vehicle;
import problems.parkinglot.vehicleType.VehicleType;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotDemo {
    public static void main(String[] args) {
        //We are accessing it using singleton pattern and we can change the feeStrategy later using setMethod.
        ParkingLot parkingLot= ParkingLot.getInstance(new VehicleBasedFeeStrategy());

        List<ParkingSpot> parkingSpotsFloor1 = List.of(
                new ParkingSpot(101, VehicleType.CAR),
                new ParkingSpot(102, VehicleType.CAR),
                new ParkingSpot(103, VehicleType.BIKE)
        );

        List<ParkingSpot> parkingSpotsFloor2 = List.of(
                new ParkingSpot(201, VehicleType.BIKE),
                new ParkingSpot(202, VehicleType.TRUCK)
        );

        // Add a floor with different types of spots
        Floor floor1 = new Floor(1, parkingSpotsFloor1);
        Floor floor2 = new Floor(2, parkingSpotsFloor2);
        parkingLot.addFloor(floor1);
        parkingLot.addFloor(floor2);

        Vehicle car1 = new Car("ABC123");
        Vehicle car2 = new Car("XYZ789");
        Vehicle bike1 = new Bike("M1234");

        // View available spots
        System.out.println("Available spots for Car:");
        for (Floor floor: parkingLot.getFloors()) {
            System.out.println("Floor: " + floor.getFloorNumber() + " " + floor.getAvailableSpots(VehicleType.CAR));
        }

        List<String> parkingTickets = new ArrayList<>();


        // Park vehicles
        try {
            Ticket ticket1 = parkingLot.parkVehicle(car1);
            System.out.println("Car 1 parked: " + ticket1.getTicketId());
            parkingTickets.add(ticket1.getTicketId());

            Ticket ticket2 = parkingLot.parkVehicle(car2);
            System.out.println("Car 2 parked: " + ticket2.getTicketId());
            parkingTickets.add(ticket2.getTicketId());

            Ticket ticket3 = parkingLot.parkVehicle(bike1);
            System.out.println("Bike 1 parked: " + ticket3.getTicketId());
            parkingTickets.add(ticket3.getTicketId());

            // Try parking another car when spots are full
            Vehicle car3 = new Car("DL0432");
           Ticket ticket4= parkingLot.parkVehicle(car3);
            System.out.println("Car 3 parked: "+ ticket4.getTicketId());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        //unpark
        try {
            double fee = parkingLot.unPark(parkingTickets.getFirst()); // valid ticket ID
            System.out.println("Ticket: " + parkingTickets.getFirst() + ", Fee: " + fee);

            fee = parkingLot.unPark("1"); // invalid ticket ID
        } catch (Exception e) {
            System.out.println("Exception while unparking: " + e.getMessage());
        }


    }
}
