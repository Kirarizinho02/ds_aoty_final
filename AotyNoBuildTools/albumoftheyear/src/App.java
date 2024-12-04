import views.TelaInicial;
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        //Executo a aplicação usando o Swing Utilities porque tenho além de uma página no meu projeto e confesso que não entendi parte da lógica no pdf
        SwingUtilities.invokeLater(() -> {
            new TelaInicial().setVisible(true);
        });
    }
}