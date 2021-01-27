import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;

class Belka extends Rectangle2D.Float
{
   Belka(int x, int y)
   {
      this.x=x;
      this.y=y;
      this.width=80;
      this.height=10;
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
