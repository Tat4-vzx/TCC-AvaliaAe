package org.example.dao;

import org.example.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FeedBackDAO {

    public static void salvarFeedBack(String mensagem, String nome) {
        String sql = "INSERT INTO feedback (mensagem, nome) VALUES (?, ?)";

        try (Connection conexao = Conexao.conectar()) {

            if (conexao == null) {
                System.err.println("Não foi possível salvar o feedback. Conexão nula.");
                return;
            }

            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, mensagem);

                stmt.executeUpdate();
                System.out.println("Feedback anônimo enviado com sucesso!");
            }

        } catch (SQLException erro) {
            System.err.println("Erro ao salvar o feedback no banco:");
            erro.printStackTrace();
        }
    }
}