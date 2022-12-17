package MH.IcePang.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TestController {

	@Value("${Lostark-API-Key}")
	private String LostarkApiKey;

	@GetMapping("/CallAuctionsOptions")
	public String CallAuctionsOptionsApi() {
		try {
			URL url = new URL("https://developer-lostark.game.onstove.com/auctions/options");
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(); // 서버 연결
			httpURLConnection.setRequestMethod("GET");
			httpURLConnection.setRequestProperty("authorization", "Bearer "+LostarkApiKey);
			httpURLConnection.setRequestProperty("accept","application/json");
			httpURLConnection.setDoOutput(true);

			int result = httpURLConnection.getResponseCode();

			InputStream inputStream;
			if(result == 200) {
				inputStream = httpURLConnection.getInputStream();
			} else {
				inputStream = httpURLConnection.getErrorStream();
			}
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			return bufferedReader.readLine();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@GetMapping("/CallAuctionsItems")
	public String CallAuctionsItemsApi() {
		try {
			URL url = new URL("https://developer-lostark.game.onstove.com/auctions/items");
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(); // 서버 연결
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("authorization", "Bearer "+LostarkApiKey);
			httpURLConnection.setRequestProperty("accept","application/json");
			httpURLConnection.setRequestProperty("content-Type","application/json");
			httpURLConnection.setDoOutput(true);

			String parameter = "{\n"
				+ "  \"ItemGradeQuality\": 70,\n"
				+ "  \"EtcOptions\": [\n"
				+ "    {\n"
				+ "      FirstOption: 3,"
				+ "      \"SecondOption\": 118,\n"
				+ "      \"MinValue\": 3,\n"
				+ "      \"MaxValue\": 3\n"
				+ "    },\n"
				+ "    {\n"
				+ "      \"FirstOption\": 3,\n"
				+ "      \"SecondOption\": 198,\n"
				+ "      \"MinValue\": 5,\n"
				+ "      \"MaxValue\": 5\n"
				+ "    }\n"
				+ "  ],\n"
				+ "  \"Sort\": \"BUY_PRICE\",\n"
				+ "  \"CategoryCode\": 200020,\n"
				+ "  \"ItemTier\": 3,\n"
				+ "  \"ItemGrade\": \"고대\",\n"
				+ "  \"PageNo\": 0,\n"
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
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			return bufferedReader.readLine();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
