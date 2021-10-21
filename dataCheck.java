package statsVisualiser.gui;

/**
 * 
 * @author Omar Elsayed
 *
 * Checks if the selection is valid
 *
 */
public class dataCheck {

	public String message;

	public dataCheck()
	{

	}

	/**
	 * Checks the country
	 * @param country
	 * @param analysis
	 * @return boolean true if its valid
	 */
	public boolean checkCountry(String country, String analysis)
	{
		//Average of Government expenditure on education, total for the selected years
		if (analysis == "Average of Government expenditure on education, total for the selected years")
		{
			String[] countrylist = new String[5];
			countrylist[0] = "Brazil";
			countrylist[1] = "Canada";
			countrylist[2] = "None";
			countrylist[3] = "France";
			countrylist[4] = "USA";

			for (int i = 0; i < countrylist.length; i++)
			{
				if (country.equals(countrylist[i]))
				{
					return true;
				}
			}
		}

		//Current health expenditure per capita vs Mortality rate, infant
		if (analysis != "Average of Government expenditure on education, total for the selected years")
		{
			String[] countrylist = new String[5];
			countrylist[0] = "Brazil";
			countrylist[1] = "Canada";
			countrylist[2] = "China";
			countrylist[3] = "France";
			countrylist[4] = "USA";

			for (int i = 0; i < countrylist.length; i++)
			{
				if (country.equals(countrylist[i]))
				{
					return true;
				}
			}
		}
		message = "Country not available.";
		return false;
	}

	/**
	 * Checks if the viewers are valid
	 * @param viewer
	 * @param analysis
	 * @return boolean true if valid
	 */
	public boolean checkViewer(String viewer, String analysis)
	{
		if (analysis.equals("country"))
		{
			String[] viewerlist = new String[5];
			viewerlist[0] = "Time Chart";
			viewerlist[1] = "Line Chart";
			viewerlist[2] = "Bar Chart";
			viewerlist[3] = "Scatter Chart";
			viewerlist[4] = "none";
			for (int i = 0; i < viewerlist.length; i++)
			{
				if (viewer.equals(viewerlist[i]))
				{
					return true;
				}
			}

			return false;
		}
		return false;
	}


