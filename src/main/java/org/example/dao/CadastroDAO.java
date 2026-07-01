package org.example.dao;

import org.example.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastroDAO {
    public CadastroDAO() {
    }

    public static void salvarCadastro(String nome, String email, String senha) {
        String sql = "INSERT INTO login (nome, email, senha) VALUES (?, ?, ?)";

        // O primeiro try abre e gerencia a conexão
        try (Connection conexao = Conexao.conectar()) {

            if (conexao == null) {
                System.err.println("Não foi possível salvar. Conexão nula.");
                return;
            }

            // O segundo try gerencia o PreparedStatement de forma limpa
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, nome);
                stmt.setString(2, email);
                stmt.setString(3, senha);

                stmt.executeUpdate();
                System.out.println("Cadastro de '" + nome + "' salvo com sucesso!");
            } // O 'stmt' fecha automaticamente bem aqui, mesmo se der erro

        } catch (SQLException erro) {
            System.err.println("Erro ao salvar o cadastro no banco:");
            erro.printStackTrace();
        } // O 'conexao' fecha automaticamente bem aqui
    }

}