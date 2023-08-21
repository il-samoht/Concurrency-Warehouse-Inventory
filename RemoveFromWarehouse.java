public class RemoveFromWarehouse implements Runnable{
    Warehouse warehouse;
    int errorFlag;
    Object lock;
    public RemoveFromWarehouse(Object lock, Warehouse warehouse, int errorFlag){
        this.warehouse = warehouse;
        this.errorFlag = errorFlag;
        this.lock = lock;
    }
    public void run(){
        if(errorFlag == 0){
            synchronized(lock){
                try{
                    removeFromInventory();
                    Thread.sleep(1);
                }catch(InterruptedException e){
                    removeFromInventory();
                }
            }
        }else{
            removeFromInventory();
        }

    }
    
    public void removeFromInventory(){
        warehouse.inventorySize = warehouse.inventorySize - 1;
        System.out.println("Thread removed an item. Inventory size now = " + warehouse.inventorySize);
        
    }
}