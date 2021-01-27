import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;

class Plansza extends JPanel implements MouseMotionListener
{
   Belka b;
   Kulka a;
   SilnikKulki s;
   int liczba_kafelek = 7*2;
   Kafelka[] k = new Kafelka[liczba_kafelek];
   int score = 0;
   boolean game_over = false;
   boolean flaga_startu_silnika = false;


   Plansza()
   {
      super();
      addMouseMotionListener(this);

      b=new Belka(100);
      a=new Kulka(this,100,200,1,1);


      for (int i=0; i<liczba_kafelek; i++){
        k[i]=new Kafelka(this, i%7, i/7);
      }



   }

   public void paintComponent(Graphics g)
   {
     super.paintComponent(g);
     Graphics2D g2d=(Graphics2D)g;

     if (!game_over){
      System.out.println("Pierwszy IF");
      g2d.drawString("SCORE: "+score, 20, 300);
      g2d.fill(a);
      g2d.fill(b);
      for(int i=0; i<liczba_kafelek; i++)
        if (k[i].flaga_ZYCIA > 0){
          if (k[i].flaga_ZYCIA==1)
            g2d.setPaint(Color.RED);
          else if (k[i].flaga_ZYCIA==2)
            g2d.setPaint(Color.BLUE);
          g2d.fill(k[i]);
        }
      }else{
        g2d.setPaint(Color.BLUE);
        g2d.drawString("GAME OVER", 100, 300);
      }


   }

   public void mouseMoved(MouseEvent e)
   {
      b.setX(e.getX()-50);
      repaint();

   }

   public void mouseDragged(MouseEvent e)
   {
     if(!flaga_startu_silnika){
       flaga_startu_silnika=true;
       s=new SilnikKulki(this, a);
     }
   }
}
