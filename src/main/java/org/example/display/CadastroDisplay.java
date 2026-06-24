package org.example.display;

import org.example.dao.CadastroDAO;

import javax.swing.*;
import java.awt.*;

public class CadastroDisplay extends  JFrame {
    public CadastroDisplay() {
        setTitle("Cadastro");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel lblNome = new JLabel("Nome: ");
        JTextField textNome = new JTextField();

        JLabel lblEmail = new JLabel("Email: ");
        JTextField textEmail = new JTextField();

        JLabel lblSenha = new JLabel("Senha: ");
        JTextField textSenha = new JTextField();

        JButton bntSalvar = new JButton("Fazer cadastro");


        bntSalvar.addActionListener(e -> {
            String nome = textNome.getText();
            String email = textEmail.getText();
            String senha = textSenha.getText();
            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Insira seu nome");
            } else if (email.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Insira seu email");
            } else if (senha.length() < 6) {
                JOptionPane.showMessageDialog(null, "Insira sua senha mais de 6 caracteres");
            } else {
                CadastroDAO cadastroDAO = new CadastroDAO();
                cadastroDAO.salvarCadastro(nome, email, senha);
            }
        });

        painel.add(lblNome);
        painel.add(textNome);
        painel.add(lblEmail);
        painel.add(textEmail);
        painel.add(lblSenha);
        painel.add(textSenha);
        painel.add(new JLabel(""));
        painel.add(bntSalvar);
        add(painel);

    }
}
