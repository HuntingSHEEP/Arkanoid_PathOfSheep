import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;

class Belka extends Rectangle2D.Float
{
   Belka(int x)
   {
      this.x=x;
      this.y=300;
      this.width=80;
      this.height=10;
   }

   void setX(int x)
   {
      this.x=x;
   }
}
