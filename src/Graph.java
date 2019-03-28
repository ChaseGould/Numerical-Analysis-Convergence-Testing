import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;


public class Graph extends ApplicationFrame {

	
	//constructor with 1 input
	public Graph(final String title, ArrayList<Double> bisectionIterations, ArrayList<Double> bisectionErrors) {
		

		    super(title);
		    
		    final XYSeries bisectionSeries = new XYSeries("Bisection Method");
		    for (int i = 0; i < bisectionIterations.size(); i++) {
		    	bisectionSeries.add(bisectionIterations.get(i), bisectionErrors.get(i));
		    }
		       
		    //create an xyseriescollection.
		    final XYSeriesCollection XvsY = new XYSeriesCollection(bisectionSeries);	 
		 
		    final JFreeChart chart = ChartFactory.createXYLineChart(
		       title,
		        "Iterations", 
		        "Ei", 
		        XvsY,
		        PlotOrientation.VERTICAL,
		        true,
		        true,
		        false
		    );
		
		    final ChartPanel chartPanel = new ChartPanel(chart);
		    chartPanel.setPreferredSize(new java.awt.Dimension(1800, 972));
		    setContentPane(chartPanel);
			}

	//constructor with 2 inputs
	public Graph(final String title, ArrayList<Double> newtonIterations, ArrayList<Double> newtonErrors, 
		ArrayList<Double> bisectionIterations, ArrayList<Double> bisectionErrors) {
	

	    super(title);
	    final XYSeries newtonSeries = new XYSeries("Newtons Method");
	    for (int i = 0; i < newtonIterations.size(); i++) {
	    	newtonSeries.add(newtonIterations.get(i), newtonErrors.get(i));
	    }
	    
	    final XYSeries bisectionSeries = new XYSeries("Bisection Method");
	    for (int i = 0; i < bisectionIterations.size(); i++) {
	    	bisectionSeries.add(bisectionIterations.get(i), bisectionErrors.get(i));
	    }
	       
	    //create an xyseriescollection and pass both series to it.
	    final XYSeriesCollection XvsY = new XYSeriesCollection(newtonSeries);
	    XvsY.addSeries(bisectionSeries);		 
	 
	    final JFreeChart chart = ChartFactory.createXYLineChart(
	       title,
	        "Iterations", 
	        "Ei", 
	        XvsY,
	        PlotOrientation.VERTICAL,
	        true,
	        true,
	        false
	    );
	
	    final ChartPanel chartPanel = new ChartPanel(chart);
	    chartPanel.setPreferredSize(new java.awt.Dimension(1800, 972));
	    setContentPane(chartPanel);
		}
	
	//constructor with 3 inputs
	public Graph(final String title, ArrayList<Double> newtonIterations, ArrayList<Double> newtonErrors, 
		ArrayList<Double> bisectionIterations, ArrayList<Double> bisectionErrors, 
		ArrayList<Double> fixedPointIterations, ArrayList<Double> fixedPointErrors) {
	
	    super(title);
	    final XYSeries newtonSeries = new XYSeries("Newtons Method");
	    for (int i = 0; i < newtonIterations.size(); i++) {
	    	newtonSeries.add(newtonIterations.get(i), newtonErrors.get(i));
	    }
	    
	    final XYSeries bisectionSeries = new XYSeries("Bisection Method");
	    for (int i = 0; i < bisectionIterations.size(); i++) {
	    	bisectionSeries.add(bisectionIterations.get(i), bisectionErrors.get(i));
	    }
	    
	    final XYSeries fixedPointSeries = new XYSeries("Fixed Point Method");
	    for (int i = 0; i < fixedPointIterations.size(); i++) {
	    	fixedPointSeries.add(fixedPointIterations.get(i), fixedPointErrors.get(i));
	    }
	    	    
	    //create an xyseriescollection and pass both series to it.
	    final XYSeriesCollection XvsY = new XYSeriesCollection(newtonSeries);
	    XvsY.addSeries(bisectionSeries);
	    if(fixedPointIterations.size() > 0)
	    	XvsY.addSeries(fixedPointSeries);	 
	 
	    final JFreeChart chart = ChartFactory.createXYLineChart(
	       title,
	        "Iterations", 
	        "Ei", 
	        XvsY,
	        PlotOrientation.VERTICAL,
	        true,
	        true,
	        false
	    );
	
	    final ChartPanel chartPanel = new ChartPanel(chart);
	    chartPanel.setPreferredSize(new java.awt.Dimension(1800, 972));
	    setContentPane(chartPanel);
		}
}