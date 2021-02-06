import java.awt.*;
import java.awt.geom.*;

public class Floor extends Rectangle2D.Float {
    Plansza p;
    boolean isAlive;
    boolean superFloor;
    int lifeCycles = 0;

    Floor(Plansza p, int x, int y, int width)
    {
        this.width=width;
        this.height=100;
        this.x=x;
        this.y=y;
        this.isAlive=false;
        this.superFloor=false;
        this.p = p;
    }

    public Color getTexture(){
        if (superFloor)
            return Color.DARK_GRAY;
        return Color.BLACK;
    }

    public boolean intersects(Kulka q){
        boolean test1 = contains(q.x, q.y);
        boolean test2 = contains(q.x+q.width, q.y);
        boolean test3 = contains(q.x+q.width, q.y+q.height);
        boolean test4 = contains(q.x, q.y+q.height);

        return test1 || test2 || test3 || test4;
    }

    public boolean intersects(Bonus q){
        //TODO:[BUG] BELKA NIŻSZA OD BONUSU
        boolean test1 = contains(q.x, q.y);
        boolean test2 = contains(q.x+q.width, q.y);
        boolean test3 = contains(q.x+q.width, q.y+q.height);
        boolean test4 = contains(q.x, q.y+q.height);

        return test1 || test2 || test3 || test4;
    }
}
