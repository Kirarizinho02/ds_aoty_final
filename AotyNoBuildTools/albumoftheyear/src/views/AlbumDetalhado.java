package views;

import models.Album;
import repository.AlbumRepository;
import java.util.List;

import javax.swing.*;
import java.awt.*;

public class AlbumDetalhado extends JFrame {
    private AlbumRepository repository;
    private Album albumAtual;
    private int idAtual;

    //Definindo componentes
    private JLabel lblContador;
    private JLabel lblArtista;
    private JLabel lblTitulo;
    private JLabel lblScoreUser;
    private JLabel lblScoreCritics;
    private JTextArea txtTracklist;
    private JLabel lblGravadora;
    private JLabel lblGenero;
    private JLabel lblTipo;
    private JLabel lblLancamento;
    private JLabel lblProdutor;
    private JLabel lblEscritores;

    public AlbumDetalhado() {
        repository = new AlbumRepository();
        //Função pra obter o menor id do banco de dados (as vezes dava erro, caso, por exemplo, você excluisse o id = 1)
        idAtual = obterMenorIdValido();

            //Definindo tamanho, título, layout principal e botão pra fechar
            setTitle("Detalhes do Álbum");
            setSize(800, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new GridLayout(1, 3));

            //Coluna 1 com o Id do álbum, nome do artista e título do álbum, além dos botões de editar e excluir
            JPanel col1 = new JPanel(new BorderLayout());
            JPanel infoAlbumPanel = new JPanel(new GridLayout(3, 1));
            lblContador = new JLabel("ID: ");
            lblArtista = new JLabel("Artista: ");
            lblTitulo = new JLabel("Título: ");
            lblContador.setHorizontalAlignment(SwingConstants.LEFT); 
            lblArtista.setHorizontalAlignment(SwingConstants.CENTER); 
            lblTitulo.setHorizontalAlignment(SwingConstants.CENTER); 
            infoAlbumPanel.add(lblContador);
            infoAlbumPanel.add(lblArtista);
            infoAlbumPanel.add(lblTitulo);

            JPanel botoesCol1 = new JPanel(new FlowLayout());
            JButton btnEditar = new JButton("Editar");
            JButton btnExcluir = new JButton("Excluir");
            botoesCol1.add(btnEditar);
            botoesCol1.add(btnExcluir);

            col1.add(infoAlbumPanel, BorderLayout.NORTH);
            col1.add(botoesCol1, BorderLayout.SOUTH);

            //Coluna principal com grande parte das informações do álbum. No AOTY eles tem o user score e o critic score, que é uma média das notas.
            JPanel col2 = new JPanel(new BorderLayout());
            JPanel infoScorePanel = new JPanel(new GridLayout(4, 1));
            lblScoreUser = new JLabel("Score Usuários: ");
            lblScoreCritics = new JLabel("Score Críticos: ");
            txtTracklist = new JTextArea();
            txtTracklist.setEditable(false);
            JScrollPane scrollTracklist = new JScrollPane(txtTracklist);
            lblScoreUser.setHorizontalAlignment(SwingConstants.CENTER); 
            lblScoreCritics.setHorizontalAlignment(SwingConstants.CENTER); 
            infoScorePanel.add(lblScoreUser);
            infoScorePanel.add(lblScoreCritics);
            infoScorePanel.add(scrollTracklist);
            col2.add(infoScorePanel, BorderLayout.CENTER);

            //Coluna da direita com as outras informações relevantes do álbum assim como os botões para avançar, voltar, ir pra tela inicial e o formulário de cadastro.
            JPanel col3 = new JPanel(new BorderLayout());
            JPanel infoDetalhesPanel = new JPanel(new GridLayout(6, 1));
            lblGravadora = new JLabel("Gravadora: ");
            lblGenero = new JLabel("Gênero: ");
            lblTipo = new JLabel("Tipo: ");
            lblLancamento = new JLabel("Lançamento: ");
            lblProdutor = new JLabel("Produtor: ");
            lblEscritores = new JLabel("Escritores: ");
            lblGravadora.setHorizontalAlignment(SwingConstants.CENTER); 
            lblGenero.setHorizontalAlignment(SwingConstants.CENTER); 
            lblTipo.setHorizontalAlignment(SwingConstants.CENTER); 
            lblLancamento.setHorizontalAlignment(SwingConstants.CENTER);
            lblProdutor.setHorizontalAlignment(SwingConstants.CENTER); 
            lblEscritores.setHorizontalAlignment(SwingConstants.CENTER); 
            infoDetalhesPanel.add(lblGravadora);
            infoDetalhesPanel.add(lblGenero);
            infoDetalhesPanel.add(lblTipo);
            infoDetalhesPanel.add(lblLancamento);
            infoDetalhesPanel.add(lblProdutor);
            infoDetalhesPanel.add(lblEscritores);

            JPanel botoesCol3 = new JPanel(new GridLayout(2, 2));
            JButton btnProximo = new JButton("Próximo");
            JButton btnVoltar = new JButton("Anterior");
            JButton btnCadastro = new JButton("Tela de Cadastro");
            JButton btnInicial = new JButton("Tela Inicial");
            botoesCol3.add(btnProximo);
            botoesCol3.add(btnVoltar);
            botoesCol3.add(btnCadastro);
            botoesCol3.add(btnInicial);

            col3.add(infoDetalhesPanel, BorderLayout.CENTER);
            col3.add(botoesCol3, BorderLayout.SOUTH);

        //Adiciona as colunas pro layout
        add(col1);
        add(col2);
        add(col3);

        //Carrega o primeiro album
        carregarAlbum(idAtual);

        //As ações dos botões tão sendo definidas aqui, mas as funções tão pra baixo
        btnEditar.addActionListener(e -> editarAlbum());
        btnExcluir.addActionListener(e -> excluirAlbum());
        btnProximo.addActionListener(e -> proximoAlbum());
        btnVoltar.addActionListener(e -> voltarAlbum());
        btnCadastro.addActionListener(e -> abrirTelaCadastro());
        btnInicial.addActionListener(e -> abrirTelaInicial());
    }

