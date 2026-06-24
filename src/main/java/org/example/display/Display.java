package org.example.display;
import javax.swing.*;
import java.awt.*;

public class Display extends JFrame {
    public Display() {
        setTitle("Sistema De FeedBack");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel painel = new JPanel(new GridLayout(5, 1, 8, 10));
        JLabel titulo = new JLabel("Sistema De FeedBack", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));

        JButton login = new JButton("Fazer Login");
        login.addActionListener(e -> {
            LoginDisplay loginDisplay = new LoginDisplay();
            loginDisplay.setVisible(true);

        });

        JButton cadastro = new JButton("Fazer cadastro");
        cadastro.addActionListener(e -> {
                    CadastroDisplay cadastroDisplay = new CadastroDisplay();
                    cadastroDisplay.setVisible(true);


                });

        JButton anonimo = new JButton("Anônimo");
        anonimo.addActionListener(e -> {
            AnonimoDisplay anonimoDisplay = new AnonimoDisplay();
            anonimoDisplay.setVisible(true);
        });

            painel.add(titulo);
            painel.add(login);
            painel.add(cadastro);
            painel.add(anonimo);
            painel.add(new JLabel(" "));
            add(painel);

        }
    }

