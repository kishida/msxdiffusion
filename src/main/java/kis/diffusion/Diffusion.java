package kis.diffusion;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author naoki
 */
public class Diffusion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final int s = 3;
        
        BufferedImage img = new BufferedImage(256, 212, BufferedImage.TYPE_INT_RGB);
        
        int[][] yr = new int[257][2];
        int[][] yg = new int[257][2];
        for (int y = 0; y < 192; ++y) {
            int t;

            int cr = y % 2;
            int cw = 1 - cr;

            int dr = yr[0][cr] + (y % 96);
            int b1 = y / 96 * 2;
            int r = dr / 12;
            if (r > 7){
                r = 7;
            }
            dr = dr - r * 12;
            int tr = dr * 6 / 16; yr[0][cw] = tr;
            t = dr / 16; yr[1][cw] = t;
            dr = dr - tr - t;
            
            int dg = yg[0][cr];
            int g = dg / 16;
            if (g > 7){
                g = 7;
            }
            dg = dg - g * 16;
            int tg = dg * 6 / 16; yg[0][cw] = tg;
            t = dg / 16; yg[1][cw] = t;
            dg = dg - tg - t;

            img.setRGB(0, y, color(r * 32 + g * 4 + b1));
            
            for (int x = 1; x < 256; ++x) {
                dr = dr + yr[x][cr] + (y % 96);

                r = dr / 12;
                dr = dr - r * 12;
                if (r > 7){
                    r = 7;
                }
                t = dr / 16; yr[x + 1][cw] = t; dr = dr - t;
                t = dr / 3; yr[x][cw] = yr[x][cw] + t; dr = dr - t;
                t = dr * 3 / 10; yr[x - 1][cw] = yr[x - 1][cw] + t; dr = dr - t;

                dg = dg + yg[x][cr] + (x % 128);
                g = dg / 16;
                dg = dg - g * 16;
                if (g > 7){
                    g = 7;
                }
                t = dg / 16; yg[x + 1][cw] = t; dg = dg - t;
                t = dg / 3; yg[x][cw] = yg[x][cw] + t; dg = dg - t;
                t = dg * 3 / 10; yg[x - 1][cw] = yg[x - 1][cw] + t; dg = dg - t;

                img.setRGB(x, y, color(r * 32 + g * 4 + b1 + x / 128));
            }
        }
        JFrame f = new JFrame("256");
        BufferedImage vimg = new BufferedImage(256 * s, 212 * s, BufferedImage.TYPE_INT_RGB);
        vimg.createGraphics().drawImage(img, 0, 0, 256 * s, 212 * s, f);
        JLabel lbl = new JLabel(new ImageIcon(vimg));
        f.add(lbl);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(256 * s + 50, 212 * s + 50);
        f.setVisible(true);
    }
    static int color(int c) {
        int r = c / 32;
        int g = (c / 4) & 7;
        int b = c & 3;
        return (r * 255 / 7) << 16 | (g * 255 / 7) << 8  | (b * 255 / 3);
    }
}
