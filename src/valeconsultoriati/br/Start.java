package valeconsultoriati.br;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.StringTokenizer;

public class Start {
	
	static Properties prop;

	public static void getProp(String path){
		prop = new Properties();
		System.out.println("Path Conf:" + path);
		FileInputStream file;
		try {
			file = new FileInputStream(path);
			prop.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		String path = System.getProperty("path.conf");
		
		if (path == null && args != null){
			path = args[0];
		}
		getProp(path);
		if (path != null) {
			lerArquivo(prop.getProperty("path.ofuscadores"));
		} else {
			System.out.println("Arquivo ofuscador não encontrado, defina o parametro.");
		}
	}

	private static void lerArquivo(String path) {
		FileReader reader;
		BufferedReader leitor;
		try {
			reader = new FileReader(new File(path));
			leitor = new BufferedReader(reader);
			String linha = null;

			while ((linha = leitor.readLine()) != null) {
				tokenLinha(linha);
			}
			leitor.close();
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void tokenLinha(String linha) {
		StringTokenizer token = new StringTokenizer(linha, "->");
		ArrayList<String> dados = new ArrayList<String>();
		while (token.hasMoreTokens()) {
			dados.add(token.nextToken());
		}
		String gerador = "cpf";
		try {
			gerador = dados.get(2);
		} catch (Exception e) {

		}
		System.out.println("Tabela:" + dados.get(0) + " campo: " + dados.get(1) + " Gerador: " + gerador);

		String url = String.format("jdbc:postgresql://%s:%s/%s",
				((prop.getProperty("host") == null) ? "localhost" : prop.getProperty("host")),
				((prop.getProperty("port") == null ? "5432" : prop.getProperty("port"))),
				((prop.getProperty("database") == null ? "postgres" : prop.getProperty("database"))));

		UpdatesData update = new UpdatesData(url,
				((prop.getProperty("user") == null ? "postgres" : prop.getProperty("user"))),
				((prop.getProperty("password") == null ? "" : prop.getProperty("password"))));

		update.doUpdates(dados.get(0), dados.get(1), gerador);
	}

}
