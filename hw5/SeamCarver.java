import edu.princeton.cs.algs4.Picture;

import java.awt.*;

public class SeamCarver {
    private Picture picture;

    public SeamCarver(Picture picture) {
        this.picture = picture;
    }

    public Picture picture() {                  // current picture
        return new Picture(this.picture);
    }

    public int width() {                        // width of current picture
        return this.picture.width();
    }

    public int height() {                       // height of current picture
        return this.picture.height();
    }

    public double energy(int x, int y) {           // energy of pixel at column x and row y
        if (x < 0 || x >= this.width() || y < 0 || y > this.height()) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        int x1 = (this.width() + x - 1) % this.width();
        int x2 = (x + 1) % this.width();
        int xGradient = squareGradient(picture.get(x1, y), picture.get(x2, y));

        int y1 = (this.height() + y - 1) % this.height();
        int y2 = (y + 1) % this.height();
        int yGradient = squareGradient(picture.get(x, y1), picture.get(x, y2));

        return xGradient + yGradient;
    }

    private int squareGradient(Color color1, Color color2) {
        int dR = color1.getRed() - color2.getRed();
        int dG = color1.getGreen() - color2.getGreen();
        int dB = color1.getBlue() - color2.getBlue();
        return dR * dR + dG * dG + dB * dB;
    }

    public int[] findHorizontalSeam() {           // sequence of indices for horizontal seam
        Picture copy = this.picture;

        this.picture = new Picture(copy.height(), copy.width());
        for (int i = 0; i < copy.height(); i++) {
            for (int j = 0; j < copy.width(); j++) {
                this.picture.set(i, j, copy.get(j, i));
            }
        }

        int[] res = this.findVerticalSeam();
        this.picture = copy;
        return res;
    }

    public int[] findVerticalSeam() {             // sequence of indices for vertical seam
        int[][] e = new int[this.width()][this.height()];
        int[][] M = new int[this.width()][this.height()];
        int[] seam = new int[this.height()];

        for (int i = 0; i < this.width(); i++) {
            for (int j = 0; j < this.height(); j++) {
                e[i][j] = (int) this.energy(i, j);
            }
        }

        for (int i = 0; i < this.width(); i++) {
            M[i][0] = e[i][0];
        }
        for (int j = 1; j < this.height(); j++) {
            for (int i = 0; i < this.width(); i++) {
                M[i][j] = e[i][j] + M[i][j - 1];
                if (i - 1 >= 0 && e[i][j] + M[i - 1][j - 1] < M[i][j]) {
                    M[i][j] = e[i][j] + M[i - 1][j - 1];
                }
                if (i + 1 < this.width() && e[i][j] + M[i + 1][j - 1] < M[i][j]) {
                    M[i][j] = e[i][j] + M[i + 1][j - 1];
                }
            }
        }

        seam[this.height() - 1] = 0;
        for (int i = 0; i < this.width(); i++) {
            if (M[i][this.height() - 1] < M[seam[this.height() - 1]][this.height() - 1]) {
                seam[this.height() - 1] = i;
            }
        }
        for (int j = this.height() - 2; j >= 0; j--) {
            int i = seam[j + 1];
            if (seam[j + 1] - 1 >= 0 && M[seam[j + 1] - 1][j] < M[i][j]) {
                i = seam[j + 1] - 1;
            }
            if (seam[j + 1] + 1 < this.width() && M[seam[j + 1] + 1][j] < M[i][j]) {
                i = seam[j + 1] + 1;
            }
            seam[j] = i;
        }

        return seam;
    }

    public void removeHorizontalSeam(int[] seam) {  // remove horizontal seam from picture
        this.picture = SeamRemover.removeHorizontalSeam(this.picture, seam);
    }

    public void removeVerticalSeam(int[] seam) {    // remove vertical seam from picture
        this.picture = SeamRemover.removeVerticalSeam(this.picture, seam);
    }
}
