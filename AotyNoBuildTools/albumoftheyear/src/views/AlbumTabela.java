package views;

import models.Album;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class AlbumTabela extends JFrame {
    private JTable table;
    private JButton botaoVoltar; 
    private DefaultTableModel tableModel;

    public AlbumTabela() {
        super("Gerenciamento de Álbuns");
        initializeComponents();
    }

    private void initializeComponents() {
        //Configuração da tabela 
        String[] columnNames = {"ID", "Nome", "Artista", "Gênero", "Tipo"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        //Painel especial pro botão de voltar
        JPanel painelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botaoVoltar = new JButton("Voltar");
        //Definindo a função do botão
        botaoVoltar.addActionListener((ActionEvent e) -> abrirTelaInicial());
        painelInferior.add(botaoVoltar);

        //Layout da janela 
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(painelInferior, BorderLayout.SOUTH);

        //Tamnho da janela
        this.setSize(800, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    //Função para mostrar os registros da tabela
    public void atualizarTabela(List<Album> albums) {
        tableModel.setRowCount(0);
        for (Album album : albums) {
            tableModel.addRow(new Object[]{
                album.getId(),
                album.getNomeAlbum(),
                album.getNomeArtista(),
                album.getGenero(),
                album.getTipo()
            });
        }
    }

    //Função para pegar o id do álbum selecionado que pode ser usado pra excluir ou editar
    public int getSelectedAlbumId() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            return (int) tableModel.getValueAt(selectedRow, 0);
        }
        return -1;
    }

    //Função para abrir a tela inicial e fechar a atual
    private void abrirTelaInicial() {
        new TelaInicial().setVisible(true);
        dispose();
    }
}
