package visualplot;



import java.awt.Color;
import java.util.ArrayList;

import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

import grafica.GLayer;
import grafica.GPlot;
import grafica.GPoint;
import grafica.GPointsArray;
import processing.core.PApplet;

public class GraphVisualPlot{
	private ArrayList<LineSegmentChoice> extraLines;
	private ArrayList<PointChoice> extraPoints;
	private Function threePointOnePlot;
	private int gridPoints = 10;
	private GPointsArray points;
	private double initiate;
	private double close;
	private GPlot plot;
	
	

	public GraphVisualPlot(Function f, double u, double b) {
		this.threePointOnePlot = f;
		this.initiate = u;
		this.close = b;
		this.extraLines = new ArrayList<LineSegmentChoice>();
		this.extraPoints = new ArrayList<PointChoice>();
		this.calculatePoints();
	}

	// calculate points from the function
		private void calculatePoints() {
			this.points = new GPointsArray(gridPoints);

			// load points and step width
			double dz = (this.close - this.initiate) / (this.gridPoints - 1);

			// save function name for expression
			String powerFunctionName = this.threePointOnePlot.getFunctionName();

			// evaluating the points
			for (int i = 0; i < this.gridPoints; i++) {
				double z = this.initiate + i * dz;
				String tExpression = powerFunctionName + "(" + Double.toString(z) + ")";
				Expression functionArgument = new Expression(tExpression, this.threePointOnePlot);
				double fz = functionArgument.calculate();



				this.points.add((float) z, (float) fz);
			}

			// this.firstView = true;
		}
		
		public void fixNumberOfGridPoints(int gridPoints) {
			this.gridPoints = gridPoints;
		}


		/**
		 * plot the function
		 * 
		 * @param box
		 *            Processing window
		 */
		public void plot(PApplet box) {
			String layerId = "layer1";
			if (this.plot == null) {
				this.plot = new GPlot(box);
				this.plot.setPos(25, 25);
				this.plot.setDim((float) box.width - 150, (float) box.height - 150);
				this.plot.setTitleText(this.threePointOnePlot.getFunctionName());
				this.plot.getXAxis().setAxisLabelText("x");
				this.plot.getYAxis().setAxisLabelText("f(x)");
				this.plot.addLayer(layerId, this.points);
				this.plot.activatePanning();
				this.plot.activateZooming();
			}

			GLayer layerOne = this.plot.getLayer(layerId);
			layerOne.setPoints(this.points);
			layerOne.setPointSize(0f);

			this.plot.defaultDraw();

			// draw additional lines
			this.plot.beginDraw();
			for (LineSegmentChoice duoPoint : this.extraLines) {
				this.plot.drawLine(duoPoint.pointU, duoPoint.pointV, duoPoint.lineColor.getRGB(), 1f);
			}

			for (PointChoice choice : this.extraPoints) {
				this.plot.drawPoint(choice.pointLocation, choice.pointColour.getRGB(), choice.pointDiameter);
			}

			this.plot.drawHorizontalLine(0);
			this.plot.drawVerticalLine(0);
			this.plot.endDraw();
		}
		
		// add an additional point to the plot
		
		public void addPoint(GPoint pointPosition, Color pointColor, float pointDiameter) {
			PointChoice choice = new PointChoice(pointPosition, pointColor, pointDiameter);
			this.extraPoints.add(choice);
		}

		public void clearPoints() {
			this.extraPoints.clear();
		}

		/**
		 * add an additional line to the plot
		 */
		public void addLine(GPoint pointU, GPoint pointV, Color lineColor) {
			LineSegmentChoice choice = new LineSegmentChoice(pointU, pointV, lineColor);
			this.extraLines.add(choice);
		}

		/**
		 * clear all additional lines
		 */
		public void clearLines() {
			this.extraLines.clear();
		}

		

		/**
		 * nested class for the representation of line segment elements
		 * 
		 * @author ripo9018
		 *
		 */
		
		private class PointChoice {
			private GPoint pointLocation;
			private Color pointColour;
			private float pointDiameter;

			public PointChoice(GPoint pointLocation, Color pointColour, float pointDiameter) {
				this.pointLocation = pointLocation;
				this.pointColour = pointColour;
				this.pointDiameter = pointDiameter;
			}
		}
		
		private class LineSegmentChoice {
			private GPoint pointU;
			private GPoint pointV;
			private Color lineColor;

			public LineSegmentChoice(GPoint pointU, GPoint pointV, Color lineColor) {
				this.pointU = pointU;
				this.pointV = pointV;
				this.lineColor = lineColor;
			}
		}	
}

