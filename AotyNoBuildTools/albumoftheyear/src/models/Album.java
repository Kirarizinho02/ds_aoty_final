package models;

public class Album {
    private int id;
    private int scoreUser;
    private int scoreCritics;
    private String gravadora;
    private String nomeAlbum;
    private String nomeArtista;
    private String produtor;
    private String escritor;
    private String dataLancamento;
    private String tipo;
    private String genero;
    private String tracklist;

    public Album(){

    }

    public Album(int id, int scoreUser, int scoreCritics, String gravadora, String nomeAlbum, String nomeArtista, String produtor, String escritor, String dataLancamento, String tipo, String genero, String tracklist) {
        this.id = id;
        this.scoreUser = scoreUser;
        this.scoreCritics = scoreCritics;
        this.gravadora = gravadora;
        this.nomeAlbum = nomeAlbum;
        this.nomeArtista = nomeArtista;
        this.produtor = produtor;
        this.escritor = escritor;
        this.dataLancamento = dataLancamento;
        this.tipo = tipo;
        this.genero = genero;
        this.tracklist = tracklist;
    }

    
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getScoreUser() {
        return scoreUser;
    }

    public void setScoreUser(int scoreUser) {
        this.scoreUser = scoreUser;
    }

    public int getScoreCritics() {
        return scoreCritics;
    }

    public void setScoreCritics(int scoreCritics) {
        this.scoreCritics = scoreCritics;
    }

    public String getGravadora() {
        return gravadora;
    }

    public void setGravadora(String gravadora) {
        this.gravadora = gravadora;
    }

    public String getNomeAlbum() {
        return nomeAlbum;
    }

    public void setNomeAlbum(String nomeAlbum) {
        this.nomeAlbum = nomeAlbum;
    }

    public String getNomeArtista() {
        return nomeArtista;
    }

    public void setNomeArtista(String nomeArtista) {
        this.nomeArtista = nomeArtista;
    }

    public String getProdutor() {
        return produtor;
    }

    public void setProdutor(String produtor) {
        this.produtor = produtor;
    }

    public String getEscritor() {
        return escritor;
    }

    public void setEscritor(String escritor) {
        this.escritor = escritor;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTracklist() {
        return tracklist;
    }

    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
    }
}
