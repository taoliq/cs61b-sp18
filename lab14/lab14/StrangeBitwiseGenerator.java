package lab14;

import lab14lib.Generator;

public class StrangeBitwiseGenerator implements Generator {
    private int period;
    private int state;

    public StrangeBitwiseGenerator(int period) {
        state = 0;
        this.period = period;
    }

    public double next() {
        state = (state + 1);
        int weirdState = state & (state >>> 3) & (state >>> 8) % period;
        return normalize(weirdState);
    }

    private double normalize(double init) {
        return (init / period) * 2 - 1.0;
    }
}
