import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;
import java.lang.Math;

class Belka extends Rectangle2D.Float
{
   double roundPercentage = 0.2;
   double bounceAngle = 15;
   Belka(int x, int y)
   {
      this.x=x;
      this.y=y;
      this.width=120;
      this.height=10;
   }

   public boolean contains(double x, double y){
      // ALSO THE BOUNDARIES ARE IN SCOPE
      boolean testX = ((this.x<=x) && (x<=this.x+this.width));
      boolean testY = ((this.y<=y) && (y<=this.y+this.height));
      return (testX && testY);
   }

   public boolean intersects(Kulka q){
      //TODO:[BUG] BELKA NIŻSZA OD KULKI
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

   public boolean leftRoundSection(Kulka q){
      return (x<=q.x+q.width) && (q.x+q.width<=x+width*roundPercentage);
   }

   public boolean rightRoundSection(Kulka q){
      return (x+width*(1-roundPercentage)<=q.x) && (q.x<=x+width);
   }

   public void rotateSpeedVector(Kulka q, double B){
      if ((180 < B) && (B <= 360)){
         q.dx *= -1;
         q.dy *= -1;
         B-=180;
      }if ((0 < B) && (B <= 180)){
         B = Math.toRadians(B);
         double dx1 = q.dx*Math.cos(B) - q.dy*Math.sin(B);
         double dy1 = q.dx*Math.sin(B) + q.dy*Math.cos(B);
         q.dx = dx1;
         q.dy = dy1;
      }
   }

   public void changeAngle(Kulka q){

      if (leftRoundSection(q)){
         double proportion = (x+roundPercentage*width-(q.x+q.width))/(width*roundPercentage);
         rotateSpeedVector(q, 360-bounceAngle*proportion);
      }else if(rightRoundSection(q)){
         double proportion = (q.x - (x+width*(1-roundPercentage)))/(width*roundPercentage);
         rotateSpeedVector(q, 15*proportion);
      }
   }

   void setX(int x){
      this.x=x;
   }

   void setY(int y){
      this.y=y;
   }

}
