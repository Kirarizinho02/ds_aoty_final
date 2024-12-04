package controllers;

import models.Album;
import repository.AlbumRepository;
import views.AlbumTabela;
import views.AlbumForm;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AlbumController {
    private AlbumRepository repository;
    private AlbumTabela tableView;

    public AlbumController() {
        repository = new AlbumRepository();
        tableView = new AlbumTabela();
        inicializar();
    }

    private void inicializar() {
        atualizarTabela();

        JToolBar toolBar = new JToolBar();
        JButton botaoAdd = new JButton("Adicionar");
        JButton botaoEdit = new JButton("Editar");
        JButton botaoExcluir = new JButton("Excluir");
        JPanel painelBotoes = new JPanel();

        painelBotoes.add(botaoAdd);
        painelBotoes.add(botaoEdit);
        painelBotoes.add(botaoExcluir);

        toolBar.setLayout(new BorderLayout());

        toolBar.add(painelBotoes, BorderLayout.WEST);

        tableView.add(toolBar, java.awt.BorderLayout.NORTH);

        botaoAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarAlbum();
            }
        });

        botaoEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarAlbum();
            }
        });

        botaoExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirAlbum();
            }
        });


        tableView.setVisible(true);
    }

    private void atualizarTabela() {
        List<Album> albums = repository.obterTodosAlbums();
        tableView.atualizarTabela(albums);
    }

    private void adicionarAlbum() {
        AlbumForm form = new AlbumForm(tableView, "Adicionar Álbum");
        form.setVisible(true);
        Album novoAlbum = form.getAlbum();
        if (novoAlbum != null) {
            repository.adicionarAlbum(novoAlbum);
            atualizarTabela();
        }
    }

    private void editarAlbum() {
        int selectedId = tableView.getSelectedAlbumId();
        if (selectedId != -1) {
            Album album = repository.obterAlbumPorId(selectedId);
            if (album != null) {
                AlbumForm form = new AlbumForm(tableView, "Editar Álbum", album);
                form.setVisible(true);
                Album albumAtualizado = form.getAlbum();
                if (albumAtualizado != null) {
                    albumAtualizado.setId(selectedId);
                    repository.atualizarAlbum(albumAtualizado);
                    atualizarTabela();
                }
            } else {
                JOptionPane.showMessageDialog(tableView, "Álbum não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(tableView, "Selecione um álbum para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void excluirAlbum() {
        int selectedId = tableView.getSelectedAlbumId();
        if (selectedId != -1) {
            int confirm = JOptionPane.showConfirmDialog(tableView, "Tem certeza que deseja excluir este álbum?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                repository.excluirAlbum(selectedId);
                atualizarTabela();
            }
        } else {
            JOptionPane.showMessageDialog(tableView, "Selecione um álbum para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void iniciar() {
        SwingUtilities.invokeLater(() -> tableView.setVisible(true));
    }

}
