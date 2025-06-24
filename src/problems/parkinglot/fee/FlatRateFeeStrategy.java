package problems.parkinglot.fee;

import problems.parkinglot.Ticket;

public class FlatRateFeeStrategy implements FeeStrategy{
    private static final double RATE_PER_HOUR=10.0;

    @Override
    public double calculateFee(Ticket ticket) {
        long duration=  (ticket.getExitTimeStamp()-ticket.getEntryTimeStamp());
        long hour= (duration/(1000*60*60))+1;
        return RATE_PER_HOUR*hour ;
    }
}
