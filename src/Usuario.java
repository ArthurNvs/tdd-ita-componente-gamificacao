import java.io.Serializable;

public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nome;
	private Integer pontosTopico;
	private Integer pontosComentario;
	private Integer pontosCurtida;
	private Integer pontosMoeda;
	private Integer pontosEstrela;

	public Usuario(String nome) {
		this.nome = nome;
		this.setPontosComentario(0);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getPontosTopico() {
		return pontosTopico;
	}

	public void setPontosTopico(Integer pontosTopico) {
		this.pontosTopico = pontosTopico;
	}

	public Integer getPontosComentario() {
		return pontosComentario;
	}

	public void setPontosComentario(Integer pontosComentario) {
		this.pontosComentario = pontosComentario;
	}

	public Integer getPontosCurtida() {
		return pontosCurtida;
	}

	public void setPontosCurtida(Integer pontosCurtida) {
		this.pontosCurtida = pontosCurtida;
	}

	public Integer getPontosMoeda() {
		return pontosMoeda;
	}

	public void setPontosMoeda(Integer pontosMoeda) {
		this.pontosMoeda = pontosMoeda;
	}

	public Integer getPontosEstrela() {
		return pontosEstrela;
	}

	public void setPontosEstrela(Integer pontosEstrela) {
		this.pontosEstrela = pontosEstrela;
	}

	public String retornarTodosPontos() {
		String dadosUsuario = "(usuário: " + getNome();

		if (getPontosTopico() != null && getPontosTopico() > 0)
			dadosUsuario += "/ pontos tópico: " + getPontosTopico();

		if (getPontosCurtida() != null && getPontosCurtida() > 0)
			dadosUsuario += "/ pontos curtida: " + getPontosCurtida();

		if (getPontosComentario() != null && getPontosComentario() > 0)
			dadosUsuario += "/ pontos comentário: " + getPontosComentario();

		if (getPontosEstrela() != null && getPontosEstrela() > 0)
			dadosUsuario += "/ pontos estrela: " + getPontosEstrela();

		if (getPontosMoeda() != null && getPontosMoeda() > 0)
			dadosUsuario += "/ pontos moeda: " + getPontosMoeda();

		return dadosUsuario + ")";
	}

}
