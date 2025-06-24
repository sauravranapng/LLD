package problems.parkinglot.fee;

import problems.parkinglot.Ticket;

public interface FeeStrategy {
    double calculateFee(Ticket ticket);
}
