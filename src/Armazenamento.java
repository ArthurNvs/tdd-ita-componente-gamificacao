import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Armazenamento {

	Usuario usuario;
	private String filePath = "/Users/arthur/Projetos/ita-java/ita/componente-gamificacao/usuarios/";

	public void gravarDadosUsuario(Usuario usuario) throws IOException {
		try {
			FileOutputStream fileOut = new FileOutputStream(filePath + usuario.getNome());
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(usuario);
			objectOut.close();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	public Usuario lerDadosDeUsuario(String nomeUsuario)
			throws FileNotFoundException, IOException, ClassNotFoundException, NoFileException {
		usuario = new Usuario(nomeUsuario);

		try {
			File file = new File(filePath + usuario.getNome());
			ObjectInputStream object = new ObjectInputStream(new FileInputStream(file));
			usuario = (Usuario) object.readObject();
			object.close();

			if (usuario.getPontosComentario() == null)
				return null;

			return usuario;
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	public ArrayList<Usuario> retornarUsuariosCadastrados()
			throws ClassNotFoundException, IOException, NenhumUsuarioCadastradoException {
		ArrayList<Usuario> usuariosCadastrados = new ArrayList<Usuario>();
		File file = new File(filePath);
		File[] usuariosFile = file.listFiles();

		for (File usuarioFile : usuariosFile) {
			ObjectInputStream object = new ObjectInputStream(new FileInputStream(filePath + usuarioFile.getName()));
			usuario = (Usuario) object.readObject();
			object.close();
			usuariosCadastrados.add(usuario);
		}

		if (usuariosCadastrados.isEmpty())
			throw new NenhumUsuarioCadastradoException();

		return usuariosCadastrados;
	}

	public HashMap<String, HashMap<String, Integer>> retornarPontosUsuarios() throws ClassNotFoundException, IOException, NenhumUsuarioCadastradoException {
		ArrayList<Usuario> usuariosCadastrados = retornarUsuariosCadastrados();
		HashMap<String, HashMap<String, Integer>> pontosUsuarios = new HashMap<>();

		for (Usuario usuario : usuariosCadastrados) {
			HashMap<String, Integer> pontosDoUsuario = new HashMap<>();

			if (usuario.getPontosComentario() != null && usuario.getPontosComentario() > 0)
				pontosDoUsuario.put("comentario", usuario.getPontosComentario());

			if (usuario.getPontosMoeda() != null && usuario.getPontosMoeda() > 0)
				pontosDoUsuario.put("moedas", usuario.getPontosMoeda());

			if (usuario.getPontosCurtida() != null && usuario.getPontosCurtida() > 0)
				pontosDoUsuario.put("curtida", usuario.getPontosCurtida());

			if (usuario.getPontosEstrela() != null && usuario.getPontosEstrela() > 0)
				pontosDoUsuario.put("estrela", usuario.getPontosEstrela());

			if (usuario.getPontosTopico() != null && usuario.getPontosTopico() > 0)
				pontosDoUsuario.put("topico", usuario.getPontosTopico());

			pontosUsuarios.put(usuario.getNome(), pontosDoUsuario);
		}

		return pontosUsuarios;
	}

}
