

public class Warehouse implements Runnable{
    int inventorySize = 0;
    public Object lock = new Object();
    public Object bigLock = new Object();
    boolean lockswitch = false;
    String[] args;
    public int executionTotal;
    public int executionCount = 0;
    Thread threads[] = new Thread[1000000];
    public int threadCount;

    public Warehouse(String[] args){
        this.args = args;
        executionTotal = Integer.parseInt(args[0]) + Integer.parseInt(args[1]);
        //bigThread();
        //bigThread();

        MakeThreads(args);
        
        PrintFinal(args);
    }
    
    public void bigThread(){
        Thread lockThread = new Thread(this);
        lockThread.start();
        
        try{
            lockThread.join();
        }catch(InterruptedException e){
            
        }
        /*
        if (lockThread.getState() == State.NEW){
            try{
                lockThread.join();
            }catch(InterruptedException e){
                
            }
        }else{
            bigThread();
        }
        */
        
    }

    public void run(){
        synchronized(bigLock){
            if(lockswitch == false){
                try{
                    MakeThreads(args);
                    Thread.sleep(1);
                }catch(InterruptedException e){
                    MakeThreads(args);
                }
            }else{
                try{
                    PrintFinal(args);
                    Thread.sleep(1);
                }catch(InterruptedException e){
                    PrintFinal(args);
                }
            }
        }

        /*
        if(lockswitch == false){
            synchronized(bigLock){
                try{
                    MakeThreads(args);
                    Thread.sleep(1);
                }catch(InterruptedException e){
                    MakeThreads(args);
                }
            }
            
        }else{
            synchronized(bigLock){
                try{
                    PrintFinal(args);
                    Thread.sleep(1);
                }catch(InterruptedException e){
                    PrintFinal(args);
                }
            }
            
        }
        */
    }

    public void MakeThreads(String[] args){
        lockswitch = true;
        AddToWarehouse atw = new AddToWarehouse(lock, this, Integer.parseInt(args[2]));
        RemoveFromWarehouse rfw = new RemoveFromWarehouse(lock, this, Integer.parseInt(args[2]));
        for(int i = 0; i < Integer.parseInt(args[0]); i++){
            Thread atwThread = new Thread(atw);
            atwThread.start();
            try{
                if(Integer.parseInt(args[2]) == 0){
                    atwThread.join();
                }
            }catch(InterruptedException e){

            }
            
        }

        for(int i = 0; i < Integer.parseInt(args[1]); i++){
            Thread rfwThread = new Thread(rfw);
            rfwThread.start();
            try{
                if(Integer.parseInt(args[2]) == 0){
                    rfwThread.join();
                }
            }catch(InterruptedException e){

            }
        }
        
    }

    public void PrintFinal(String[] args){
        if(Integer.parseInt(args[2]) == 0){
            synchronized(lock){
                try{
                    System.out.println("There are " + inventorySize + " items in the inventory");
                    Thread.sleep(1);
                }catch(InterruptedException e){
                    System.out.println("There are " + inventorySize + " items in the inventory");
                }
                
            }
        }else{
            try{
                Thread.sleep(executionTotal*2+10);
            }catch(InterruptedException e){

            }
            
            System.out.println("There are " + inventorySize + " items in the inventory");
        }        
        lockswitch = false;
    }
    
}
