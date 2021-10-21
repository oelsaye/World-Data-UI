/**
 *
 * @author Saif Jouda, Omar Elsayed, Youssef Youssef
 *
 * Creates the main user interface.
 *
 *
 */


package statsVisualiser.gui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Vector;

//Our imports -----------------------------------------------------------
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//Our imports -----------------------------------------------------------

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class MainUI extends JFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private static MainUI instance;

	public static MainUI getInstance() {
		if (instance == null)
			instance = new MainUI();

		return instance;
	}
	public int series = 3;
	String values="Line Chart";
	String countryvalue = "Brazil";
	int time1value = 2021;
	int time2value = 2021;
	String analysisvalue = "CO2 emissions vs Energy use vs PM2.5 air pollution, mean annual exposure";

	public boolean pie = true;
	public boolean bar = true;
	public boolean time = true;
	public boolean line = true;
	public boolean scatter = true;
	public boolean reportt = true;

	String analysistype = "Testing Analysis Type";
	String analysis1 = "CO2 emissions";
	String analysis2 = "Energy use vs PM2.5 air pollution";
	String analysis3 = "mean annual exposure";

	Boolean[] activeViewers= new Boolean[6];


	dataCheck checks = new dataCheck();
	boolean checksvalue;
	boolean checksvalue2;

	public int startint = 1;


	Analysis analy = new Analysis("Brazil","2010","2015","CO2 emissions vs Energy use vs PM2.5 air pollution, mean annual exposure");

  DataObject dataObj=analy.initiateStrategy();


  	/**
  	 * The Interface
  	 * Creates the viewers and shows everything
  	 */
	private MainUI() {
		// Set window title
		super("Country Statistics");

		final Analysis analyse = new Analysis(countryvalue,Integer.toString(time1value),Integer.toString(time2value),analysisvalue);

		activeViewers[0]=false;
		activeViewers[1]=false;
		activeViewers[2]=false;
		activeViewers[3]=false;
		activeViewers[4]=false;
		activeViewers[5]=false;

		//final String values="Pie Chart";

		// Set top bar
		JLabel chooseCountryLabel = new JLabel("Choose a country: ");
		Vector<String> countriesNames = new Vector<String>();
		countriesNames.add("USA");
		countriesNames.add("Canada");
		countriesNames.add("France");
		countriesNames.add("China");
		countriesNames.add("Brazil");
		countriesNames.sort(null);
		final JComboBox<String> countriesList = new JComboBox<String>(countriesNames);

		JLabel from = new JLabel("From");
		JLabel to = new JLabel("To");
		Vector<String> years = new Vector<String>();
		for (int i = 2021; i >= 2010; i--) {
			years.add("" + i);
		}
		final JComboBox<String> fromList = new JComboBox<String>(years);

		final JComboBox<String> toList = new JComboBox<String>(years);

		JPanel north = new JPanel();
		north.add(chooseCountryLabel);
		north.add(countriesList);
		north.add(from);
		north.add(fromList);
		north.add(to);
		north.add(toList);

		final JPanel west = new JPanel();
		west.setLayout(new GridLayout(2, 0));

		//  Set bottom bar
		JButton recalculate = new JButton("Recalculate");
		recalculate.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
					checksvalue = checks.checkCountry(countryvalue, analysisvalue);
					if (checksvalue == false)
					{
						displayErrorMessage(checks.message);
					}
					checksvalue2 = checks.checkYear1(time1value, time2value, analysisvalue, countryvalue);
					if (checksvalue2 == false)
					{
						displayErrorMessage(checks.message);
					}
					if (checksvalue == true && checksvalue2 == true)
					{
						getContentPane().remove(west);
						west.removeAll();
						pie = true;
						bar = true;
						time = true;
						line = true;
						scatter = true;
						reportt = true;
						activeViewers[0]=false;
						activeViewers[1]=false;
						activeViewers[2]=false;
						activeViewers[3]=false;
						activeViewers[4]=false;
						activeViewers[5]=false;
						getContentPane().add(west, BorderLayout.WEST);
						Analysis analys = new Analysis(countryvalue,String.valueOf(time1value),String.valueOf(time2value),analysisvalue);
						dataObj= analys.initiateStrategy();
						revalidate();
						repaint();
					}
	      }
	    });

		JLabel viewsLabel = new JLabel("Available Views: ");

		final Vector<String> viewsNames = new Vector<String>();
	//	viewsNames.add("Pie Chart");
		viewsNames.add("Line Chart");
		viewsNames.add("Bar Chart");
		viewsNames.add("Scatter Chart");
		viewsNames.add("Time Chart");
		viewsNames.add("Report");

		final JComboBox<String> viewsList = new JComboBox<String>(viewsNames);
		JButton addView = new JButton("+");

		//createCharts(west, values);
		//=============================================================================================

		// CREATE DEFAULT CHARTS HERE (CHARTS VIEWED WHEN THE PROGRAM FIRST OPENS)

			createLine(west);
			createTimeSeries(west);
			createBar(west);
			createScatter(west);
			createReport(west);

		//==============================================================================================

		addView.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	    	  //JPanel west = new JPanel();
	    	  //west.setLayout(new GridLayout(2, 0));
	    	  startint = startint + 1;
	    	  createCharts(west, values);
	    	  getContentPane().add(west, BorderLayout.WEST);
	    	  System.out.println("+ Button pressed");
	    	  revalidate();
	    	  repaint();
	      }
	    });


		JButton removeView = new JButton("-");
		removeView.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				/*if (values == "Pie Chart" && pie == false)
				{
					createPie(west);
				}*/

				if (values == "Line Chart" && line == false)
				{
					createLine(west);
				}

				else if (values == "Bar Chart" && bar == false)
				{
					createBar(west);
				}

				else if (values == "Scatter Chart" && scatter == false)
				{
					createScatter(west);
				}

				else if (values == "Report" && reportt == false)
				{
					createReport(west);
				}

				else if (values == "Time Chart" && time == false)
				{
					createTimeSeries(west);
				}

				else
				{
					displayError();
				}

				System.out.println("- Button pressed");
				removeCharts(west, values);
				revalidate();
		    repaint();

			}
		});


		/**
			Analysis Type Choose
		*/
		JLabel methodLabel = new JLabel("        Choose analysis method: ");

		Vector<String> methodsNames = new Vector<String>();
		methodsNames.add("CO2 emissions vs Energy use vs PM2.5 air pollution, mean annual exposure");
		methodsNames.add("PM2.5 air pollution, mean annual exposure vs Forest area");
		methodsNames.add("Ratio of CO2 emissions and GDP per capita (current US$)");
		methodsNames.add("Average Forest area for the selected years");
		methodsNames.add("Average of Government expenditure on education, total for the selected years");
		methodsNames.add("Ratio of Hospital beds and Current health expenditure");
		methodsNames.add("Current health expenditure per capita vs Mortality rate, infant");
		methodsNames.add("Ratio of Government expenditure on education, total vs Current health expenditure");

		final JComboBox<String> methodsList = new JComboBox<String>(methodsNames);

		JPanel south = new JPanel();
		south.add(viewsLabel);
		south.add(viewsList);
		south.add(addView);
		south.add(removeView);

		south.add(methodLabel);
		south.add(methodsList);
		south.add(recalculate);

		JPanel east = new JPanel();

		// Set charts region
		//JPanel west = new JPanel();
	//	west.setLayout(new GridLayout(2, 0));
	//	createCharts(west, values);

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(south, BorderLayout.SOUTH);
		getContentPane().add(west, BorderLayout.WEST);

		class time1Change implements ItemListener{

		    public void itemStateChanged(ItemEvent event) {
		       if (event.getStateChange() == ItemEvent.SELECTED) {
						 	Object item = event.getItem();
							String objectstring;
							objectstring = (String) item;

							if (Integer.parseInt(objectstring) <= time2value)
							{
							checksvalue = checks.checkYear1(Integer.parseInt(objectstring), time2value, analysisvalue, countryvalue);
							System.out.println(checksvalue);
							System.out.println(Integer.parseInt(objectstring) + "   " + time2value + "   " + analysisvalue + "    " + countryvalue);

							if (checksvalue == false)
							{
							 displayErrorMessage(checks.message);
							}

					//		if (checksvalue == true)
					//		{
								 time1value = Integer.parseInt(objectstring);
					//		}
							}

						//	else
						//	{
						//	 displayError();
						//	}
		        }
		    }
		}

		class time2Change implements ItemListener{

		    public void itemStateChanged(ItemEvent event) {
		       if (event.getStateChange() == ItemEvent.SELECTED) {
							 Object item = event.getItem();
							 String objectstring;
							 objectstring = (String) item;
							 if (Integer.parseInt(objectstring) >= time1value)
							 {
							 checksvalue = checks.checkYear1(time1value, Integer.parseInt(objectstring), analysisvalue, countryvalue);
							 System.out.println(checksvalue);
							 System.out.println(time2value + "   " + Integer.parseInt(objectstring) + "   " + analysisvalue + "    " + countryvalue);

							 if (checksvalue == false)
							 {
								 displayErrorMessage(checks.message);
							 }

							// if (checksvalue == true)
						//	 {
								 time2value = Integer.parseInt(objectstring);
							// }
							 }
						//	 else
							// {
							//	 displayError();
							// }
		        }
		    }
		}


		class ItemChangeListener implements ItemListener{
				public void itemStateChanged(ItemEvent event) {
					 if (event.getStateChange() == ItemEvent.SELECTED) {
							Object item = event.getItem();
							String chosen;
							chosen = (String) item;

							if (chosen == "Pie Chart" || chosen == "Line Chart" || chosen == "Bar Chart" ||
		        		  chosen == "Scatter Chart" || chosen == "Report" || chosen == "Time Chart")
		          {
		        	  values = (String) item;
		        	  System.out.println(values);
		          }

							if (chosen == "Brazil" || chosen == "Canada" || chosen == "China" ||
		        		  chosen == "France" || chosen == "USA")
		          {
								checksvalue = checks.checkCountry(chosen, analysisvalue);
								System.out.println(analysisvalue);
								if (checksvalue == true)
								{
												countryvalue = (String) item;
										System.out.println(countryvalue);
								}
								else
								{
									displayErrorMessage(checks.message);
								}
		          }

	//---------------------------------------------------------------------------------------------------------------------------------

						if (chosen == "CO2 emissions vs Energy use vs PM2.5 air pollution, mean annual exposure")
						{
						//	analyse.setAnalysisNum("1");
							analyse.setAnalysisType(chosen);
							if (analysisvalue != (String) item)
							{
								analysisvalue = (String) item;
								values="Line Chart";
								countryvalue = "Brazil";
								time1value = 2021;
								time2value = 2021;
								refreshStuff(viewsList);
								refreshStuff(countriesList);
								refreshStuff(methodsList);
								refreshStuff(fromList);
								refreshStuff(toList);
							}
						}

						else if (chosen == "PM2.5 air pollution, mean annual exposure vs Forest area")
						{
						//	analyse.setAnalysisNum("2");
							analyse.setAnalysisType(chosen);
							if (analysisvalue != (String) item)
							{
								analysisvalue = (String) item;
								values="Line Chart";
								countryvalue = "Brazil";
								time1value = 2021;
								time2value = 2021;
								refreshStuff(viewsList);
								refreshStuff(countriesList);
								refreshStuff(methodsList);
								refreshStuff(fromList);
								refreshStuff(toList);
							}
						}

						else if (chosen == "Ratio of CO2 emissions and GDP per capita (current US$)")
						{
						//	analyse.setAnalysisNum("3");
							analyse.setAnalysisType(chosen);
							if (analysisvalue != (String) item)
							{
								analysisvalue = (String) item;
								values="Line Chart";
								countryvalue = "Brazil";
								time1value = 2021;
								time2value = 2021;
								refreshStuff(viewsList);
								refreshStuff(countriesList);
								refreshStuff(methodsList);
								refreshStuff(fromList);
								refreshStuff(toList);
							}
						}
						else if (chosen == "Average Forest area for the selected years")
						{
						//	analyse.setAnalysisNum("4");
							analyse.setAnalysisType(chosen);
							if (analysisvalue != (String) item)
							{
								analysisvalue = (String) item;
								values="Line Chart";
								countryvalue = "Brazil";
								time1value = 2021;
								time2value = 2021;
								refreshStuff(viewsList);
								refreshStuff(countriesList);
								refreshStuff(methodsList);
								refreshStuff(fromList);
								refreshStuff(toList);

							}
						}
						else if (chosen == "Average of Government expenditure on education, total for the selected years")
						{
						//	analyse.setAnalysisNum("5");
							analyse.setAnalysisType(chosen);
							if (analysisvalue != (String) item)
							{
								analysisvalue = (String) item;
								values="Line Chart";
								countryvalue = "Brazil";
								time1value = 2021;
								time2value = 2021;
								refreshStuff(viewsList);
								refreshStuff(countriesList);
								refreshStuff(methodsList);
								refreshStuff(fromList);
								refreshStuff(toList);
							}
						}
						else if (chosen == "Ratio of Hospital beds and Current health expenditure")
						{
						//	analyse.setAnalysisNum("6");
							analyse.setAnalysisType(chosen);
							if (analysisvalue != (String) item)
							{
								analysisvalue = (String) item;
								values="Line Chart";
								countryvalue = "Brazil";
								time1value = 2021;
								time2value = 2021;
								refreshStuff(viewsList);
								refreshStuff(countriesList);
								refreshStuff(methodsList);
								refreshStuff(fromList);
								refreshStuff(toList);
							}
						}
						else if (chosen == "Current health expenditure per capita vs Mortality rate, infant")
						{
						//	analyse.setAnalysisNum("7");
							analyse.setAnalysisType(chosen);
							if (analysisvalue != (String) item)
							{
								analysisvalue = (String) item;
								values="Line Chart";
								countryvalue = "Brazil";
								time1value = 2021;
								time2value = 2021;
								refreshStuff(viewsList);
								refreshStuff(countriesList);
								refreshStuff(methodsList);
								refreshStuff(fromList);
								refreshStuff(toList);

							}
						}
						else if (chosen == "Ratio of Government expenditure on education, total vs Current health expenditure")
						{
						//	analyse.setAnalysisNum("8");
							analyse.setAnalysisType(chosen);
							if (analysisvalue != (String) item)
							{
								analysisvalue = (String) item;
								values="Line Chart";
								countryvalue = "Brazil";
								time1value = 2021;
								time2value = 2021;
								refreshStuff(viewsList);
								refreshStuff(countriesList);
								refreshStuff(methodsList);
								refreshStuff(fromList);
								refreshStuff(toList);
							}
						}

					 }

				}

		}

		viewsList.addItemListener(new ItemChangeListener()); //CHECKS WHICH ONE IS SELECTED
		countriesList.addItemListener(new ItemChangeListener()); //CHECKS WHICH ONE IS SELECTED
		methodsList.addItemListener(new ItemChangeListener()); //CHECKS WHICH ONE IS SELECTED
		fromList.addItemListener(new time1Change()); //CHECKS WHICH ONE IS SELECTED
		toList.addItemListener(new time2Change()); //CHECKS WHICH ONE IS SELECTED
	}


