package VendingMachine;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class VendingMachine {
	private Inventory<Coin> cashInventory = new Inventory<Coin>();
    private Inventory<Item> itemInventory = new Inventory<Item>();  
    private long totalSales;
    private Item currentItem;
    private long currentBalance;
    
   
    private void VendingMachine(){       
             
        for(Coin c : Coin.values()){
            cashInventory.putNewItem(c, 7);
        }
       
        for(Item itm : Item.values()){
            itemInventory.putNewItem(itm, 6);
        }
       
    }
        
        
       
        public long selectItemAndGetPrice(Item item) {
            if(itemInventory.hasItem(item)){
                currentItem = item;
                return currentItem.getPrice();
            }
            throw new SoldOutException("Sold Out, Please buy another item");
        }

     
        public void insertCoin(Coin coin) {
            currentBalance = currentBalance + coin.getCatgory();
            cashInventory.add(coin);
        }
    
    
        
        
        
       // @Override
        public Bucket<Item, List<Coin>> collectItemAndChange() {
            Item item = collectItem();
            totalSales = totalSales + currentItem.getPrice();
           
            List<Coin> change = collectChange();
           
            return new Bucket<Item, List<Coin>>(item, change);
        }
        
        
        private Item collectItem() throws NoChangeAvailableException,NotSufficientPaidException{
    if(isFullPaid()){
        if(hasSufficientChange()){
            itemInventory.dedcut(currentItem);;
            return currentItem;
        }           
        throw new NoChangeAvailableException("Not Sufficient change in Inventory");
       
    }
    long remainingBalance = currentItem.getPrice() - currentBalance;
    throw new NotSufficientPaidException("Price not full paid, remaining : ", remainingBalance);
}
 
        
        
        private List<Coin> collectChange() {
            long changeAmount = currentBalance - currentItem.getPrice();
            List<Coin> change = getChange(changeAmount);
            updateCashInventory(change);
            currentBalance = 0;
            currentItem = null;
            return change;
        }    
        
        
        
        
        public List<Coin> refund(){
            List<Coin> refund = getChange(currentBalance);
            updateCashInventory(refund);
            currentBalance = 0;
            currentItem = null;
            return refund;
        }
       
       
        private boolean isFullPaid() {
            if(currentBalance >= currentItem.getPrice()){
                return true;
            }
            return false;
        }
        
        
        private List<Coin> getChange(long amount) throws NoChangeAvailableException{
            List<Coin> changes = Collections.EMPTY_LIST;
           
            if(amount > 0){
                changes = new ArrayList<Coin>();
                long balance = amount;
                while(balance > 0){
                	
                	if(balance >= Coin.TWODOLLAR.getCatgory() && cashInventory.hasItem(Coin.TWODOLLAR))
                	{
                    changes.add(Coin.TWODOLLAR);
                    balance = balance - Coin.TWODOLLAR.getCatgory();
                    continue;
                   
                }
                	else if(balance >= Coin.ONEDOLLAR.getCatgory() && cashInventory.hasItem(Coin.ONEDOLLAR))
                	{
                    changes.add(Coin.ONEDOLLAR);
                    balance = balance - Coin.ONEDOLLAR.getCatgory();
                    continue;
                   
                }
                	
                	else if(balance >= Coin.HALFDOLLAR.getCatgory() && cashInventory.hasItem(Coin.HALFDOLLAR))
                	{
                    changes.add(Coin.HALFDOLLAR);
                    balance = balance - Coin.HALFDOLLAR.getCatgory();
                    continue;
                   
                }
                	
                	else if(balance >= Coin.QUARTER.getCatgory()&& cashInventory.hasItem(Coin.QUARTER)){
                        changes.add(Coin.QUARTER);
                        balance = balance - Coin.QUARTER.getCatgory();
                        continue;
                       
                    }
                    
                    else if(balance >= Coin.DIME.getCatgory()&& cashInventory.hasItem(Coin.DIME)) {
                        changes.add(Coin.DIME);
                        balance = balance - Coin.DIME.getCatgory();
                        continue;
                       
                    }
                    else if(balance >= Coin.NICKLE.getCatgory() && cashInventory.hasItem(Coin.NICKLE)) {
                        changes.add(Coin.NICKLE);
                        balance = balance - Coin.NICKLE.getCatgory();
                        continue;
                       
                    }
                    else if(balance >= Coin.PENNY.getCatgory() && cashInventory.hasItem(Coin.PENNY)) {
                        changes.add(Coin.PENNY);
                        balance = balance - Coin.PENNY.getCatgory();
                        continue;
                       
                    }
                    else{
                        throw new NoChangeAvailableException(" No Change Available,Please try another product");
                    }
                }
            }
           
            return changes;
        }
        
   
        
        public void reset(){
            cashInventory.clear();
            itemInventory.clear();
            totalSales = 0;
            currentItem = null;
            currentBalance = 0;
        } 
           
        public void printBill(){
            System.out.println("Total Sales : " + totalSales);
            System.out.println("Current Item Inventory : " + itemInventory);
            System.out.println("Current Cash Inventory : " + cashInventory);
        }
        
        
        
        private boolean hasSufficientChange(){
            return hasSufficientChangeForAmount(currentBalance - currentItem.getPrice());
        }
       
        private boolean hasSufficientChangeForAmount(long amount){
            boolean hasChange = true;
            try{
                getChange(amount);
            }
            catch(NoChangeAvailableException ncae){
                return hasChange = false;
            }
           
            return hasChange;
        }
        
        private void updateCashInventory(List<Coin> change) {
            for(Coin c : change){
                cashInventory.dedcut(c);
            }
        }
       
        public long getTotalSales(){
            return totalSales;
        } 
}
