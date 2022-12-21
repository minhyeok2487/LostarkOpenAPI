package MH.IcePang.service;

import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SuppressionService {

	private final MarketApiService marketApiService;

	public JSONArray GetSonavel() {
		JSONArray data = marketApiService.CallMarketCategories(50000);
//		JSONArray array = new JSONArray();
//		for(int i =0; i<data.size(); i++) {
//			JSONObject temp = (JSONObject) data.get(i);
//			JSONObject dto = new JSONObject();
//			System.out.println(temp.get("Name").toString());
////			if(temp.get("Name").toString().equals("정제된 파괴강석")) {
////				double count = 33.4;
////				double RecentPrice = Double.parseDouble(temp.get("RecentPrice").toString())*count;
////				dto.put("Name", "정제된 파괴강석");
////				dto.put("Count", count);
////				dto.put("RecentPrice", RecentPrice);
////			}
////			if(temp.get("Name").toString().equals("정제된 수호강석")) {
////				double count = 100.0;
////				double RecentPrice = Double.parseDouble(temp.get("RecentPrice").toString())*count;
////				dto.put("Name", "정제된 수호강석");
////				dto.put("Count", count);
////				dto.put("RecentPrice", RecentPrice);
////			}
////			if(temp.get("Name").toString().equals("찬란한 명예의 돌파석")) {
////				double count = 4.0;
////				double RecentPrice = Double.parseDouble(temp.get("RecentPrice").toString())*count;
////				dto.put("Name", "찬란한 명예의 돌파석");
////				dto.put("Count", count);
////				dto.put("RecentPrice", RecentPrice);
////			}
////			array.add(dto);
//		}
		return data;
	}
}
