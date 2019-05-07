
public class Planet {

    private static final double G = 6.67e-11;
    private static String path = "images/";

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dx = p.xxPos - xxPos;
        double dy = p.yyPos - yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Planet p) {
        double dis = this.calcDistance(p);
        return G * this.mass * p.mass / (dis * dis);
    }

    public double calcForceExertedByX(Planet p) {
        double dis = this.calcDistance(p);
        return calcForceExertedBy(p) * (p.xxPos - this.xxPos) / dis;
    }

    public double calcForceExertedByY(Planet p) {
        double dis = this.calcDistance(p);
        return calcForceExertedBy(p) * (p.yyPos - this.yyPos) / dis;
    }

    public double calcNetForceExertedByX(Planet[] ps) {
        double netFroceX = 0;
        for (Planet p : ps) {
           if (this.equals(p)) {
               continue;
           }
           netFroceX += calcForceExertedByX(p);
        }
        return netFroceX;
    }

    public double calcNetForceExertedByY(Planet[] ps) {
        double netFroceY = 0;
        for (Planet p : ps) {
           if (this.equals(p)) {
               continue;
           }
           netFroceY += calcForceExertedByY(p);
        }
        return netFroceY;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / this.mass;
        double aY = fY / this.mass;

        this.xxVel += aX * dt;
        this.yyVel += aY * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, path + imgFileName);
//        StdDraw.show();
    }
}