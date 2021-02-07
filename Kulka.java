import java.awt.geom.*;
import java.lang.Math;
import java.awt.*;

class Kulka extends Ellipse2D.Float
{
   Plansza p;
   double dx,dy;
   boolean inBar = false;
   boolean isAlive;
   boolean isFlying=false;
   Color normalBall = new Color(0, 51, 51);
   Color fireBall = new Color(250, 81, 2);
   int ballType = 0;


   Kulka(Plansza p,int x,int y,double dx,double dy, boolean isAlive)
   {
      this.x=x;
      this.y=y;
      this.width=10;
      this.height=10;

      this.p=p;
      this.dx=dx;
      this.dy=dy;
      this.isAlive=isAlive;
   }

   public Color getTexture(){
       if(ballType == 1)
           return fireBall;

       return normalBall;
   }

   void setX(int x){
       this.x = x;
   }

   void setY(int y){
       this.y = y;
   }

   void setDXDY (double dx, double dy){
       this.dx = dx;
       this.dy = dy;
   }

   public double getSpeed(){
       return Math.sqrt(dx*dx + dy*dy);
   }

   void addDeltaX(int dx){
       this.x += dx;
   }

   void nextKrok()
   {
       x+=dx;
       y+=dy;
        //TODO: FIX BALL IN WALL BUG
       if(getMinX()<0 || getMaxX()>p.getWidth())  dx=-dx;
       if(getMinY()<0){
           if(dy<0){
               dy=-dy;
           }
       }

       if((getMinY()>p.getHeight()) && (p.getHeight()>5)){
           isAlive=false;
           p.ballCount--;
           if(p.ballCount==0)
               p.game_over = true;
       }

       bounceFromBar();
       bounceFromBricks();
       bounceFromFloor();

       p.repaint();
   }

   void bounceFromBar(){
       if (p.b.intersects(this) && !inBar){
           dy=-dy;

           if(p.b.sticky){
               isFlying=false;
               double velocity = Math.sqrt(dx*dx + dy*dy);
               dx=0;
               dy=-velocity;
           }
           inBar=true;
           p.b.changeAngle(this);

       }else if(! p.b.intersects(this)){
           inBar=false;
       }
   }

   void bounceFromBricks(){
       //TODO: [BASIC FEATURE] ODBICIE OD BOCZNYCH KRAWĘDZI
       for(int u=0; u<p.liczba_kafelek; u++){
           if(p.k[u].intersects(this) && (p.k[u].flaga_ZYCIA>0)){
               if(p.a[0].ballType == 0){
                   //System.out.println("BOUNCED FROM BRICK!");
                   dy=-dy;
                   p.k[u].flaga_ZYCIA--;
               }else if(p.a[0].ballType == 1){
                   System.out.println("BRICK IS BURNED DOWN!");
                   p.k[u].flaga_ZYCIA=0;
               }

               if (p.k[u].flaga_ZYCIA==0){
                   p.score++;
                   p.k[u].createBonus(u);
               }
               u=p.liczba_kafelek+1;
           }
       }
   }

   void bounceFromFloor(){
       if(p.floor.isAlive){
           if(p.floor.intersects(this)){
               dy=-dy;
           }
       }
   }

}
