import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Placar {
	
	Armazenamento database;
	String dadosUsuario;
	
	public Placar(Armazenamento database) {
		this.database = database;
	}

	public Usuario pontuarUsuario(String nomeUsuario, Integer pontos, String tipoPonto) throws IOException, ClassNotFoundException, NoFileException, CampoInvalidoException {
		if(nomeUsuario == null || pontos == null || tipoPonto == null)
			throw new CampoInvalidoException();
		
		Usuario usuario = new Usuario(nomeUsuario);
		
		if (database.lerDadosDeUsuario(nomeUsuario) != null) {
			usuario = database.lerDadosDeUsuario(nomeUsuario);
			pontuaPorTipo(pontos, tipoPonto, usuario);
			database.gravarDadosUsuario(usuario);
			return usuario;
		}
		
		pontuaPorTipo(pontos, tipoPonto, usuario);
		try {
			database.gravarDadosUsuario(usuario);
		} catch (IOException e) {
			//
		}
		
		return usuario;
	}
	
	public String pegarPontosUsuario(Usuario usuario) throws FileNotFoundException, ClassNotFoundException, IOException, NoFileException {
		if (usuario != null)
			return mostrarDadosUsuario(usuario);
		
		return "nenhum usuário encontrado";
	}

	private void pontuaPorTipo(Integer pontos, String tipoPonto, Usuario usuario) {
		if(tipoPonto == "estrela")
			usuario.setPontosEstrela(pontos);
		
		if(tipoPonto == "curtida")
			usuario.setPontosCurtida(pontos);
		
		if(tipoPonto == "topico")
			usuario.setPontosTopico(pontos);
		
		if(tipoPonto == "comentario")
			usuario.setPontosComentario(pontos);
		
		if(tipoPonto == "moeda")
			usuario.setPontosMoeda(pontos);
	}

	private String mostrarDadosUsuario(Usuario usuario) throws FileNotFoundException, ClassNotFoundException, IOException, NoFileException {
		if (database.lerDadosDeUsuario(usuario.getNome()) != null)
			return usuario.retornarTodosPontos();
		
        return "usuário inexistente";
	}

	@SuppressWarnings("unchecked")
	public String rankingPorTipo(String tipoPonto) throws ClassNotFoundException, IOException, NenhumUsuarioCadastradoException, CampoInvalidoException {
		if (tipoPonto == null || tipoPonto == "")
				throw new CampoInvalidoException();
		
		HashMap<String, HashMap<String, Integer>> usuarios = database.retornarPontosUsuarios();
		@SuppressWarnings("rawtypes")
		Iterator iterator = usuarios.entrySet().iterator();
		String resultado = tipoPonto + " --> ";
		
		Map<String, Integer> usuarioPonto = new  HashMap<String, Integer>();
		
		while(iterator.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry<String, HashMap<String, Integer>> entry = (Map.Entry)iterator.next();
			if  (entry.getValue().containsKey(tipoPonto)) 
				usuarioPonto.put(entry.getKey(), entry.getValue().get(tipoPonto));
		}
		
		Map<String, Integer> usuariosEmOrdemm = ordenaComComparator(usuarioPonto, false);
		resultado += usuariosEmOrdemm.toString().replaceAll("[{}]", " ");
		return resultado.replace("=", " com ");
		
	}

	private Map<String, Integer> ordenaComComparator(Map<String, Integer> usuarioPonto, boolean order) {
		List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(usuarioPonto.entrySet());

        Collections.sort(list, new Comparator<Entry<String, Integer>>() {
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                if (order)
                    return o1.getValue().compareTo(o2.getValue());

                return o2.getValue().compareTo(o1.getValue());
            }
        });

        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Entry<String, Integer> entry : list)
            sortedMap.put(entry.getKey(), entry.getValue());

        return sortedMap; 
	}
}
