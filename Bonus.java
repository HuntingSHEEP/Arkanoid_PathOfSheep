import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bonus extends Rectangle2D.Float
{
    Plansza p;
    BufferedImage[] textures;

    boolean isAlive = false;
    int heightMargin, widthMargin;
    int type = -1;

    Bonus(Plansza p, int x, int y, int width){
        this.heightMargin = 1;
        this.widthMargin = 2*6;

        this.width=width-this.widthMargin;
        this.height=20-this.heightMargin;
        this.x=x*(this.width+this.widthMargin) + (int) (this.widthMargin/2);
        this.y=y*(this.height+this.heightMargin)+this.heightMargin;
        this.p=p;

        textures = new BufferedImage[10];
        loadTextures();
    }

    private void loadTextures() {
        String[] pliki ={"textures/redBall.png", "textures/extendBar.png", "textures/shortenBar.png", "textures/extendRoundZone.png", "textures/shortenRoundZone.png",  "textures/floor.png", "textures/superFloor.png" ,"textures/stickyBar.png","textures/addBall.png","textures/fireBall.png"};

        try
        {
            for (int i=0; i<10; i++){
                File f=new File(pliki[i]);
                textures[i] = ImageIO.read(f);
            }

        }
        catch(IOException e)
        {
            System.err.println("Problem z pobraniem tekstury: "+e);
        }
    }

    public void exec(){
        if (type == 0){
            //BALL SPEED PENALTY
            p.s.delay = 0.4;
            p.s.delayCount = 2000;

        }else if(type == 1){
            //EXTEND BAR
            if (p.b.width + 20 <= 240){
                p.b.width += 20;
                p.b.x -= 10;
            }

        }else if(type == 2){
            //SHORTEN THE BAR
            if (p.b.width - 20 >= 60){
                p.b.width -= 20;
                p.b.x += 10;
            }

        }else if(type == 3){
            //INCREASE ROUND PERCENTAGE
            if (p.b.roundPercentage + 0.1 <= 0.4){
                p.b.roundPercentage += 0.1;
            }

        }else if(type == 4){
            //DECREASE ROUND PERCENTAGE
            if (p.b.roundPercentage - 0.1 >= 0.1){
                p.b.roundPercentage -= 0.1;
            }

        }else if(type == 5){
            //FLOOR
            p.floor.lifeCycles+=10*20;
            p.floor.isAlive=true;


        }else if(type == 6){
            //SUPER FLOOR
            p.floor.lifeCycles+=10*20;
            p.floor.isAlive=true;
            p.floor.superFloor=true;

        }else if(type == 7){
            //STICKY BAR
            p.b.sticky=true;
            p.bonusEngine.stickyBarCycles += 10 * 10;
            System.out.println("++ CYCLES :"+p.bonusEngine.stickyBarCycles);

        }else if(type == 8){
            //ADD BALL
            for (int i=0; i<p.maxAmountOfBalls; i++){
                //TODO: [BUG] FIX MAX AMOUNT OF BALLS
                if (!p.a[i].isAlive){
                    p.a[i].isAlive=true;
                    p.a[i].isFlying=false;
                    p.a[i].setX((int) p.b.x + (int) (p.b.width/2));
                    p.a[i].setY((int) p.b.y - 10);
                    p.a[i].setDXDY(0, -p.a[i].getSpeed());
                    p.ballCount++;
                    i= p.maxAmountOfBalls+2;
                }
            }
        }else if(type == 9){
            //FIRE BALL VEL DUM-DUM
            p.bonusEngine.fireBallCycles += 15*10;
            for (int i=0; i<p.maxAmountOfBalls; i++){
                p.a[i].ballType = 1;
            }

        }

    }

    public BufferedImage getTexture(){
       return textures[type];
    }
}
