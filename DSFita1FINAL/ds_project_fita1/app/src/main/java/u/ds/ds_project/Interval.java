package u.ds.ds_project;

import java.io.Serializable;
import java.util.Date;
import java.util.Observer;
import java.util.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Interval: Es un espai de temps definit entre que l'usuari inicia y finalitza una tasca.
 * @author Sandy Umasi(1363361), Eloi Sasal(1368473), Miriam Traver(1391805).
 */
public class Interval implements Observer, Serializable{

    private Date dataInicial =null;
    private Date dataFinal =null;
    private long duracio =0; //long*
    private static Logger logs = LoggerFactory.getLogger(Interval.class);

    private Tasca Tasca;

    Interval(Tasca pare){
        this.Tasca = pare;
    }

    //Classe per actualitzar l'interval.
    public void update(Observable arg0, Object arg1) {

        if (dataInicial == null) {
            dataInicial = new Date();
        }
        //Cambiem de format. De int a long.
        duracio += ((long) Rellotge.getRellotge().getStep());
        dataFinal = new Date();
        this.Tasca.update(); //
    }
    public long getDuracio() {
        return duracio;
    }
    public Date getDataInicial() {
        return dataInicial;
    }
    public Date getDataFinal() {
        return dataFinal;
    }
    public Tasca getTasca() {
        return Tasca;
    }
    public void setTask(Tasca tascaset) {
        logs.info("Modificant la tasca.");
        this.Tasca = tascaset;
    }

}
