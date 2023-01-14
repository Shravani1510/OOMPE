package visualplot;

import java.awt.Color;
import org.mariuszgromada.math.mxparser.Function;
import grafica.GPoint;
import processing.core.PApplet;

/**
 * @author Christian Koch
 *
 */
public class GraphVisualPlotOne extends PApplet {

	GraphVisualPlot plotter;
	
	public static void main(String[] args) {
		PApplet.main(GraphVisualPlotOne.class);
	}



	public void setup() {
		final Color blue = new Color(51, 204, 255);
		Function powerFunction = new Function("f(t) = (124567/40000)*t + sin(2*pi*2*t)");
		plotter = new GraphVisualPlot(powerFunction, 0, 20);
		
		double k =0;
		while (k<20){
			
			double fz = powerFunction.calculate(k);  
			GPoint pointOne = new GPoint((float) k, 0);
			GPoint pointTwo = new GPoint((float) k, (float) fz);
			this.plotter.addLine(pointOne, pointTwo, blue);

			
			GPoint pointThree = new GPoint((float) k+(float)1, (float) fz);
		    this.plotter.addLine(pointTwo, pointThree, blue);
			
		    float m = 0;
			while( m < 1) {
				GPoint pointFour = new GPoint((float) k+m, 0);
				GPoint pointFive = new GPoint((float) k+m, (float) fz);
				this.plotter.addLine(pointFour, pointFive,blue);
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