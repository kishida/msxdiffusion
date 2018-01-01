/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kis.diffusion;

/**
 *
 * @author naoki
 */
public class Div {
    public static void main(String[] args) {
        int a = 87;
        int c = 0xa0;
        int d = 5;
        int b = 0;
        for (int i = 0; i < d; ++i) {
            b *= 2;
            ++b;
            a -= c;
            if (a < 0) {
                --b;
                a += c;
            }
            c >>= 1;
        }
        System.out.printf("%%:%d /:%d%n", 87 % 10, 87 / 10);
        System.out.printf("a:%d b:%d%n", a, b);
    }
}
