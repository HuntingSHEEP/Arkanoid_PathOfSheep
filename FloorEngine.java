public class FloorEngine extends Thread {
    boolean running=true;

    Plansza p;

    FloorEngine(Plansza p){
        this.p = p;
        start();
    }

    public void run(){
        try{
            while(running){

                if (p.floor.isAlive){
                    if(p.floor.lifeCycles == 0){
                        p.floor.isAlive=false;
                    }else if(p.floor.lifeCycles > 0){
                        p.floor.lifeCycles--;
                    }
                }

                sleep(100);
            }
        }catch (InterruptedException e) {System.out.println("Floor Engine Issue: " +e);}
    }
}
