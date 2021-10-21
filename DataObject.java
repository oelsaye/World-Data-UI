/**
 *
 * @author Saif Jouda, Youssef Youssef
 * 
 *	Data Object class
 *	maintains an object containing data for the use of analysis and MainUI
 */
package statsVisualiser.gui;

public class DataObject {
  private String countryName;
  private int countryNum;
  private	int time1Name;
  private int time2Name;
  private int analysisNum;
  private String analysisType;
  private float[][] data;
  private int series;
  private String[] seriesNames;
  private int[] years;

  /**
   * Constructor
   * @param country the country
   * @param time1 the "from" year
   * @param time2 the  "to" year
   * @param analysis the analysis type
   */
  public DataObject(String country, int time1, int time2, String analysis){
  	countryName=country;
    time1Name=time1;
    time2Name=time2;
    analysisType=analysis;
    }

  /**
   * Constructor
   * @param country the country
   * @param time1 the "from" year
   * @param time2 the "to" year
   * @param analysis the type
   * @param seriesInput the series
   * @param dataInput the data array
   * @param years the years array
   */
  public DataObject(String country, int time1, int time2, String analysis,String[] seriesInput,float[][] dataInput, int[] years){
  data=dataInput;
  series= seriesInput.length;
	seriesNames=seriesInput;
	countryName=country;
  time1Name=time1;
  time2Name=time2;
  analysisType=analysis;
  this.years = years;
  }

  /**
   * gets the country
   * @return the country
   */
  public String getCountry(){
    return countryName;
  }

  /**
   * gets the first year
   * @return the "from" year
   */
  public int getTime1(){
    return time1Name;
  }

  /**
   * gets the second year
   * @return the "to" year
   */
  public int getTime2(){
    return time2Name;
  }

  /**
   * get the analysis type
   * @return string of the type
   */
  public String getAnalysis(){
    return analysisType;
  }

  /**
   * set the data
   * @param newData float array
   */
  public void setData(float[][] newData){
    data=newData;
  }

  /**
   * sets the series
   * @param a integer
   */
  public void setSeries(int a) {
	  series=a;
  }

  /**
   * gets the series
   * @return integer
   */
  public int getSeries() {
	  return series;
  }

  /**
   * get the data
   * @return
   */
  public float[][] getData() {
	  return data;
  }

  /**
   * get series names
   * @return string array
   */
  public String[] getSeriesNames() {
	  return seriesNames;
  }

  /**
   * gets the years
   * @return integer array storing the years
   */
  public int[] years() {
          return years;
      }

}
