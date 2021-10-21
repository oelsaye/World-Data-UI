package statsVisualiser.gui;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

/**
 * 
 * @author Youssef Youssef
 * 
 * Data class, gets the data from the world bank 
 * 
 */
public class Data {

	private float[][] data;

	// EN.ATM.PM25.MC.M3 = PM2.5 air pollution, mean annual exposure (micrograms per
	// cubic meter)
	// EN.ATM.CO2E.PC = CO2 emissions (metric tons per capita)
	// EG.USE.PCAP.KG.OE Energy use (kg of oil equivalent per capita)
	// AG.LND.FRST.ZS = Forest area (% of land area)
	// NY.GDP.PCAP.CD = GDP per capita (current US$)
	// SE.XPD.TOTL.GD.ZS = Government expenditure on education, total (% of GDP)
	// SH.MED.BEDS.ZS = Hospital beds (per 1,000 people)
	// SH.XPD.CHEX.GD.ZS = Current health expenditure (% of GDP)
	// SH.XPD.CHEX.PC.CD = Current health expenditure per capita (current US$)
	// SP.DYN.IMRT.IN = Mortality rate, infant (per 1,000 live births)
	// SE.XPD.TOTL.GD.ZS = Government expenditure on education, total (% of
	// government expenditure)

	/**
	 * Constructor
	 * @param country the country
	 * @param year1 the "from" year
	 * @param year2 the "to" year
	 * @param indicator	 the indicator
	 */
	public Data(String country, String year1, String year2, String indicator) {
		String cntry = "";
		if (country == "China") {
			cntry = "chn";
		} else if (country == "USA") {
			cntry = "usa";

		} else if (country == "Brazil") {
			cntry = "bra";

		} else if (country == "France") {
			cntry = "fra";

		} else if (country == "Canada") {
			cntry = "can";

		}
		int length = Integer.parseInt(year2) - Integer.parseInt(year1) + 1;
		System.out.println(length);
		
		
		
		data = new float[length][2];

		String urlString = String.format("http://api.worldbank.org/v2/country/%s/indicator/%s?date=%s:%s&format=json",
				cntry, indicator, year1, year2);
		//System.out.println(urlString);
		float populationForYear = 0;
		// int cumulativePopulation = 0;
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			// IF THE RESPONSE IS 200 OK GET THE LINE WITH THE RESULTS
			if (responsecode == 200) {
				String inline = "";
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {

					inline += sc.nextLine();
				}
				sc.close();
				// PROCESS THE JSON AS ONE LINE
				JsonArray jsonArray = new JsonParser().parse(inline).getAsJsonArray();
				// int size = jsonArray.size();
				int sizeOfResults = jsonArray.get(1).getAsJsonArray().size();

				for (int i = 0; i < sizeOfResults; i++) {

					// GET FOR EACH ENTRY THE YEAR FROM THE FIELD
					float year = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("date").getAsInt();
					// CHECK IF THERE IS A VALUE FOR THE POPULATION FOR A
					// GIVEN YEAR
					if (jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull())
						populationForYear = 0;
					else
						// GET THE POPULATION FOR THE GIVEN YEAR FROM THE
						// FIELD
						populationForYear = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value")
								.getAsFloat();
					data[length - 1 - i][1] = populationForYear;
					data[length - 1 - i][0] = year;


				}


			}
		} catch (IOException e) {
			// TODO Auto-generated catch block e.printStackTrace();
		}
		return;

	}

	/**
	 * returns the data
	 * @return float array with the data
	 */
	public float[][] getData() {
		return data;
	}


}
