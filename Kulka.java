import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;
import java.lang.Math;

class Kulka extends Ellipse2D.Float
{
   Plansza p;
   double dx,dy;
   double c;
   int drift=3;
   boolean inBar = false;


   Kulka(Plansza p,int x,int y,double dx,double dy)
   {
      this.x=x;
      this.y=y;
      this.width=10;
      this.height=10;

      this.p=p;
      this.dx=dx;
      this.dy=dy;
      this.c = Math.sqrt(dx*dx + dy*dy);

   }

   void setX(int x){this.x = x;}
   void setY(int y){this.y = y;}

   void nextKrok()
   {
       x+=dx;
       y+=dy;

       if(getMinX()<0 || getMaxX()>p.getWidth())  dx=-dx;
       if(getMinY()<0) dy=-dy;
       if((getMinY()>p.getHeight()) && (p.getHeight()>5) ){
           p.game_over = true;
       }

       bounceFromBar();
       bounceFromBricks();

       p.repaint();
   }

   void bounceFromBar(){
       if (p.b.intersects(this) && !inBar){
           dy=-dy;
           inBar=true;

           p.b.changeAngle(this);

       }else if(! p.b.intersects(this)){
           inBar=false;
       }
   }

   void bounceFromBricks(){
       for(int u=0; u<p.liczba_kafelek; u++){
           if(p.k[u].intersects(this) && (p.k[u].flaga_ZYCIA>0)){
               dy=-dy;
               p.k[u].flaga_ZYCIA--;
               if (p.k[u].flaga_ZYCIA==0){
                   p.score++;
               }
               u=p.liczba_kafelek+1;
           }
       }
   }



   void odbicie_od_belki(){

     int margines = 10;
     double strefaCONST = 0.2;
     double dPROGconst = 0.5 - strefaCONST/2;
     double gPROGconst = 0.5 + strefaCONST/2;
     double nx = 5;
     float gornaGRANICA = p.b.x + p.b.width + margines;
     float dolnaGRANICA = p.b.x - margines;
     float dBELKI = p.b.width + 2*margines;
     double a = dolnaGRANICA + dPROGconst*dBELKI;
     double b = dolnaGRANICA + gPROGconst*dBELKI;

     if (y == p.b.y){


       if ((dolnaGRANICA < x) && (x < gornaGRANICA)){
         dy=-dy;

         //1 STREFA KĄTÓW
         if (x < gornaGRANICA - gPROGconst*dBELKI){
           //System.out.println("dy "+dy+", dx "+dx + ", nx "+nx+", a "+a+", x "+x+", ulamek "+(nx*(a-x)/(a-dolnaGRANICA)));
           double mnoznik = nx*(a-x)/(a-dolnaGRANICA);
           if (0<dx){mnoznik = 1/mnoznik;}
           dx*=mnoznik;

           double z = Math.sqrt(dx*dx + dy*dy);
           z = c/z;
           dx *= z;
           dy *= z;
           //System.out.println("C "+c+", C nowe "+Math.sqrt(dx*dx + dy*dy)+". Z "+z);

        //2 STREFA KĄTÓW
         }else if (gornaGRANICA - dPROGconst*dBELKI < x){
           //System.out.println("dy "+dy+", dx "+dx + ", nx "+nx+", a "+a+", x "+x+", ulamek "+(nx*(a-x)/(a-dolnaGRANICA)));
           double mnoznik = nx*(x-b)/(a-dolnaGRANICA);
           if (dx<0){mnoznik = 1/mnoznik;}
           dx*=mnoznik;
           double z = Math.sqrt(dx*dx + dy*dy);
           z = c/z;
           dx *= z;
           dy *= z;
           //|dy/dx| > 0.5
           //System.out.println("C "+c+", C nowe "+Math.sqrt(dx*dx + dy*dy)+". Z "+z+"; dx, dy: "+dx+", "+dy);

         }
       }
     }
   }


}
