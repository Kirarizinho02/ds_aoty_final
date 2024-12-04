package views;

import controllers.AlbumController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TelaInicial extends JFrame {
    public TelaInicial() {
        //Configuração principal, título, tamanho, etc
        setTitle("Página Inicial - AOTY");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //Painel esquerdo vazio para gerar espaço
        JPanel painelEsquerda = new JPanel();
        painelEsquerda.setPreferredSize(new Dimension(100, 0));
        add(painelEsquerda, BorderLayout.WEST);

        //Painel direito vazio para gerar espaço
        JPanel painelDireita = new JPanel();
        painelDireita.setPreferredSize(new Dimension(100, 0));
        add(painelDireita, BorderLayout.EAST);

        //Painel principal
        JPanel painelCentro = new JPanel(new BorderLayout());

        //Topo do painel central, com o título e subtítulo com tamanhos diferentes e styles aplicados (negrito e itálico)
        JPanel painelTopo = new JPanel(new GridLayout(2, 1));
        JLabel lblTitulo = new JLabel("AOTY", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        JLabel lblSubtitulo = new JLabel("Bem vindo ao Album Of The Year", SwingConstants.CENTER);
        lblSubtitulo.setFont(new Font("Arial", Font.ITALIC, 16));
        painelTopo.add(lblTitulo);
        painelTopo.add(lblSubtitulo);
        painelCentro.add(painelTopo, BorderLayout.NORTH);

        //Botões do painel central e margens para eles
        JPanel painelBotoes = new JPanel(new GridLayout(3, 1, 10, 10)); 
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50)); 

        //Funções dos botões
        JButton btnTabela = criarBotao("Tela de Tabela", e -> {
            new AlbumController().iniciar();
            dispose();
        });

        JButton btnFormulario = criarBotao("Tela de Cadastro", e -> {
            new AlbumForm(this, "Cadastro de Álbuns").setVisible(true);
            dispose();
        });

        JButton btnDetalhes = criarBotao("Tela de Detalhes", e -> {
            new AlbumDetalhado().setVisible(true);
            dispose();
        });

        //Adicionando os botões ao painel e então o painel no principal
        painelBotoes.add(btnTabela);
        painelBotoes.add(btnFormulario);
        painelBotoes.add(btnDetalhes);
        painelCentro.add(painelBotoes, BorderLayout.CENTER);

        //Rodapé da página com o botão de sair
        JPanel painelRodape = new JPanel();
        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> System.exit(0));
        painelRodape.add(btnSair);
        painelCentro.add(painelRodape, BorderLayout.SOUTH);

        add(painelCentro, BorderLayout.CENTER);

        //Configurações finais
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //Altura e largura fixas para os botões para padroniza-los além da grid
    private JButton criarBotao(String texto, ActionListener acao) {
        JButton botao = new JButton(texto);
        botao.addActionListener(acao);
        botao.setPreferredSize(new Dimension(200, 40));
        return botao;
    }
}
