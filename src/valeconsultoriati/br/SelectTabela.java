package valeconsultoriati.br;

public class SelectTabela {
	
	public static int LIMIT = 10000;
	
	public String buildQuery(String tabela, String campo, int offSet){
		String query = String.format("SELECT %s FROM %s ORDER BY 1 LIMIT %d OFFSET %d;", campo, tabela, LIMIT, offSet);
		System.out.println("Registros: " + offSet);
		return query;
	}
	
	public String buildQueryCount(String tabela){
		String query = String.format("SELECT count(*) AS total FROM %s;", tabela);
		System.out.println("Query: " + query);
		return query;
	}
	
}
