
public class NBody {
    public static String imageToDraw = "images/starfield.jpg";
    public static String path = "images/";


    public static double readRadius(String s) {
        In in = new In(s);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String s) {
        In in = new In(s);
        int N = in.readInt();
        Planet[] Planets = new Planet[N];

        in.readDouble();
        for (int i = 0; i < Planets.length; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            Planets[i] = new Planet(xP, yP, xV, yV, m, img);
        }
        return Planets;
    }

    public static void drawBackground(double radius) {
        StdDraw.setScale(-radius * 2, radius * 2);
        StdDraw.clear();

        StdDraw.picture(0, 0, imageToDraw);

//        StdDraw.show();
//        StdDraw.pause(2000);
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] planets = readPlanets(filename);
        double radius = readRadius(filename);

        drawBackground(radius);
        for (Planet p : planets) {
            p.draw();
        }
        StdDraw.show();

        StdDraw.enableDoubleBuffering();
        double currentTime = 0;
        while (currentTime < T) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            drawBackground(radius);
            for (Planet p : planets) {
                p.draw();
            }
            StdDraw.show(20);
//            StdDraw.pause(10);

            currentTime += dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}