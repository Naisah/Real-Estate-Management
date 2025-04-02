package MyLib;

import java.awt.List;
import java.util.ArrayList;

public class Lot {
    protected Block block;
    protected int lotNumber;
    protected int lotSize;
    protected double lotPrice;
    protected LotStatus status;
    protected String lotType;

    public Lot(Block block, int lotNumber, int lotSize, double lotPrice, LotStatus status, String lotType) {
        this.block = block;
        this.lotNumber = lotNumber;
        this.lotSize = lotSize;
        this.lotPrice = lotPrice;
        this.status = status;
        this.lotType = lotType;
    }
    
    public int getLotNumber() {
        return lotNumber;
    }

    public int getLotSize() {
        return lotSize;
    }

    public double getLotPrice() {
        return lotPrice;
    }

    public int getBlockNumber() {
        return block.getBlockNumber();
    }

    public LotStatus getStatus() {
        return status;
    }
    
    public String getLotType() {
        return lotType;
    }

    public void setStatus(LotStatus status) {
        this.status = status;
    }
    
    public void setLotPrice(double lotPrice) {
        this.lotPrice = lotPrice;
    }

    public void setLotSize(int lotSize) {
        this.lotSize = lotSize;
    }
    
    public double calculateFinalPrice() {
        return lotPrice;
    }
   
    public int calculateFinalSize() {
        return lotSize;
    }
    
    public void viewLot() {
        System.out.println("Block: " + getBlockNumber()+ " | " + "Lot Number: " + lotNumber + " | Size: " + lotSize + " sqm | Price: ₱" + lotPrice +
                " | Status: " + status);
    }
}
    
