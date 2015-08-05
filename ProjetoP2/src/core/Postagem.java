package core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

public class Postagem {
	private String mensagem;
	private String data;
	private int pontosPopularidade;
	private ArrayList<String> tags = new ArrayList<String>();
	private int likes;
	private int deslikes;

	public Postagem(String conteudo, String data) {
		this.mensagem = conteudo;
		this.data = data;
		this.pontosPopularidade = 0;
		this.likes = 0;
		this.deslikes = 0;
		this.tags.addAll(procuraHastags(conteudo));
	}

	private Collection<? extends String> procuraHastags(String conteudo2) {
		return tags;
		// num sei
	}
	
	public String getConteudo(){
		return mensagem;
	}
	
	public String getData() throws Exception{
		String data = this.data;
		SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String output = myFormat.format(input.parse(data));
		return output;
	}
}
