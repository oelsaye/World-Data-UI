package statsVisualiser.gui;

/**
 * 
 * @author Youssef Youssef, Saif Jouda, Omar Elsayed
 *
 *	Analysis Class, used to store analysis objects and perform the strategy
 *	
 *
 */

public class Analysis implements Analysis1{


	private String countryName;
	private	String time1Name;
	private String time2Name;
	private String analysisType;
	private int[] years;
	private float[][] datas;


	/**
	 * Constructor
	 * @param countryName the country
	 * @param time1Name the "from" year
	 * @param time2Name the "to" year
	 * @param analysisType the type
	 */
	public Analysis(String countryName,String time1Name,String time2Name,String analysisType) {
		this.countryName = countryName;
		this.time1Name = time1Name;
		this.time2Name = time2Name;
		this.analysisType = analysisType;

	}

	/**
	 * sets the analysis type
	 * @param analysis
	 */
	public void setAnalysisType(String analysis) {
		analysisType = analysis;

	}

	/**
	 * Performs the strategy
	 */
	public DataObject initiateStrategy() {
		DataObject data;
		if (analysisType.equals("CO2 emissions vs Energy use vs PM2.5 air pollution, mean annual exposure")) {

			Data dataCO2 = new Data(countryName,time1Name,time2Name,"EN.ATM.CO2E.PC");
			Data dataEnergy = new Data(countryName,time1Name,time2Name,"EG.USE.PCAP.KG.OE");
			Data dataAIR = new Data(countryName,time1Name,time2Name,"EN.ATM.PM25.MC.M3");

			int length = Integer.parseInt(time2Name)-Integer.parseInt(time1Name)+1;
			this.datas = new float[3][length];
			this.years = new int[length];

			for (int i =0;i<length;i++) {
				this.datas[0][i] = dataCO2.getData()[i][1];
				this.datas[1][i] = dataEnergy.getData()[i][1];
				this.datas[2][i] = dataAIR.getData()[i][1];
				years[i]  = (int) dataCO2.getData()[i][0];


			}
			String[] series = new String[3];
			series[0] = "CO2 emissions(metric tons per capita)";
			series[1] = "Energy(kg of oil equivalent per capita)";
			series[2] = "PM2.5 air pollution(micrograms per cubic meter)";
			data = new DataObject(countryName,Integer.parseInt(time1Name),Integer.parseInt(time2Name),analysisType,series,datas,years);
			return data;

		}
		else {

			SecondAnalysis analysis = new SecondAnalysis(countryName,time1Name,time2Name,analysisType);
			data= analysis.initiateStrategy();
			return data;

		}

	}
/*
	public float[][] getData() {
		// TODO Auto-generated method stub
		return datas;
	}

	public int[] getYears() {
		// TODO Auto-generated method stub
		return years;
	}

	public void displayViewer() {
		// TODO Auto-generated method stub

	}

	public void displayResult() {
		// TODO Auto-generated method stub

	}
*/
	/**
	 * sets the "from" year
	 * @param time1
	 */
	public void setTimePeriod1(String time1) {
		time1Name = time1;

	}

	/**
	 * sets the "to" year
	 * @param time2
	 */
	public void setTimePeriod2(String time2) {
		time2Name = time2;

	}

	/**
	 * sets the country
	 * @param country the country
	 */
	public void setCountryName(String country) {
		countryName = country;

	}

}


/**
 * 
 * @author Youssef Youssef
 * 
 * Calculates the second analysis
 *
 */
class SecondAnalysis implements Analysis1 {


	private String countryName;
	private	String time1Name;
	private String time2Name;
	private String analysisType;
	private int[] years;
	private float[][] datas;
	private String[] series;

	/**
	 * Constructor
	 * @param countryName the country
	 * @param time1Name the from year
	 * @param time2Name the to year
	 * @param analysisType the type
	 */
	public SecondAnalysis(String countryName,String time1Name,String time2Name,String analysisType) {
		this.countryName = countryName;
		this.time1Name = time1Name;
		this.time2Name = time2Name;
		this.analysisType = analysisType;

	}


