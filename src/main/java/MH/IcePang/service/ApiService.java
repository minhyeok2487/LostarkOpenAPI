package MH.IcePang.service;

import MH.IcePang.dto.DataDto;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ApiService {

	@Value("${Lostark-API-Key}")
	private String LostarkApiKey;

	public JSONObject 각인찾기(DataDto dataDto) {
		//int[][] engraving = {{5,3},{4,3},{3,3}};
		int ItemGradeQuality = 80;
		int CategoryCode1 = 200020;
		String etc1 = "[{FirstOption: 2, SecondOption: 16},{FirstOption: 3, SecondOption: 141, MinValue: 3, MaxValue: 6},{FirstOption: 3, SecondOption: 293, MinValue: 3, MaxValue: 3}]";
		JSONObject result1 = CallAuctionsItemsApi(ItemGradeQuality, etc1, CategoryCode1);
		return result1;
	}



	public JSONObject CallAuctionsItemsApi(int ItemGradeQuality, String Etc, int CategoryCode) {
		try {
			URL url = new URL("https://developer-lostark.game.onstove.com/auctions/items");
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(); // 서버 연결
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("authorization", "Bearer "+LostarkApiKey);
			httpURLConnection.setRequestProperty("accept","application/json");
			httpURLConnection.setRequestProperty("content-Type","application/json");
			httpURLConnection.setDoOutput(true);

			String parameter = "{"
				+ "  ItemGradeQuality: " + ItemGradeQuality
				+ " ,EtcOptions: " + Etc
				+ " ,Sort: \"BUY_PRICE\""
				+ " ,CategoryCode: " + CategoryCode
				+ " ,\"ItemTier\": 3,\n"
				+ "  \"ItemGrade\": \"고대\",\n"
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

			return object;
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException | ParseException e) {
			throw new RuntimeException(e);
		}
	}


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
}
