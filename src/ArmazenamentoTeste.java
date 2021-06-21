import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;

import org.junit.Test;

public class ArmazenamentoTeste {
	
	Usuario usuario;
	Armazenamento armazenamento;
	
	public void iniciaMocks() {
		usuario = new Usuario("teste");
		usuario.setPontosTopico(25);
		usuario.setPontosEstrela(10);
		
		armazenamento = new Armazenamento();
		
	}

	@Test
	public void armazenarPlacarDeUsuario() throws Exception {
		iniciaMocks();
		armazenamento.gravarDadosUsuario(usuario);
	}
	
	@Test
	public void lerDadosPorUsuario() throws Exception {
		iniciaMocks();
		assertEquals("teste", armazenamento.lerDadosDeUsuario("teste").getNome());
	}
	
	@Test(expected=NullPointerException.class)
	public void lerDadosPorUsuarioVazio() throws Exception {
		iniciaMocks();
		assertEquals("teste", armazenamento.lerDadosDeUsuario("").getNome());
	}
	
	@Test
	public void retornarTodosUsuariosCadastrados() throws ClassNotFoundException, IOException, NenhumUsuarioCadastradoException {
		iniciaMocks();
		assertFalse(armazenamento.retornarUsuariosCadastrados().isEmpty());
		
	}
	
	@Test
	public void retornarPontosValidosDeTodosUsuario() throws ClassNotFoundException, IOException, NenhumUsuarioCadastradoException {
		iniciaMocks();
		armazenamento.retornarPontosUsuarios();
	}
	
}