//	private void createCharts(JPanel west, Boolean[] type) {  //MAKES THE VIEWERS -------------------------------------------------

	public void refreshStuff(JComboBox<String> value)
	{
		value.setSelectedIndex(0);
	}

	private void removeCharts(JPanel west, String type){
		if (type == "Line Chart" && activeViewers[0]==true)
		{

			activeViewers[0]=false;
		}
		else if (type == "Time Chart" && activeViewers[1]==true)
		{

			activeViewers[1]=false;
		}

		else if (type == "Bar Chart" && activeViewers[2]==true)
		{

			activeViewers[2]=false;
		}

		/*else if (type == "Pie Chart" && activeViewers[3]==true)
		{

			activeViewers[3]=false;
		}*/

		else if (type == "Scatter Chart" && activeViewers[4]==true)
		{

			activeViewers[4]=false;
		}

		else if (type == "Report" && activeViewers[5]==true)
		{

				activeViewers[5]=false;
		}
	}

	/**
	 * Creates the chart depending on the input
	 * @param west	the JPanel, "the side to be added to"
	 * @param type  string of the selected chart
	 */
	private void createCharts(JPanel west, String type) {

		if (type == "Line Chart" && activeViewers[0]==false)
		{
			createLine(west);
			System.out.println("Made line graph");
			activeViewers[0]=true;
		}

		else if (type == "Time Chart" && activeViewers[1]==false)
		{
			createTimeSeries(west);
			System.out.println("Made time graph" );
			activeViewers[1]=true;
		}

		else if (type == "Bar Chart" && activeViewers[2]==false)
		{
			createBar(west);
			System.out.println("Made bar graph");
			activeViewers[2]=true;
		}

		/*else if (type == "Pie Chart" && activeViewers[3]==false)
		{
			createPie(west);
			System.out.println("Made pie graph");
			activeViewers[3]=true;
		}*/

		else if (type == "Scatter Chart" && activeViewers[4]==false)
		{
		  createScatter(west);
			System.out.println("Made scatter graph");
			activeViewers[4]=true;
		}

		else if (type == "Report" && activeViewers[5]==false)
		{
				createReport(west);
				activeViewers[5]=true;
		}

		else
		{
			displayError();
		}

	}

	//Error reportMessage
	public void displayError()
	{
		JOptionPane.showMessageDialog(null, "Error, please try again!");
	}

	public void displayErrorMessage(String message)
	{
		JOptionPane.showMessageDialog(null, message);
	}



