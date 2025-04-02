/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package MyLib;
import java.util.List;

/**
 *
 * @author Naisah
 */
public interface LotService {
    Lot findLot(int blockNumber, int lotNumber);
    void updateLot(Lot lot);
    List<Lot> getFilteredLots(int blockNum, int minSize, int maxSize, double minPrice, double maxPrice);
    List<Lot> getAllLots();
    List<Lot> getAvailableLots();
    List<Lot> getReservedLots();
    List<Lot> getSoldLots();
    void saveLotsToFile();
    public void loadLotsFromFile();

}