public class Planet {
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
		double dx = p.xxPos - this.xxPos;
		double dy = p.yyPos - this.yyPos;
		double r = Math.sqrt(dx * dx + dy * dy);
		return r;
	}
	public double calcForceExertedBy(Planet p) {
		double G = 6.67 * Math.pow(10, -11);
		double r = this.calcDistance(p);
		double F = G * p.mass * this.mass / (r * r);
		return F;
	}

	public double calcForceExertedByX(Planet p) {
		double dx = p.xxPos - this.xxPos;
		double r = this.calcDistance(p);

		double F = this.calcForceExertedBy(p);
		return F * dx / r;
	}
	public double calcForceExertedByY(Planet p) {
		double dy = p.yyPos - this.yyPos;
		double r = this.calcDistance(p);

		double F = this.calcForceExertedBy(p);
		return F * dy / r;
	}
	public double calcNetForceExertedByX(Planet[] allPlanet) {
		double fx = 0;
		for(Planet p : allPlanet) {
			if (!this.equals(p)) {
				fx += this.calcForceExertedByX(p);
			}
		}
		return fx;
	}
	public double calcNetForceExertedByY(Planet[] allPlanet) {
		double fy = 0;
		for(Planet p : allPlanet) {
			if (!this.equals(p)) {
				fy += this.calcForceExertedByY(p);
			}
		}
		return fy;
	}
	public void update(double dt, double fx, double fy) {
		// calculate the acceleration with fx and fy
		double ax = fx / this.mass;
		double ay = fy / this.mass;
		double vx = this.xxVel + dt * ax;
		double vy = this.yyVel + dt * ay;
		double xp = this.xxPos + dt * vx;
		double yp = this.yyPos + dt * vy;

		this.xxPos = xp;
		this.yyPos = yp;
		this.xxVel = vx;
		this.yyVel = vy;
	}

	// draw the planet
	public void draw() {
		String filename = "images/" + this.imgFileName;
		// StdDraw.setScale(-radius, radius);
		StdDraw.picture(this.xxPos, this.yyPos, filename);
		// StdDraw.show();
	}

}