	/**
	 * Initiates the strategy
	 * @return data object
	 */
	public DataObject initiateStrategy() {
		DataObject data;
		if (analysisType.equals("PM2.5 air pollution, mean annual exposure vs Forest area")){
			Data dataAIR = new Data(countryName,time1Name,time2Name,"EN.ATM.PM25.MC.M3");
			Data dataFOR = new Data(countryName,time1Name,time2Name,"AG.LND.FRST.ZS");
			int length = Integer.parseInt(time2Name)-Integer.parseInt(time1Name)+1;

			this.datas = new float[2][length];
			this.years = new int[length];
			for (int i =0;i<length;i++) {
				this.datas[0][i] = dataAIR.getData()[i][1];
				this.datas[1][i] = dataFOR.getData()[i][1];
				years[i]  = (int) dataAIR.getData()[i][0];
			}

				String[] series = new String[2];
				series[0] = "PM2.5 air pollution(micrograms per cubic meter)";
				series[1] = "Forest area(% of land area)";

				data = new DataObject(countryName,Integer.parseInt(time1Name),Integer.parseInt(time2Name),analysisType,series,datas,years);
				return data;



		}
		else {

			ThirdAnalysis analysis = new ThirdAnalysis(countryName,time1Name,time2Name,analysisType);
			data= analysis.initiateStrategy();
			return data;

		}
	}



}

/**
 * 
 * @author Youssef Youssef
 * 
 * Calculates the second analysis
 *
 */
class ThirdAnalysis implements Analysis1{


	private String countryName;
	private	String time1Name;
	private String time2Name;
	private String analysisType;
	private int[] years;
	private float[][] datas;

	/**
	 * Constructor
	 * @param countryName the country
	 * @param time1Name the from year
	 * @param time2Name the to year
	 * @param analysisType the type
	 */
	public ThirdAnalysis(String countryName,String time1Name,String time2Name,String analysisType) {
		this.countryName = countryName;
		this.time1Name = time1Name;
		this.time2Name = time2Name;
		this.analysisType = analysisType;
	}


	/**
	 * Initiates the strategy
	 * @return data object
	 */
	public DataObject initiateStrategy() {
		DataObject data;
		 if (analysisType.equals("Ratio of CO2 emissions and GDP per capita (current US$)")){
				Data dataCO2 = new Data(countryName,time1Name,time2Name,"EN.ATM.CO2E.PC");
				Data dataGDP = new Data(countryName,time1Name,time2Name,"NY.GDP.PCAP.CD");
				int length = Integer.parseInt(time2Name)-Integer.parseInt(time1Name)+1;
				this.datas = new float[2][length];
				this.years = new int[length];
				for (int i =0;i<length;i++) {
					this.datas[0][i] = dataCO2.getData()[i][1];
					this.datas[1][i] = dataGDP.getData()[i][1];
					years[i]  = (int) dataCO2.getData()[i][0];

				}

					String[] series = new String[2];
					series[0] = "Ratio of CO2 emissions(metric tons per capita)";
					series[1] = "GDP per capita(current US$)";

					data = new DataObject(countryName,Integer.parseInt(time1Name),Integer.parseInt(time2Name),analysisType,series,datas,years);
					return data;
		}
		else {

			FourthAnalysis analysis = new FourthAnalysis(countryName,time1Name,time2Name,analysisType);
			data= analysis.initiateStrategy();
			return data;

		}

	}








}

/**
 * 
 * @author Youssef Youssef
 * 
 * Calculates the second analysis
 *
 */
class FourthAnalysis implements Analysis1{


	private String countryName;
	private	String time1Name;
	private String time2Name;
	private String analysisType;
	private int[] years;
	private float[][] datas;

	/**
	 * Constructor
	 * @param countryName the country
	 * @param time1Name the from year
	 * @param time2Name the to year
	 * @param analysisType the type
	 */
	public FourthAnalysis(String countryName,String time1Name,String time2Name,String analysisType) {
		this.countryName = countryName;
		this.time1Name = time1Name;
		this.time2Name = time2Name;
		this.analysisType = analysisType;

	}


