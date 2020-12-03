package tankes2;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.geom.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.Semaphore;
import static java.lang.Thread.sleep;
public class ConsumidorSemaforo extends Thread{
    private Y rcS;
    private DibujaTanke panelS;
    private Semaphore sem;
    public ConsumidorSemaforo(DibujaTanke panelS, Y rcS){
        this.panelS=panelS;
        this.rcS=rcS;
        sem = new Semaphore(1);
    }
     public void run(){
        while(true){
            if (rcS.getY()<250){
                try{
                    sem.acquire();
                        panelS.aguaS.getAgua().remove(panelS.aguaS.getAgua().size()-1);
                        rcS.setY(rcS.getY()+5);
                        System.out.println("Consumiendo semaforo");
                    sem.release();
                } catch (InterruptedException exc) { 
                    System.out.println(exc); 
                }
                panelS.repaint();
                try{    
                    sleep(500+(int)Math.random()*200);
                }catch(InterruptedException e){}
            }
        }
     }
}
