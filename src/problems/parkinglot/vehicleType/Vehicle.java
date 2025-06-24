package problems.parkinglot.vehicleType;

import java.util.Objects;

public abstract class Vehicle {
    protected final String licensePlate;
    protected final VehicleType type;
    //using protected in constructor ensure  makes it clear that it's only meant to be accessed by subclasses
    // (or classes within the same package), not by arbitrary client code.
    protected Vehicle(String licensePlate,VehicleType vehicleType) {
        this.licensePlate=licensePlate;
        this.type = vehicleType;
    }
    public VehicleType getType(){
        return type;
    }
    public String getLicensePlate(){
        return licensePlate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle vehicle)) return false;
        return licensePlate.equals(vehicle.licensePlate) && type == vehicle.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(licensePlate, type);
    }
}
