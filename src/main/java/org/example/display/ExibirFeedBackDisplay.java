package org.example.display;

import org.example.dao.FeedBackDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ExibirFeedBackDisplay extends JFrame {

    public ExibirFeedBackDisplay() {
        // --- CONFIGURAÇÕES DA JANELA DESKTOP TELA CHEIA ---
        setTitle("Feedbacks Recebidos");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Tela cheia automática
        setResizable(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Color corFundoApp = new Color(248, 249, 250); // Off-white moderno

        // Painel Principal com os desenhos fluidos de fundo (Identidade Visual unificada)
        JPanel painelPrincipal = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Formas geométricas sutis no fundo
                g2.setColor(new Color(67, 97, 238, 14)); // Azul Royal translúcido
                g2.fillOval(-100, -100, 450, 450);

                g2.setColor(new Color(46, 196, 182, 10)); // Verde Água translúcido
                g2.fillOval(getWidth() - 350, getHeight() - 400, 450, 450);

                g2.dispose();
            }
        };
        painelPrincipal.setBackground(corFundoApp);
        painelPrincipal.setBorder(new EmptyBorder(40, 40, 40, 40));

        // --- CARD CENTRALIZADO EXPANDIDO (Para caber melhor a tabela) ---
        JPanel cardCentral = new JPanel(new BorderLayout(15, 20)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Fundo do Card Branco Arredondado
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 28, 28);

                // Moldura cinza fina
                g2.setColor(new Color(233, 236, 239));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 28, 28);

                g2.dispose();
            }
        };
        cardCentral.setOpaque(false);
        cardCentral.setBorder(new EmptyBorder(40, 45, 35, 45));

        // --- CABEÇALHO DO CARD ---
        JPanel painelCabecalho = new JPanel();
        painelCabecalho.setLayout(new BoxLayout(painelCabecalho, BoxLayout.Y_AXIS));
        painelCabecalho.setOpaque(false);

        JLabel titulo = new JLabel("Feedbacks da Plataforma");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 38));
        titulo.setForeground(new Color(33, 37, 41));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel("Confira abaixo as opiniões e sugestões enviadas pelos usuários");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        subtitulo.setForeground(new Color(108, 117, 125));
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitulo.setBorder(new EmptyBorder(6, 0, 20, 0));

        painelCabecalho.add(titulo);
        painelCabecalho.add(subtitulo);
        cardCentral.add(painelCabecalho, BorderLayout.NORTH);

        // --- CONFIGURAÇÃO DA TABELA MODERNA (Centro) ---
        String[] colunas = {"Usuário / Nome", "Mensagem do Feedback"};
        DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Desabilita a edição direta das células
            }
        };

        JTable tabelaFeedbacks = new JTable(modeloTabela);
        tabelaFeedbacks.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        tabelaFeedbacks.setRowHeight(40); // Linhas mais altas transmitem design moderno
        tabelaFeedbacks.setGridColor(new Color(233, 236, 239));
        tabelaFeedbacks.setShowVerticalLines(false); // Oculta linhas verticais estilo "Flat Design"
        tabelaFeedbacks.setSelectionBackground(new Color(67, 97, 238, 30)); // Azul translúcido ao selecionar
        tabelaFeedbacks.setSelectionForeground(new Color(33, 37, 41));

        // Dimensionar as colunas proporcionalmente
        tabelaFeedbacks.getColumnModel().getColumn(0).setPreferredWidth(180); // Coluna do Nome menor
        tabelaFeedbacks.getColumnModel().getColumn(1).setPreferredWidth(520); // Coluna do texto maior

        // Estilização do Cabeçalho da Tabela
        JTableHeader cabecalho = tabelaFeedbacks.getTableHeader();
        cabecalho.setFont(new Font("Segoe UI", Font.BOLD, 15));
        cabecalho.setBackground(new Color(67, 97, 238)); // Casado com o Azul Royal principal
        cabecalho.setForeground(Color.WHITE);
        cabecalho.setReorderingAllowed(false); // Impede arrastar colunas
        cabecalho.setPreferredSize(new Dimension(cabecalho.getWidth(), 45));

        // Carregar os dados vindos do banco de dados utilizando o DAO
        List<String[]> dadosBanco = FeedBackDAO.listarFeedbacks();
        for (String[] linha : dadosBanco) {
            modeloTabela.addRow(linha);
        }

        JScrollPane scrollPane = new JScrollPane(tabelaFeedbacks);
        scrollPane.setBorder(new LineBorder(new Color(222, 226, 230), 1, true));
        scrollPane.getViewport().setBackground(Color.WHITE); // Fundo interno branco
        cardCentral.add(scrollPane, BorderLayout.CENTER);

        // --- PAINEL DO BOTÃO VOLTAR (Sul) ---
        JPanel painelBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotao.setOpaque(false);
        painelBotao.setBorder(new EmptyBorder(20, 0, 0, 0));

        Color corBotao = new Color(108, 117, 125); // Cinza moderno neutro para voltar
        Color corBotaoHover = new Color(133, 142, 150);

        BotaoModerno btnVoltar = new BotaoModerno("Voltar ao Menu", corBotao, corBotaoHover);
        btnVoltar.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setPreferredSize(new Dimension(240, 48));
        btnVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVoltar.addActionListener(e -> dispose()); // Fecha a tela atual

        painelBotao.add(btnVoltar);
        cardCentral.add(painelBotao, BorderLayout.SOUTH);

        // --- CENTRALIZADOR INTELIGENTE (GridBagLayout) ---
        JPanel centralizador = new JPanel(new GridBagLayout());
        centralizador.setOpaque(false);

        // Dimensões do card adaptadas para exibição confortável de tabelas em monitores desktop
        cardCentral.setPreferredSize(new Dimension(850, 600));
        centralizador.add(cardCentral);

        painelPrincipal.add(centralizador, BorderLayout.CENTER);
        add(painelPrincipal);
    }

    /**
     * CLASSE INTERNA DO BOTÃO ARREDONDADO
     */
    class BotaoModerno extends JButton {
        private Color corAtual;
        private Color corBase;
        private Color corHover;
        private int raioBorda = 15;

        public BotaoModerno(String text, Color corBase, Color corHover) {
            super(text);
            this.corBase = corBase;
            this.corHover = corHover;
            this.corAtual = corBase;

            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);

            addMouseListener(new MouseAdapter() {
                @Override public void mouseEntered(MouseEvent e) { corAtual = corHover; repaint(); }
                @Override public void mouseExited(MouseEvent e) { corAtual = corBase; repaint(); }
                @Override public void mousePressed(MouseEvent e) { corAtual = corBase.darker(); repaint(); }
                @Override public void mouseReleased(MouseEvent e) { corAtual = corHover; repaint(); }
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