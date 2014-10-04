package ca.etsmtl.log720.lab1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serialisation {
	/**
	 * Serialisation d'un objet depuis un fichier
	 * @param object
	 * @param fileName
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void encodeToFile(Object object, String fileName) throws FileNotFoundException, IOException {
		 FileOutputStream fileOut = new FileOutputStream(fileName);
		 ObjectOutputStream out = new ObjectOutputStream(fileOut);
		 out.writeObject(object);
		 out.close();
		 fileOut.close();
		 System.out.printf("Serialisation de l'objet dans "+fileName);
    }
	
	/**
	 * Deserialisation d'un objet depuis un fichier
	 * @param fileName
	 * @return obj
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
    public static Object decodeFromFile(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
		Object objectToReturn = null;
		FileInputStream fileIn = new FileInputStream(fileName);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		objectToReturn = in.readObject();
		in.close();
		fileIn.close();
		return objectToReturn;
    }
}
