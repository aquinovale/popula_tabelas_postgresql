package valeconsultoriati.br;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UpdateTabela {

	public ArrayList<String> update(String tabela, ResultSet campos, String keyName, String gerador) {
		ArrayList<String> DML = new ArrayList<String>();

		try {
			while (campos.next()) {
				DML.add(updateField(tabela, keyName, campos.getString(keyName), gerador));
			}
		} catch (SQLException e) {
			System.out.println("Algo estranho no processo");
		}
		return DML;
	}

	private String updateField(String tabela, String keyName, String value, String gerador) {
		String query = String.format("UPDATE %s SET %s = '%s' WHERE %s = '%s';", tabela, keyName, geradorNumeros(gerador),
				keyName, value);
		return query;
	}

	private BigInteger geradorNumeros(String gerador) {
		GeraCpfCnpj dados = new GeraCpfCnpj();
		return new BigInteger(!(gerador.equals("CPF")) ? dados.cpf() : dados.cnpj());
	}
}
