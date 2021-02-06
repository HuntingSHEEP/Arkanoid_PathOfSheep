import java.awt.geom.*;

class Kulka extends Ellipse2D.Float
{
   Plansza p;
   double dx,dy;
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
   }

   void setX(int x){
       this.x = x;
   }

   void setY(int y){
       this.y = y;
   }

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
       bounceFromFloor();

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
       //TODO: [BASIC FEATURE] ODBICIE OD BOCZNYCH KRAWĘDZI
       for(int u=0; u<p.liczba_kafelek; u++){
           if(p.k[u].intersects(this) && (p.k[u].flaga_ZYCIA>0)){
               dy=-dy;
               p.k[u].flaga_ZYCIA--;
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
