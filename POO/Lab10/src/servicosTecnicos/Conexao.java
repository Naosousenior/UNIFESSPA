// Define o pacote onde a classe está localizada
package servicosTecnicos;

// Importa as bibliotecas necessárias para exibir mensagens e conectar ao banco de dados
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Classe utilitária responsável por fornecer conexões com o banco de dados
public class Conexao {

    // Constantes com os dados necessários para conectar ao banco de dados
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/unifesspa"; // URL do banco (host, porta, nome do banco)
    private static final String USER = "root";       // Usuário do banco de dados
    private static final String PASS = "serenaya";   // Senha do banco de dados

    // Método público e estático que retorna uma conexão com o banco
    public static Connection getConnection() throws SQLException {
        Connection connection = null;

        try {
            // Carrega o driver JDBC do MySQL (necessário para que o Java saiba como se conectar ao banco)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Cria a conexão com o banco de dados usando os dados fornecidos
            connection = DriverManager.getConnection(JDBC_URL, USER, PASS);

        } catch (ClassNotFoundException e) {
            // Mostra uma mensagem de erro se o driver não for encontrado
            JOptionPane.showMessageDialog(null, "Driver MySQL JDBC Driver não encontrado!");
            e.printStackTrace();

        } catch (SQLException ex) {
            // Mostra uma mensagem se ocorrer algum erro na conexão com o banco
            JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco!");
        }

        // Retorna a conexão criada (pode ser null se houve erro)
        return connection;

        // Linha comentada que era usada como alternativa: chamada direta do DriverManager
        // return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
