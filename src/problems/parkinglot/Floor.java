package problems.parkinglot;

import problems.parkinglot.vehicleType.VehicleType;
import java.util.List;
import java.util.Optional;

public class Floor {
    private final int floorNumber;
    private final List<ParkingSpot> parkingSpots;

    public Floor(int floorNumber, List<ParkingSpot> parkingSpots) {
        this.floorNumber = floorNumber;
        this.parkingSpots = parkingSpots;
    }
    //What synchronized does? --> it makes this operation on some instance of Floor thread safe means . Which means when this method on some floor f
    // will be executing no other thread can call this method untill previous thread completes. But concurrent read is not problem for us, the problem is writing and reading at the same time
    // So we will add "synchronized" on park method too because there we are writing in ParkingSpot . And we need to have synchronized on both read and write method for this exclusion to work.
    public synchronized Optional<ParkingSpot> getAvailableSpot(VehicleType type){
        //why have i used == instead of equals
        //Enums are singletons — each constant is a unique instance and it is null safe , comple time safe ,
        // and give clear intent that i m comparing constants not object's content.
        return parkingSpots.stream().filter(s-> s.isAvailable()&&s.getVehicleType()==type).findFirst();
    }
    public int getFloorNumber(){
        return floorNumber;
    }
    /*
    Why we are not using synchronized here ? --> because we are not extracting property of resource we are getting complete resource with reference.
    hence we are not concerned whether it is getting updated by write or not.
     */
    public List<ParkingSpot> getParkingSpots(){
        return parkingSpots;
    }

    public synchronized List<Integer> getAvailableSpots(VehicleType vehicleType){
        return parkingSpots.stream().filter(s->(s.isAvailable()&&s.getVehicleType()==vehicleType)).map(ParkingSpot::getSpotNumber).toList();
    }



}
