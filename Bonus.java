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
            p.floor.isAlive=true;

        }else if(type == 6){
            p.floor.isAlive=true;
            p.floor.superFloor=true;
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

        return Color.DARK_GRAY;
    }
}
