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
public class MarketApiService {
	@Value("${Lostark-API-Key}")
	private String LostarkApiKey;

	private final ApiService apiService;

	public JSONObject GetMarketsOptions() {
		try {
			URL url = new URL("https://developer-lostark.game.onstove.com/markets/options");
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(); // 서버 연결
			httpURLConnection.setRequestMethod("GET");
			httpURLConnection.setRequestProperty("authorization", "Bearer "+LostarkApiKey);
			httpURLConnection.setRequestProperty("accept","application/json");
			httpURLConnection.setRequestProperty("content-Type","application/json");
			httpURLConnection.setDoOutput(true);
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
		} catch (IOException | ParseException e) {
			throw new RuntimeException(e);
		}
	}

	public JSONArray CallMarketCategories(int CategoryCode) {
		try {
			URL url = new URL("https://developer-lostark.game.onstove.com/markets/items/");
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(); // 서버 연결
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("authorization", "Bearer "+LostarkApiKey);
			httpURLConnection.setRequestProperty("accept","application/json");
			httpURLConnection.setRequestProperty("content-Type","application/json");
			httpURLConnection.setDoOutput(true);

			String parameter = "{\n"
				+ "  \"Sort\": \"RECENT_PRICE\",\n"
				+ "  \"CategoryCode\": "+CategoryCode+",\n"
				+ "  \"PageNo\": 1,\n"
				+ "  \"SortCondition\": \"DESC\"\n"
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

			// 모든 데이터 불러오기 추가
			int pageNo = 0;
			int pageSize = 10;
			int totalCount = Integer.parseInt(object.get("TotalCount").toString());
			JSONArray jsonArray = new JSONArray();
			while (pageNo*pageSize < totalCount) {
				pageNo++;
				JSONObject data = GetMarketCategoriesItems(CategoryCode, pageNo);
				JSONArray resultJsonArray = (JSONArray) data.get("Items");
				JSONObject tempJson = new JSONObject();
				for(int i = 0; i < resultJsonArray.size(); i++) {
					tempJson = (JSONObject) resultJsonArray.get(i);
					jsonArray.add(tempJson);
				}
			}
			// 모든 데이터 불러오기 완료

			httpURLConnection.disconnect();
			return jsonArray;
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

	public JSONObject GetMarketCategoriesItems(int CategoryCode, int PageNo) {
		try {
			URL url = new URL("https://developer-lostark.game.onstove.com/markets/items/");
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(); // 서버 연결
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("authorization", "Bearer "+LostarkApiKey);
			httpURLConnection.setRequestProperty("accept","application/json");
			httpURLConnection.setRequestProperty("content-Type","application/json");
			httpURLConnection.setDoOutput(true);

			String parameter = "{\n"
				+ "  \"Sort\": \"RECENT_PRICE\",\n"
				+ "  \"CategoryCode\": "+CategoryCode+",\n"
				+ "  \"PageNo\": "+PageNo+",\n"
				+ "  \"SortCondition\": \"DESC\"\n"
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

	public double GetMarketItem(String itemId) {
		try {
			URL url = new URL("https://developer-lostark.game.onstove.com/markets/items/"+itemId);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(); // 서버 연결
			httpURLConnection.setRequestMethod("GET");
			httpURLConnection.setRequestProperty("authorization", "Bearer "+LostarkApiKey);
			httpURLConnection.setRequestProperty("accept","application/json");
			httpURLConnection.setRequestProperty("content-Type","application/json");
			httpURLConnection.setDoOutput(true);
			int result = httpURLConnection.getResponseCode();

			InputStream inputStream;
			if(result == 200) {
				inputStream = httpURLConnection.getInputStream();
			} else {
				inputStream = httpURLConnection.getErrorStream();
			}
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

			JSONParser parser = new JSONParser();
			JSONArray jsonArray = new JSONArray();
			jsonArray = (JSONArray) parser.parse(inputStreamReader);
			JSONObject object = (JSONObject) jsonArray.get(0);
			jsonArray = (JSONArray) object.get("Stats");
			object = (JSONObject) jsonArray.get(0);
			httpURLConnection.disconnect();
			return Double.parseDouble(object.get("AvgPrice").toString());
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException | ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
