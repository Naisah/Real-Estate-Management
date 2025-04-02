/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLib;

public class Billing {
    private String status = "Available";
    private String owner;
    private double totalPrice;
    private double reservationfee;

    // Constructor that accepts a lot and calculates total price

    public Billing(String owner, double totalPrice) {
        this.owner = owner;
        this.totalPrice = totalPrice;
    }

    // Getter for total price
    public double getTotalPrice() {
        return totalPrice;
    }

    // Process cash payment
    public String processCashPayment() {
        this.status = "Sold";
        return "Payment successful! The lot is now SOLD.";
    }   
    
    // Process loan payment
    public double processLoanPayment(int years) {
        double monthlyPayment = totalPrice*0.8 / (years * 12);
        this.status = "Reserved";
        return monthlyPayment;
    }
    
    // Process downpayment 
    public double processDownPayment(int years) {
        //20 percent of lotprice
        double downpayment = totalPrice*0.2;
        this.status = "Reserved";
        return downpayment;
    }
    
    //reservation fee
    public double processReservationFee(){ 
        //5 percent of totalLotprice
        double reservationFee=totalPrice*0.05;
        return reservationFee;
    }
    // Getter for status
    public String getStatus() {
        return status;
    }
}
