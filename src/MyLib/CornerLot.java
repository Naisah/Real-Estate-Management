package MyLib;

import java.text.DecimalFormat;

 public class CornerLot extends Lot {
    private static final double PREMIUM_PERCENTAGE = 0.20; // 20% increase
    private static final int EXTRA_SIZE = 20; // Extra sqm for corner lots

    public CornerLot(Block block, int lotNumber, int lotSize, double lotPrice, LotStatus status) {
        super(block, lotNumber, lotSize, lotPrice, status, "Corner Lot"); 
    }

    @Override
    public double calculateFinalPrice() {
        return lotPrice * (1 + PREMIUM_PERCENTAGE);
    }
    
    @Override
    public int calculateFinalSize() {
        return super.getLotSize() + EXTRA_SIZE; 
    }
    
    public String getFormattedFinalPrice() {         
        DecimalFormat df = new DecimalFormat("#,###.##"); // Customize as needed
        return df.format(calculateFinalPrice()); 
        }

    @Override
    public void viewLot() {
        System.out.println("[Corner Lot] Block: " + getBlockNumber() + " | Lot Number: " + lotNumber +
                " | Size: " + lotSize + " sqm (Extra Space) | Price: ₱" + calculateFinalPrice() +
                " | Status: " + status);
    }
}