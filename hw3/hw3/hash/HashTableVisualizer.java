package hw3.hash;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;

public class HashTableVisualizer {

    public static void main(String[] args) {
        /* scale: StdDraw scale
           N:     number of items
           M:     number of buckets */

        /* After getting your simpleOomages to spread out
           nicely, be sure to try
           scale = 0.5, N = 2000, M = 100. */

        double scale = 1.0;
        int N = 20;
        int M = 10;

        HashTableDrawingUtility.setScale(scale);
        List<Oomage> oomies = new ArrayList<>();
        ArrayList<Integer> params = new ArrayList<>();
        for (int i = 1; i < N; i += 1) {
            params.add(2);
            ComplexOomage oomage = new ComplexOomage(params);
            oomies.add(oomage);
            System.out.println(oomage.hashCode());
        }
        visualize(oomies, M, scale);
    }

    public static void visualize(List<Oomage> oomages, int M, double scale) {
        HashTableDrawingUtility.drawLabels(M);
        int[] numInBucket = new int[M];
        for (Oomage s : oomages) {
            int bucketNumber = (s.hashCode() & 0x7FFFFFFF) % M;
            double x = HashTableDrawingUtility.xCoord(numInBucket[bucketNumber]);
            numInBucket[bucketNumber] += 1;
            double y = HashTableDrawingUtility.yCoord(bucketNumber, M);
            s.draw(x, y, scale);
        }
    }
} 
