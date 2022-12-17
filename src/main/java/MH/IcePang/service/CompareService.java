package MH.IcePang.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompareService {
	@Value("${Lostark-API-Key}")
	private String LostarkApiKey;

	private final MarketApiService marketApiService;

	public JSONObject CompareFishing() {
		JSONObject result = marketApiService.CallMarketCategories(90600);
		JSONArray resultJsonArray = new JSONArray();
		resultJsonArray = (JSONArray) result.get("Items");
		JSONObject tempJson = new JSONObject();
		for(int i = 0; i < resultJsonArray.size(); i++) {
			tempJson = (JSONObject) resultJsonArray.get(i);
			double RecentPrice = Double.parseDouble(tempJson.get("RecentPrice").toString());
			double BundleCount = Double.parseDouble(tempJson.get("BundleCount").toString());
			double one = RecentPrice/BundleCount;
			tempJson.put("one",one);
			((JSONObject) resultJsonArray.get(i)).put("one", one);
		}
		System.out.println(resultJsonArray.toString());
		result.put("Items",resultJsonArray);
		return result;
	}

	//낚시 오레하 제작 비용 계산 메서드
	public double GetMakePrice(JSONObject result, String makeName) {
		JSONArray jsonArray = new JSONArray();
		jsonArray = (JSONArray) result.get("Items");
		double makePrice = 0.0;
		int carp = 0;
		int pearl = 0;
		int fish = 0;
		int price = 0;
		if(makeName.equals("최상급")) {
			carp = 52;
			pearl = 96;
			fish = 142;
			price = 285;
		}
		if(makeName.equals("상급")) {
			carp = 16;
			pearl = 64;
			fish = 128;
			price = 237;
		}
		if(makeName.equals("중급")) {
			carp = 10;
			pearl = 40;
			fish = 80;
			price = 194;
		}
		JSONObject tempJson = new JSONObject();
		for (int i=0; i <jsonArray.size(); i++) {
			tempJson = (JSONObject) jsonArray.get(i);
			String Name = tempJson.get("Name").toString();
			double one = Double.parseDouble(tempJson.get("one").toString());
			switch (Name) {
				case "오레하 태양 잉어" :
					makePrice += one * carp;
					break;
				case "자연산 진주" :
					makePrice += one * pearl;
					break;
				case "생선" :
					makePrice += one * fish;
					break;
			}
		}
		makePrice += price;
		makePrice = Math.round(makePrice * 100.0) / 100.0;
		//System.out.println(makeName+" 제작 비용 = "+makePrice);
		return makePrice;
	}

}
