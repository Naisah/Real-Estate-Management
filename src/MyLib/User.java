/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLib;

import MyApp.MainForm;

/**
 *
 * @author Irene
 */
public class User {
   private String name;
   private int age;
   private int preferredBlockNum;
   private double minBudget;
   private double maxBudget;
   private int minLotSize;
   private int maxLotSize;

    public User(String name, int age, int preferredBlockNum, double minBudget, double maxBudget, int minLotSize, int maxLotSize) {
        this.name = name;
        this.age = age;
        this.preferredBlockNum = preferredBlockNum;
        this.minBudget = minBudget;
        this.maxBudget = maxBudget;
        this.minLotSize = minLotSize;
        this.maxLotSize = maxLotSize;
    }
    
    public boolean checkAge(){//checks if the user is eligible to buy a property (age is not less than 18)
        boolean val=true;
            if (age<18){
            val=false;
        }
            return val;
    }
}
