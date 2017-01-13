
public class NBody {
    public static void main(String[] args) {
        // read all variables
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] allPlanet = readPlanets(filename);
        int N = allPlanet.length;

        StdDraw.clear();
        String background = "images/starfield.jpg";
        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0, 0, background);

//
//        // draw planet
//        for (int i =0; i < N; i++) {
//            System.out.println(allPlanet[i].imgFileName);
//            allPlanet[i].draw();
//        }

        String audio = "audio/2001.mid";
        readAudio(audio);
        for (double time = 0; time < T ; time += dt ) {
            double[] xForces = new double[N];
            double[] yForces = new double[N];

            for (int i = 0; i < N; i++) {
                xForces[i] = allPlanet[i].calcNetForceExertedByX(allPlanet);
                yForces[i] = allPlanet[i].calcNetForceExertedByY(allPlanet);
            }
            for (int i = 0; i < N; i++) {
                allPlanet[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.clear();
            StdDraw.setScale(-radius, radius);
            StdDraw.picture(0, 0, background);
            // draw planet
            for (int i = 0; i < N; i++) {
                allPlanet[i].draw();
            }
            StdDraw.show(10);
        }
        // System.out.println("done");
        StdOut.printf("%d\n", N);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < N; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %12s\n",
                    allPlanet[i].xxPos, allPlanet[i].yyPos, allPlanet[i].xxVel, allPlanet[i].yyVel, allPlanet[i].mass, allPlanet[i].imgFileName);
        }
    }

    public static double readRadius(String file) {

        In in = new In(file);
        int N = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String file) {
        In in = new In(file);
        int N = in.readInt();
        double radius = in.readDouble();
        Planet[] allPlanet = new Planet[N];
        int i = 0;
        while(i < N) {
            double xp = in.readDouble();
            double yp = in.readDouble();
            double xv = in.readDouble();
            double yv = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            allPlanet[i] = new Planet(xp, yp, xv, yv, mass, img);
            i++;
        }
        return allPlanet;
    }

    public static void readAudio(String file) {
        StdAudio.play(file);
        StdAudio.close();
    }
}
