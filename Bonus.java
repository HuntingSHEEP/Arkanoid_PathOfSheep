import java.awt.geom.*;

public class Bonus extends Rectangle2D.Float
{
    boolean isAlive = false;
    int margines;
    int type = 0;

    Bonus(int x, int y, int width){
        this.margines=2;
        this.width=width-this.margines;
        this.height=20-this.margines;
        this.x=x*(this.width+this.margines)+this.margines;
        this.y=y*(this.height+this.margines)+this.margines;
    }
}
