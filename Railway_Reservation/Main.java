package Railway_Reservation;

import java.util.*;

public class Main {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Train> trains = new ArrayList<>();
        Queue<Passenger> racList = new LinkedList<>();
        Queue<Passenger> waitingList = new LinkedList<>();
        Map<Integer, ArrayList<Passenger>> passengerMap = new HashMap<>();
        System.out.println("Enter the coach count : ");
        int coachCount = in.nextInt();
        System.out.println("Enter the cabin : ");
        int cabinCount = in.nextInt(), pnrNo = 0;
        for (int i = 0; i < 1; i++) {
            trains.add(new Train("Pallavan", coachCount, cabinCount));
        }
        while (true) {
            System.out.println("""
                    Enter the option :\s
                    1.Seats\s
                    2.Book\s                
                    3.Pnr search\s
                    4.Passenger details\s
                    5.Seat Availability\s
                    6.Cancel\s
                    7.Exit""");
            int option = in.nextInt();
            switch (option) {
                case 1 -> {
                    System.out.printf("%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s", "pnr no", "Train", "coach no", "cabin no", "seat no", "name", "age", "berth", "gender");
                    System.out.println();
                    for (Train train : trains) {
                        for (Coach coach : train.coach) {
                            for (Cabin cabin : coach.cabin) {
                                for (Passenger passenger : cabin.Seats) {
                                    System.out.println(passenger);
                                }
                            }
                        }
                    }
                }

                case 2 -> {
                    System.out.println("Enter the passenger count between 1 to 4 :");
                    int passengerCount = in.nextInt();
                    while (passengerCount > 4 || passengerCount < 1) {
                        System.out.println("Enter the valid passenger count : ");
                        passengerCount = in.nextInt();
                    }
                    ArrayList<Passenger> passengers = new ArrayList<>();
                    int count = 0;
                    while (count < passengerCount) {
                        System.out.println("Enter the name : ");
                        String name = in.next();
                        System.out.println("Enter the age : ");
                        int age = in.nextInt();
                        System.out.println("Enter the berth preference :");
                        String berthPreference = in.next();
                        System.out.println("Enter the gender : ");
                        String gender = in.next();
                        count++;
                        passengers.add(new Passenger("Pallavan", ' ', -1, -1, -1, name, age, berthPreference, gender, "Available"));
                    }
                    int upper = 0, middle = 0, lower = 0;

                    for (Passenger passenger : passengers) {
                        if (passenger.getAge() > 60) {
                            passenger.setBerthPreference("L");
                        }
                        switch (passenger.getBerthPreference()) {
                            case "U" -> upper++;
                            case "M" -> middle++;
                            case "L" -> lower++;
                        }
                    }

                    //for preference
                    ++pnrNo;
                    boolean isFound = false;
                    loop:
                    for (Train train : trains) {
                        for (Coach coach : train.coach) {
                            for (Cabin cabin : coach.cabin) {
                                if (isFound) break loop;
                                if (cabin.getUpper() >= upper && cabin.getLower() >= lower && cabin.getMiddle() >= middle) {
                                    isFound = true;
                                    System.out.println("preferred berths allocated");
                                    for (Passenger passenger : passengers) {
                                        for (Passenger seats : cabin.Seats) {
                                            if (seats.getStatus().equals("Available") && passenger.getBerthPreference().equals(seats.getBerthPreference())) {
                                                switch (passenger.getBerthPreference()) {
                                                    case "U" -> cabin.setUpper(cabin.getUpper() - 1);
                                                    case "M" -> cabin.setMiddle(cabin.getMiddle() - 1);
                                                    case "L" -> cabin.setLower(cabin.getLower() - 1);
                                                }
                                                seats.setPnrNo(pnrNo);
                                                seats.setCabinNo(seats.getCabinNo());
                                                seats.setSeatNo(seats.getSeatNo());
                                                seats.setStatus("Unavailable");
                                                seats.setName(passenger.getName());
                                                seats.setAge(passenger.getAge());
                                                seats.setGender(passenger.getGender());


                                                passenger.setPnrNo(pnrNo);
                                                passenger.setTrainNo(seats.getTrainNo());
                                                passenger.setCabinNo(seats.getCabinNo());
                                                passenger.setSeatNo(seats.getSeatNo());
                                                passenger.setCoachNo(seats.getCoachNo());
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (isFound) {
                        passengerMap.put(pnrNo, passengers);
                        break;
                    }

                    //for adding all
                    loop:
                    for (Train train : trains) {
                        for (Coach coach : train.coach) {
                            for (Cabin cabin : coach.cabin) {
                                if (isFound) break loop;
                                if (cabin.getUpper() + cabin.getLower() + cabin.getMiddle() >= middle + lower + upper) {
                                    isFound = true;
                                    System.out.println("preferred berth Not available");
                                    for (Passenger passenger : passengers) {
                                        for (Passenger seats : cabin.Seats) {
                                            if (seats.getStatus().equals("Available")) {
                                                if (passenger.getBerthPreference().equals("U") && cabin.getUpper() > 0) {
                                                    cabin.setUpper(cabin.getUpper() - 1);
                                                } else if (passenger.getBerthPreference().equals("M") && cabin.getMiddle() > 0) {
                                                    cabin.setMiddle(cabin.getMiddle() - 1);
                                                } else if (passenger.getBerthPreference().equals("L") && cabin.getLower() > 0) {
                                                    cabin.setLower(cabin.getLower() - 1);
                                                } else if (cabin.getUpper() > 0){
                                                    cabin.setUpper(cabin.getUpper() - 1);
                                                    passenger.setBerthPreference("U");
                                                } else if (cabin.getLower() > 0) {
                                                    cabin.setLower(cabin.getLower() - 1);
                                                    passenger.setBerthPreference("L");
                                                } else if (cabin.getMiddle() > 0) {
                                                    cabin.setMiddle(cabin.getMiddle() - 1);
                                                    passenger.setBerthPreference("M");
                                                }
                                                seats.setPnrNo(pnrNo);
                                                seats.setCabinNo(seats.getCabinNo());
                                                seats.setSeatNo(seats.getSeatNo());
                                                seats.setBerthPreference(passenger.getBerthPreference());
                                                seats.setStatus("Unavailable");
                                                seats.setName(passenger.getName());
                                                seats.setAge(passenger.getAge());
                                                seats.setGender(passenger.getGender());

                                                passenger.setPnrNo(pnrNo);
                                                passenger.setTrainNo(seats.getTrainNo());
                                                passenger.setCabinNo(seats.getCabinNo());
                                                passenger.setSeatNo(seats.getSeatNo());
                                                passenger.setCoachNo(seats.getCoachNo());
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (isFound) {
                        passengerMap.put(pnrNo, passengers);
                        break;
                    }

                    //for rac
                    loop:
                    for (Train train : trains) {
                        for (Coach coach : train.coach) {
                            for (Cabin cabin : coach.cabin) {
                                if (isFound) break loop;
                                if (cabin.getUpper() + cabin.getLower() + cabin.getMiddle() + cabin.getSideUpper() + cabin.getSideLower() >= middle + lower + upper) {
                                    isFound = true;
                                    System.out.println("preferred berth rac");
                                    for (Passenger passenger : passengers) {
                                        for (Passenger seats : cabin.Seats) {
                                            if (seats.getStatus().equals("Available")) {
                                                if (passenger.getBerthPreference().equals("U") && cabin.getUpper() > 0) {
                                                    cabin.setUpper(cabin.getUpper() - 1);
                                                } else if (passenger.getBerthPreference().equals("M") && cabin.getMiddle() > 0) {
                                                    cabin.setMiddle(cabin.getMiddle() - 1);
                                                } else if (passenger.getBerthPreference().equals("L") && cabin.getLower() > 0) {
                                                    cabin.setLower(cabin.getLower() - 1);
                                                } else if (cabin.getUpper() > 0) {
                                                    cabin.setUpper(cabin.getUpper() - 1);
                                                    passenger.setBerthPreference("U");
                                                } else if (cabin.getLower() > 0) {
                                                    cabin.setLower(cabin.getLower() - 1);
                                                    passenger.setBerthPreference("L");
                                                } else if (cabin.getMiddle() > 0) {
                                                    cabin.setMiddle(cabin.getMiddle() - 1);
                                                    passenger.setBerthPreference("M");
                                                } else if (cabin.getSideLower() > 0) {
                                                    cabin.setSideLower(cabin.getSideLower() - 1);
                                                    passenger.setBerthPreference("SL");
                                                    racList.add(passenger);
                                                } else if (cabin.getSideUpper() > 0) {
                                                    cabin.setSideUpper(cabin.getSideUpper() - 1);
                                                    passenger.setBerthPreference("SU");
                                                    racList.add(passenger);
                                                }
                                                seats.setPnrNo(pnrNo);
                                                seats.setCabinNo(seats.getCabinNo());
                                                seats.setSeatNo(seats.getSeatNo());
                                                seats.setBerthPreference(passenger.getBerthPreference());
                                                seats.setStatus("Unavailable");
                                                seats.setName(passenger.getName());
                                                seats.setAge(passenger.getAge());
                                                seats.setGender(passenger.getGender());

                                                passenger.setPnrNo(pnrNo);
                                                passenger.setTrainNo(seats.getTrainNo());
                                                passenger.setCabinNo(seats.getCabinNo());
                                                passenger.setSeatNo(seats.getSeatNo());
                                                passenger.setCoachNo(seats.getCoachNo());
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }


                    if (isFound) {
                        passengerMap.put(pnrNo, passengers);
                        break;
                    }

                    //for waiting list
                    loop:
                    for (Train train : trains) {
                        for (Coach coach : train.coach) {
                            for (Cabin cabin : coach.cabin) {
                                if (isFound) break loop;
                                    isFound = true;
                                    System.out.println("preferred berth all");
                                    for (Passenger passenger : passengers) {
                                        for (Passenger seats : cabin.Seats) {
                                            if (seats.getStatus().equals("Available")) {
                                                if (passenger.getBerthPreference().equals("U") && cabin.getUpper() > 0) {
                                                    cabin.setUpper(cabin.getUpper() - 1);
                                                } else if (passenger.getBerthPreference().equals("M") && cabin.getMiddle() > 0) {
                                                    cabin.setMiddle(cabin.getMiddle() - 1);
                                                } else if (passenger.getBerthPreference().equals("L") && cabin.getLower() > 0) {
                                                    cabin.setLower(cabin.getLower() - 1);
                                                } else if (cabin.getUpper() > 0) {
                                                    cabin.setUpper(cabin.getUpper() - 1);
                                                    passenger.setBerthPreference("U");
                                                } else if (cabin.getLower() > 0) {
                                                    cabin.setLower(cabin.getLower() - 1);
                                                    passenger.setBerthPreference("L");
                                                } else if (cabin.getMiddle() > 0) {
                                                    cabin.setMiddle(cabin.getMiddle() - 1);
                                                    passenger.setBerthPreference("M");
                                                } else if (cabin.getSideLower() > 0) {
                                                    cabin.setSideLower(cabin.getSideLower() - 1);
                                                    passenger.setBerthPreference("SL");
                                                    racList.add(passenger);
                                                } else if (cabin.getSideUpper() > 0) {
                                                    racList.add(passenger);
                                                    cabin.setSideUpper(cabin.getSideUpper() - 1);
                                                    passenger.setBerthPreference("SU");
                                                } else if (waitingList.size() < 3) {
                                                    waitingList.offer(passenger);
                                                    passenger.setBerthPreference("WL");
                                                }
                                                seats.setPnrNo(pnrNo);
                                                seats.setCabinNo(seats.getCabinNo());
                                                seats.setSeatNo(seats.getSeatNo());
                                                seats.setBerthPreference(passenger.getBerthPreference());
                                                seats.setStatus("Unavailable");
                                                seats.setName(passenger.getName());
                                                seats.setAge(passenger.getAge());
                                                seats.setGender(passenger.getGender());

                                                passenger.setPnrNo(pnrNo);
                                                passenger.setTrainNo(seats.getTrainNo());
                                                passenger.setCabinNo(seats.getCabinNo());
                                                passenger.setSeatNo(seats.getSeatNo());
                                                passenger.setCoachNo(seats.getCoachNo());
                                                break;
                                            }
                                    }
                                }
                            }
                        }
                    }

                    if (isFound) {
                        passengerMap.put(pnrNo, passengers);
                        pnrNo--;
                    } else {
                        System.out.println("seats not available");
                    }
                }


                case 3 -> {
                    System.out.println("Enter the pnr no :");
                    int pnrno = in.nextInt();
                    System.out.printf("%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s", "pnrno", "Train", "coachno", "cabinno", "seatno", "name", "age", "berth", "gender");
                    System.out.println();
                    //System.out.println(pnrno+" "+passengerMap.get(pnrno));
                    for (Train train : trains) {
                        for (Coach coach : train.coach) {
                            for (Cabin cabin : coach.cabin) {
                                for (Passenger passenger : cabin.Seats) {
                                    if (passenger.getPnrNo() == pnrno) {
                                        System.out.println(passenger);
                                    }
                                }
                            }
                        }
                    }
                }


                case 4 -> {
                    System.out.printf("%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s%-10s", "pnrno", "Train", "coachno", "cabinno", "seatno", "name", "age", "berth", "gender");
                    System.out.println();
                    for (Train train : trains) {
                        for (Coach coach : train.coach) {
                            for (Cabin cabin : coach.cabin) {
                                for (Passenger passenger : cabin.Seats) {
                                    if (passenger.getStatus().equals("Unavailable")) {
                                        System.out.println(passenger);
                                    }
                                }
                            }
                        }
                    }
                    for (Passenger passenger : racList){
                        System.out.println(passenger);
                    }
                    for (Passenger passenger : waitingList){
                        System.out.println(passenger);
                    }
                }

                case 5 -> {
                    for (Train train : trains) {
                        for (Coach coach : train.coach) {
                            for (Cabin cabin : coach.cabin) {
                                System.out.println(cabin);
                            }
                        }
                    }
                }
                case 6->{
                    System.out.println("Choose the option : \n1.Full PNR \n2.Partial");
                    int choice = in.nextInt();
                    switch (choice){
                        case 1->{
                            Queue<Passenger>cancelPassengers = new LinkedList<>();
                            System.out.println("Enter the pnr no : ");
                            int pnr = in.nextInt();
                            for (Train train : trains) {
                                for (Coach coach : train.coach) {
                                    for (Cabin cabin : coach.cabin) {
                                        for (Passenger passenger : cabin.Seats) {
                                            if (passenger.getPnrNo()==pnr) {
                                               cancelPassengers.add(passenger);
                                               passenger.setName("");
                                               passenger.setAge(-1);
                                               passenger.setGender("");
                                               passenger.setPnrNo(-1);
                                               passenger.setStatus("Available");
                                                switch (passenger.getBerthPreference()) {
                                                    case "U" -> cabin.setUpper(cabin.getUpper() + 1);
                                                    case "M" -> cabin.setMiddle(cabin.getMiddle() + 1);
                                                    case "L" -> cabin.setLower(cabin.getLower() + 1);
                                                    case "SU" -> cabin.setSideUpper(cabin.getSideUpper() + 1);
                                                    case "SL" -> cabin.setSideLower(cabin.getLower() + 1);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if(!racList.isEmpty()){
                                loop:
                                for (Train train : trains) {
                                    for (Coach coach : train.coach) {
                                        for (Cabin cabin : coach.cabin) {
                                            for (Passenger cancel : cancelPassengers) {
                                                for (Passenger passenger : cabin.Seats) {
                                                    if(cancel.getSeatNo()==passenger.getSeatNo()&&passenger.getCoachNo()==cancel.getCoachNo()){
                                                        if(racList.isEmpty()){
                                                            break loop;
                                                        }
                                                        for (Passenger rac : racList){
                                                            passenger.setPnrNo(rac.getPnrNo());
                                                            passenger.setName(rac.getName());
                                                            passenger.setCoachNo(rac.getCoachNo());
                                                            passenger.setGender(rac.getGender());
                                                            passenger.setAge(rac.getAge());
                                                            racList.poll();
                                                            break;
                                                        }
                                                    }
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        case 2->{
                            Queue<Passenger>cancelPassengers = new LinkedList<>();
                            System.out.println("Enter the pnr no : ");
                            int pnr = in.nextInt();
                            System.out.println("Enter the delete count :");
                            int count = in.nextInt(),counter=0;
                            while (count>counter) {
                                System.out.println("Enter the seat no: ");
                                int seatno = in.nextInt();
                                for (Train train : trains) {
                                    for (Coach coach : train.coach) {
                                        for (Cabin cabin : coach.cabin) {
                                            for (Passenger passenger : cabin.Seats) {
                                                if (passenger.getPnrNo() == pnr && passenger.getSeatNo() == seatno) {
                                                    counter++;
                                                    cancelPassengers.add(passenger);
                                                    passenger.setName("");
                                                    passenger.setAge(-1);
                                                    passenger.setGender("");
                                                    passenger.setPnrNo(-1);
                                                    passenger.setStatus("Available");
                                                    switch (passenger.getBerthPreference()) {
                                                        case "U" -> cabin.setUpper(cabin.getUpper() + 1);
                                                        case "M" -> cabin.setMiddle(cabin.getMiddle() + 1);
                                                        case "L" -> cabin.setLower(cabin.getLower() + 1);
                                                        case "SU" -> cabin.setSideUpper(cabin.getSideUpper() + 1);
                                                        case "SL" -> cabin.setSideLower(cabin.getLower() + 1);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}







