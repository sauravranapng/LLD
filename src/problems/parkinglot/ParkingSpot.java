package problems.parkinglot;

import problems.parkinglot.vehicleType.Vehicle;
import problems.parkinglot.vehicleType.VehicleType;

public class ParkingSpot {
    private final int spotNumber;
    private final VehicleType vehicleType;
    private Vehicle vehicle;
    private boolean isOccupied;

    public ParkingSpot(int spotNumber, VehicleType vehicleType) {
        this.spotNumber = spotNumber;
        this.vehicleType = vehicleType;
        this.isOccupied =false;
    }
    public synchronized boolean isAvailable(){
        return !isOccupied;
    }

    public synchronized boolean park(Vehicle vehicle){
        if(isOccupied||vehicleType!=vehicle.getType()){
            return false;
        }
        this.vehicle=vehicle;
         isOccupied=true;
         return  true;
    }
    public synchronized void unPark(){
        vehicle=null;
        isOccupied=false;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
