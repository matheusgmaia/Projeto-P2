package core;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {

	// boolean logado;//modificar privacidade
	private Usuario usuarioLogado = null;
	private ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
	ArrayList<Usuario> listaUsuarioLogado = new ArrayList<Usuario>();

	/* criar construtor do controller? */

	public String cadastraUsuario(String nomeUsuario, String emailUsuario,
			String senhaUsuario, String dataNasUsuario, String imgAvatar)
			throws Exception {

		if (nomeUsuario.equals("") || nomeUsuario == null
				|| nomeUsuario.equals("  ")) {
			throw new Exception(
					"Erro no cadastro de Usuarios. Nome do usuario nao pode ser vazio.");
		} else {

			Usuario usuario = new Usuario(nomeUsuario, emailUsuario,
					senhaUsuario, dataNasUsuario, imgAvatar);
			listaUsuario.add(usuario);
			return usuario.getEmail();
		}

	}

	public String cadastraUsuario(String nomeUsuario, String emailUsuario,
			String senhaUsuario, String dataNasUsuario) throws Exception {

		if (nomeUsuario.equals("") || nomeUsuario == null
				|| nomeUsuario.equals("  ")) {
			throw new Exception(
					"Erro no cadastro de Usuarios. Nome do usuario nao pode ser vazio.");
		} else {

			Usuario usuario = new Usuario(nomeUsuario, emailUsuario,
					senhaUsuario, dataNasUsuario);
			listaUsuario.add(usuario);

			return usuario.getEmail();
		}

	}

	public boolean login(String emailUsuario, String senhaUsuario)
			throws Exception {
		if (this.usuarioLogado == null) {
			Usuario user = this.buscaUsuario(emailUsuario);
			if (user == null) {
				throw new Exception(
						"Nao foi possivel realizar login. O usuario com email "
								+ emailUsuario + " nao esta cadastrado.");
			}

			else if (user.getSenha().equals(senhaUsuario)) {
				usuarioLogado = user;
				return true;
			}

			else {
				throw new Exception(
						"Nao foi possivel realizar login. Senha Invalida.");
			}
		} else {
			throw new Exception(
					"Nao foi possivel realizar login. Um usuario ja esta logado: "
							+ this.usuarioLogado.getNome() + ".");
		}
	}

	public String getInfoUsuario(String nomeInformacao, String emailUsuario)
			throws Exception {
		String informacaoRequerida = "";
		Usuario usuarioRequerido = this.buscaUsuario(emailUsuario);
		
		if (usuarioRequerido == null) {

			throw new Exception("Um usuario com email " + emailUsuario
					+ " nao esta cadastrado.");
		}

		else if (nomeInformacao.equals("Senha")
				|| nomeInformacao.equals("SENHA")
				|| nomeInformacao.equals("senha")) {
			throw new Exception("A senha do usuario eh protegida.");
		}

		else if (nomeInformacao.equals("Nome") || nomeInformacao.equals("NOME")
				|| nomeInformacao.equals("nome")) {
			informacaoRequerida = usuarioRequerido.getNome();
		}

		else if (nomeInformacao.equals("Foto") || nomeInformacao.equals("FOTO")
				|| nomeInformacao.equals("foto")) {
			informacaoRequerida = usuarioRequerido.getFoto();
		}

		else if (nomeInformacao.equals("Email")
				|| nomeInformacao.equals("EMAIL")
				|| nomeInformacao.equals("email")) {
			informacaoRequerida = usuarioRequerido.getEmail();
		}

		else { 
			String data = usuarioRequerido.getData();
			SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			String output = myFormat.format(input.parse(data));
			informacaoRequerida = output;
		}

		return informacaoRequerida;

	}
	
	
	
	

	public String getNome(String emailUsuario) {
		// String nomeUsuario = "";
		Usuario user = this.buscaUsuario(emailUsuario);
		if (user == null) {
			return null;
		} else {
			return user.getNome();
		}
		// return nomeUsuario;

	}

	public void logout() throws Exception {

		if (this.usuarioLogado != null) {
			this.usuarioLogado = null;
		} else {
			throw new Exception(
					"Nao eh possivel realizar logout. Nenhum usuario esta logado no +pop.");
		}

	}

	public void atualizaSenhaUsuario(String novaSenha) {
		this.usuarioLogado.setSenha(novaSenha);
	}

	public void atualizaNomeUsuario(String novoNome) {
		this.usuarioLogado.setNome(novoNome);
	}

	public void atualizaEmail(String novoEmail) {
		this.usuarioLogado.setEmail(novoEmail);
	}

	public void atualizaFoto(String novaFoto) {
		this.usuarioLogado.setFoto(novaFoto);
	}

	public void ataulizaData(String novaData) {
		this.usuarioLogado.setData(novaData);
	}

	public void atualizaTelefone(String novoTelefone) {
		this.usuarioLogado.setTelefone(novoTelefone);
	}

	public Usuario buscaUsuario(String emailUsuario) {
		Usuario user = null;
		for (Usuario usuario : listaUsuario) {
			if (usuario.getEmail().equals(emailUsuario))
				user = usuario;
		}

		return user;
	}

	public void postarMensagem(String conteudo) throws Exception {
		LocalDate horaAtual = LocalDate.now();
		if (this.usuarioLogado == null) {
			throw new Exception(
					"Nao eh possivel postar mensagem. Nenhum usuario esta logado no +pop.");
		}

		if (conteudo.length() <= 200) {
			Postagem novaPostagem = new Postagem(conteudo, horaAtual.toString());
			this.usuarioLogado.adicionarPostagemAoPerfil(novaPostagem);
			enviaPostagemParaAmgios(novaPostagem);

		} else {
			throw new Exception("Apenas 200 caracteres seu cego");
		}

	}

	private void enviaPostagemParaAmgios(Postagem novaPostagem) {
		for (Usuario usuario : usuarioLogado.getAmigos()) {
			usuario.adicionarMensagemAoMural(novaPostagem);
		}
	}

}
