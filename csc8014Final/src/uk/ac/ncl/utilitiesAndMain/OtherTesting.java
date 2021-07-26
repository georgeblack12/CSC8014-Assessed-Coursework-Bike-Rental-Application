package uk.ac.ncl.utilitiesAndMain;


import uk.ac.ncl.Ids.BikeSerialNumber;
import uk.ac.ncl.Ids.CustomerNumber;
import uk.ac.ncl.bikes.Bike;
import uk.ac.ncl.bikes.ElectricBike;
import uk.ac.ncl.people.CustomerRecord;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * A class for all the testing I did throughout the project. This excludes the testing needed for the methods in
 * RentalManager. The testing for the methods in RentalManager is done in RentalManager.
 */
public class OtherTesting {
    public static void main(String[] args) {
        //used for testing. Have to remove comments from code in CustomerNumber and BikeSerialNumber to complete testing
//        CustomerNumber georgeId = CustomerNumber.getInstance("dg", 2015);
//        CustomerNumber gId = CustomerNumber.getInstance("dg", 2015);
//        CustomerNumber tId = CustomerNumber.getInstance("tt", 2017);
//        CustomerNumber aId = CustomerNumber.getInstance("Ag", 1997);
//        System.out.println(georgeId);
//        System.out.println(gId);
//        System.out.println(tId);
//        System.out.println(aId);
//
//
//        BikeSerialNumber bId = BikeSerialNumber.getInstance();
//        System.out.println(bId.returnNumberPart());
//        System.out.println(bId.getId());
//        BikeSerialNumber cId= BikeSerialNumber.getInstance();
//        BikeSerialNumber kId= BikeSerialNumber.getInstance();
//        BikeSerialNumber rId= BikeSerialNumber.getInstance();
//
//
//
//
//
//        Bike georgeBike = Bike.getInstance("road");
//        Bike gBike = Bike.getInstance("road");
//        Bike gGlike = Bike.getInstance("road");
//        Bike gGike = Bike.getInstance("electric");
//        Bike gSike = Bike.getInstance("electric");
//        ArrayList<Bike> bikes = new ArrayList<>();
//        bikes.add(georgeBike);
//        bikes.add(gBike);
//        bikes.add(gGlike);
//        bikes.add(gGike);
//        bikes.add(gSike);
//
//        for(Bike bike: bikes){
//            System.out.println(bike);
//        }
//
//
//        ElectricBike gG = (ElectricBike) gGike;
//        System.out.println(gG.getCharge());
//
//
//
//
//
//        final Calendar myDob = Calendar.getInstance();
//        myDob.set(2020,1,1);
//        Date dDob=myDob.getTime();
//        final Calendar myRecord = Calendar.getInstance();
//        myRecord.set(2018,1,1);
//        Date dMyRecord = myRecord.getTime();
//
//
//        CustomerRecord cust1=new CustomerRecord("George","Black",dDob,
//                "Gold class",dMyRecord);
//
//
//        CustomerRecord cust3=new CustomerRecord("Johnny","Malibu",dMyRecord,"Gold cLass",
//                dDob);
//
//        CustomerRecord cust4 = new CustomerRecord("Johnny", "Malibu", dMyRecord, "Gold cLass",
//                dDob);
//        CustomerRecord cust5 = new CustomerRecord("Johnny", "Malibu", dMyRecord, "Gold cLass",
//                dDob);
//
//        ArrayList<CustomerRecord> customerRecords = new ArrayList<>();
//        customerRecords.add(cust1);
//        customerRecords.add(cust3);
//        customerRecords.add(cust4);
//        customerRecords.add(cust5);
//
//        for(CustomerRecord cust: customerRecords){
//            System.out.println(cust);
//        }
//
//        myRecord.set(2021,1,1);
//        Date falseMyRecord = myRecord.getTime();
//        CustomerRecord custFalse=new CustomerRecord("George","Black",dDob,
//                "Gold class",falseMyRecord);
//        System.out.println(custFalse);
//
// //        have to make appropriate methods when testing
//        Bike[] roadBikes=RentalManager.createMultipleBikes("road");
//        Bike newRoad=Bike.getInstance("fails"); //fails
//        Bike[] electricBikes=RentalManager.createMultipleBikes("eLeCtric");
//        Bike newElectric=Bike.getInstance("electric"); //fails

//        Bike[] fakeRoadBikes=RentalManager.createMultipleBikes("coad"); //invalid bike Type;



    }
}


