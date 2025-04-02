package MyLib;

public class Block extends Subdivision{ 
    private int blockNumber;
 
    public Block(int blockNumber) {
        super("AACPP Real Estate"); // Hardcoded or default value for subdivision
        this.blockNumber = blockNumber;
    }
 
    public int getBlockNumber() {
        return blockNumber;
    }
 
    @Override
    public void displayDetails() {
        System.out.println("Subdivision: " + getSubdivisionName() + ", Block Number: " + blockNumber);
    }
}
