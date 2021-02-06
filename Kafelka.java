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
        //bonus = ThreadLocalRandom.current().nextInt(0, 1 + 1);
        bonus = 1;
        this.p = p;
    }

    public void createBonus(){
        System.out.println("CREATE BONUS!");
        if ( (1<=bonus) && (bonus <=2)){

            System.out.println("INSIDE IF");

            Thread watek = new Thread(){
                public void run(){
                    System.out.println("WĄTEK");
                    }
            };
            watek.start();

            for (int i=0; i<p.liczba_kafelek; i++){
                if (p.fallingBonus[i].type == 0) {
                    p.fallingBonus[i].type = bonus;
                }
            }

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
