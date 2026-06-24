package org.example.display;

import org.example.dao.FeedBackDAO;

import javax.swing.*;
import java.awt.*;

public class FeedBackDisplay extends JFrame {
    public FeedBackDisplay (){
        setTitle("FeedBack");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel lblFeedBack= new JLabel("FeedBack: ");
        JTextField textFeedBack = new JTextField();


        JButton bntSalvar =  new JButton("Enviar");

        bntSalvar.addActionListener(e -> {
            String feedback = textFeedBack.getText();
            String nome = textFeedBack.getText();
            if (feedback.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Insira seu FeedBack.");
            } else {
                FeedBackDAO feedbackDAO = new FeedBackDAO();
                feedbackDAO.salvarFeedBack(nome ,feedback);
            }
        });

        painel.add(lblFeedBack);
        painel.add(textFeedBack);
        painel.add(new JLabel(""));
        painel.add(bntSalvar);
        add(painel);
    }
}
