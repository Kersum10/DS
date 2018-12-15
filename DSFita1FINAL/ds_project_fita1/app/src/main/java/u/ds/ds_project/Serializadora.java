package u.ds.ds_project;

import java.io.*;

/**
 * Serialitzable: Aquesta classe fa ús del rellotge per poder observar els canvis i anar notificant.
 * @author Sandy Umasi(1363361), Eloi Sasal(1368473), Miriam Traver(1391805).
 */
public class Serializadora implements Serializable{
    private static final long serialVersionUID = -2666788566300547683L;
    protected static ObjectInputStream readerObject;
    protected static ObjectOutputStream writerObject;

    public void writeObject(Object objeto) {
        try {
            writerObject = new ObjectOutputStream(new FileOutputStream("informe.dat"));
            writerObject.writeObject(objeto);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object readObject(String nameFile){
        Object back = null;
        try {
            readerObject = new ObjectInputStream(new FileInputStream(nameFile));
            back = readerObject.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return back;
    }
}
