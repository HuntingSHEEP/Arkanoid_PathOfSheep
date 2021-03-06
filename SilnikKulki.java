

class SilnikKulki extends Thread
{
   Kulka[] a;
   Plansza p;
   boolean running = true;
   double delay = 1;
   int delayCount=0;


   SilnikKulki(Plansza p,Kulka[] a)
   {
      this.a=a;
      this.p=p;
      start();
   }

   public void run()
   {
      try
      {
         while(running)
         {
             for(int w=0; w<p.maxAmountOfBalls; w++){
                 if (a[w].isAlive){
                     if(a[w].isFlying){
                         a[w].nextKrok();
                     }
                 }
             }


            if (delayCount>0)
                delayCount--;
            else
                delay=1;

            for (int i=0; i<5; i++){

              final long INTERVAL = (int) (delay * 1000000);
              long start = System.nanoTime();
              long end;
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
