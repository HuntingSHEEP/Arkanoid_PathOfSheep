import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;

class Kafelka extends Rectangle2D.Float
{
  Plansza p;
  int margines;
  int flaga_ZYCIA;


   Kafelka(Plansza p, int x, int y)
   {
     this.margines=1;
     this.width=60-this.margines;
     this.height=20-this.margines;
     this.x=x*(this.width+this.margines)+this.margines;
     this.y=y*(this.height+this.margines)+this.margines;
     this.flaga_ZYCIA=2;


   }

   void setX(int x)
   {
      this.x=x;
   }
}