//=====================================================================================================================
//=============================================CREATES THE GRAPHS======================================================
//=====================================================================================================================


/*
------------------------ Report ------------------------
*/
	/**
	 * Creates the report graph
	 * @param west JPanel
	 */
	private void createReport(JPanel west) {
		JTextArea report = new JTextArea();
		report.setEditable(false);
		report.setPreferredSize(new Dimension(400, 300));
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		report.setBackground(Color.white);
		String reportMessage, reportMessage2;

		reportMessage  = "==============================\n";
		int y = dataObj.getTime2()-dataObj.getTime1();
		for (int i = dataObj.getTime2(); i >= dataObj.getTime1(); i--)
		{

			if (dataObj.getSeries() == 1)
			{
				reportMessage  = reportMessage + "Year " + i + ":\n" + dataObj.getSeriesNames()[0] + " => " + dataObj.getData()[0][y] + "\n";
			}
			if (dataObj.getSeries() == 2)
			{
				reportMessage  = reportMessage + "Year " + i +":\n" + dataObj.getSeriesNames()[0] + " => " + dataObj.getData()[0][y] + "\n"
				+ dataObj.getSeriesNames()[1] + " => " + dataObj.getData()[1][y] + "\n";
			}
			if (dataObj.getSeries() == 3)
			{
				reportMessage  = reportMessage + "Year " + i + ":\n" + dataObj.getSeriesNames()[0] + " => " + dataObj.getData()[0][y] + "\n"
				+ dataObj.getSeriesNames()[1] + " => " + dataObj.getData()[1][y] + "\n" + dataObj.getSeriesNames()[2] + " => " + dataObj.getData()[2][y] + "\n";
			}
			y--;
		}

		/*
		reportMessage =  "==============================\n" + "Year"+ "2018:\n"
				+ "\tMortality/1000 births => 5.6\n" + "\tHealth Expenditure per Capita => 10624\n"
				+ "\tHospital Beds/1000 people => 2.92\n" + "\n" + "Year 2017:\n" + "\tMortality/1000 births => 5.7\n"
				+ "\tHealth Expenditure per Capita => 10209\n" + "\tHospital Beds/1000 people => 2.87\n" + "\n"
				+ "Year 2016:\n" + "\tMortality/1000 births => 5.8\n" + "\tHealth Expenditure per Capita => 9877\n"
				+ "\tHospital Beds/1000 people => 2.77\n";
			*/
		reportMessage2 = "Unemployment: Mev vs Women\n" + "==========================\n" + "Men=>\n"
				+ "\tEmployed: 96.054%\n" + "\tUnemployed: 3.946%\n" + "\n" + "Women=>\n" + "\tEmployed: 96.163%\n"
				+ "\tUnemployed: 3.837%\n";

		report.setText(reportMessage);
		JScrollPane outputScrollPane = new JScrollPane(report);
		//west.add(outputScrollPane);
		//report.setVisible(false);

		if (startint == 1)
		{
			report.setVisible(false);
		}

		if (startint != 1)
		{
			report.setVisible(true);
		}


		if (reportt == true)
		{
			west.add(outputScrollPane);
			reportt = false;
			activeViewers[5] = true;
		}

		else
		{
			//chartPanel = null;
			getContentPane().remove(west);
			west.removeAll();
			activeViewers[5] = false;
			if (activeViewers[0] == true) //Line chart
			{
				line = true;
				createLine(west);
			}

			if (activeViewers[1] == true) //Time chart
			{
				time = true;
				createTimeSeries(west);
			}

			if (activeViewers[2] == true) //Bar chart
			{
				bar = true;
				createBar(west);
			}

			/*if (activeViewers[3] == true) //Pie chart
			{
				pie = true;
				createPie(west);
			}*/

			if (activeViewers[4] == true) //Scatter Chart
			{
				scatter = true;
				createScatter(west);
			}

			if (activeViewers[5] == true) //Report
			{
				reportt = true;
				createReport(west);
			}

			getContentPane().add(west, BorderLayout.WEST);
			reportt = true;
			analysis3 = "ASDADADADA";
		}
	}

	/*
	------------------------ Scatter ------------------------
	*/
	/**
	 * Creates a scatter chart
	 * @param west JPanel
	 */
	private void createScatter(JPanel west) {

			TimeSeries series1 = new TimeSeries("");
			TimeSeriesCollection dataset = new TimeSeriesCollection();
			TimeSeriesCollection dataset2 = new TimeSeriesCollection();
			if (dataObj.getSeries() >= 1)
			{
				series1 = new TimeSeries(dataObj.getSeriesNames()[0]);
				int amount1 = dataObj.getTime2()-dataObj.getTime1();
				for (int i = dataObj.getTime2(); i >= dataObj.getTime1(); i--)
				{
					series1.add(new Year(i), dataObj.getData()[0][amount1]);
					amount1 = amount1 - 1;
					//System.out.println("Year: " + i + "| Amount: " + amount1);
				}
				dataset.addSeries(series1);
			}

			TimeSeries series2 = new TimeSeries("");

			if (dataObj.getSeries() >= 2)
			{
				series2 = new TimeSeries(dataObj.getSeriesNames()[1]);
				int amount2 = dataObj.getTime2()-dataObj.getTime1();
				for (int i = dataObj.getTime2(); i >= dataObj.getTime1(); i--)
				{
					series2.add(new Year(i), dataObj.getData()[1][amount2]);
					amount2 = amount2 - (int)(1);
				}
	/*
				series2.add(new Year(2018), 10624);
				series2.add(new Year(2017), 10209);
				series2.add(new Year(2016), 9877);
				series2.add(new Year(2015), 9491);
				series2.add(new Year(2014), 9023);
				series2.add(new Year(2013), 8599);
				series2.add(new Year(2012), 8399);
				series2.add(new Year(2011), 8130);
				series2.add(new Year(2010), 7930);*/
				dataset2.addSeries(series2);
			}

			TimeSeries series3 = new TimeSeries("");
			if (dataObj.getSeries() >= 3)
			{
				series3 = new TimeSeries(dataObj.getSeriesNames()[2]);
				int amount3 = dataObj.getTime2()-dataObj.getTime1();
				for (int i = dataObj.getTime2(); i >= dataObj.getTime1(); i--)
				{
					series3.add(new Year(i), dataObj.getData()[2][amount3]);
					amount3 = amount3 - (int)(1);
				}
				/*
				series3.add(new Year(2018), 2.92);
				series3.add(new Year(2017), 2.87);
				series3.add(new Year(2016), 2.77);
				series3.add(new Year(2015), 2.8);
				series3.add(new Year(2014), 2.83);
				series3.add(new Year(2013), 2.89);
				series3.add(new Year(2012), 2.93);
				series3.add(new Year(2011), 2.97);
				series3.add(new Year(2010), 3.05);*/
				dataset.addSeries(series3);
			}

			XYPlot plot = new XYPlot();
			XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);
			XYItemRenderer itemrenderer2 = new XYLineAndShapeRenderer(false, true);

			if (dataObj.getSeries() >= 1)
			{
				plot.setDataset(0, dataset);
				plot.setRenderer(0, itemrenderer1);
				DateAxis domainAxis = new DateAxis("Year");
				plot.setDomainAxis(domainAxis);
				plot.setRangeAxis(new NumberAxis(""));
			}

			if (dataObj.getSeries() >= 2)
			{
				plot.setDataset(1, dataset2);
				plot.setRenderer(1, itemrenderer2);
				plot.setRangeAxis(1, new NumberAxis("US$"));
			}

			plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
			plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

			JFreeChart scatterChart = new JFreeChart(dataObj.getAnalysis(),
					new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

			ChartPanel chartPanel = new ChartPanel(scatterChart);
			chartPanel.setPreferredSize(new Dimension(400, 300));
			chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
			chartPanel.setBackground(Color.white);
			//west.add(chartPanel);

			if (startint == 1)
			{
				chartPanel.setVisible(false);
			}

			if (startint != 1)
			{
				chartPanel.setVisible(true);
			}

			if (scatter == true)
			{
				west.add(chartPanel);
				scatter = false;
				activeViewers[4] = true;
			}

			else
			{
				//chartPanel = null;
				getContentPane().remove(west);
				west.removeAll();
				activeViewers[4] = false;
				if (activeViewers[0] == true) //Line chart
				{
					line = true;
					createLine(west);
				}

				if (activeViewers[1] == true) //Time chart
				{
					time = true;
					createTimeSeries(west);
				}

				if (activeViewers[2] == true) //Bar chart
				{
					bar = true;
					createBar(west);
				}

				/*if (activeViewers[3] == true) //Pie chart
				{
					pie = true;
					createPie(west);
				}*/

				if (activeViewers[4] == true) //Scatter Chart
				{
					scatter = true;
					createScatter(west);
				}

				if (activeViewers[5] == true) //Report
				{
					reportt = true;
					createReport(west);
				}

				getContentPane().add(west, BorderLayout.WEST);
				scatter = true;
				analysis3 = "ASDADADADA";
			}
		}


	/*
	------------------------ Pie Chart ------------------------
	*/
