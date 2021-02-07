public class BonusEngine extends Thread {
    boolean running=true;
    int fireBallCycles = 0;
    int stickyBarCycles = 0;

    Plansza p;

    BonusEngine(Plansza p){
        this.p = p;
        start();
    }

    public void run(){
        try{
            System.out.println("START ENGINE");
            while(running){


                if (p.floor.isAlive){
                    if(p.floor.lifeCycles == 0){
                        System.out.println("Dead Floor cycles: "+ p.floor.lifeCycles);
                        p.floor.isAlive=false;
                        p.floor.superFloor=false;
                    }else if(p.floor.lifeCycles > 0){

                        p.floor.lifeCycles--;
                        System.out.println("Floor cycles: "+ p.floor.lifeCycles);
                    }
                }

                if (fireBallCycles>0){
                    fireBallCycles--;
                }else{
                    for (int i=0; i<p.maxAmountOfBalls; i++){
                        p.a[i].ballType = 0;
                    }
                }

                if(stickyBarCycles>0){
                    stickyBarCycles--;
                    System.out.println("Sticky bar cycles: "+ stickyBarCycles);
                }else{
                    p.b.sticky=false;
                }

                sleep(100);
            }
        }catch (InterruptedException e) {System.out.println("Floor Engine Issue: " +e);}
    }
}
