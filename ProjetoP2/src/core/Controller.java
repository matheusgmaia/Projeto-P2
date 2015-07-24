package core;

import java.util.ArrayList;

public class Controller {

	boolean logado;
	ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
	ArrayList<Usuario> listaUsuarioLogado = new ArrayList<Usuario>();
	/* criar construtor do controller? */
	/* criar construtor do controller? */
			
	public void cadastraUsuario(String emailUsuario, String senhaUsuario,
			String nomeUsuario, String dataNasUsuario, String telContatoUsuario) {
		Usuario usuario = new Usuario(emailUsuario, senhaUsuario, nomeUsuario,
				dataNasUsuario, telContatoUsuario);
		listaUsuario.add(usuario);

	}

	public void loginUsuario(String emailUsuario, String senhaUsuario) {
		for (int i = 0; i < listaUsuario.size(); i++) {
			if (listaUsuario.get(i).getemailUsuario().equals(emailUsuario)
					&& listaUsuario.get(i).getSenhaUsuario()
							.equals(senhaUsuario)
					&& listaUsuarioLogado.size() == 0) {
				this.logado = true;
				listaUsuarioLogado.add(listaUsuario.get(i));
			}
		}

	}

	public void logout(String emailUsuario) {
		for (int i = 0; i < listaUsuarioLogado.size(); i++) {

			if (listaUsuarioLogado.get(i).getemailUsuario()
					.equals(emailUsuario)
					&& this.getStatus() == true) {
				listaUsuarioLogado.remove(0);
				this.logado = false;
			}

		}

	}

	public boolean getStatus() {
		return logado;
	}

	public void atualizaSenhaUsuario(String emailUsuario, String antigaSenha,
			String novaSenha) {
		for (int i = 0; i < listaUsuarioLogado.size(); i++) {
			if (listaUsuarioLogado.get(i).getemailUsuario()
					.equals(emailUsuario)) {
				listaUsuarioLogado.get(i).setNovaSenha(novaSenha);
			}
		}

	}

}
