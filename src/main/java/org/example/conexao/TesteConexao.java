package org.example.conexao;

import java.sql.Connection;
import java.sql.SQLException;

public class TesteConexao {
    public static void main(String[] args) {
        System.out.println("Iniciando o teste de conexão...");

        // O try-with-resources garante o fechamento da conexão se ela for aberta com sucesso
        try (Connection conn = Conexao.conectar()) {

            if (conn != null) {
                System.out.println("Sucesso! Conexão com o banco 'ttmpa' estabelecida.");
            } else {
                System.err.println("Falha: A conexão retornou nula.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao fechar os recursos: " + e.getMessage());
        }
    }
}
