import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;

class SilnikKulki extends Thread
{
   Kulka a;
   Plansza p;

   SilnikKulki(Plansza p,Kulka a)
   {
      this.a=a;
      this.p=p;
      start();
   }

   public void run()
   {
      try
      {
         while(true)
         {
            a.nextKrok();

            for (int i=0; i<50; i++){

              final long INTERVAL = 100000;
              long start = System.nanoTime();
              long end=0;
              do{
                  end = System.nanoTime();
              }while(start + INTERVAL >= end);

              p.repaint();
            }
         }
      }
      catch(Exception e){}
   }
}
