package frame;

import ConstantsAndMethods.ConstantsAndMethods;
import java.awt.BorderLayout;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;



public class DynamicGraphCat extends JPanel {

	/**
	 * @author G.Nivert
	 */
	private static final long serialVersionUID = 1L;
	private TimeSeries series;
	
	/**
     * Creates a graph.
     * 
     * @param title  the title.
     * 
     */
    public DynamicGraphCat(final String title) {
    	
    	JPanel p1;
    	
    	p1 = new JPanel();
    	p1.setLayout(new BorderLayout());
    	
    	series = new TimeSeries("Activity");
    	
    	TimeSeriesCollection dataset = new TimeSeriesCollection(this.series);
        JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        p1.add(chartPanel,BorderLayout.WEST);
        this.add(p1);
        
        new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				do
				{
				try {
					double rand = Math.random()*100;
		            Millisecond now = new Millisecond();
					series.add(now, rand);
				ConstantsAndMethods._Activity = (int)rand;
				if(Connection.f != null) Connection.f.interFace.paintComponent(Connection.f.interFace.getGraphics());
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}while(true);
			}
		}).start();

    }    

    /**
     * Creates a sample chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return A sample chart.
     */
    private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
            "Cat Activity", 
            "Time", 
            "Value",
            dataset, 
            true, 
            true, 
            false
        );
        final XYPlot plot = result.getXYPlot();
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setAutoRange(true);
        axis.setFixedAutoRange(30000);
        //axis.setDateFormatOverride(new SimpleDateFormat("ss.SS"));
        return result;
    }
	
}