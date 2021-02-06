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
            while(running){

                if (p.floor.isAlive){
                    if(p.floor.lifeCycles == 0){
                        p.floor.isAlive=false;
                    }else if(p.floor.lifeCycles > 0){
                        p.floor.lifeCycles--;
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
                }else{
                    p.b.sticky=false;
                }

                sleep(100);
            }
        }catch (InterruptedException e) {System.out.println("Floor Engine Issue: " +e);}
    }
}
