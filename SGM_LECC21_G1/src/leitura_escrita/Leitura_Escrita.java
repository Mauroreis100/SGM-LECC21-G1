package leitura_escrita;

import java.io.*;

public class Leitura_Escrita {

	//Método para gravar o objecto no caminho especificado
	public static boolean gravarObjecto(Object obj, String caminho) {
		File arquivo=new File(caminho);
		if(! arquivo.exists()) {
			try {
				arquivo.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			FileOutputStream fileOutput=new FileOutputStream(arquivo);
			ObjectOutputStream objOutput=new ObjectOutputStream(fileOutput);
			objOutput.writeObject(obj);
			objOutput.flush();
			fileOutput.flush();
			objOutput.close();
			fileOutput.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}

//Método para gravar o objecto no caminho especificado
	public static Object recuperarObjecto(String caminho) {
		File arquivo=new File(caminho);
		try {
			FileInputStream fileInput=new FileInputStream(arquivo);
			ObjectInputStream objInput=new ObjectInputStream(fileInput);
			Object retorno=objInput.readObject();
			objInput.close();
			fileInput.close();
			return retorno;
			}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
}
