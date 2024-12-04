package repository;

import models.Album;
import config.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumRepository {

    public void adicionarAlbum(Album album) {
        String sql = "INSERT INTO album (id, scoreUser, scoreCritics, gravadora, nomeAlbum, nomeArtista, produtor, escritor, dataLancamento, tipo, genero, tracklist) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setInt(1, album.getId());
            stmt.setInt(2, album.getScoreUser());
            stmt.setInt(3, album.getScoreCritics());
            stmt.setString(4, album.getGravadora());
            stmt.setString(5, album.getNomeAlbum());
            stmt.setString(6, album.getNomeArtista());
            stmt.setString(7, album.getProdutor());
            stmt.setString(8, album.getEscritor());
            
            String dataLancamento = album.getDataLancamento();  
            if (dataLancamento != null && !dataLancamento.isEmpty()) {
                java.sql.Date sqlDate = java.sql.Date.valueOf(dataLancamento); 
                stmt.setDate(9, sqlDate);
            } else {
                stmt.setNull(9, java.sql.Types.DATE);
            }
            
            stmt.setString(10, album.getTipo());
            stmt.setString(11, album.getGenero());
            stmt.setString(12, album.getTracklist());
    
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Album adicionado com sucesso");
            }
    
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar album");
            e.printStackTrace();
        }
    }

    public List<Album> obterTodosAlbums() {
        List<Album> albums = new ArrayList<>();
        String sql = "SELECT * FROM Album";

        try (Connection conn = DbConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Album album = new Album(
                    rs.getInt("id"),
                    rs.getInt("scoreUser"),
                    rs.getInt("scoreCritics"),
                    rs.getString("gravadora"),
                    rs.getString("nomeAlbum"),
                    rs.getString("nomeArtista"),
                    rs.getString("produtor"),
                    rs.getString("escritor"),
                    rs.getString("dataLancamento"),
                    rs.getString("tipo"),
                    rs.getString("genero"),
                    rs.getString("tracklist")
                );
                albums.add(album);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao obter álbums");
            e.printStackTrace();
        }

        return albums;
    }

    public Album obterAlbumPorId(int id) {
        String sql = "SELECT * FROM Album WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Album(
                    rs.getInt("id"),
                    rs.getInt("scoreUser"),
                    rs.getInt("scoreCritics"),
                    rs.getString("gravadora"),
                    rs.getString("nomeAlbum"),
                    rs.getString("nomeArtista"),
                    rs.getString("produtor"),
                    rs.getString("escritor"),
                    rs.getString("dataLancamento"),
                    rs.getString("tipo"),
                    rs.getString("genero"),
                    rs.getString("tracklist")
                );
            }

        } catch (SQLException e) {
            System.out.println("Erro ao obter álbum por ID");
            e.printStackTrace();
        }

        return null;
    }

    public void atualizarAlbum(Album album) {
        String sql = "UPDATE Album SET scoreUser = ?, scoreCritics = ?, gravadora = ?, nomeAlbum = ?, nomeArtista = ?, produtor = ?, escritor = ?, dataLancamento = ?, tipo = ?, genero = ?, tracklist = ? WHERE id = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, album.getScoreUser());
            stmt.setInt(2, album.getScoreCritics());
            stmt.setString(3, album.getGravadora());
            stmt.setString(4, album.getNomeAlbum());
            stmt.setString(5, album.getNomeArtista());
            stmt.setString(6, album.getProdutor());
            stmt.setString(7, album.getEscritor());
            stmt.setString(8, album.getDataLancamento());
            stmt.setString(9, album.getTipo());
            stmt.setString(10, album.getGenero());
            stmt.setString(11, album.getTracklist());
            stmt.setInt(12, album.getId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Album atualizado com sucesso");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar álbum");
            e.printStackTrace();
        }
    }

    public void excluirAlbum(int id) {
        String sql = "DELETE FROM Album WHERE id = ?";

        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Album excluído com sucesso");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir álbum");
            e.printStackTrace();
        }
    }
}
