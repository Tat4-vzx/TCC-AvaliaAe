package org.example.dao;

import org.example.conexao.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

                // Se o nome não for nulo nem vazio, salva o nome. Caso contrário, envia NULL.
                if (nome != null && !nome.trim().isEmpty()) {
                    stmt.setString(2, nome.trim());
                } else {
                    stmt.setNull(2, Types.VARCHAR);
                }

                stmt.executeUpdate();
                System.out.println(nome != null ? "Feedback de " + nome + " enviado!" : "Feedback anônimo enviado!");
            }

        } catch (SQLException erro) {
            System.err.println("Erro ao salvar o feedback no banco:");
            erro.printStackTrace();
        }
    }
    public static List<String[]> listarFeedbacks() {
        List<String[]> lista = new ArrayList<>();
        String sql = "SELECT nome, mensagem FROM feedback ORDER BY id DESC"; // Ordena pelos mais recentes

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (conexao == null) {
                System.err.println("Não foi possível listar os feedbacks. Conexão nula.");
                return lista;
            }

            while (rs.next()) {
                String nome = rs.getString("nome");
                String mensagem = rs.getString("mensagem");

                // Se o nome for nulo ou vazio no banco, exibe como Anônimo na tabela
                if (nome == null || nome.trim().isEmpty()) {
                    nome = "Anônimo";
                }

                lista.add(new String[]{nome, mensagem});
            }

        } catch (SQLException erro) {
            System.err.println("Erro ao listar os feedbacks do banco:");
            erro.printStackTrace();
        }

        return lista;
    }
}