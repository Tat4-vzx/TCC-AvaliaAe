package org.example.display;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OpcaoDisplay extends JFrame {
    public OpcaoDisplay() {

        // --- CONFIGURAÇÕES DA JANELA DESKTOP TELA CHEIA ---
        setTitle("Sistema de Feedback - Seleção de Perfil");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Color corFundoApp = new Color(248, 249, 250);

        // Painel Principal com os mesmos desenhos abstratos de fundo para manter a identidade
        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 20)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(new Color(67, 97, 238, 18));
                g2.fillOval(-150, -150, 450, 450);

                g2.setColor(new Color(46, 196, 182, 15));
                g2.fillOval(getWidth() - 350, getHeight() - 350, 500, 500);

                g2.dispose();
            }
        };

        painelPrincipal.setBorder(new EmptyBorder(160, 40, 40, 40));
        painelPrincipal.setBackground(corFundoApp);

        // --- CABEÇALHO ---
        JPanel painelCabecalho = new JPanel();
        painelCabecalho.setLayout(new BoxLayout(painelCabecalho, BoxLayout.Y_AXIS));
        painelCabecalho.setBackground(corFundoApp);
        painelCabecalho.setOpaque(false);

        JLabel titulo = new JLabel("Selecione seu Perfil");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 46));
        titulo.setForeground(new Color(33, 37, 41));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel("Escolha como deseja acessar a plataforma");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        subtitulo.setForeground(new Color(108, 117, 125));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitulo.setBorder(new EmptyBorder(10, 0, 25, 0));

        painelCabecalho.add(titulo);
        painelCabecalho.add(subtitulo);
        painelPrincipal.add(painelCabecalho, BorderLayout.NORTH);

        // --- PAINEL DE BOTÕES (2 Opções) ---
        JPanel painelBotoes = new JPanel(new GridLayout(2, 1, 0, 20));
        painelBotoes.setOpaque(false);

        Color corCliente = new Color(46, 196, 182); // Verde/Ciano amigável
        Color corClienteHover = new Color(60, 209, 195);

        Color corColaborador = new Color(67, 97, 238); // Azul corporativo
        Color corColaboradorHover = new Color(72, 149, 239);

        // Ação para CLIENTE
        JButton btnCliente = criarBotaoInterativo("Sou Cliente", corCliente, corClienteHover);
        btnCliente.addActionListener(e -> {
            ClienteDisplay displayCliente = new ClienteDisplay();
            displayCliente.setVisible(true);
            this.dispose(); // Fecha a tela de seleção
        });

        // Ação para COLABORADOR (COM VALIDAÇÃO)
        JButton btnColaborador = criarBotaoInterativo("Sou Colaborador", corColaborador, corColaboradorHover);
        btnColaborador.addActionListener(e -> {
            if (autenticarColaborador()) {
                ColaboradorDisplay displayColaborador = new ColaboradorDisplay();
                displayColaborador.setVisible(true);
                this.dispose(); // Fecha a tela de seleção
            }
        });

        painelBotoes.add(btnCliente);
        painelBotoes.add(btnColaborador);

        // --- CONTEINER "CARD" CENTRAL ---
        JPanel cardCentral = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 28, 28);

                g2.setColor(new Color(233, 236, 239));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 28, 28);

                g2.dispose();
            }
        };
        cardCentral.setOpaque(false);
        cardCentral.setBorder(new EmptyBorder(40, 35, 40, 35));
        cardCentral.add(painelBotoes, BorderLayout.CENTER);

        // Centralizador GridBagLayout
        JPanel centralizador = new JPanel(new GridBagLayout());
        centralizador.setOpaque(false);
        cardCentral.setPreferredSize(new Dimension(460, 260));
        centralizador.add(cardCentral);

        painelPrincipal.add(centralizador, BorderLayout.CENTER);
        add(painelPrincipal);
    }

    // --- MÉTODO DE VALIDAÇÃO ---
    private boolean autenticarColaborador() {
        JTextField campoEmail = new JTextField();
        JPasswordField campoSenha = new JPasswordField();

        Object[] camposModal = {
                "E-mail:", campoEmail,
                "Senha:", campoSenha
        };

        int opcaoEscolhida = JOptionPane.showConfirmDialog(
                this,
                camposModal,
                "Acesso Restrito - Colaborador",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (opcaoEscolhida == JOptionPane.OK_OPTION) {
            String email = campoEmail.getText();
            String senha = new String(campoSenha.getPassword());

            // --- AQUI ESTÁ A INTEGRAÇÃO COM O BANCO DE DADOS ---
            // Chama a DAO para verificar se o e-mail e senha existem na tabela
            if (org.example.dao.AutenticacaoDAO.autenticar(email, senha)) {
                return true; // Autenticação com sucesso, banco validou
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "E-mail ou senha incorretos!",
                        "Erro de Autenticação",
                        JOptionPane.ERROR_MESSAGE
                );
                return false; // Falha na autenticação
            }
        }

        return false;
    }

    private JButton criarBotaoInterativo(String texto, Color corBase, Color corHover) {
        BotaoModerno botao = new BotaoModerno(texto, corBase, corHover);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 18));
        botao.setForeground(Color.WHITE);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return botao;
    }

    // Classe interna de botão customizado
    class BotaoModerno extends JButton {
        private Color corAtual;
        private Color baseColor;
        private Color hoverColor;
        private int raioBorda = 18;

        public BotaoModerno(String text, Color corBase, Color corHover) {
            super(text);
            this.baseColor = corBase;
            this.hoverColor = corHover;
            this.corAtual = corBase;

            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);

            addMouseListener(new MouseAdapter() {
                @Override public void mouseEntered(MouseEvent e) { corAtual = hoverColor; repaint(); }
                @Override public void mouseExited(MouseEvent e) { corAtual = baseColor; repaint(); }
                @Override public void mousePressed(MouseEvent e) { corAtual = baseColor.darker(); repaint(); }
                @Override public void mouseReleased(MouseEvent e) { corAtual = hoverColor; repaint(); }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(corAtual);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), raioBorda, raioBorda);
            super.paintComponent(g);
            g2.dispose();
        }
    }
}