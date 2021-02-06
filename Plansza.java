import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;


class Plansza extends JPanel implements MouseMotionListener, MouseListener
{
    Belka b;
    Kulka a;
    Color barColor;

    String brickTextureSource0 ="textures/Fabric026_1K_Color.jpg";
    BufferedImage brickTextureImage0;
    TexturePaint brickTexture0;

    String brickTextureSource1 ="textures/Fabric023_1K_Color.jpg";
    BufferedImage brickTextureImage1;
    TexturePaint brickTexture1;

    SilnikKulki s;

    int rows = 3;
    int columns = 12;
    int liczba_kafelek = columns * rows;
    Kafelka[] k = new Kafelka[liczba_kafelek];
    Bonus [] fallingBonus = new Bonus[liczba_kafelek];
    //TODO: CREATE ONE THREAD TO HANDLE ALL BONUSES
    int score = 0;
    boolean game_over = false;
    boolean engineStartFlag = false;

   Plansza()
   {
      super();
      addMouseMotionListener(this);
      addMouseListener(this);

      b=new Belka(325-60, 443);
      a=new Kulka(this,325-5,433,0,-2);

      for (int i=0; i<liczba_kafelek; i++){
          k[i]=new Kafelka(this, i%columns, i/columns, 650/columns);
          fallingBonus[i] = new Bonus(i%columns, i/columns, 650/columns);
      }
      loadTextures();
   }

   void loadTextures(){
       barColor = new Color(0,60,60);

       try
       {
           File f=new File(brickTextureSource0);
           brickTextureImage0 =ImageIO.read(f);
           brickTexture0 = new TexturePaint(brickTextureImage0, k[0]);

           File f1=new File(brickTextureSource1);
           brickTextureImage1 =ImageIO.read(f1);
           brickTexture1 = new TexturePaint(brickTextureImage1, k[0]);
       }
       catch(IOException e)
       {
           System.err.println("Problem z plikiem");
       }
   }

   public void paintComponent(Graphics g)
   {
       super.paintComponent(g);
       Graphics2D g2d=(Graphics2D)g;


       if (!game_over){
           g2d.drawString("SCORE: "+score, 20, 300);

           g2d.setPaint(new Color(0, 51, 51));
           g2d.fill(a);

           g2d.setPaint(barColor);
           g2d.fill(b);
           g2d.setPaint(new GradientPaint(b.x,b.y, new Color(32,178,170), b.x+(int)(b.width*b.roundPercentage), b.y+b.height, barColor));

           g2d.fill(new Rectangle2D.Float(b.x, b.y,(int) (b.width * b.roundPercentage), b.height));
           g2d.setPaint(new GradientPaint((b.x + (int) (b.width*(1- b.roundPercentage))) ,b.y, barColor, b.x+ b.width, b.y+b.height, new Color(32,178,170)));
           g2d.fill(new Rectangle2D.Float((b.x + (int) (b.width*(1- b.roundPercentage))) ,b.y, (int) (b.width * b.roundPercentage), b.height));


           for(int i=0; i<liczba_kafelek; i++){

               if (fallingBonus[i].isAlive){
                   System.out.println("I'M ALIVE!");
                   //if (fallingBonus[i].type == 1)
                   g2d.setPaint(Color.GREEN);
                   g2d.fill(fallingBonus[i]);
               }


               if (k[i].flaga_ZYCIA > 0){
                   if (k[i].flaga_ZYCIA==1)
                       g2d.setPaint(brickTexture0);
                   else if (k[i].flaga_ZYCIA==2){
                       g2d.setPaint(brickTexture1);
                   }
                   g2d.fill(k[i]);
               }
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
       if (!engineStartFlag)
           repaint();
   }


    public void mouseClicked(MouseEvent e){
        System.out.println("CLICKED!");
        if(!engineStartFlag){
            engineStartFlag =true;
            s=new SilnikKulki(this, a);
        }
    }

    public void mouseDragged(MouseEvent e)
    {

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
