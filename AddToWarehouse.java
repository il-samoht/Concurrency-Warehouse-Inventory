public class AddToWarehouse implements Runnable{
    Warehouse warehouse;
    int errorFlag;
    Object lock;
    public AddToWarehouse(Object lock, Warehouse warehouse, int errorFlag){
        this.warehouse = warehouse;
        this.errorFlag = errorFlag;
        this.lock = lock;
    }
    public void run(){
        if(errorFlag == 0){
            synchronized(lock){
                try{
                    addToInventory();
                    Thread.sleep(1);
                }catch(InterruptedException e){
                    addToInventory();
                }
            }
        }else{
            addToInventory();
        }

    }
    
    public void addToInventory(){
        warehouse.inventorySize = warehouse.inventorySize + 1;
        System.out.println("Thread added an item. Inventory size now = " + warehouse.inventorySize);
        
    }
}
