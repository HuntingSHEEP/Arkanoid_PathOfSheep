import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;


class Plansza extends JPanel implements MouseMotionListener, MouseListener
{
    int maxAmountOfBalls = 10;
    int ballCount = 1;
    Belka b;
    Kulka[] a = new Kulka[maxAmountOfBalls];
    Color barColor;
    Floor floor;

    int lastMousePositionX = 325;

    String brickTextureSource0 ="textures/Fabric026_1K_Color.jpg";
    BufferedImage brickTextureImage0;
    TexturePaint brickTexture0;

    String brickTextureSource1 ="textures/Fabric023_1K_Color.jpg";
    BufferedImage brickTextureImage1;
    TexturePaint brickTexture1;

    SilnikKulki s;
    BonusEngine bonusEngine;

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


      b=new Belka(325-40, 430);
      floor=new Floor(this, 0, 455, 650);
      bonusEngine = new BonusEngine(this);

      for(int w=0; w<maxAmountOfBalls; w++)
        a[w]=new Kulka(this,325-5,420,0,-2, false);
      a[0].isAlive=true;
      a[1].isAlive=true;
      ballCount++;
      a[1].addDeltaX(10);

      for (int i=0; i<liczba_kafelek; i++){
          k[i]=new Kafelka(this, i%columns, i/columns, 650/columns);
          fallingBonus[i] = new Bonus(this,i%columns, i/columns, 650/columns);
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

           g2d.setPaint(barColor);
           g2d.fill(b);
           g2d.setPaint(new GradientPaint(b.x,b.y, new Color(32,178,170), b.x+(int)(b.width*b.roundPercentage), b.y+b.height, barColor));

           g2d.fill(new Rectangle2D.Float(b.x, b.y,(int) (b.width * b.roundPercentage), b.height));
           g2d.setPaint(new GradientPaint((b.x + (int) (b.width*(1- b.roundPercentage))) ,b.y, barColor, b.x+ b.width, b.y+b.height, new Color(32,178,170)));
           g2d.fill(new Rectangle2D.Float((b.x + (int) (b.width*(1- b.roundPercentage))) ,b.y, (int) (b.width * b.roundPercentage), b.height));

           if(floor.isAlive){
               g2d.setPaint(floor.getTexture());
               g2d.fill(floor);
           }

           for(int i=0; i<liczba_kafelek; i++){

               if (fallingBonus[i].isAlive){
                   g2d.setPaint(fallingBonus[i].getTexture());
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

           g2d.setPaint(a[0].getTexture());
           for(int i=0; i<maxAmountOfBalls; i++){
               if(a[i].isAlive)
                    g2d.fill(a[i]);
           }


      }else{
         s.running = false;
         bonusEngine.running=false;
         g2d.setPaint(Color.BLUE);
         g2d.drawString("GAME OVER", 100, 300);
      }

   }

   public void mouseMoved(MouseEvent e)
   {
       int mousePositionX=e.getX();

       for(int i=0; i<maxAmountOfBalls; i++){
           if(a[i].isAlive){
               if(!a[i].isFlying){
                   a[i].addDeltaX(-(lastMousePositionX-mousePositionX));
                   a[i].setY(getSize().height - 30 -13);
               }
           }
       }

       b.setY(getSize().height - 20-13);
       b.setX(e.getX()-(int) b.width/2);
       if (!engineStartFlag)
           repaint();

       lastMousePositionX=mousePositionX;
   }


    public void mouseClicked(MouseEvent e){
        System.out.println("CLICKED!");
        if(!engineStartFlag){
            engineStartFlag =true;
            s=new SilnikKulki(this, a);
        }

        for(int w=0; w<maxAmountOfBalls; w++){
            if(a[w].isAlive){
                if(!a[w].isFlying){
                    a[w].isFlying = true;
                    w=maxAmountOfBalls+10;
                }
            }
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
