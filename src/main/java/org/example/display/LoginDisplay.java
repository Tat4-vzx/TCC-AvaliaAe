package org.example.display;

import org.example.dao.LoginDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginDisplay extends JFrame{
    public LoginDisplay (){

// --- CONFIGURAÇÕES DA JANELA DESKTOP TELA CHEIA ---
        setTitle("Login");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Abre em tela cheia automaticamente
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Color corFundoApp = new Color(248, 249, 250); // Off-white moderno

        // Painel Principal com os desenhos fluidos de fundo (Identidade visual unificada)
        JPanel painelPrincipal = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Formas abstratas sutis no fundo para preencher as laterais da tela cheia
                g2.setColor(new Color(67, 97, 238, 14)); // Azul Royal translúcido
                g2.fillOval(-100, -100, 450, 450);

                g2.setColor(new Color(114, 9, 183, 10)); // Roxo translúcido
                g2.fillOval(getWidth() - 350, getHeight() - 400, 450, 450);

                g2.dispose();
            }
        };
        painelPrincipal.setBackground(corFundoApp);
        painelPrincipal.setBorder(new EmptyBorder(40, 40, 40, 40));

        // --- CARD CENTRALIZADO (Mantém o formulário focado e proporcional no desktop) ---
        JPanel cardCentral = new JPanel(new BorderLayout(15, 25)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Fundo do Card Branco
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 28, 28);

                // Moldura cinza sutil
                g2.setColor(new Color(233, 236, 239));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 28, 28);

                g2.dispose();
            }
        };
        cardCentral.setOpaque(false);
        cardCentral.setBorder(new EmptyBorder(45, 50, 40, 50)); // Margens internas do Card

        // --- CABEÇALHO DO CARD ---
        JPanel painelCabecalho = new JPanel();
        painelCabecalho.setLayout(new BoxLayout(painelCabecalho, BoxLayout.Y_AXIS));
        painelCabecalho.setOpaque(false);

        JLabel titulo = new JLabel("Faça seu Login");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 38)); // Ampliado para Desktop
        titulo.setForeground(new Color(33, 37, 41));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel("Insira suas credenciais para acessar");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 18)); // Ampliado para Desktop
        subtitulo.setForeground(new Color(108, 117, 125));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitulo.setBorder(new EmptyBorder(6, 0, 15, 0));

        painelCabecalho.add(titulo);
        painelCabecalho.add(subtitulo);
        cardCentral.add(painelCabecalho, BorderLayout.NORTH);

        // --- FORMULÁRIO (Centro do Card) ---
        JPanel painelFormulario = new JPanel();
        painelFormulario.setLayout(new GridLayout(4, 1, 0, 8)); // Espaçamento vertical ampliado
        painelFormulario.setOpaque(false);

        Font fontLabels = new Font("Segoe UI", Font.BOLD, 15); // Labels maiores e mais nítidas
        Font fontCampos = new Font("Segoe UI", Font.PLAIN, 16);  // Texto interno confortável ao digitar

        // Campo Nome
        JLabel lblNome = new JLabel("Usuário / Nome:");
        lblNome.setFont(fontLabels);
        lblNome.setForeground(new Color(73, 80, 87));
        JTextField textNome = new JTextField();
        textNome.setFont(fontCampos);
        textNome.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(206, 212, 218), 1, true),
                BorderFactory.createEmptyBorder(8, 12, 8, 12) // Maior área interna para digitação
        ));

        // Campo Senha
        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setFont(fontLabels);
        lblSenha.setForeground(new Color(73, 80, 87));
        JPasswordField textSenha = new JPasswordField();
        textSenha.setFont(fontCampos);
        textSenha.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(206, 212, 218), 1, true),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        painelFormulario.add(lblNome);
        painelFormulario.add(textNome);
        painelFormulario.add(lblSenha);
        painelFormulario.add(textSenha);

        cardCentral.add(painelFormulario, BorderLayout.CENTER);

        // --- BOTÃO DO FORMULÁRIO (Sul do Card) ---
        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotao.setOpaque(false);
        painelBotao.setBorder(new EmptyBorder(25, 0, 0, 0));

        Color corBotao = new Color(67, 97, 238); // Azul Royal moderno
        Color corBotaoHover = new Color(72, 149, 239);

        // Botão robusto acompanhando o padrão do menu principal
        BotaoModerno btnSalvar = new BotaoModerno("Entrar", corBotao, corBotaoHover);
        btnSalvar.setFont(new Font("Segoe UI", Font.BOLD, 18)); // Fonte de 14 para 18
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setPreferredSize(new Dimension(220, 48)); // Redimensionado para dar mais peso visual
        btnSalvar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // --- LÓGICA DE VALIDAÇÃO ---
        btnSalvar.addActionListener(e -> {
            String nome = textNome.getText().trim();
            String senha = new String(textSenha.getPassword());

// ... [Dentro do ActionListener do btnSalvar no LoginDisplay.java]
            if (nome.isEmpty()) {
                mostrarMensagemModerna("Por favor, insira o nome de usuário.", "Atenção", new Color(255, 193, 7));
            } else if (senha.isEmpty()) {
                mostrarMensagemModerna("Por favor, insira a sua senha.", "Atenção", new Color(255, 193, 7));
            } else {
                mostrarMensagemModerna("Dados validados com sucesso!", "Sucesso", new Color(67, 97, 238));
                dispose();

                LoginDAO login = new LoginDAO();
                login.validarLogin(nome, senha);

                // MODIFICADO: Passando o nome do login para a construtora
                FeedBackDisplay feedBackDisplay = new FeedBackDisplay(nome);
                feedBackDisplay.setVisible(true);
            }
        });

        painelBotao.add(btnSalvar);
        cardCentral.add(painelBotao, BorderLayout.SOUTH);

        // --- CENTRALIZADOR INTELIGENTE (GridBagLayout) ---
        JPanel centralizador = new JPanel(new GridBagLayout());
        centralizador.setOpaque(false);

        // Dimensões ideais e elegantes para a caixa de login em tela cheia
        cardCentral.setPreferredSize(new Dimension(540, 460));
        centralizador.add(cardCentral);

        painelPrincipal.add(centralizador, BorderLayout.CENTER);
        add(painelPrincipal);
    }

    /**
     * MÉTODO DE AVISO CUSTOMIZADO
     */
    private void mostrarMensagemModerna(String mensagem, String titulo, Color corTema) {
        JDialog dialog = new JDialog(LoginDisplay.this, titulo, true);
        dialog.setResizable(false);

        JPanel painelDialog = new JPanel(new BorderLayout(15, 15));
        painelDialog.setBorder(new EmptyBorder(25, 30, 20, 30));
        painelDialog.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(33, 37, 41));
        painelDialog.add(lblTitulo, BorderLayout.NORTH);

        JLabel lblMensagem = new JLabel("<html><body style='width: 280px; font-family: Segoe UI; font-size: 11pt; color: #495057;'>"
                + mensagem.replaceAll("\n", "<br>") + "</body></html>");
        painelDialog.add(lblMensagem, BorderLayout.CENTER);

        Color corHover = new Color(
                Math.min(corTema.getRed() + 25, 255),
                Math.min(corTema.getGreen() + 25, 255),
                Math.min(corTema.getBlue() + 25, 255)
        );

        BotaoModerno btnOk = new BotaoModerno("Ok", corTema, corHover);
        btnOk.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnOk.setForeground(Color.WHITE);
        btnOk.setPreferredSize(new Dimension(110, 35));
        btnOk.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnOk.addActionListener(ae -> dialog.dispose());

        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        painelBotao.setBackground(Color.WHITE);
        painelBotao.add(btnOk);
        painelDialog.add(painelBotao, BorderLayout.SOUTH);

        dialog.add(painelDialog);
        dialog.pack();
        dialog.setLocationRelativeTo(LoginDisplay.this);
        dialog.setVisible(true);
    }

    /**
     * CLASSE DO BOTÃO ARREDONDADO
     */
    class BotaoModerno extends JButton {
        private Color corAtual;
        private Color corBase;
        private Color corHover;
        private int raioBorda = 15; // Acompanha a suavidade do novo botão

        public BotaoModerno(String text, Color corBase, Color corHover) {
            super(text);
            this.corBase = corBase;
            this.corHover = corHover;
            this.corAtual = corBase;

            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    corAtual = corHover;
                    repaint();
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    corAtual = corBase;
                    repaint();
                }
                @Override
                public void mousePressed(MouseEvent e) {
                    corAtual = corBase.darker();
                    repaint();
                }
                @Override
                public void mouseReleased(MouseEvent e) {
                    corAtual = corHover;
                    repaint();
                }
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
