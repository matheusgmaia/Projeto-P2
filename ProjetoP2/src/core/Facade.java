package core;

public class Facade {

	Controller controller = null;
	
	public void iniciaSistema(){
		controller = new Controller();
	}
	
	public void fechaSistema()throws Exception{
		if (controller.getUsuarioLogado() != null){
			throw new Exception ("Nao foi possivel fechar o sistema. Um usuarix ainda esta logadx.");
		}
		
		else{
			this.controller = null;
		}
	}

	

	public String cadastraUsuario(String nomeUsuario, String emailUsuario,
			String senhaUsuario, String dataNasUsuario, String imgAvatar) throws Exception {
		controller.cadastraUsuario(nomeUsuario, emailUsuario, senhaUsuario,
				dataNasUsuario, imgAvatar);

		return emailUsuario;
	}

	public String cadastraUsuario(String nomeUsuario, String emailUsuario,
			String senhaUsuario, String dataNasUsuario) throws Exception {
		controller.cadastraUsuario(nomeUsuario, emailUsuario, senhaUsuario,
				dataNasUsuario);

		return emailUsuario;
	}

	public String getInfoUsuario(String nomeInformacao, String emailUsuario) throws Exception {
		return controller.getInfoUsuario(nomeInformacao, emailUsuario);

	}
	
	public String getInfoUsuario(String nomeInformacao) throws Exception{
		return controller.getInfoUsuarioLogado(nomeInformacao);
	}

	public void login(String emailUsuario, String senhaUsuario)
			throws Exception {
		controller.login(emailUsuario, senhaUsuario);
	}

	public void logout() throws Exception {
		controller.logout();

	}
	
	public void removeUsuario(String email){
		controller.removeUsuario(email);
	}

	public String getNome(String email) throws Exception {
		String nomeUsuario = controller.getNome(email);
		if (nomeUsuario == null) {
			throw new Exception("O usuario com email " + email
					+ " nao esta cadastrado.");
		} else {
			return nomeUsuario;
		}
	}

	public void atualizaSenhaUsuarioFacade(String novaSenha) {
		controller.atualizaSenhaUsuario(novaSenha);
	}

	public void atualizaNomeUsuario(String novoNome) {
		controller.atualizaNomeUsuario(novoNome);
	}

	public void atualizaEmail(String novoEmail) {
		controller.atualizaEmail(novoEmail);
	}

	public void atualizaFoto(String novaFoto) {
		controller.atualizaFoto(novaFoto);
	}

	public void atualizaData(String novaData) {
		controller.ataulizaData(novaData);
	}

	public void atualizadaTel(String novoTelefone) {
		controller.atualizaTelefone(novoTelefone);
	}

	public Usuario buscaUsuario(String emailUsuario) throws Exception {
		return controller.buscaUsuario(emailUsuario);

	}
	
	public void postarMensagem(String conteudo) throws Exception {
		controller.postarMensagem(conteudo);
	}
	
}
