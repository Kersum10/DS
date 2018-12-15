package u.ds.ds_project;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tasca: Aquesta classe és un conjunt d'intervals on cada un d'ells te el temps que s'ha li ha dedicat.
 * Cada un d'ells te un observer dintre de la classe start.
 * L'estructura jerarquica de tasca l'hem definit com una entitat que no pot contenir altres entitats.
 * @author Sandy Umasi(1363361), Eloi Sasal(1368473), Miriam Traver(1391805).
 */
public class Tasca extends Activitat{

    private List<Interval> intervals = new ArrayList<>();
    private boolean onPlay = false;
    private Interval nouInterval = null;
    private int MinimInterval = 0;

    static Logger logs = LoggerFactory.getLogger(Activitat.class);

    //Declaració de la tasca.
    public Tasca(final String nomTasca, final String descrpcioTasca, Project pare) {
        super(nomTasca,descrpcioTasca, pare);
        if (pare != null){
            pare.add(this); //si encuentra al padre hacemos que nos reconozca
        }
        logs.warn("Constructor de tasca: nom = "+nomTasca+" descripcio: "+descrpcioTasca);
    }
    //Actualització de la tasca.
    public void update(){
        if (dataInicial == null) {
            dataInicial = nouInterval.getDataInicial();
        }
        logs.debug("Actualitzar la tasca: " + this.getNomActivitat());
        dataFinal = nouInterval.getDataFinal();
        this.calcularDuracio();
        project.actualitzar(this); //actualizacion del proyecto padre
    }
    public void setMinimInterval(int valorInterval){
        logs.info("Nou temps mínim d'interval: " + valorInterval);
        this.MinimInterval = valorInterval;
    }
    //Iniciar la tasca.
    public void play(){
        if (!this.onPlay) {
            logs.info("Tasca iniciada");
            nouInterval = new Interval(this);
            Rellotge.getRellotge().addObserver(nouInterval);
            intervals.add(nouInterval);
            this.onPlay = true;
        }
    }
    //Finalitzar la tasca.
    public void stop(){
        if (this.onPlay) {
            logs.debug("Tasca finalitzada.");
            Rellotge.getRellotge().deleteObserver(nouInterval);
            this.onPlay = false;
        }
    }
    public List<Interval> getIntervals(){
        return intervals;
    }
    public void setIntervals(List<Interval> intervals){
        this.intervals = intervals;
    }
    public String print(){
        String taulaInstanciada = super.print();
        return taulaInstanciada;
    }
    //Funció per calcular la duració d'una tasca.
    public void calcularDuracio(){
        duracio = 0;
        for (int i = 0; i < intervals.size(); i++) {
            if (intervals.get(i).getDuracio() >= this.MinimInterval) {
                duracio += intervals.get(i).getDuracio();
                logs.info("Condició: "+
                        intervals.get(i).getDuracio()+
                        "Major o igual: "+
                        this.MinimInterval);
            }
        }
        duracio *= 1000;
    }


}