	/**
	 * Checks the "from" and "to" years
	 * @param year1
	 * @param year2
	 * @param analysis
	 * @param country
	 * @return boolean true if years are valid
	 */
	public boolean checkYear1(int year1, int year2, String analysis, String country)
	{
		//1. China, Brazil, France: 2014, Canada,USA: 2015
		if (analysis.equals("CO2 emissions vs Energy use vs PM2.5 air pollution, mean annual exposure"))
		{
			if (country == "France" || country == "Brazil" || country == "China")
			{
				int y = 0;
				int[] viewerlist = new int[6];
				viewerlist[0] = 2010;viewerlist[1] = 2011;viewerlist[2] = 2012;
				viewerlist[3] = 2013;viewerlist[4] = 2014;viewerlist[5] = 0;

				for (int i = year1; i <= year2; i++)
				{
					for (int j = 0; j < viewerlist.length; j++)
					{
						if (viewerlist[j] == i)
						{
							y = 1;
						}
					}

					if (y == 0)
					{
						message = "Must be between 2010 and 2014";
						return false;
					}
					y = 0;
				}


				return true;
			}

			if (country == "USA" || country == "Canada")
			{
				int[] viewerlist = new int[7];
				int y = 0;

				viewerlist[0] = 2010;viewerlist[1] = 2011;viewerlist[2] = 2012;
				viewerlist[3] = 2013;viewerlist[4] = 2014;viewerlist[5] = 2015;
				viewerlist[6] = 0;

				for (int i = year1; i <= year2; i++)
				{
					for (int j = 0; j < viewerlist.length; j++)
					{
						if (viewerlist[j] == i)
						{
							y = 1;
						}
					}

					if (y == 0)
					{
						message = "Must be between 2010 and 2015";
						return false;
					}
					y = 0;
				}

				return true;
			}
		}

		//-------------------------------------------------------------
		//2. All Countries to 2017
		if (analysis.equals("PM2.5 air pollution, mean annual exposure vs Forest area"))
		{
				int[] viewerlist = new int[9];
				int y = 0;

				viewerlist[0] = 2010;viewerlist[1] = 2011;viewerlist[2] = 2012;
				viewerlist[3] = 2013;viewerlist[4] = 2014;viewerlist[5] = 2015;
				viewerlist[6] = 2016;viewerlist[7] = 2017;viewerlist[8] = 0;


				for (int i = year1; i <= year2; i++)
				{
					for (int j = 0; j < viewerlist.length; j++)
					{
						if (viewerlist[j] == i)
						{
							y = 1;
						}
					}

					if (y == 0)
					{
						message = "Must be between 2010 and 2017";
						return false;
					}
					y = 0;
				}

				return true;
		}

		//------------------------------------------------------------------------------------------------
		//3. France up to 2014, All other countries up to 2016
		if (analysis.equals("Ratio of CO2 emissions and GDP per capita (current US$)"))
		{
			if (country != "France")
			{
				int[] viewerlist = new int[8];
				int y = 0;

				viewerlist[0] = 2010;viewerlist[1] = 2011;viewerlist[2] = 2012;
				viewerlist[3] = 2013;viewerlist[4] = 2014;viewerlist[5] = 2015;
				viewerlist[6] = 2016;viewerlist[7] = 0;

				for (int i = year1; i <= year2; i++)
				{
					for (int j = 0; j < viewerlist.length; j++)
					{
						if (viewerlist[j] == i)
						{
							y = 1;
						}
					}

					if (y == 0)
					{
						message = "Must be between 2010 and 2016";
						return false;
					}
					y = 0;
				}

				return true;
			}

			if (country == "France")
			{
				int[] viewerlist = new int[21];
				int y = 0;

				viewerlist[0] = 2010;viewerlist[1] = 2011;viewerlist[2] = 2012;
				viewerlist[3] = 2013;viewerlist[4] = 2014;viewerlist[5] = 0;


				for (int i = year1; i <= year2; i++)
				{
					for (int j = 0; j < viewerlist.length; j++)
					{
						if (viewerlist[j] == i)
						{
							y = 1;
						}
					}

					if (y == 0)
					{
						message = "Must be between 2010 and 2014";
						return false;
					}
					y = 0;
				}

				return true;
			}
		}

		//----------------------------------------------------------------------------------
		//4. All countries up to 2018
		if (analysis.equals("Average Forest area for the selected years"))
		{
				int[] viewerlist = new int[10];
				int y = 0;
				viewerlist[0] = 2010;viewerlist[1] = 2011;viewerlist[2] = 2012;
				viewerlist[3] = 2013;viewerlist[4] = 2014;viewerlist[5] = 2015;
				viewerlist[6] = 2016;viewerlist[7] = 2017;viewerlist[8] = 2018;
				viewerlist[9] = 0;

				for (int i = year1; i <= year2; i++)
				{
					for (int j = 0; j < viewerlist.length; j++)
					{
						if (viewerlist[j] == i)
						{
							y = 1;
						}
					}

					if (y == 0)
					{
						message = "Must be between 2010 and 2018";
						return false;
					}
					y = 0;
				}

				return true;
		}

		//----------------------------------------------------------------------------
		//5.No China, USA only 2013-14, Canada 2010-11, France: Only 2017, Brazil 2010-2017
		if (analysis.equals("Average of Government expenditure on education, total for the selected years"))
		{

			if (country == "France")
			{
				int[] viewerlist = new int[3];

				viewerlist[0] = 0;viewerlist[1] = 2017;viewerlist[2] = 0;

				int y = 0;

				for (int i = year1; i <= year2; i++)
				{
					for (int j = 0; j < viewerlist.length; j++)
					{
						if (viewerlist[j] == i)
						{
							y = 1;
						}
					}

					if (y == 0)
					{
						message = "Must be between 2017 and 2017";
						return false;
					}
					y = 0;
				}

				return true;
			}

			if (country == "Canada")
			{
				int[] viewerlist = new int[2];


				viewerlist[0] = 2010;viewerlist[1] = 2011;
				int y = 0;
				for (int i = year1; i <= year2; i++)
				{
					for (int j = 0; j < viewerlist.length; j++)
					{
						if (viewerlist[j] == i)
						{
							y = 1;
						}
					}

					if (y == 0)
					{
						message = "Must be between 2010 and 2011";
						return false;
					}
					y = 0;
				}

				return true;
			}

			if (country == "USA")
			{
				int[] viewerlist = new int[3];
				int y = 0;
				viewerlist[0] = 2013;viewerlist[1] = 2014;viewerlist[2] = 0;

				for (int i = year1; i <= year2; i++)
				{
					for (int j = 0; j < viewerlist.length; j++)
					{
						if (viewerlist[j] == i)
						{
							y = 1;
						}
					}

					if (y == 0)
					{
						message = "Must be between 2013 and 2014";
						return false;
					}
					y = 0;
				}

				return true;
			}

			if (country == "Brazil")
			{
				int y = 0;
				int[] viewerlist = new int[9];
				viewerlist[0] = 2010;viewerlist[1] = 2011;viewerlist[2] = 2012;
				viewerlist[3] = 2013;viewerlist[4] = 2014;viewerlist[5] = 2015;
				viewerlist[6] = 2016;viewerlist[7] = 2017;viewerlist[8] = 0;

				for (int i = year1; i <= year2; i++)
				{
					for (int j = 0; j < viewerlist.length; j++)
					{
						if (viewerlist[j] == i)
						{
							y = 1;
						}
					}

					if (y == 0)
					{
						message = "Must be between 2010 and 2017";
						return false;
					}
					y = 0;
				}

				return true;
			}
		}
		//-------------------------------------------------------------------------------------
		//6. All up to 2017
		if (analysis.equals("Ratio of Hospital beds and Current health expenditure"))
		{
				int y = 0;
				int[] viewerlist = new int[9];

				viewerlist[0] = 2010;viewerlist[1] = 2011;viewerlist[2] = 2012;
				viewerlist[3] = 2013;viewerlist[4] = 2014;viewerlist[5] = 2015;
				viewerlist[6] = 2016;viewerlist[7] = 2017;viewerlist[8] = 0;

				for (int i = year1; i <= year2; i++)
				{
					for (int j = 0; j < viewerlist.length; j++)
					{
						if (viewerlist[j] == i)
						{
							y = 1;
						}
					}

					if (y == 0)
					{
						message = "Must be between 2010 and 2017";
						return false;
					}
					y = 0;
				}

				return true;
		}
		//-----------------------------------------------------------------------------------
		//7. All up to 2017
		if (analysis.equals("Current health expenditure per capita vs Mortality rate, infant"))
		{
				int y = 0;
				int[] viewerlist = new int[9];

				viewerlist[0] = 2010;viewerlist[1] = 2011;viewerlist[2] = 2012;
				viewerlist[3] = 2013;viewerlist[4] = 2014;viewerlist[5] = 2015;
				viewerlist[6] = 2016;viewerlist[7] = 2017;viewerlist[8] = 0;

				for (int i = year1; i <= year2; i++)
				{
					for (int j = 0; j < viewerlist.length; j++)
					{
						if (viewerlist[j] == i)
						{
							y = 1;
						}
					}

					if (y == 0)
					{
						message = "Must be between 2010 and 2017";
						return false;
					}
					y = 0;
				}

				return true;
		}

		//-----------------------------------------------------------------------------------
		//8. No China, USA 2013-14, Canada 2010-2011, France: only 2017, Brazil 2010-2017
		if (analysis.equals("Ratio of Government expenditure on education, total vs Current health expenditure"))
		{
			if (country == "France")
			{
				int[] viewerlist = new int[2];
				int y = 0;
				viewerlist[0] = 2017;viewerlist[1] = 0;

				for (int i = year1; i <= year2; i++)
				{
					for (int j = 0; j < viewerlist.length; j++)
					{
						if (viewerlist[j] == i)
						{
							y = 1;
						}
					}

					if (y == 0)
					{
						message = "Must be between 2017 and 2017";
						return false;
					}
					y = 0;
				}

				return true;
			}

			if (country == "Canada")
			{
				int[] viewerlist = new int[3];
				int y = 0;
				viewerlist[0] = 2010;viewerlist[1] = 2011;viewerlist[2] = 0;

				for (int i = year1; i <= year2; i++)
				{
					for (int j = 0; j < viewerlist.length; j++)
					{
						if (viewerlist[j] == i)
						{
							y = 1;
						}
					}

					if (y == 0)
					{
						message = "Must be between 2010 and 2011";
						return false;
					}
					y = 0;
				}

				return true;
			}

			if (country == "USA")
			{
				int[] viewerlist = new int[3];
				int y = 0;
				viewerlist[0] = 2013;viewerlist[1] = 2014;viewerlist[2] = 0;

				for (int i = year1; i <= year2; i++)
				{
					for (int j = 0; j < viewerlist.length; j++)
					{
						if (viewerlist[j] == i)
						{
							y = 1;
						}
					}

					if (y == 0)
					{
						message = "Must be between 2013 and 2014";
						return false;
					}
					y = 0;
				}

				return true;
			}

			if (country == "Brazil")
			{
				int[] viewerlist = new int[8];
				int y = 0;
				viewerlist[0] = 2010;viewerlist[1] = 2011;viewerlist[2] = 2012;
				viewerlist[3] = 2013;viewerlist[4] = 2014;viewerlist[5] = 2015;
				viewerlist[6] = 2016;viewerlist[7] = 2017;

				for (int i = year1; i <= year2; i++)
				{
					for (int j = 0; j < viewerlist.length; j++)
					{
						if (viewerlist[j] == i)
						{
							y = 1;
						}
					}

					if (y == 0)
					{
						message = "Must be between 2010 and 2017";
						return false;
					}
					y = 0;
				}

				return true;
			}
		}

		return false;
	}


}