	/**
	 * Initiates the strategy
	 * @return data object
	 */
	public DataObject initiateStrategy() {
		DataObject data;
		if (analysisType.equals("Average Forest area for the selected years")){
			Data dataFOR = new Data(countryName,time1Name,time2Name,"AG.LND.FRST.ZS");
			int length = Integer.parseInt(time2Name)-Integer.parseInt(time1Name)+1;
			this.datas = new float[1][length];
			this.years = new int[length];
			for (int i =0;i<length;i++) {
				this.datas[0][i] = dataFOR.getData()[i][1];
				years[i]  = (int) dataFOR.getData()[i][0];
			}

				String[] series = new String[1];
				series[0] = "Forest area(% of land area)";
				data = new DataObject(countryName,Integer.parseInt(time1Name),Integer.parseInt(time2Name),analysisType,series,datas,years);
				return data;
	}
		else {

			FifthAnalysis analysis = new FifthAnalysis(countryName,time1Name,time2Name,analysisType);
			data= analysis.initiateStrategy();
			return data;

		}

	}



}

/**
 * 
 * @author Youssef Youssef
 * 
 * Calculates the second analysis
 *
 */
class FifthAnalysis implements Analysis1{


	private String countryName;
	private	String time1Name;
	private String time2Name;
	private String analysisType;
	private int[] years;
	private float[][] datas;


	/**
	 * Constructor
	 * @param countryName the country
	 * @param time1Name the from year
	 * @param time2Name the to year
	 * @param analysisType the type
	 */
	public FifthAnalysis(String countryName,String time1Name,String time2Name,String analysisType) {
		this.countryName = countryName;
		this.time1Name = time1Name;
		this.time2Name = time2Name;
		this.analysisType = analysisType;

	}



	/**
	 * Initiates the strategy
	 * @return data object
	 */
	public DataObject initiateStrategy() {
		DataObject data;
		if (analysisType.equals("Average of Government expenditure on education, total for the selected years")){
			Data dataGOV = new Data(countryName,time1Name,time2Name,"SE.XPD.TOTL.GD.ZS");
			int length = Integer.parseInt(time2Name)-Integer.parseInt(time1Name)+1;
			this.datas = new float[1][length];
			this.years = new int[length];
			for (int i =0;i<length;i++) {
				this.datas[0][i] = dataGOV.getData()[i][1];
				years[i]  = (int) dataGOV.getData()[i][0];
			}

				String[] series = new String[1];
				series[0] = "Government expenditure on education(% of GDP)";
				data = new DataObject(countryName,Integer.parseInt(time1Name),Integer.parseInt(time2Name),analysisType,series,datas,years);
				return data;
}
		else {

			SixthAnalysis analysis = new SixthAnalysis(countryName,time1Name,time2Name,analysisType);
			data= analysis.initiateStrategy();
			return data;

		}

	}




}

/**
 * 
 * @author Youssef Youssef
 * 
 * Calculates the second analysis
 *
 */
class SixthAnalysis implements Analysis1{


	private String countryName;
	private	String time1Name;
	private String time2Name;
	private String analysisType;
	private int[] years;
	private float[][] datas;


	/**
	 * Constructor
	 * @param countryName the country
	 * @param time1Name the from year
	 * @param time2Name the to year
	 * @param analysisType the type
	 */
	public SixthAnalysis(String countryName,String time1Name,String time2Name,String analysisType) {
		this.countryName = countryName;
		this.time1Name = time1Name;
		this.time2Name = time2Name;
		this.analysisType = analysisType;

	}


	/**
	 * Initiates the strategy
	 * @return data object
	 */
	public DataObject initiateStrategy() {
		DataObject data;
		if (analysisType.equals("Ratio of Hospital beds and Current health expenditure")){
		    Data dataBED = new Data(countryName,time1Name,time2Name,"SH.MED.BEDS.ZS");
			Data dataHEALTH = new Data(countryName,time1Name,time2Name,"SH.XPD.CHEX.GD.ZS");

			int length = Integer.parseInt(time2Name)-Integer.parseInt(time1Name)+1;
			this.datas = new float[2][length];
			this.years = new int[length];
			for (int i =0;i<length;i++) {
				this.datas[0][i] = dataBED.getData()[i][1];
				this.datas[1][i] = dataHEALTH.getData()[i][1];
				years[i]  = (int) dataBED.getData()[i][0];

			}

				String[] series = new String[2];
				series[0] = "Ratio of Hospital beds(per 1000 people)";
				series[1] = "Current health expenditure(% of GDP)";

				data = new DataObject(countryName,Integer.parseInt(time1Name),Integer.parseInt(time2Name),analysisType,series,datas,years);
				return data;
	}
		else {

			SeventhAnalysis analysis = new SeventhAnalysis(countryName,time1Name,time2Name,analysisType);
			data= analysis.initiateStrategy();
			return data;

		}

	}



}

