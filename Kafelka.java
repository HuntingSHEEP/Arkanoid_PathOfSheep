import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.concurrent.ThreadLocalRandom;

class Kafelka extends Rectangle2D.Float
{
  Plansza p;
  int margines;
  int flaga_ZYCIA;
  int bonus;


    Kafelka(Plansza p, int x, int y, int width)
    {
        this.margines=2;
        this.width=width-this.margines;
        this.height=20-this.margines;
        this.x=x*(this.width+this.margines)+this.margines;
        this.y=y*(this.height+this.margines)+this.margines;
        this.flaga_ZYCIA=2;
        bonus = ThreadLocalRandom.current().nextInt(0, 10 + 1);
        //bonus = 1;
        this.p = p;
    }

    public void createBonus(int index){
        if ( (0<=bonus) && (bonus <=10)){

            p.fallingBonus[index].type = bonus%10;
            p.fallingBonus[index].type = 7;
            p.fallingBonus[index].isAlive = true;

            Thread watek = new Thread(){
                public void run(){
                    for (int i=0; i<500; i++){
                        p.fallingBonus[index].y++;
                        waitSomeTime();
                        if(executeBarHit())
                            i = 600;
                        if(executeSuperFloorHit())
                            i = 600;
                    }
                    p.fallingBonus[index].isAlive = false;
                }

                public void waitSomeTime(){
                    final long INTERVAL =  10000000;
                    long start = System.nanoTime();
                    long end;
                    do{
                        end = System.nanoTime();
                    }while(start + INTERVAL >= end);
                }

                public boolean executeBarHit(){
                    if(p.b.intersects(p.fallingBonus[index])){
                        p.fallingBonus[index].exec();
                        p.fallingBonus[index].isAlive = false;
                        return true;
                    }
                    return false;
                }

                public boolean executeSuperFloorHit(){
                    if(p.floor.superFloor){
                        if(p.floor.intersects(p.fallingBonus[index])){
                            p.fallingBonus[index].exec();
                            p.fallingBonus[index].isAlive = false;
                            return true;
                        }
                    }
                    return false;
                }

            };
            watek.start();

        }
    }

    public boolean contains(double x, double y){
        // ALSO THE BOUNDARIES ARE IN SCOPE
        boolean testX = ((this.x<=x) && (x<=this.x+this.width));
        boolean testY = ((this.y<=y) && (y<=this.y+this.height));
        return (testX && testY);
    }

    public boolean intersects(Kulka q){
        boolean test1 = contains(q.x, q.y);
        boolean test2 = contains(q.x+q.width, q.y);
        boolean test3 = contains(q.x+q.width, q.y+q.height);
        boolean test4 = contains(q.x, q.y+q.height);

        return test1 || test2 || test3 || test4;
    }

    void setX(int x)
    {
        this.x=x;
    }

    void setY(int y) { this.y=y; }

}
