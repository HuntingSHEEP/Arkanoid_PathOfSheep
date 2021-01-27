import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class Plansza extends JPanel implements MouseMotionListener, MouseListener
{
   Belka b;
   Kulka a;
   SilnikKulki s;
   int liczba_kafelek = 7*2;
   Kafelka[] k = new Kafelka[liczba_kafelek];
   int score = 0;
   boolean game_over = false;
   boolean engineStartFlag = false;


   Plansza()
   {
      super();
      addMouseMotionListener(this);
      addMouseListener(this);

      b=new Belka(325-40, 443);
      a=new Kulka(this,325-5,433,0.2,-1);


      for (int i=0; i<liczba_kafelek; i++){
        k[i]=new Kafelka(this, i%7, i/7);
      }
   }

   public void paintComponent(Graphics g)
   {
     super.paintComponent(g);
     Graphics2D g2d=(Graphics2D)g;

     if (!game_over){

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
         s.running = false;
         g2d.setPaint(Color.BLUE);
         g2d.drawString("GAME OVER", 100, 300);
      }


   }

   public void mouseMoved(MouseEvent e)
   {
       if (!engineStartFlag){
           a.setX(e.getX()-(int) a.width/2);
           a.setY(getSize().height - 30);
       }
       b.setY(getSize().height - 20);
       b.setX(e.getX()-(int) b.width/2);
       repaint();
   }

   public void mouseDragged(MouseEvent e)
   {

   }


    public void mouseClicked(MouseEvent e){
        System.out.println("CLICKED!");
        if(!engineStartFlag){
            engineStartFlag =true;
            s=new SilnikKulki(this, a);
        }
    }

    public void mouseReleased(MouseEvent e){

    }


    public void mouseExited(MouseEvent e){

    }


    public void mouseEntered(MouseEvent e){

    }


    public void mousePressed(MouseEvent e){

    }
}
