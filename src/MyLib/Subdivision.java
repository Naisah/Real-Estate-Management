/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLib;

/**
 *
 * @author Irene
 */
public abstract class Subdivision { 
    private String subdivisionName;
 
    public Subdivision(String subdivisionName) {
        this.subdivisionName = subdivisionName;
    }
 
    public String getSubdivisionName() {
        return "AACPP Real Estate";
    }
 
    public abstract void displayDetails();
}
 
