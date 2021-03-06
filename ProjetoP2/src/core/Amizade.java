package core;

public class Amizade {

	public void aceitaAmizade(Usuario usuario2, BancoDeDados bancoDeDados, String email) throws Exception {
		Usuario usuario = bancoDeDados.buscaUsuario(email);
		usuario2.aceitaAmizade(usuario);
		usuario.adicionaAmigo(usuario2);
		usuario.notificacoes.add(usuario2.getNome()
				+ " aceitou sua amizade.");
	}

	public void adicionaAmigo(Usuario usuario2, BancoDeDados bancoDeDados, String email) {
		Usuario usuario = bancoDeDados.buscaUsuario(email);
		usuario.notificacoes.add(usuario2.getNome()
				+ " quer sua amizade.");
		usuario.pedidosAmizade.add(usuario2);
	}

	public void removeAmigo(Usuario usuario2, BancoDeDados bancoDeDados, String email) {
		Usuario usuario = bancoDeDados.buscaUsuario(email);
		usuario.notificacoes.add(usuario2.getNome()
				+ " removeu a sua amizade.");
		usuario.amigos.remove(usuario2);
		usuario2.removeAmigo(usuario);
	}

	public void rejeitaAmizade(Usuario usuario2, BancoDeDados bancoDeDados, String email) throws Exception {
		Usuario usuario = bancoDeDados.buscaUsuario(email);
		if (usuario == null) {
			throw new Exception("O usuario " + email
					+ " nao esta cadastrado no +pop.");
		}
		usuario2.rejeitaAmizade(usuario);
		usuario.notificacoes.add(usuario2.getNome()
				+ " rejeitou sua amizade.");
	
	}

}
