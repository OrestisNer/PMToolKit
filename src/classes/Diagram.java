package classes;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;

import application.Utils;
import application.Window;

public class Diagram implements Serializable {
	
	private IntervalCategoryDataset tasksCollection;
	private boolean hasDataSet=false;
	private JFreeChart ganttChart;
	private Project project;
	
	public Diagram(Project project){
		this.project= project;
	}
	
	private IntervalCategoryDataset createDataset(ArrayList<Activity> activities) {
		
		TaskSeries seriesOne = new TaskSeries("Planned Implementation");
		for(Activity activity : activities){
			Date startingDate=Date.from(activity.getStartingDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
			Date deadline= Date.from(activity.getDeadLine().atStartOfDay(ZoneId.systemDefault()).toInstant());
			seriesOne.add(new Task(activity.getName(),new SimpleTimePeriod(startingDate,deadline)));
		}
		
		TaskSeries seriesTwo = new TaskSeries("Actual Implementation");
		for(Activity activity : activities){
			if(activity.isCompleted()){
				Date startingDate=Date.from(activity.getStartingDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
				LocalDate actualFinished = activity.getStartingDate().plusDays(activity.getActualDuration());
				Date actualDeadline= Date.from(actualFinished.atStartOfDay(ZoneId.systemDefault()).toInstant());
				seriesTwo.add(new Task(activity.getName(),new SimpleTimePeriod(startingDate,actualDeadline)));
			}
		}
		
		final TaskSeriesCollection collection = new TaskSeriesCollection();
		collection.add(seriesOne);
		collection.add(seriesTwo);
	
		return collection;		
	}
	
	public IntervalCategoryDataset getTasks(){
		if(hasDataSet)
			return tasksCollection;
		return null;
	}
	
	
	private JFreeChart createChart(final IntervalCategoryDataset dataset) {
		final JFreeChart chart = ChartFactory.createGanttChart(
				"Gantt Chart", // chart title
				"Tasks", // domain axis label
				"Date", // range axis label
				dataset, // data
				true, // include legend
				true, // tooltips
				false // urls
				);
		return chart;
	}
	
	public void saveChart(JFreeChart chart, String filename) {
		String path="Project Files/"+project.getName()+"/";
		try {
			/**
			 * This utility saves the JFreeChart as a JPEG First Parameter:
			 * FileName Second Parameter: Chart To Save Third Parameter: Height
			 * Of Picture Fourth Parameter: Width Of Picture
			 */
			ChartUtilities.saveChartAsJPEG(new File(path+filename), chart, 800, 600);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Problem occurred creating chart.");
		}
	}
	
	public JFreeChart createGanttDiagram() throws Exception{
		Window progressWindow = Utils.createProgressBar();
		ArrayList<String> tasksIds = project.getTasksIds();
		ArrayList<Activity> tasks = Utils.getTasksFromID(tasksIds);
		IntervalCategoryDataset dataset = this.createDataset(tasks);
		ganttChart = this.createChart(dataset);
		progressWindow.getStage().close();
		return ganttChart;
		
	}
	
	
	public static void openGanttWindow(JFreeChart chart){
		JFrame frame= new JFrame();
        ChartPanel panel = new ChartPanel(chart);
        frame.setLocation(500, 200);
        frame.setExtendedState(frame.getExtendedState()|JFrame.MAXIMIZED_BOTH );
		frame.setVisible(true);
		frame.setContentPane(panel);
	}
}
