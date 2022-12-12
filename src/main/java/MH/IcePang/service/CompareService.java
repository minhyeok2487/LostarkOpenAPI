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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CompareService {
	@Value("${Lostark-API-Key}")
	private String LostarkApiKey;

	public JSONObject 낚시비교() {
		JSONObject result = CallMarketsItems(90600);
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
		double highestGrade = GetMakePrice(resultJsonArray, "최상급");
		double advancedGrade = GetMakePrice(resultJsonArray, "상급");
		double intermediateGrade = GetMakePrice(resultJsonArray, "중급");

		return result;
	}

	//낚시 오레하 제작 비용 계산 메서드
	public double GetMakePrice(JSONArray jsonArray, String makeName) {
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
		System.out.println(makeName+" 제작 비용 = "+makePrice);
		return makePrice;
	}

	//데이터 불러오는 메서드
	public JSONObject CallMarketsItems(int ItemCode) {
		try {
			URL url = new URL("https://developer-lostark.game.onstove.com/markets/items/");
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(); // 서버 연결
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("authorization", "Bearer "+LostarkApiKey);
			httpURLConnection.setRequestProperty("accept","application/json");
			httpURLConnection.setRequestProperty("content-Type","application/json");
			httpURLConnection.setDoOutput(true);

			String parameter = "{\n"
				+ "  \"Sort\": \"GRADE\",\n"
				+ "  \"CategoryCode\": "+ItemCode+",\n"
				+ "  \"PageNo\": 0,\n"
				+ "  \"SortCondition\": \"ASC\"\n"
				+ "}";
			byte[] out = parameter.getBytes(StandardCharsets.UTF_8);

			OutputStream stream = httpURLConnection.getOutputStream();
			stream.write(out);

			int result = httpURLConnection.getResponseCode();

			InputStream inputStream;
			if(result == 200) {
				inputStream = httpURLConnection.getInputStream();
			} else {
				inputStream = httpURLConnection.getErrorStream();
			}
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

			JSONParser parser = new JSONParser();
			JSONObject object = (JSONObject) parser.parse(inputStreamReader);
			httpURLConnection.disconnect();
			return object;
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (ProtocolException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
