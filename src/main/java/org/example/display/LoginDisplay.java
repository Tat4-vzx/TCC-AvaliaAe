package org.example.display;

import org.example.dao.LoginDAO;
import javax.swing.*;
import java.awt.*;

public class LoginDisplay extends JFrame {

    public LoginDisplay() {
        setTitle("Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel lblNome = new JLabel("Nome: ");
        JTextField textNome = new JTextField();

        JLabel lblSenha = new JLabel("Senha: ");
        JPasswordField textSenha = new JPasswordField(); // Alterado para ocultar a senha na tela

        JButton btnSalvar = new JButton("Entrar");

        // O evento do botão agora captura e valida os dados corretamente
        btnSalvar.addActionListener(e -> {
            // Captura o texto exatamente no momento do clique
            String nome = textNome.getText().trim();
            String senha = new String(textSenha.getPassword()).trim();

            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, insira seu nome.");
            } else if (senha.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, insira sua senha.");
            } else {
                // 1. Salva o nome no banco de dados através do seu DAO
                LoginDAO.validarLogin(nome,  senha);

                JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");

                // 2. Abre a tela de feedback
                FeedBackDisplay feedBackDisplay = new FeedBackDisplay();
                feedBackDisplay.setVisible(true);

                // 3. Fecha a tela de login atual (agora no lugar certo!)
                this.dispose();
            }
        });

        // Organização dos componentes no Grid
        painel.add(lblNome);
        painel.add(textNome);
        painel.add(lblSenha);
        painel.add(textSenha);
        painel.add(new JLabel("")); // Espaço em branco para alinhar o botão
        painel.add(btnSalvar);

        add(painel);
        add(painel);
    }
}