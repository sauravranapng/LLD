package problems.parkinglot.fee;

import problems.parkinglot.Ticket;
import problems.parkinglot.vehicleType.VehicleType;

import java.util.Map;


public class VehicleBasedFeeStrategy implements FeeStrategy{
    private final Map<VehicleType, Double> hourlyRate = Map.of(
            VehicleType.CAR, 20.0,
            VehicleType.BIKE, 10.0,
            VehicleType.TRUCK, 30.0
    );
    @Override
    public  double calculateFee(Ticket ticket){
        long duration= (ticket.getExitTimeStamp()-ticket.getEntryTimeStamp());
        long hour=(duration/(1000*60*60))+1;
        return hour*hourlyRate.get(ticket.getVehicle().getType());
    }
}
