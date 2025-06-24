package problems.parkinglot;

import problems.parkinglot.fee.FeeStrategy;
import problems.parkinglot.vehicleType.Vehicle;

import java.lang.management.PlatformLoggingMXBean;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {
    private static ParkingLot instance;
    private final List<Floor> floors;
    private final Map<String,Ticket> activeTickets;
    private  FeeStrategy feeStrategy;

    ParkingLot(FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
        this.floors = new ArrayList<>();
        this.activeTickets = new ConcurrentHashMap<>();

    }
    public static synchronized ParkingLot getInstance(FeeStrategy feeStrategy){
        if(instance==null){
           instance = new ParkingLot(feeStrategy);
        }
        return instance;
    }
    public void addFloor(Floor floor){
        floors.add(floor);
    }

    public void setFeeStrategy(FeeStrategy feeStrategy){
        this.feeStrategy=feeStrategy;
    }
    public synchronized Ticket parkVehicle(Vehicle vehicle) throws Exception {
        for( Floor floor:floors){
            Optional<ParkingSpot>spotOpt=floor.getAvailableSpot(vehicle.getType());
            if(spotOpt.isPresent()){
                ParkingSpot spot=spotOpt.get();
                if(spot.park(vehicle)){
                    String ticketId= UUID.randomUUID().toString();
                    Ticket ticket =new Ticket(ticketId,spot,vehicle);
                   activeTickets.put(ticketId,ticket);
                   return ticket;
                }

            }
        }
        throw new Exception("No available spot for " + vehicle.getType());
    }
   public synchronized double unPark(String ticketId) throws Exception {
        Ticket ticket=activeTickets.remove(ticketId);
        if(ticket==null){
            throw new Exception("Invalid TicketId");
        }
     ParkingSpot spot= ticket.getSpot();
        spot.unPark();
        ticket.setExitTimeStamp();
        return feeStrategy.calculateFee(ticket);
   }
   public List<Floor> getFloors(){
        return floors;
   }

}
