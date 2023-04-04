import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.BitSet;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel {
    static int WIDTH = 1000;
    static int HEIGHT = 1000;

    BitSet primes;

    public Main() {
        primes = new BitSet(WIDTH * HEIGHT);
        isPrimes();
    }

    private void isPrimes() {
        primes.set(2, primes.size());

        for (int p = 2; p * p< primes.size(); p++) {
            if (primes.get(p)) {
                for (int i = p * p; i < primes.size(); i += p) {
                    primes.clear(i);
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics imageGraphics = image.getGraphics();

        int centerX = WIDTH / 2;
        int centerY = HEIGHT / 2;

        int x = centerX;
        int y = centerY;

        int dir = 0;
        int len = 1;
        int step = 0;
        int stepSize = 1;

        for (int n = 0; n <= primes.size(); n++) {
            if (primes.get(n)) {
                imageGraphics.setColor(Color.BLACK);
            } else {
                imageGraphics.setColor(Color.WHITE);
            }

            imageGraphics.fillRect(x, y, 1, 1);

            switch (dir) {
                case 0 -> x += stepSize;
                case 1 -> y -= stepSize;
                case 2 -> x -= stepSize;
                case 3 -> y += stepSize;
            }

            if (step % len == 0) {
                dir = (dir + 1) % 4;

                if (dir % 2 == 0) {
                    len++;
                }
            }
            step++;
        }

        g.drawImage(image, 0, 0, null);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ulam Spiral");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setLocationRelativeTo(null);

        Main panel = new Main();
        panel.setBackground(Color.WHITE);
        frame.add(panel);
        frame.setVisible(true);
    }
}