/**
 * 
 * @author Youssef Youssef
 * 
 * Calculates the second analysis
 *
 */
class SeventhAnalysis implements Analysis1{


	private String countryName;
	private	String time1Name;
	private String time2Name;
	private String analysisType;
	private int[] years;
	private float[][] datas;


	/**
	 * Constructor
	 * @param countryName the country
	 * @param time1Name the from year
	 * @param time2Name the to year
	 * @param analysisType the type
	 */
	public SeventhAnalysis(String countryName,String time1Name,String time2Name,String analysisType) {
		this.countryName = countryName;
		this.time1Name = time1Name;
		this.time2Name = time2Name;
		this.analysisType = analysisType;

	}


	/**
	 * Initiates the strategy
	 * @return data object
	 */
	public DataObject initiateStrategy() {
		DataObject data;
		if (analysisType.equals("Current health expenditure per capita vs Mortality rate, infant")){
			Data dataHEALTH = new Data(countryName,time1Name,time2Name,"SH.XPD.CHEX.PC.CD");
			Data dataRATE = new Data(countryName,time1Name,time2Name,"SP.DYN.IMRT.IN");

			int length = Integer.parseInt(time2Name)-Integer.parseInt(time1Name)+1;
			this.datas = new float[2][length];
			this.years = new int[length];
			for (int i =0;i<length;i++) {
				this.datas[0][i] = dataHEALTH.getData()[i][1];
				this.datas[1][i] = dataRATE.getData()[i][1];
				years[i]  = (int) dataHEALTH.getData()[i][0];
			}

				String[] series = new String[2];
				series[0] = "Current health expenditure per capita(% of GDP)";
				series[1] = "Mortality rate,infant(per 1000 live births)";

				data = new DataObject(countryName,Integer.parseInt(time1Name),Integer.parseInt(time2Name),analysisType,series,datas,years);
				return data;
}
		else {

			EigthAnalysis analysis = new EigthAnalysis(countryName,time1Name,time2Name,analysisType);
			data= analysis.initiateStrategy();
			return data;

		}

	}



}



/**
 * 
 * @author Youssef Youssef
 * 
 * Calculates the second analysis
 *
 */
class EigthAnalysis implements Analysis1{


	private String countryName;
	private	String time1Name;
	private String time2Name;
	private String analysisType;
	private int[] years;
	private float[][] datas;


	/**
	 * Constructor
	 * @param countryName the country
	 * @param time1Name the from year
	 * @param time2Name the to year
	 * @param analysisType the type
	 */
	public EigthAnalysis(String countryName,String time1Name,String time2Name,String analysisType) {
		this.countryName = countryName;
		this.time1Name = time1Name;
		this.time2Name = time2Name;
		this.analysisType = analysisType;

	}


	/**
	 * Initiates the strategy
	 * @return data object
	 */
	public DataObject initiateStrategy() {
		DataObject data;
		if (analysisType.equals("Ratio of Government expenditure on education, total vs Current health expenditure")){
			Data dataGOV = new Data(countryName,time1Name,time2Name,"SE.XPD.TOTL.GD.ZS");
			Data dataHEALTH = new Data(countryName,time1Name,time2Name,"SH.XPD.CHEX.GD.ZS");

			int length = Integer.parseInt(time2Name)-Integer.parseInt(time1Name)+1;
			this.datas = new float[2][length];
			this.years = new int[length];
			for (int i =0;i<length;i++) {
				this.datas[0][i] = dataGOV.getData()[i][1];
				this.datas[1][i] = dataHEALTH.getData()[i][1];
				years[i]  = (int) dataGOV.getData()[i][0];
			}

				String[] series = new String[2];
				series[0] = "Government expenditure on education total(% of GDP)";
				series[1] = "Current health expenditure(% of GDP)";

				data = new DataObject(countryName,Integer.parseInt(time1Name),Integer.parseInt(time2Name),analysisType,series,datas,years);
				return data;
}
		else {

			return null;

		}

	}



}
