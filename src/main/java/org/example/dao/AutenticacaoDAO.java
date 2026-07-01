package org.example.dao;

import org.example.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

    public class AutenticacaoDAO {

        public static boolean autenticar(String email, String senha) {
            // A consulta busca qualquer registro que bata exatamente com e-mail e senha
            String sql = "SELECT 1 FROM login WHERE email = ? AND senha = ?";

            // O primeiro try gerencia a conexão
            try (Connection conexao = Conexao.conectar()) {

                if (conexao == null) {
                    System.err.println("Não foi possível autenticar. Conexão nula.");
                    return false;
                }

                // O segundo try gerencia o PreparedStatement
                try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                    stmt.setString(1, email);
                    stmt.setString(2, senha);

                    // O terceiro try gerencia o ResultSet (resultado da consulta)
                    try (ResultSet rs = stmt.executeQuery()) {
                        // Se rs.next() for verdadeiro, significa que encontrou a combinação no banco
                        if (rs.next()) {
                            System.out.println("Autenticação bem-sucedida para: " + email);
                            return true;
                        }
                    } // rs é fechado automaticamente aqui
                } // stmt é fechado automaticamente aqui

            } catch (SQLException erro) {
                System.err.println("Erro ao validar credenciais no banco:");
                erro.printStackTrace();
            } // conexao é fechada automaticamente aqui

            // Se chegou até aqui, ou não encontrou o usuário ou deu erro
            return false;
        }
    }

