package u.ds.ds_project;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.Observer;
import java.util.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//TODO: import sun.plugin2.message.GetNameSpaceMessage;

/**
 * Realització de les proves A1 i A2 per tal de poder passar la Fita1. Les passa totes sense cap problema.
 * @author Sandy Umasi(1363361), Eloi Sasal(1368473), Miriam Traver(1391805).
 */
public class MainAplication {

    private static final long serialVersionUID = -2666788566300547683L;

    static Project root;
    static Project p1;
    static Project p2;
    static Tasca t1;
    static Tasca t2;
    static Tasca t3;

    static final Logger log= LoggerFactory.getLogger(MainAplication.class);

    //Crida al test que es requereix.
    public static void main (String [] args) throws InterruptedException, IOException, ClassNotFoundException{
        log.info("First log");
        testA2();
        System.out.println("Find el programa!");
    }

    /**
     * Es realitza una prova per cada una de les activitats.
     * @throws InterruptedException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void testA0() throws InterruptedException, IOException, ClassNotFoundException{
        Project ProjectTest = new Project("root", "root Project", null);
        Activitat ActivitatTest = new Activitat("activitatTest", "prova", ProjectTest);
        Tasca TascaTest = new Tasca("tascaTest", "prova", ProjectTest);
        Interval intervalTest = new Interval(TascaTest);
        Serializadora SerializadoraTest = new Serializadora();
    }
    //Prova A1, del dossier.
    public static void testA1() throws InterruptedException, IOException, ClassNotFoundException{

        Serializadora serializadora = new Serializadora();
        serializadora.writeObject(root);

        (new Thread(Rellotge.getRellotge())).start(); //
        root = new Project(".", "", null);
        p1 = new Project("P1", "", root);
        p2 = new Project("P2", "", p1);
        t1 = new Tasca("T1", "", p2);
        t2 = new Tasca("T2", "", p2);
        t3 = new Tasca("T3", "", p1);

        Rellotge.getRellotge().setStep(2);
        t3.play();
        Thread.sleep(3000);
        t3.stop();
        Thread.sleep(7000);
        t2.play();
        Thread.sleep(10000);
        t2.stop();

        t3.play();
        Thread.sleep(2000);
        t3.stop();

        //Guardar-mostrar a fitxers:
        FileOutputStream fileOut = new FileOutputStream("informe.dat");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(root);

        FileInputStream fileIn = new FileInputStream("informe.dat");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        Project project = (Project) in.readObject();

        System.out.println("\n" + project.print());
    }

    //Prova A2, del dossier.
    public static void testA2() throws InterruptedException, IOException, ClassNotFoundException{
        (new Thread(Rellotge.getRellotge())).start(); //
        root = new Project(".", "", null);
        p1 = new Project("P1", "", root);
        p2 = new Project("P2", "", p1);
        t1 = new Tasca("T1", "", p2);
        t2 = new Tasca("T2", "", p2);
        t3 = new Tasca("T3", "", p1);

        Serializadora serializadora = new Serializadora();
        serializadora.writeObject(root);

        Rellotge.getRellotge().setStep(2);
        t3.play();
        Thread.sleep(4000);
        t2.play();
        Thread.sleep(2000);
        t3.stop();
        Thread.sleep(2000);
        t1.play();
        Thread.sleep(4000);
        t1.stop();
        Thread.sleep(2000);
        t2.stop();
        Thread.sleep(4000);
        t3.play();
        Thread.sleep(2000);
        t3.stop();

        //Guardar-mostrar a fitxers:
        FileOutputStream fileOut = new FileOutputStream("informe.dat");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(root);

        FileInputStream fileIn = new FileInputStream("informe.dat");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        Project project = (Project) in.readObject();

        System.out.println("\n" + project.print());

    }
    public static void serializable() throws InterruptedException{
        //TODO
    }

}