    //Função para carregar o álbum e mostrar os seus detalhes na tela, ele obtem o id por uma função bem semelhante a do PDF e usa isso pra renderizar as infos
    private void carregarAlbum(int id) {
        albumAtual = repository.obterAlbumPorId(id);
        if (albumAtual != null) {
            lblContador.setText("ID: " + albumAtual.getId());
            lblArtista.setText("Artista: " + albumAtual.getNomeArtista());
            lblTitulo.setText("Título: " + albumAtual.getNomeAlbum());
            lblScoreUser.setText("Score Usuários: " + albumAtual.getScoreUser());
            lblScoreCritics.setText("Score Críticos: " + albumAtual.getScoreCritics());
            
            //Ele separa a tracklist, bota aonde tinha vírgulas um "\n" e faz com que ela apareça com o número respectivo dela na frente
            //A string foi formatada com o String Builder porque ela precisa adicionar o número do for, o hifen, a faixa e o comando pra pular a linha
            String[] faixas = albumAtual.getTracklist().split(",\\s*");
            StringBuilder tracklistFormatada = new StringBuilder();
            for (int i = 0; i < faixas.length; i++) {
                tracklistFormatada.append(i + 1).append(" - ").append(faixas[i]).append("\n");
            }
            txtTracklist.setText(tracklistFormatada.toString());
            
            lblGravadora.setText("Gravadora: " + albumAtual.getGravadora());
            lblGenero.setText("Gênero: " + albumAtual.getGenero());
            lblTipo.setText("Tipo: " + albumAtual.getTipo());
            lblLancamento.setText("Lançamento: " + albumAtual.getDataLancamento());
            lblProdutor.setText("Produtor: " + albumAtual.getProdutor());
            lblEscritores.setText("Escritores: " + albumAtual.getEscritor());
        } else {
            //Mensagem padrão de erro mas com a error message
            JOptionPane.showMessageDialog(this, "Álbum não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    //Lógica também semelhante a função do PDF, mas aqui é pra função de edição.
    private void editarAlbum() {
        int idSelecionadoAlbumAtual = albumAtual.getId();
        if (idSelecionadoAlbumAtual != -1) {
            Album album = repository.obterAlbumPorId(idSelecionadoAlbumAtual);
            if (album != null) {
                AlbumForm form = new AlbumForm(this, "Editar Álbum", album);
                form.setVisible(true);
                Album albumAtualizado = form.getAlbum();
                if (albumAtualizado != null) {
                    albumAtualizado.setId(idSelecionadoAlbumAtual); 
                    repository.atualizarAlbum(albumAtualizado);
                    carregarAlbum(albumAtualizado.getId()); 
                }
            } else {
                JOptionPane.showMessageDialog(this, "Álbum não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um álbum para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    //Mesma coisa das funçõpes acima, pega o id do álbum e oferece um diálogo para excluir
    private void excluirAlbum() {
        int idSelecionadoAlbumAtual = albumAtual.getId(); 
        if (idSelecionadoAlbumAtual != -1) {
            int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir este álbum?", "Sim", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                repository.excluirAlbum(idSelecionadoAlbumAtual);
                JOptionPane.showMessageDialog(this, "Álbum excluído com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                proximoAlbum(); 
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um álbum para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    //Aqui é pra apresentar o próximo album na lista
    private void proximoAlbum() {
        idAtual++;
        carregarAlbum(idAtual);
    }

    //Aqui é pra voltar a lista no album anterior
    private void voltarAlbum() {
        if (idAtual > 1) {
            idAtual--;
            carregarAlbum(idAtual);
        } else {
            JOptionPane.showMessageDialog(this, "Este é o primeiro álbum.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    //Função para abrir a tela de cadastro e cadastrar o álbum e logo depois mostrar o álbum cadastrado
    private void abrirTelaCadastro() {
        AlbumForm form = new AlbumForm(this, "Cadastro de Álbum");
        form.setVisible(true);
        Album novoAlbum = form.getAlbum(); 
    
        if (novoAlbum != null) {
            repository.adicionarAlbum(novoAlbum); 
            JOptionPane.showMessageDialog(this, "Álbum cadastrado com sucesso!");
            carregarAlbum(novoAlbum.getId()); 
        }
    }

    //Função pra, como eu disse acima, pegar o menor id válido e não conflitar caso o id = 1 ser nulo.
    private int obterMenorIdValido() {
        List<Album> albums = repository.obterTodosAlbums(); 
        int menorId = Integer.MAX_VALUE;
        for (Album album : albums) {
            if (album.getId() < menorId) {
                menorId = album.getId();
            }
        }

        return menorId == Integer.MAX_VALUE ? 1 : menorId; //Retorna 1 se não achar nenhum
    }

    //Função pra abrir a tela inicial e fechar a tela aberta atualmente
    private void abrirTelaInicial() {
        new TelaInicial().setVisible(true);
        dispose();
    }

}
