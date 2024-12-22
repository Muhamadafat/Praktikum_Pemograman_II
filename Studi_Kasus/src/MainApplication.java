import view.MahasiswaView;
import javax.swing.SwingUtilities;

public class MainApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MahasiswaView mahasiswaView = new MahasiswaView();
            mahasiswaView.setVisible(true);
        });
    }
}
