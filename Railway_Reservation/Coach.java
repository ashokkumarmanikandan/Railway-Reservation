package Railway_Reservation;

import java.util.ArrayList;

public class Coach{
    private final String trainName;
    private final char coachNo;
     ArrayList<Cabin>cabin;
    public Coach(String trainName , char coachNo , int cabinCount) {
        this.trainName = trainName;
        this.coachNo = coachNo;
        cabin = new ArrayList<>();
        for (int i = 0; i < cabinCount; i++){
            cabin.add(new Cabin(trainName,coachNo,i,i*5,1,1,1,1,1));
        }
    }

    public String getTrainName() {
        return trainName;
    }

    public char getCoachNo() {
        return coachNo;
    }

    public ArrayList<Cabin> getCabin() {
        return cabin;
    }

    public void setCabin(ArrayList<Cabin> cabin) {
        this.cabin = cabin;
    }

    @Override
    public String toString() {
        return "Coach{" +
                "trainNo=" + trainName +
                ", coachNo=" + coachNo +
                '}';
    }
}
