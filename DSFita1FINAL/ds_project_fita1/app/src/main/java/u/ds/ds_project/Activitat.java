package u.ds.ds_project;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Activitat: Aquesta classe te 2 subclasses (projecte i tasca) amb els seus atributs i un atribut projecte el qual ens indica el pare.
 * Les activitats s'agrupen de forma jerarquica i permet a l'usuari organitzar les tasques.
 * @author Sandy Umasi(1363361), Eloi Sasal(1368473), Miriam Traver(1391805).
 */
public class Activitat implements Serializable{
    private static final long serialVersionUID = -2666788566300547683L;
    static Logger logs = LoggerFactory.getLogger(Activitat.class);
    protected String name;
    protected String descripcioActivitat;
    protected long duracio = 0;
    protected Date dataInicial = null;
    protected Date dataFinal = null;
    protected SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-YYY HH:mm:ss");
    protected Project project = null;

    public Activitat(String nameToSet, String descr, Project project){

        logs.warn("Constructor activitat: nom = " + nameToSet + " descripcio: " + descr);
        //Si existeix un pare li assignem el projecte.
        if(project != null){
            this.project = project;
        }
        this.name = nameToSet;
        this.descripcioActivitat = descr;
    }
    //Eliminar una activitat.
    public void remove(Activitat projectComponent) {
        logs.warn("Funcio no programada!");
        throw new UnsupportedOperationException();
    }
    public Activitat getFill(int i) {
        throw new UnsupportedOperationException();
    }
    public String getNomActivitat() {
        return name;
    }
    public String getDescripcioActivitat() {
        return descripcioActivitat;
    }
    public Date getDadaInicial() {
        return dataInicial;
    }
    public Date getDataFinal() {
        return dataFinal;
    }
    public String print(){
        String taulaInstanciada = " ";
        String dataInicialAPrintar = " ";
        String dataFinalAPrintar = " ";
        String duracioAPrintar;
        duracioAPrintar = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(duracio),
                TimeUnit.MILLISECONDS.toMinutes(duracio)
                        - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duracio)),
                TimeUnit.MILLISECONDS.toSeconds(duracio)
                        - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duracio)));
        if (dataInicial != null) {
            dataInicialAPrintar = dateFormat.format(this.getDadaInicial());
            dataFinalAPrintar = dateFormat.format(this.getDataFinal());
        }
        taulaInstanciada = "\n"
                + String.format(
                "%-5s%20s%23s%15s"
                , this.getNomActivitat()
                , dataInicialAPrintar
                , dataFinalAPrintar
                , duracioAPrintar);
        return taulaInstanciada;
    }
    public Project getProjecte() {
        return project;
    }
    public void setProjecte(Project projecte) {
        this.project = projecte;
    }
    public long getDuracio() {
        return duracio;
    }
}
