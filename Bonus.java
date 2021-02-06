import java.awt.*;
import java.awt.geom.*;

public class Bonus extends Rectangle2D.Float
{
    Plansza p;
    boolean isAlive = false;
    int heightMargin, widthMargin;
    int type = -1;

    Bonus(Plansza p, int x, int y, int width){
        this.heightMargin = 10;
        this.widthMargin = 2*12;

        this.width=width-this.widthMargin;
        this.height=20-this.heightMargin;
        this.x=x*(this.width+this.widthMargin) + (int) (this.widthMargin/2);
        this.y=y*(this.height+this.heightMargin)+this.heightMargin;
        this.p=p;
    }

    public void exec(){
        if (type == 0){
            //BALL SPEED PENALTY
            p.s.delay = 0.4;
            p.s.delayCount = 2000;

        }else if(type == 1){
            //EXTEND BAR
            if (p.b.width + 20 <= 240){
                p.b.width += 20;
                p.b.x -= 10;
            }

        }else if(type == 2){
            //SHORTEN THE BAR
            if (p.b.width - 20 >= 60){
                p.b.width -= 20;
                p.b.x += 10;
            }

        }else if(type == 3){
            //INCREASE ROUND PERCENTAGE
            if (p.b.roundPercentage + 0.1 <= 0.4){
                p.b.roundPercentage += 0.1;
            }

        }else if(type == 4){
            //DECREASE ROUND PERCENTAGE
            if (p.b.roundPercentage - 0.1 >= 0.1){
                p.b.roundPercentage -= 0.1;
            }

        }else if(type == 5){
            //FLOOR
            p.floor.lifeCycles+=10*25;
            p.floor.isAlive=true;


        }else if(type == 6){
            //SUPER FLOOR
            p.floor.lifeCycles+=10*25;
            p.floor.isAlive=true;
            p.floor.superFloor=true;

        }else if(type == 7){
            //STICKY BAR
            p.b.sticky=true;
            p.bonusEngine.stickyBarCycles += 30 * 10;

        }else if(type == 8){
            //ADD BALL
            for (int i=0; i<p.maxAmountOfBalls; i++){
                if (!p.a[i].isAlive){
                    p.a[i].isAlive=true;
                    p.a[i].setX((int) p.b.x + (int) (p.b.width/2));
                    p.ballCount++;
                    i= p.maxAmountOfBalls+2;
                }
            }
        }else if(type == 9){
            //FIRE BALL VEL DUM-DUM
            p.bonusEngine.fireBallCycles += 12*10;
            for (int i=0; i<p.maxAmountOfBalls; i++){
                p.a[i].ballType = 1;
            }

        }

    }

    public Color getTexture(){
        if (type == 0)
            return Color.RED;
        if (type == 1)
            return Color.GREEN;
        if (type == 2)
            return Color.BLUE;
        if (type == 3)
            return Color.CYAN;
        if (type == 4)
            return Color.YELLOW;
        if (type == 5)
            return Color.BLACK;
        if (type == 6)
            return Color.magenta;
        if (type == 7)
            return new Color(51, 153, 102);
        if (type == 8)
            return new Color(0, 102, 102);
        if (type == 9)
            return new Color(255, 83, 26);

        return Color.DARK_GRAY;
    }
}
