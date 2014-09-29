package ca.etsmtl.log720.lab1;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Serialisation {
	/**
	 * Serialisation d'un objet depuis un fichier
	 * @param object
	 * @param fileName
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void encodeToFile(Object object, String fileName) throws FileNotFoundException, IOException {
        // ouverture de l'encodeur vers le fichier
        XMLEncoder encoder = new XMLEncoder(new FileOutputStream(fileName));
        try {
            // serialisation de l'objet
            encoder.writeObject(object);
            encoder.flush();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            // fermeture de l'encodeur
            encoder.close();
        }
    }
	
	/**
	 * Deserialisation d'un objet depuis un fichier
	 * @param fileName
	 * @return obj
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
    public static Object decodeFromFile(String fileName) throws FileNotFoundException, IOException {
        Object object = null;
        // ouverture de decodeur
        XMLDecoder decoder = new XMLDecoder(new FileInputStream(fileName));
        try {
            // deserialisation de l'objet
            object = decoder.readObject();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            // fermeture du decodeur
            decoder.close();
        }
        return object;
    }
}
