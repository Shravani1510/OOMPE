package assignmentOne;

import java.awt.Color;

import org.mariuszgromada.math.mxparser.Function;

import grafica.GPoint;
import processing.core.PApplet;


public class ManagePlotterTest extends PApplet {

	ManageThreePointOnePlotter plotter;
	
	
	public static void main(String[] args) {
		PApplet.main(ManagePlotterTest.class);
	}

	

	public void setup() {
		
		final Color blue = new Color(51, 204, 255);
		Function plotFunction = new Function("f(t) = (124567/40000)*t + sin(2*pi*2*t)");
		plotter = new ManageThreePointOnePlotter(plotFunction, 0.0, 20);
     
		for (double idx = 0; idx <= 20; idx=idx+ 0.1) {
		    
			double point = plotFunction.calculate(idx);  
			
			GPoint pointOne = new GPoint((float) idx, 0);
			GPoint pointTwo = new GPoint((float) idx, (float) point);
			this.plotter.addLine(pointOne, pointTwo, blue);
		}
	}
	
	public void settings() {
		size(1150, 850, P3D);
	}

	public void draw() {
		background(0);
		plotter.plot(this);
	}
}