package u.ds.ds_project;


import java.util.List;


/**
 * Projecte: Des d'aquesta classe podem possar activitats-tasques-projectes que heredin d'Activitat.
 * Un projecte és un conjunt de tasques o d'altres projectes.
 * El temps total és la suma del temps del conjunt i subconjunt.
 * @author Sandy Umasi(1363361), Eloi Sasal(1368473), Miriam Traver(1391805).
 */
public class Project extends Activitat{

    private List<Activitat> activitats = new java.util.ArrayList();

    public Project(final String nomProjecte, final String descripcioProjecte,
                   final Project projectePare) {
        //Possar relacions entre projectes.
        super(nomProjecte, descripcioProjecte, projectePare);
        if (projectePare == null) {
            logs.warn("Projecte arrel creat, amb nom: " + nomProjecte
                    + "I el seu projecte pare és el següent: " + "null-------------> Es el projecte arrel.");
        } else {
            logs.warn("Projecte creat: " + nomProjecte
                    + "Amb projecte pare: " + projectePare.getNomActivitat());
            projectePare.add(this);
        }
    }

    public List<Activitat> getActivitats() {
        return activitats;
    }
    public void setActivitats(List<Activitat> activitatsNew) {
        this.activitats = activitatsNew;
    }
    //Classe per afegir activitats.
    public void add(Activitat afegirActivitats) {
        this.activitats.add(afegirActivitats);
    }
    //Classe per eliminar una activitat.
    public void remove(Activitat activitatAElimar) {
        if (this.activitats.contains(activitatAElimar)) {
            System.out.println("Eliminant: ");
            System.out.println(activitatAElimar);
            this.activitats.remove(activitatAElimar);
        }
    }
    //Classe per actualitzar la data de les activitats.
    public void actualitzar(Activitat actualitzarActivitat) {
        if (dataInicial == null)
            dataInicial = actualitzarActivitat.getDadaInicial();

        //comprobar si es cert segons l'ordre de les crides, ja que es tracten super classes i dates inicials i finals
        dataFinal = actualitzarActivitat.getDataFinal();
        this.calcularDuracio();

        //Comprobació de si el projecte és buit.
        if (project != null)
            project.actualitzar(this);

    }
    //Classe que calcula el temps total de les activitats.
    public void calcularDuracio() {
        duracio = 0;

        for (int i = 0; i < activitats.size(); i++)
            duracio += activitats.get(i).getDuracio();
    }
    //Classe que s'encarrega de printar les activitats.
    public String print() {
        String taulaInstanciada = super.print();
        if (activitats != null) {
            for (Activitat novaActivitat : activitats)
                taulaInstanciada += novaActivitat.print();
        }
        return taulaInstanciada;
    }


}
