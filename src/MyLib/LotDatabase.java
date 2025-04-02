/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLib;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;


public class LotDatabase {
    private List<Lot> lots;
    private double VATPrice = 0.12;

    public LotDatabase(String filename) {
        lots = new ArrayList<>();
        loadLotsFromFile();
    }

    protected void loadLotsFromFile() {
        lots.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("lots.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 6) continue; 

                int blockNumber = Integer.parseInt(parts[0].trim());
                int lotNumber = Integer.parseInt(parts[1].trim());
                int lotSize = Integer.parseInt(parts[2].trim());
                double lotPrice = lotSize * 7500;
                LotStatus status = LotStatus.valueOf(parts[4].trim().toUpperCase()); 
                String lotType = parts[5].trim();

                Lot lot;
                if (lotNumber == 1 || lotNumber == 4 || lotNumber == 12 || lotNumber == 16) {
                    lot = new CornerLot(new Block(blockNumber), lotNumber, lotSize, lotPrice, status);
                } else {
                    lot = new Lot(new Block(blockNumber), lotNumber, lotSize, lotPrice, status, "Regular Lot");
                }

                lots.add(lot);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid LotStatus found in file.");
        }
    }

    private boolean isCornerLot(int lotNumber) {
        return lotNumber == 1 || lotNumber == 4 || lotNumber == 16 || lotNumber == 20;
    }


    public List<Lot> getAllLots() {
        return lots;
    }

    public List<Lot> getAvailableLots() {
        List<Lot> availableLots = new ArrayList<>();
        for (Lot lot : lots) {
            if (lot.getStatus() == LotStatus.AVAILABLE) {
                availableLots.add(lot);
            }
        }
        return availableLots;
    }
    
    public List<Lot> getReservedLots() {
        List<Lot> reservedLots = new ArrayList<>();
        for (Lot lot : lots) {
            if (lot.getStatus() == LotStatus.RESERVED) {
                reservedLots.add(lot);
            }
        }
        return reservedLots;
    }
    
    public List<Lot> getSoldLots() {
        List<Lot> soldLots = new ArrayList<>();
        for (Lot lot : lots) {
            if (lot.getStatus() == LotStatus.SOLD) {
                soldLots.add(lot);
            }
        }
        return soldLots;
    }

    public List<Lot> getFilteredLots(int blockNum, int minSize, int maxSize, double minPrice, double maxPrice) {
        List<Lot> filteredLots = new ArrayList<>();
        for (Lot lot : lots) {
            if (lot.getBlockNumber() == blockNum &&
                lot.getLotSize() >= minSize && lot.getLotSize() <= maxSize &&  
                lot.getLotPrice() >= minPrice && lot.getLotPrice() <= maxPrice && 
                lot.getStatus() == LotStatus.AVAILABLE) {
                filteredLots.add(lot);
            }
        }
        return filteredLots;
    }

    public Lot findLot(int blockNumber, int lotNumber) {
    for (Lot lot : lots) { 
        if (lot.getBlockNumber() == blockNumber && lot.getLotNumber() == lotNumber) {
            return lot; 
        }
    }
        return null; 
    }
        
    public void updateLot(Lot updatedLot) {
    for (int i = 0; i < lots.size(); i++) {
        Lot existingLot = lots.get(i);
        if (existingLot.getBlockNumber() == updatedLot.getBlockNumber() &&
            existingLot.getLotNumber() == updatedLot.getLotNumber()) {

            if (isCornerLot(updatedLot.getLotNumber())) {
                lots.set(i, new CornerLot(
                    updatedLot.block, 
                    updatedLot.getLotNumber(), 
                    updatedLot.getLotSize(), 
                    updatedLot.getLotPrice(), 
                    updatedLot.getStatus()
                ));
            } else {
                lots.set(i, updatedLot);
            }

            saveToFile();
            return;
        }
    }
    }

  
    protected void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("lots.txt"))) {
            for (Lot lot : lots) {
                writer.println(lot.getBlockNumber() + "," + lot.getLotNumber() + "," +
                               lot.getLotSize() + "," + lot.getLotPrice() + "," + lot.getStatus() + "," + lot.getLotType());  
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
