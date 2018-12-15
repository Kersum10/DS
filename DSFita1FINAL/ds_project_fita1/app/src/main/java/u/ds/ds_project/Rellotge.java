package u.ds.ds_project;
//TODO: import java.awt.print.Printable;
import java.util.Observable;
import java.util.Date;

/**
 * Rellotge: En aquesta classe el que fem es obtenir la data, hora i els temps per tal de poder cronometrar el temps d'inici i fi dels projectes.
 * Utilitzem el patró de disseny OBSERVER, ja que és observat per l'interval, així el pot fer servir en qualsevol moment, i en un temps de refresc.
 *@author Sandy Umasi(1363361), Eloi Sasal(1368473), Miriam Traver(1391805).
 */
public class Rellotge extends Observable implements Runnable{

    private Date data = null;
    private int tempsDeRefresc = 1;
    private static Rellotge myClock = null;

    private Rellotge(){
    }
    //Creació del rellotge.
    public static Rellotge getRellotge(){
        if (myClock == null) {
            myClock = new Rellotge();
        }
        return myClock;
    }
    //Inici del rellotge
    public void run() {
        while (true){
            data = new Date();
            //Possa els canvis que s'han fet.
            myClock.setChanged();
            //Notifica si hi han hagut canvis.
            myClock.notifyObservers();

            try {
                Thread.sleep(tempsDeRefresc *1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public int getStep(){
        return tempsDeRefresc;
    }
    public void setStep(int stepToSet){
        this.tempsDeRefresc = stepToSet;
    }
}