/*
						private void createPie(JPanel west) {
							// Different way to create pie chart
							/*
							 * var dataset = new DefaultPieDataset(); dataset.setValue("Unemployed", 3.837);
							 * dataset.setValue("Employed", 96.163);
							 *
							 * JFreeChart pieChart = ChartFactory.createPieChart("Women's Unemployment",
							 * dataset, true, true, false);

								DefaultCategoryDataset dataset = new DefaultCategoryDataset();
								/*dataset.addValue(3.946, "Unemployed", "Men");
								dataset.addValue(96.054, "Employed", "Men");
								dataset.addValue(3.837, "Unemployed", "Women");
								dataset.addValue(96.163, "Employed", "Women");

								for (int i=0;i<dataObj.getSeries();i++){
									dataset.addValue(3.946, "Unemployed", "Men");
									dataset.addValue(96.054, "Employed", "Men");
								}

								JFreeChart pieChart = ChartFactory.createMultiplePieChart(analysis3, dataset,
										TableOrder.BY_COLUMN, true, true, false);

								ChartPanel chartPanel = new ChartPanel(pieChart);
								chartPanel.setPreferredSize(new Dimension(400, 300));
								chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
								chartPanel.setBackground(Color.white);

							if (pie == true)
							{
								west.add(chartPanel);
								pie = false;
								activeViewers[3] = true;
							}

							else
							{
								//chartPanel = null;
								getContentPane().remove(west);
								west.removeAll();
								activeViewers[3] = false;
								if (activeViewers[0] == true) //Line chart
								{
									line = true;
									createLine(west);
								}

								if (activeViewers[1] == true) //Time chart
								{
									time = true;
									createTimeSeries(west);
								}

								if (activeViewers[2] == true) //Bar chart
								{
									bar = true;
									createBar(west);
								}

								if (activeViewers[3] == true) //Pie chart
								{
									pie = true;
									createPie(west);
								}

								if (activeViewers[4] == true) //Scatter Chart
								{
									scatter = true;
									createScatter(west);
								}

								if (activeViewers[5] == true) //Report
								{
									reportt = true;
									createReport(west);
								}

								getContentPane().add(west, BorderLayout.WEST);
								pie = true;
								analysis3 = "ASDADADADA";
							}
						}
*/
	/*
	------------------------ Bar Graph ------------------------
	*/

	/**
	 * Creates a bar graph
	 * @param west JPanel
	 */
	private void createBar(JPanel west) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		if (dataObj.getSeries() >= 1)
		{
			int amount2 = dataObj.getTime2()-dataObj.getTime1();
			for (int i = dataObj.getTime2(); i >= dataObj.getTime1(); i--)
			{
				dataset.setValue(dataObj.getData()[0][amount2], dataObj.getSeriesNames()[0], new Year(i));
				amount2 = amount2 - (int)(1);
			}
			/*
			dataset.setValue(5.6, "Mortality/1000 births", "2018");*/
		}

		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

		if (dataObj.getSeries() >= 2)
		{
			int amount3 = dataObj.getTime2()-dataObj.getTime1();
			for (int i = dataObj.getTime1(); i >= dataObj.getTime1(); i--)
			{
				dataset2.setValue(dataObj.getData()[1][amount3], dataObj.getSeriesNames()[1], new Year(i));
				amount3 = amount3 - (int)(1);
			}
			/*
			dataset2.setValue(10623, "Health Expenditure per Capita", "2018");*/
		}
		DefaultCategoryDataset dataset3 = new DefaultCategoryDataset();
		if (dataObj.getSeries() >= 3)
		{
			int amount4 = dataObj.getTime2()-dataObj.getTime1();
			for (int i = dataObj.getTime1(); i >= dataObj.getTime1(); i--)
			{
				dataset3.setValue(dataObj.getData()[2][amount4], dataObj.getSeriesNames()[1], new Year(i));
				amount4 = amount4 - (int)(1);
			}
		}

		CategoryPlot plot = new CategoryPlot();
		BarRenderer barrenderer1 = new BarRenderer();
		BarRenderer barrenderer2 = new BarRenderer();
		BarRenderer barrenderer3 = new BarRenderer();

		if (dataObj.getSeries() >= 1)
		{
			plot.setDataset(0, dataset);
			plot.setRenderer(0, barrenderer1);
			CategoryAxis domainAxis = new CategoryAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis(""));
		}

		if (dataObj.getSeries() >= 2)
		{
			plot.setDataset(1, dataset2);
			plot.setRenderer(1, barrenderer2);
			plot.setRangeAxis(1, new NumberAxis("US$"));
		}

		if (dataObj.getSeries() >= 3)
		{
			plot.setDataset(2, dataset3);
			plot.setRenderer(2, barrenderer3);
			plot.setRangeAxis(2, new NumberAxis("US$"));
		}

		plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis
		plot.mapDatasetToRangeAxis(2, 2); // 2nd dataset to 2nd y-axis

		JFreeChart barChart = new JFreeChart(dataObj.getAnalysis(),
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

		// Different way to create bar chart
		/*
		 * dataset = new DefaultCategoryDataset();
		 *
		 * dataset.addValue(3.946, "Unemployed", "Men"); dataset.addValue(96.054,
		 * "Employed", "Men"); dataset.addValue(3.837, "Unemployed", "Women");
		 * dataset.addValue(96.163, "Employed", "Women"); barChart =
		 * ChartFactory.createBarChart("Unemployment: Men vs Women", "Gender",
		 * "Percentage", dataset, PlotOrientation.VERTICAL, true, true, false);
		 */

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		//west.add(chartPanel);

		if (startint == 1)
		{
			chartPanel.setVisible(false);
		}

		if (startint != 1)
		{
			chartPanel.setVisible(true);
		}

		if (bar == true)
		{
			west.add(chartPanel);
			bar = false;
			activeViewers[2] = true;
		}

		else
		{
			//chartPanel = null;
			getContentPane().remove(west);
			west.removeAll();
			activeViewers[2] = false;
			if (activeViewers[0] == true) //Line chart
			{
				line = true;
				createLine(west);
			}

			if (activeViewers[1] == true) //Time chart
			{
				time = true;
				createTimeSeries(west);
			}

			if (activeViewers[2] == true) //Bar chart
			{
				bar = true;
				createBar(west);
			}

			/*if (activeViewers[3] == true) //Pie chart
			{
				pie = true;
				createPie(west);
			}*/

			if (activeViewers[4] == true) //Scatter Chart
			{
				scatter = true;
				createScatter(west);
			}

			if (activeViewers[5] == true) //Report
			{
				reportt = true;
				createReport(west);
			}

			getContentPane().add(west, BorderLayout.WEST);
			bar = true;
			analysis3 = "ASDADADADA";
		}
	}


	/*
	------------------------ Lines Graph ------------------------
	*/

	/**
	 * Creates line graph
	 * @param west JPanel
	 */
	private void createLine(JPanel west) {
		XYSeries series1 = new XYSeries("");
		if (dataObj.getSeries() >= 1)
		{
			series1 = new XYSeries(dataObj.getSeriesNames()[0]);
			int amount1 = dataObj.getTime2()-dataObj.getTime1();
			for (int i = dataObj.getTime2(); i >= dataObj.getTime1(); i--)
			{
				series1.add(i, dataObj.getData()[0][amount1]);
				amount1 = amount1 - (int)(1);
			}
			/*
			series1.add(2018, 5.6);
			series1.add(2017, 5.7);
			series1.add(2016, 5.8);
			series1.add(2015, 5.8);
			series1.add(2014, 5.9);
			series1.add(2013, 6.0);
			series1.add(2012, 6.1);
			series1.add(2011, 6.2);
			series1.add(2010, 6.4);*/
		}

		XYSeries series2 = new XYSeries("");
		if (dataObj.getSeries() >= 2)
		{
			series2 = new XYSeries(dataObj.getSeriesNames()[1]);
			int amount2 = dataObj.getTime2()-dataObj.getTime1();
			for (int i = dataObj.getTime2(); i >= dataObj.getTime1(); i--)
			{
				series2.add(i, dataObj.getData()[1][amount2]);
				amount2 = amount2 - (int)(1);
			}
			/*
			series2.add(2018, 10624);
			series2.add(2017, 10209);
			series2.add(2016, 9877);
			series2.add(2015, 9491);
			series2.add(2014, 9023);
			series2.add(2013, 8599);
			series2.add(2012, 8399);
			series2.add(2011, 8130);
			series2.add(2010, 7930);*/
		}

		XYSeries series3 = new XYSeries("");
		if (dataObj.getSeries() >= 3)
		{
			series3 = new XYSeries(dataObj.getSeriesNames()[2]);
			int amount3 = dataObj.getTime2()-dataObj.getTime1();
			for (int i = dataObj.getTime2(); i >= dataObj.getTime1(); i--)
			{
				series3.add(i, dataObj.getData()[2][amount3]);
				amount3 = amount3 - (int)(1);
			}
			/*
			series3.add(2018, 2.92);
			series3.add(2017, 2.87);
			series3.add(2016, 2.77);
			series3.add(2015, 2.8);
			series3.add(2014, 2.83);
			series3.add(2013, 2.89);
			series3.add(2012, 2.93);
			series3.add(2011, 2.97);
			series3.add(2010, 3.05);*/
		}

		XYSeriesCollection dataset = new XYSeriesCollection();

		if (dataObj.getSeries() >= 1)
		{
			dataset.addSeries(series1);
		}

		if (dataObj.getSeries() >= 2)
		{
			dataset.addSeries(series2);
		}

		if (dataObj.getSeries() >= 3)
		{
			dataset.addSeries(series3);
		}

		JFreeChart chart = ChartFactory.createXYLineChart(dataObj.getAnalysis(), "Year", "", dataset,
				PlotOrientation.VERTICAL, true, true, false);

		XYPlot plot = chart.getXYPlot();

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesStroke(0, new BasicStroke(2.0f));

		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.white);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);

		chart.getLegend().setFrame(BlockBorder.NONE);

		chart.setTitle(
				new TextTitle(dataObj.getAnalysis(), new Font("Serif", java.awt.Font.BOLD, 18)));

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		//west.add(chartPanel);

		if (startint == 1)
		{
			chartPanel.setVisible(false);
		}

		if (startint != 1)
		{
			chartPanel.setVisible(true);
		}
		if (line == true)
		{
			west.add(chartPanel);
			line = false;
			activeViewers[0] = true;
		}

		else
		{
			//chartPanel = null;
			getContentPane().remove(west);
			west.removeAll();
			activeViewers[0] = false;
			if (activeViewers[0] == true) //Line chart
			{
				line = true;
				createLine(west);
			}

			if (activeViewers[1] == true) //Time chart
			{
				time = true;
				createTimeSeries(west);
			}

			if (activeViewers[2] == true) //Bar chart
			{
				bar = true;
				createBar(west);
			}

		/*	if (activeViewers[3] == true) //Pie chart
			{
				pie = true;
				createPie(west);
			}*/

			if (activeViewers[4] == true) //Scatter Chart
			{
				scatter = true;
				createScatter(west);
			}

			if (activeViewers[5] == true) //Report
			{
				reportt = true;
				createReport(west);
			}

			getContentPane().add(west, BorderLayout.WEST);
			line = true;
			analysis3 = "ASDADADADA";
		}

	}


	/*
	------------------------ Time Chart ------------------------
	*/

	/**
	 * Creates a Time chart
	 * @param west JPanel
	 */
	private void createTimeSeries(JPanel west) {
		TimeSeries series1 = new TimeSeries("");
		if (dataObj.getSeries() >= 1)
		{
			series1 = new TimeSeries(dataObj.getSeriesNames()[0]);
			int amount1 = dataObj.getTime2()-dataObj.getTime1();
			for (int i = dataObj.getTime2(); i >= dataObj.getTime1(); i--)
			{
				series1.add(new Year(i), dataObj.getData()[0][amount1]);
				amount1 = amount1 - (int)(1);
			}

/*series1.add(new Year(2010), 6.4);*/
		}

		TimeSeries series2 = new TimeSeries("");
		TimeSeriesCollection dataset2 = new TimeSeriesCollection();
		if (dataObj.getSeries() >= 2)
		{
			series2 = new TimeSeries(dataObj.getSeriesNames()[1]);
			int amount2 = dataObj.getTime2()-dataObj.getTime1();
			for (int i = dataObj.getTime2(); i >= dataObj.getTime1(); i--)
			{
				series2.add(new Year(i), dataObj.getData()[1][amount2]);
				amount2 = amount2 - (int)(1);
			}
//		series2.add(new Year(2010), 7930);
			dataset2.addSeries(series2);
		}

		TimeSeries series3 = new TimeSeries("");
		if (dataObj.getSeries() >= 3)
		{
			series3 = new TimeSeries(dataObj.getSeriesNames()[2]);
			int amount3 = dataObj.getTime2()-dataObj.getTime1();
			for (int i = dataObj.getTime2(); i >= dataObj.getTime1(); i--)
			{
				series3.add(new Year(i), dataObj.getData()[2][amount3]);
				amount3 = amount3 - (int)(1);
			}
/*
			series3.add(new Year(2010), 3.05);*/
		}

		TimeSeriesCollection dataset = new TimeSeriesCollection();
		if (dataObj.getSeries() >= 1)
		{
				dataset.addSeries(series1);
		}

		if (dataObj.getSeries() >= 3)
		{
				dataset.addSeries(series3);
		}

		XYPlot plot = new XYPlot();
		XYSplineRenderer splinerenderer1 = new XYSplineRenderer();
		XYSplineRenderer splinerenderer2 = new XYSplineRenderer();

		if (dataObj.getSeries() >= 1)
		{
			plot.setDataset(0, dataset);
			plot.setRenderer(0, splinerenderer1);
			DateAxis domainAxis = new DateAxis("Year");
			plot.setDomainAxis(domainAxis);
			plot.setRangeAxis(new NumberAxis("Kg"));
		}

		if (dataObj.getSeries() >= 2)
		{
			plot.setDataset(1, dataset2);
			plot.setRenderer(1, splinerenderer2);
			plot.setRangeAxis(1, new NumberAxis("US$"));
		}

		plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

		JFreeChart chart = new JFreeChart(dataObj.getAnalysis(),
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		//west.add(chartPanel);

		if (startint == 1)
		{
			chartPanel.setVisible(false);
		}

		if (startint != 1)
		{
			chartPanel.setVisible(true);
		}

		if (time == true)
		{
			west.add(chartPanel);
			time = false;
			activeViewers[1] = true;
		}

		else
		{
			//chartPanel = null;
			getContentPane().remove(west);
			west.removeAll();
			activeViewers[1] = false;
			if (activeViewers[0] == true) //Line chart
			{
				line = true;
				createLine(west);
			}

			if (activeViewers[1] == true) //Time chart
			{
				time = true;
				createTimeSeries(west);
			}

			if (activeViewers[2] == true) //Bar chart
			{
				bar = true;
				createBar(west);
			}

			/*if (activeViewers[3] == true) //Pie chart
			{
				pie = true;
				createPie(west);
			}*/

			if (activeViewers[4] == true) //Scatter Chart
			{
				scatter = true;
				createScatter(west);
			}

			if (activeViewers[5] == true) //Report
			{
				reportt = true;
				createReport(west);
			}

			getContentPane().add(west, BorderLayout.WEST);
			time = true;
			analysis3 = "ASDADADADA";
		}

	}

	public static void userVerify(String[] args) {
		int x;
		Login.main(args);
		while(1==1){
			x=1;
		}
	}


	/*
	------------------------------------- Main ------------------------------------------------
	*/

	/*
	 * The main
	 */
	public static void main(String[] args) {
		userVerify(args);
		Login.main(args);
		JFrame frame = MainUI.getInstance();
		frame.setSize(900, 600);
		frame.pack();
		frame.setVisible(true);

	}
	// TODO Auto-generated method stub

}
