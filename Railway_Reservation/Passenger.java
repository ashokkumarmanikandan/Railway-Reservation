package Railway_Reservation;

public class Passenger {
private String name;
private int age;
private String berthPreference;
private String gender;
private int cabinNo;
private int pnrNo;
private int seatNo;
private String status;
private  String trainNo;
private  char coachNo;

    public Passenger(String trainNo, char coachNo, int pnrNo, int cabinNo, int seatNo, String name, int age, String berthPreference, String gender, String status) {
        this.trainNo = trainNo;
        this.coachNo = coachNo;
        this.pnrNo = pnrNo;
        this.cabinNo = cabinNo;
        this.name = name;
        this.age = age;
        this.berthPreference = berthPreference;
        this.gender = gender;
        this.seatNo = seatNo;
        this.status = status;
    }

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}
    public String getBerthPreference() {return berthPreference;}
    public void setBerthPreference(String berthPreference) {this.berthPreference = berthPreference;}
    public String getGender() {return gender;}
    public void setGender(String gender) {this.gender = gender;}
    public int getCabinNo() {return cabinNo;}
    public void setCabinNo(int cabinNo) {this.cabinNo = cabinNo;}
    public int getPnrNo() {return pnrNo;}
    public int getSeatNo() {return seatNo;}
    public void setSeatNo(int seatNo) {this.seatNo = seatNo;}
    public void setPnrNo(int pnrNo) {this.pnrNo = pnrNo;}
    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = String.valueOf(trainNo);
    }

    public char getCoachNo() {
        return coachNo;
    }

    public void setCoachNo(char coachNo) {
        this.coachNo = coachNo;
    }

    @Override
    public String toString() {
        return String.format("%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s", pnrNo, trainNo,coachNo,cabinNo+1,seatNo,name,age,berthPreference,gender);
    }
}
