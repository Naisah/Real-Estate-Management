/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyLib;

import java.util.List;

/**
 *
 * @author Naisah
 */
public class LotDatabaseAdapter implements LotService {
    private LotDatabase lotDatabase;

    public LotDatabaseAdapter(LotDatabase lotDatabase) {
        this.lotDatabase = lotDatabase;
    }

    @Override
    public Lot findLot(int blockNumber, int lotNumber) {
        return lotDatabase.findLot(blockNumber, lotNumber);
    }

    @Override
    public void updateLot(Lot lot) {
        lotDatabase.updateLot(lot);
    }

    @Override
    public List<Lot> getFilteredLots(int blockNum, int minSize, int maxSize, double minPrice, double maxPrice) {
        return lotDatabase.getFilteredLots(blockNum, minSize, maxSize, minPrice, maxPrice);
    }

    @Override
    public List<Lot> getAllLots() {
        return lotDatabase.getAllLots();
    }

    @Override
    public List<Lot> getAvailableLots() {
        return lotDatabase.getAvailableLots();
    }

    @Override
    public void saveLotsToFile() {
        lotDatabase.saveToFile();
    }

    @Override
    public void loadLotsFromFile() {
        lotDatabase.loadLotsFromFile();
    }

    @Override
    public List<Lot> getReservedLots() {
        return lotDatabase.getReservedLots();
    }

    @Override
    public List<Lot> getSoldLots() {
        return lotDatabase.getSoldLots();
    }
}
