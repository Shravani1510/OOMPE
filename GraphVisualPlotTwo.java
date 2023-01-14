package visualplot;


import java.awt.Color;
import org.mariuszgromada.math.mxparser.Function;
import grafica.GPoint;
import processing.core.PApplet;

/**
 * @author Christian Koch
 *
 */
public class GraphVisualPlotTwo extends PApplet {

	GraphVisualPlot plotter;
	
	public static void main(String[] args) {
		PApplet.main(GraphVisualPlotTwo.class);
	}

	public void setup() {
		final Color blue = new Color(51, 204, 255);
		Function powerFunction = new Function("f(t) = (124567/40000)*t + sin(2*pi*2*t)");
		plotter = new GraphVisualPlot(powerFunction, 0.0, 20);
		
		double k=0;
		while(k<20){
			
			double fz = powerFunction.calculate(k);  
			double fw = fz +0.5*(powerFunction.calculate(k+1)- powerFunction.calculate(k));  
			GPoint pointOne = new GPoint((float) k, (float)fw);
			GPoint pointTwo = new GPoint((float) k+(float)1, (float) fw);
			this.plotter.addLine(pointOne, pointTwo, blue);
			GPoint pointThree = new GPoint((float) k+(float)1, (float) 0);
		    this.plotter.addLine(pointTwo, pointThree, blue);
			GPoint pointFour = new GPoint((float) k, (float)0);
			this.plotter.addLine(pointOne, pointFour, blue);
	
		   float m = 0;
			while( m <1) {
				double fy1 = fz +0.5*(powerFunction.calculate(k+1+m)- powerFunction.calculate(k+m)); 
				GPoint FirstPoint = new GPoint((float) k+m, (float)fy1);
				GPoint FourthPoint = new GPoint((float) k+m, (float)0);
				this.plotter.addLine(FirstPoint, FourthPoint, blue);
				
			   m = (float) (m + 0.01);
			}
			k+=1;
		}
		
	}
	
	public void settings() {
		size(1150, 850, P3D);
	}


	public void draw() {
		background(151);
		plotter.plot(this);
	}
}
