package Railway_Reservation;

import java.util.ArrayList;

public class Cabin{
    private int cabinNo;
    private int upper;
    private int middle;
    private int lower;
    private int sideUpper;
    private int sideLower;
    ArrayList<Passenger> Seats;

    public Cabin(String trainNo, char coachNo, int cabinNo, int seatN0, int upper, int middle, int lower, int sideUpper, int sideLower) {
        this.cabinNo = cabinNo;
        this.upper = upper;
        this.middle = middle;
        this.lower = lower;
        this.sideUpper = sideUpper;
        this.sideLower = sideLower;
        Seats = new ArrayList<>();
        for (int i = 1; i <= upper; i++) {
            Seats.add(new Passenger(trainNo, coachNo, -1, cabinNo, ++seatN0, "", -1, "U", "", "Available"));
            Seats.add(new Passenger(trainNo, coachNo, -1, cabinNo, ++seatN0, "", -1, "M", "", "Available"));
            Seats.add(new Passenger(trainNo, coachNo, -1, cabinNo, ++seatN0, "", -1, "L", "", "Available"));
            if (i < 3) {
                Seats.add(new Passenger(trainNo, coachNo, -1, cabinNo, ++seatN0, "", -1, "SU"+i, "", "Available"));
                Seats.add(new Passenger(trainNo, coachNo, -1, cabinNo, ++seatN0, "", -1, "SL"+i, "", "Available"));
            }
        }
    }

    public int getCabinNo() {return cabinNo;}
    public void setCabinNo(int cabinNo) {this.cabinNo = cabinNo;}
    public int getUpper() {return upper;}
    public void setUpper(int upper) {this.upper = upper;}
    public int getMiddle() {return middle;}
    public void setMiddle(int middle) {this.middle = middle;}
    public int getLower() {return lower;}
    public void setLower(int lower) {this.lower = lower;}
    public int getSideUpper() {return sideUpper;}
    public void setSideUpper(int sideUpper) {this.sideUpper = sideUpper;}
    public int getSideLower() {return sideLower;}
    public void setSideLower(int sideLower) {this.sideLower = sideLower;}

    @Override
    public String toString() {
        return "cabinNo=" + cabinNo + ", upper=" + upper + ", middle=" + middle + ", lower=" + lower +
                ", sideUpper=" + sideUpper + ", sideLower=" + sideLower ;
    }
}
