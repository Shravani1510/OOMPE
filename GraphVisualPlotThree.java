package visualplot;
import java.awt.Color;
import org.mariuszgromada.math.mxparser.Function;
import grafica.GPoint;
import processing.core.PApplet;
/**
 * @author Christian Koch
 *
 */
 public class GraphVisualPlotThree extends PApplet {

	GraphVisualPlot plotter;
	
 public static void main(String[] args) {
		PApplet.main(GraphVisualPlotThree.class);
	}


 public void setup() {
	 	final Color blue = new Color(51, 204, 255);
		Function powerFunction = new Function("f(t) = (124567/40000)*t + sin(2*pi*2*t)");
		plotter = new GraphVisualPlot(powerFunction, 0.0, 20);
		
		double k=0;
		while(k<20){
			float m = 0;
				while( m < 5) { 
						 //i = i+n;
						double fx1 = powerFunction.calculate(k+m); 
						double fz1 = powerFunction.calculate(k+5+m); 
							
						GPoint pointFour = new GPoint((float) k+m, (float) ((float) (fz1-fx1)*(k+m)/5)); 
						GPoint pointFive = new GPoint((float) k+m, (float) 0);
						this.plotter.addLine(pointFour, pointFive,  blue);
						
					     m = (float) (m + 0.001);
					}
				k+=5;	
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