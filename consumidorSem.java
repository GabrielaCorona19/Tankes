package tankes;
import java.awt.geom.*;
import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;

public class consumidorSem extends Thread{
    private Y rc;
    private DibujaTanke panel;
    private Semaphore sem;
    
    public consumidorSem(DibujaTanke panel, Y rc){
        this.panel=panel;
        this.rc=rc;
        this.sem= new Semaphore(1);
    }
    
    public void run(){
        while(true){
            if(panel.agua.getAgua().size()>0){
                if(sem.tryAcquire()){
                    try{
                        sem.acquire();
                             panel.agua.getAgua().remove(panel.agua.getAgua().size()-1);
                             panel.repaint();
                             rc.setY(rc.getY()+5);
                             System.out.println("Productor");
                        sem.release();
                    }catch (InterruptedException exc) { 
                    System.out.println(exc); 
                    }
                }
                try{
                    sleep(500+(int)Math.random()*200);
                }catch(Exception e){}
            
            }
        }
    }
    
}
