import java.awt.geom.*;

public class Bonus extends Rectangle2D.Float {
    boolean isAlive = true;
    int margines;
    int type = 0;

    Bonus(int x, int y){
        this.margines=12;
        this.width=width-this.margines;
        this.height=10-this.margines;
        this.x=x*(this.width+this.margines)+this.margines;
        this.y=y*(this.height+this.margines)+this.margines;
    }
}
