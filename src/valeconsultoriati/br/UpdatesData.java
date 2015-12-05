package valeconsultoriati.br;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdatesData {
	
	private DoUpdates updates;
	
	public UpdatesData(String url, String user, String password) {
		updates = new DoUpdates(new Connect(url, user, password));
	}
	
	public void doUpdates(String tabela, String campo, String gerador){
		ResultSet rs = updates.connect(new SelectTabela().buildQueryCount(tabela));
		int paginacao = 0;
		int total = 0;
		int count = 0;
		try {
			while (rs.next()){
				paginacao = rs.getInt("total")/ SelectTabela.LIMIT;
				count = rs.getInt("total");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("######  Total Registros: " + count);
		for(int i = 0; i <= paginacao; i++){
			updates.executeUpdate(tabela, campo, total, gerador);
			total+=SelectTabela.LIMIT;
		}
		System.out.println("######  TABELA FINALIZADA  #######");
	}
	
	
}
