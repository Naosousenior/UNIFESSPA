package tests;

import servicosTecnicos.ConexaoBD;
import servicosTecnicos.Env;

public class TestaConexao {
    public static void main(String[] args) {
        try {
        	Env.load("src/assets/environment.env");
            ConexaoBD.initConnection(Env.get("BD_URL"),Env.get("BD_USER"),Env.get("BD_PASSWORD"));
        } catch (Exception e) {
            System.err.println("Erro na execução: " + e.getMessage());
        }
    }
}
