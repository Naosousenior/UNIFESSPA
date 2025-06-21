package servicosTecnicos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class ConexaoBD {
    private static Connection banco;
    

    public static void initConnection(String url,String user,String password) throws SQLException {
    	ConexaoBD.banco = DriverManager.getConnection(url,user,password);
    	System.out.println("Conexão realizada com sucesso!");
    }

    public static void executeInstrucao(String stmt) throws SQLException {
    	Statement st = ConexaoBD.banco.createStatement();
    	st.execute(stmt);
    	st.close();
    }
    
    public static ResultSet queryInstrucao(String stmt) throws SQLException {
    	Statement st = ConexaoBD.banco.createStatement();
    	ResultSet rs = st.executeQuery(stmt);
    	st.close();
    	
    	return rs;
    }
}
