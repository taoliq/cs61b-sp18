package lab14;

import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator {
    private int period;
    private int state;
    private double factor;

    public AcceleratingSawToothGenerator(int period, double factor) {
        state = 0;
        this.period = period;
        this.factor = factor;
    }

    public double next() {
        state = (state + 1);
        if (state == period) {
            state = 0;
            period *= factor;
        }

        return normalize(state % period);
    }

    private double normalize(double init) {
        return (init / period) * 2 - 1.0;
    }
}
