package Railway_Reservation;

import java.util.ArrayList;

public class Train {
     private final String trainName;
     ArrayList<Coach> coach;

    public Train(String trainName, int coachCount, int cabinCount) {
        this.trainName = trainName;
        coach = new ArrayList<>();
        for (int i = 0; i < coachCount; i++){
            coach.add(new Coach(trainName,(char) (i+'a'),cabinCount));
        }
    }
    @Override
    public String toString() {
        return "Train{" +
                "trainNo=" + trainName+
                ", coach=" + coach +
                '}';
    }

}
