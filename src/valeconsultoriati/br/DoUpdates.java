package valeconsultoriati.br;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DoUpdates {
	
	private Connect connect;
	
	public DoUpdates(Connect connect) {
		this.connect = connect;
	}
	
	public boolean executeUpdate(String tabela, String campo, int offSet, String gerador) {
		ArrayList<String> updates = prepareUpdates(tabela, campo, offSet, gerador);
		for(String update : updates){
			connectUpdate(update);
		}
		return true;
	}
	
	public boolean connectUpdate(String query) {
		return this.connect.executeUpdate(query);
	}
	
	public ResultSet connect(String query) {
		return this.connect.execute(query);
	}

	private ArrayList<String> prepareUpdates(String tabela, String campo, int offSet, String gerador) {
		return new UpdateTabela().update(tabela, connect(executeQuery(tabela, campo, offSet)), campo, gerador);
	}

	private String executeQuery(String tabela, String campo, int offSet) {
		return new SelectTabela().buildQuery(tabela, campo, offSet);
	}	

}
