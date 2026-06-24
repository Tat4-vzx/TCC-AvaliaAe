package org.example.dao;

import org.example.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    // Método que busca no banco se existe o nome e a senha informados
    public static boolean validarLogin(String nome, String senha) {
        // Busca na tabela cadastro (onde estão os dados reais do usuário)
        String sql = "SELECT * FROM login WHERE nome = ? AND senha = ?";

        try (Connection conexao = Conexao.conectar()) {
            if (conexao == null) {
                return false;
            }

            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, nome);
                stmt.setString(2, senha);

                // Executa a consulta no banco
                try (ResultSet rs = stmt.executeQuery()) {
                    // Se o banco retornar alguma linha, significa que o usuário e senha estão corretos!
                    return rs.next();
                }
            }
        } catch (SQLException erro) {
            System.err.println("Erro ao validar login no banco:");
            erro.printStackTrace();
            return false;
        }
    }
}