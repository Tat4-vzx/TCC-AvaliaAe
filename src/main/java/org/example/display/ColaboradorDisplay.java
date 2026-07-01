package org.example.display;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ColaboradorDisplay extends JFrame {
    public ColaboradorDisplay() {

        // --- CONFIGURAÇÕES DA JANELA DESKTOP TELA CHEIA ---
        setTitle("Sistema de Feedback");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Color corFundoApp = new Color(248, 249, 250);

        // Painel Principal Customizado com Desenhos Geométricos no Fundo
        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 20)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Desenhos abstratos de fundo
                g2.setColor(new Color(67, 97, 238, 18));
                g2.fillOval(-150, -150, 450, 450);

                g2.setColor(new Color(46, 196, 182, 15));
                g2.fillOval(getWidth() - 350, getHeight() - 350, 500, 500);

                g2.setColor(new Color(114, 9, 183, 10));
                g2.fillOval(getWidth() - 250, -100, 350, 350);

                g2.dispose();
            }
        };

        painelPrincipal.setBorder(new EmptyBorder(130, 40, 40, 40));
        painelPrincipal.setBackground(corFundoApp);

        // --- CABEÇALHO ---
        JPanel painelCabecalho = new JPanel();
        painelCabecalho.setLayout(new BoxLayout(painelCabecalho, BoxLayout.Y_AXIS));
        painelCabecalho.setBackground(corFundoApp);
        painelCabecalho.setOpaque(false);

        JPanel painelIcone = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int w = getWidth();
                int h = getHeight();
                int tamanhoIcone = 70;
                int x = (w - tamanhoIcone) / 2;
                int y = (h - tamanhoIcone) / 2;

                g2.setColor(new Color(67, 97, 238));
                g2.fillRoundRect(x, y, tamanhoIcone, tamanhoIcone - 15, 20, 20);

                int[] xPontos = {x + 18, x + 32, x + 18};
                int[] yPontos = {y + tamanhoIcone - 15, y + tamanhoIcone - 15, y + tamanhoIcone - 2};
                g2.fillPolygon(xPontos, yPontos, 3);

                g2.setColor(Color.WHITE);
                g2.setStroke(new BasicStroke(3.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g2.drawLine(x + 20, y + 18, x + 50, y + 18);
                g2.drawLine(x + 20, y + 29, x + 40, y + 29);

                g2.dispose();
            }
        };
        painelIcone.setPreferredSize(new Dimension(100, 85));
        painelIcone.setBackground(corFundoApp);
        painelIcone.setOpaque(false);
        painelIcone.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titulo = new JLabel("Sistema de Feedback");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 46));
        titulo.setForeground(new Color(33, 37, 41));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel("Escolha como deseja acessar a plataforma");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        subtitulo.setForeground(new Color(108, 117, 125));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitulo.setBorder(new EmptyBorder(10, 0, 15, 0));

        painelCabecalho.add(painelIcone);
        painelCabecalho.add(titulo);
        painelCabecalho.add(subtitulo);
        painelPrincipal.add(painelCabecalho, BorderLayout.NORTH);

        // --- PAINEL DE BOTÕES (CORRIGIDO: Alterado de 3 para 4 linhas) ---
        JPanel painelBotoes = new JPanel(new GridLayout(4, 1, 0, 18));
        painelBotoes.setOpaque(false); // Deixado transparente para herdar o fundo branco do card

        Color corLogin = new Color(67, 97, 238);
        Color corLoginHover = new Color(72, 149, 239);

        Color corCadastro = new Color(46, 196, 182);
        Color corCadastroHover = new Color(60, 209, 195);

        Color corAnonimo = new Color(108, 117, 125);
        Color corAnonimoHover = new Color(133, 142, 150);

        Color corFeedBack = new Color(114, 9, 183);
        Color corFeedBackHover = new Color(139, 42, 201);

        JButton login = criarBotaoInterativo("Se identificar", corLogin, corLoginHover);
        login.addActionListener(e -> {
            LoginDisplay loginDisplay = new LoginDisplay();
            loginDisplay.setVisible(true);
        });


        JButton anonimo = criarBotaoInterativo("Entrar como Anônimo", corAnonimo, corAnonimoHover);
        anonimo.addActionListener(e -> {
            FeedBackDisplay feedBackDisplay = new FeedBackDisplay(null);
            feedBackDisplay.setVisible(true);
        });

        JButton exibirFeed = criarBotaoInterativo("Exibir FeedBacks", corFeedBack, corFeedBackHover);
        exibirFeed.addActionListener(e -> {
            ExibirFeedBackDisplay exibirFeedBackDisplay = new ExibirFeedBackDisplay();
            exibirFeedBackDisplay.setVisible(true);
        });

        painelBotoes.add(login);
        painelBotoes.add(anonimo);
        painelBotoes.add(exibirFeed);

        // --- CONTEINER "CARD" ---
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
        cardCentral.setBorder(new EmptyBorder(35, 35, 35, 35));
        cardCentral.add(painelBotoes, BorderLayout.CENTER);

        // Centralizador Inteligente GridBagLayout
        JPanel centralizador = new JPanel(new GridBagLayout());
        centralizador.setOpaque(false);

        // MODIFICAÇÃO: Aumentada a altura de 340 para 420 para acomodar confortavelmente o 4º botão
        cardCentral.setPreferredSize(new Dimension(460, 420));
        centralizador.add(cardCentral);

        painelPrincipal.add(centralizador, BorderLayout.CENTER);
        add(painelPrincipal);
    }

    private JButton criarBotaoInterativo(String texto, Color corBase, Color corHover) {
        BotaoModerno botao = new BotaoModerno(texto, corBase, corHover);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 18));
        botao.setForeground(Color.WHITE);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return botao;
    }

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