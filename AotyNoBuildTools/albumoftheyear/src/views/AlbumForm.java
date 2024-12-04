package views;

import repository.AlbumRepository;
import models.Album;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumForm extends JDialog {
    private JTextField txtNomeAlbum, txtNomeArtista, txtGravadora, txtScoreUser, txtScoreCritics, txtProdutor, txtEscritor, txtTrack, txtDataLancamento;
    //Isso aqui é pra não aparecer aqueles avisos no VSCode de que a combobox precisa de um parâmetro
    @SuppressWarnings("rawtypes")
    private JComboBox cbTipo, cbGenero;
    private JButton btnSalvar, btnCancelar, btnAdicionarFaixa;
    private Album album;
    private DefaultListModel<String> tracklistModel; // Modelo para o painel de tracklist
    private List<String> tracklist;

    //Método construtor para o preenchimento comum
    public AlbumForm(Frame parent, String title) {
        super(parent, title, true);
        initializeComponents();
        tracklist = new ArrayList<>();
    }

    //Método construtor para a edição
    public AlbumForm(Frame parent, String title, Album album) {
        this(parent, title);
        this.album = album;
        preencherFormulario();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void initializeComponents() {
        setLayout(new BorderLayout());

        //Paineis usados, sendo que o o tracklist é exclusivo para exibir a tracklist
        JPanel painelPrincipal = new JPanel(new GridLayout(0, 2));
        JPanel painelTracklist = new JPanel(new BorderLayout());

        //Campos de entrada de informações, todos agrupadinhos
        painelPrincipal.add(new JLabel("Nome do Álbum:"));
        txtNomeAlbum = new JTextField();
        painelPrincipal.add(txtNomeAlbum);

        painelPrincipal.add(new JLabel("Artista:"));
        txtNomeArtista = new JTextField();
        painelPrincipal.add(txtNomeArtista);

        painelPrincipal.add(new JLabel("Gravadora:"));
        txtGravadora = new JTextField();
        painelPrincipal.add(txtGravadora);

        painelPrincipal.add(new JLabel("Score Usuário:"));
        txtScoreUser = new JTextField();
        painelPrincipal.add(txtScoreUser);

        painelPrincipal.add(new JLabel("Score Crítico:"));
        txtScoreCritics = new JTextField();
        painelPrincipal.add(txtScoreCritics);

        painelPrincipal.add(new JLabel("Data de Lançamento:"));
        txtDataLancamento = new JTextField();
        painelPrincipal.add(txtDataLancamento);

        //Combobox do tipo, eu sei que no papel coloquei como select, mas achei melhor na versão final apresentar dessa forma
        painelPrincipal.add(new JLabel("Tipo:"));
        cbTipo = new JComboBox();
        cbTipo.addItem("EP");
        cbTipo.addItem("Album");
        cbTipo.addItem("Single");
        cbTipo.addItem("Coletânea");
        painelPrincipal.add(cbTipo);

        //Combobox do gênero da música / álbum
        painelPrincipal.add(new JLabel("Gênero:"));
        cbGenero = new JComboBox();
        cbGenero.addItem("Rock");
        cbGenero.addItem("Pop");
        cbGenero.addItem("Metal");
        cbGenero.addItem("Shoegaze");
        cbGenero.addItem("MPB");
        cbGenero.addItem("Gospel");
        cbGenero.addItem("Neo-Psychedelia");
        cbGenero.addItem("Ambiente");
        cbGenero.addItem("Jazz");
        cbGenero.addItem("R&B");
        cbGenero.addItem("Rap");
        cbGenero.addItem("Hip Hop");
        cbGenero.addItem("Industrial");
        cbGenero.addItem("Eletrônica");
        cbGenero.addItem("Alternativo");
        painelPrincipal.add(cbGenero);

        painelPrincipal.add(new JLabel("Produtor:"));
        txtProdutor = new JTextField();
        painelPrincipal.add(txtProdutor);

        painelPrincipal.add(new JLabel("Escritor:"));
        txtEscritor = new JTextField();
        painelPrincipal.add(txtEscritor);

        //Seção da tracklist e o botão para ir adicionando as tracks
        painelPrincipal.add(new JLabel("Track:"));
        txtTrack = new JTextField();
        JPanel painelAdicionar = new JPanel(new BorderLayout());
        btnAdicionarFaixa = new JButton("Adicionar");
        painelAdicionar.add(txtTrack, BorderLayout.CENTER);
        painelAdicionar.add(btnAdicionarFaixa, BorderLayout.EAST);
        painelPrincipal.add(painelAdicionar);

        //Painel que exibe a tracklist
        tracklistModel = new DefaultListModel<>();
        JList<String> listTracklist = new JList<>(tracklistModel);
        painelTracklist.add(new JLabel("Tracklist:"), BorderLayout.NORTH);
        painelTracklist.add(new JScrollPane(listTracklist), BorderLayout.CENTER);

        btnAdicionarFaixa.addActionListener(e -> adicionarFaixa());

        //Botões de salvar e cancelar 
        JPanel painelBotoes = new JPanel();
        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvar());
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> abrirTelaInicial());
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);

        //Adição dos paineis no layout principal
        add(painelPrincipal, BorderLayout.CENTER);
        add(painelTracklist, BorderLayout.EAST);
        add(painelBotoes, BorderLayout.SOUTH);

        setSize(600, 400);
        setLocationRelativeTo(null);
    }

    //Função pra adicionar as faixas na tracklist e verificar se elas não são nulas.
    private void adicionarFaixa() {
        String faixa = txtTrack.getText().trim();
        if (!faixa.isEmpty()) {
            tracklist.add(faixa);
            tracklistModel.addElement((tracklist.size()) + " - " + faixa);
            txtTrack.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Insira o nome da faixa antes de adicionar.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Função pra preencher o formulário caso seja um edit
    private void preencherFormulario() {
        txtNomeAlbum.setText(album.getNomeAlbum());
        txtNomeArtista.setText(album.getNomeArtista());
        txtGravadora.setText(album.getGravadora());
        txtScoreUser.setText(String.valueOf(album.getScoreUser()));
        txtScoreCritics.setText(String.valueOf(album.getScoreCritics()));
        txtDataLancamento.setText(album.getDataLancamento());
        cbTipo.setSelectedItem(album.getTipo());
        cbGenero.setSelectedItem(album.getGenero());
        txtProdutor.setText(album.getProdutor());
        txtEscritor.setText(album.getEscritor());
        txtTrack.setText(album.getTracklist()); 
    }

    //Função pra salvar novos registros no banco de dados
    private void salvar() {
        if (album == null) {
            album = new Album();  
        }

        album.setNomeAlbum(txtNomeAlbum.getText());
        album.setNomeArtista(txtNomeArtista.getText());
        album.setGravadora(txtGravadora.getText());
        album.setScoreUser(Integer.parseInt(txtScoreUser.getText()));
        album.setScoreCritics(Integer.parseInt(txtScoreCritics.getText()));
        album.setDataLancamento(txtDataLancamento.getText());
        album.setTipo((String) cbTipo.getSelectedItem());
        album.setGenero((String) cbGenero.getSelectedItem());
        album.setProdutor(txtProdutor.getText());
        album.setEscritor(txtEscritor.getText());
        album.setTracklist(String.join(", ", tracklist)); 
    
        AlbumRepository repository = new AlbumRepository();
        repository.adicionarAlbum(album);  
    
        abrirTelaInicial();  
    }

    //Função pra abrir a tela principal
    private void abrirTelaInicial() {
        new TelaInicial().setVisible(true);
        dispose();
    }

    public Album getAlbum() {
        return album;
    }
}
