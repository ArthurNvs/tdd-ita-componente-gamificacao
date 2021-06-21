import java.io.IOException;

public interface Armazenamentos {
	
	public void lerDadosArmazenados() throws IOException;
	public void gravarPlacar(String usuario, Integer pontos, String tipoPontos) throws IOException;

}
