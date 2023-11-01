package interfaces;

import java.util.Vector;

public interface Operacoes {

	public Vector recuperarBD();

	public boolean gravarObjecto(Vector objecto);

	public int procurarCodigo(Vector lista, int codigo);

	public Vector removerObjecto(Vector lista, int codigo);

	public boolean existe(String nome, Vector lista);

	public int procurarNome(String nome, Vector lista);
	
}
