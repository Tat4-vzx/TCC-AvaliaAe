package org.example.display;

import org.example.dao.CadastroDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CadastroDisplay extends  JFrame {
    public CadastroDisplay() {
        // --- CONFIGURAÇÕES DA JANELA DESKTOP TELA CHEIA ---
        setTitle("Criar Cadastro");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Abre em tela cheia automaticamente
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Color corFundoApp = new Color(248, 249, 250); // Off-white moderno

        // Painel Principal com os desenhos fluidos de fundo (Garante a mesma identidade visual)
        JPanel painelPrincipal = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Formas abstratas sutis no fundo para preencher os cantos vazios da tela cheia
                g2.setColor(new Color(46, 196, 182, 14)); // Verde-água translúcido
                g2.fillOval(-120, -120, 450, 450);

                g2.setColor(new Color(67, 97, 238, 10)); // Azul translúcido
                g2.fillOval(getWidth() - 380, getHeight() - 380, 500, 500);

                g2.dispose();
            }
        };
        painelPrincipal.setBackground(corFundoApp);
        painelPrincipal.setBorder(new EmptyBorder(40, 40, 40, 40));

        // --- CARD CENTRALIZADO (Mantém o formulário elegante e proporcional no desktop) ---
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

        JLabel titulo = new JLabel("Crie sua Conta");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 38)); // Aumentado para Desktop
        titulo.setForeground(new Color(33, 37, 41));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel("Preencha os dados para se cadastrar");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 18)); // Aumentado para Desktop
        subtitulo.setForeground(new Color(108, 117, 125));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitulo.setBorder(new EmptyBorder(6, 0, 15, 0));

        painelCabecalho.add(titulo);
        painelCabecalho.add(subtitulo);
        cardCentral.add(painelCabecalho, BorderLayout.NORTH);

        // --- FORMULÁRIO (Centro do Card) ---
        JPanel painelFormulario = new JPanel();
        // Aumentado ligeiramente o espaçamento vertical entre os componentes (hgap: 0, vgap: 8)
        painelFormulario.setLayout(new GridLayout(6, 1, 0, 8));
        painelFormulario.setOpaque(false);

        Font fontLabels = new Font("Segoe UI", Font.BOLD, 15); // Labels maiores
        Font fontCampos = new Font("Segoe UI", Font.PLAIN, 16);  // Texto interno maior ao digitar

        // Campo Nome
        JLabel lblNome = new JLabel("Nome Completo:");
        lblNome.setFont(fontLabels);
        lblNome.setForeground(new Color(73, 80, 87));
        JTextField textNome = new JTextField();
        textNome.setFont(fontCampos);
        textNome.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(206, 212, 218), 1, true),
                BorderFactory.createEmptyBorder(8, 12, 8, 12) // Mais padding interno no campo
        ));

        // Campo Email
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(fontLabels);
        lblEmail.setForeground(new Color(73, 80, 87));
        JTextField textEmail = new JTextField();
        textEmail.setFont(fontCampos);
        textEmail.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(206, 212, 218), 1, true),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));

        // Campo Senha
        JLabel lblSenha = new JLabel("Senha (mínimo 6 caracteres):");
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
        painelFormulario.add(lblEmail);
        painelFormulario.add(textEmail);
        painelFormulario.add(lblSenha);
        painelFormulario.add(textSenha);

        cardCentral.add(painelFormulario, BorderLayout.CENTER);

        // --- BOTÃO DO FORMULÁRIO (Sul do Card) ---
        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotao.setOpaque(false);
        painelBotao.setBorder(new EmptyBorder(25, 0, 0, 0));

        Color corBotao = new Color(46, 196, 182); // Verde-água/Teal original
        Color corBotaoHover = new Color(60, 209, 195);

        // Botão alargado e mais alto para combinar com as resoluções maiores
        BotaoModerno btnSalvar = new BotaoModerno("Fazer Cadastro", corBotao, corBotaoHover);
        btnSalvar.setFont(new Font("Segoe UI", Font.BOLD, 18)); // Fonte de 14 para 18
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setPreferredSize(new Dimension(240, 48)); // Redimensionado de 200x40 para 240x48
        btnSalvar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // --- LÓGICA DE VALIDAÇÃO ---
        btnSalvar.addActionListener(e -> {
            String nome = textNome.getText().trim();
            String email = textEmail.getText().trim();
            String senha = new String(textSenha.getPassword());

            if (nome.isEmpty()) {
                mostrarMensagemModerna("Por favor, insira seu nome.", "Atenção", new Color(255, 193, 7));
            } else if (email.isEmpty()) {
                mostrarMensagemModerna("Por favor, insira seu email.", "Atenção", new Color(255, 193, 7));
            } else if (senha.length() < 6) {
                mostrarMensagemModerna("A senha deve ter no mínimo 6 caracteres.", "Atenção", new Color(255, 193, 7));
            } else {
                CadastroDAO cadastrar =  new CadastroDAO();
                cadastrar.salvarCadastro(nome, email, senha);
                mostrarMensagemModerna("Cadastro realizado com sucesso!", "Bem-vindo", new Color(46, 196, 182));
                dispose();
                FeedBackDisplay feedBackDisplay = new FeedBackDisplay(nome);
                feedBackDisplay.setVisible(true);
            }
        });

        painelBotao.add(btnSalvar);
        cardCentral.add(painelBotao, BorderLayout.SOUTH);

        // --- CENTRALIZADOR INTELIGENTE (GridBagLayout) ---
        JPanel centralizador = new JPanel(new GridBagLayout());
        centralizador.setOpaque(false);

        // Dimensões otimizadas para acomodar o formulário com muito conforto visual no monitor
        cardCentral.setPreferredSize(new Dimension(540, 560));
        centralizador.add(cardCentral);

        painelPrincipal.add(centralizador, BorderLayout.CENTER);
        add(painelPrincipal);
    }

    /**
     * MÉTODO DE AVISO CUSTOMIZADO
     */
    private void mostrarMensagemModerna(String mensagem, String titulo, Color corTema) {
        JDialog dialog = new JDialog(CadastroDisplay.this, titulo, true);
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
        dialog.setLocationRelativeTo(CadastroDisplay.this);
        dialog.setVisible(true);
    }

    /**
     * CLASSE DO BOTÃO ARREDONDADO
     */
    class BotaoModerno extends JButton {
        private Color corAtual;
        private Color corBase;
        private Color corHover;
        private int raioBorda = 15; // Suavizado para o tamanho do novo botão

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

