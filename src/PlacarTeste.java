import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class PlacarTeste {
	
	Placar placar;
	Armazenamento armazem;
	Usuario usuario;
	
	public void iniciaPlacar() throws IOException {
		armazem = new Armazenamento();
		placar = new Placar(armazem);
		
	}
	
	@Test
	public void registrarPontoParaUsuario() throws Exception {
		iniciaPlacar();
		assertEquals("(usuário: player/ pontos curtida: 10)", placar.pegarPontosUsuario(placar.pontuarUsuario("player", 10, "curtida")));
	}
	
	@Test
	public void retornarPontosDeUsuario() throws ClassNotFoundException, IOException, NoFileException, CampoInvalidoException {
		iniciaPlacar();
		usuario = placar.pontuarUsuario("player2", 40, "estrela");
		usuario = placar.pontuarUsuario("player2", 20, "curtida");
		assertEquals("(usuário: player2/ pontos curtida: 20/ pontos estrela: 40)", placar.pegarPontosUsuario(usuario));
	}
	
	@Test
	public void pontuarUsuarioInexistente() throws ClassNotFoundException, IOException, NoFileException, CampoInvalidoException {
		iniciaPlacar();
		assertEquals("usuário inexistente", placar.pegarPontosUsuario(placar.pontuarUsuario("", 20, "estrela")));
	}
	
	@Test(expected=CampoInvalidoException.class)
	public void pontuarUsuarioCampoInvalido() throws ClassNotFoundException, IOException, NoFileException, CampoInvalidoException {
		iniciaPlacar();
		assertEquals("usuário inexistente", placar.pegarPontosUsuario(placar.pontuarUsuario(null, null, null)));
	}
	
	@Test
	public void retornarPontosSemUsuario() throws FileNotFoundException, ClassNotFoundException, IOException, NoFileException {
		iniciaPlacar();
		assertEquals("nenhum usuário encontrado", placar.pegarPontosUsuario(usuario));
	}
	
	@Test
	public void retornarRankingPorTipoPonto() throws ClassNotFoundException, IOException, NenhumUsuarioCadastradoException, NoFileException, CampoInvalidoException {
		iniciaPlacar();
		placar.pontuarUsuario("player3", 20, "curtida");
		placar.pontuarUsuario("player4", 80, "curtida");
		placar.pontuarUsuario("playeSemPontos", 0, "curtida");
		assertEquals("curtida -->  player4 com 80, player2 com 20, player3 com 20, player com 10 ", placar.rankingPorTipo("curtida"));
	}
	
	@Test(expected=CampoInvalidoException.class)
	public void retornarRankingPorTipoPontoErro() throws ClassNotFoundException, IOException, NenhumUsuarioCadastradoException, NoFileException, CampoInvalidoException {
		iniciaPlacar();
		placar.rankingPorTipo("");
	}

}
