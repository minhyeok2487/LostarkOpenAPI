package MH.IcePang.service;

import MH.IcePang.dto.SuppressionDto;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SuppressionService {

	private final MarketApiService marketApiService;

	public SuppressionDto GetSonavel(JSONArray data) {
		SuppressionDto dto = new SuppressionDto();
		for(int i =0; i<data.size(); i++) {
			JSONObject temp = (JSONObject) data.get(i);
			if(temp.get("Name").toString().equals("정제된 파괴강석")) {
				double count = 3.34;
				double RecentPrice = Double.parseDouble(temp.get("RecentPrice").toString());
				dto.setNameData1("정제된 파괴강석");
				dto.setCountData1(count*10);
				dto.setPriceData1(RecentPrice);
				dto.setTotalData1(count*RecentPrice);
			}
			if(temp.get("Name").toString().equals("정제된 수호강석")) {
				double count = 10.0;
				double RecentPrice = Double.parseDouble(temp.get("RecentPrice").toString());
				dto.setNameData2("정제된 수호강석");
				dto.setCountData2(count*10);
				dto.setPriceData2(RecentPrice);
				dto.setTotalData2(count*RecentPrice);
			}
			if(temp.get("Name").toString().equals("찬란한 명예의 돌파석")) {
				double count = 4.0;
				double RecentPrice = Double.parseDouble(temp.get("RecentPrice").toString());
				dto.setNameData3("찬란한 명예의 돌파석");
				dto.setCountData3(count);
				dto.setPriceData3(RecentPrice);
				dto.setTotalData3(count*RecentPrice);
			}
		}
		dto.setTotalPrice(dto.getTotalData1()+dto.getTotalData2()+dto.getTotalData3());
		return dto;
	}

	public SuppressionDto Gethanumatan(JSONArray data) {
		SuppressionDto dto = new SuppressionDto();
		for(int i =0; i<data.size(); i++) {
			JSONObject temp = (JSONObject) data.get(i);
			if(temp.get("Name").toString().equals("파괴강석")) {
				double count = 5.15;
				double RecentPrice = Double.parseDouble(temp.get("RecentPrice").toString());
				dto.setNameData1("파괴강석");
				dto.setCountData1(count*10);
				dto.setPriceData1(RecentPrice);
				dto.setTotalData1(count*RecentPrice);
			}
			if(temp.get("Name").toString().equals("수호강석")) {
				double count = 15.25;
				double RecentPrice = Double.parseDouble(temp.get("RecentPrice").toString());
				dto.setNameData2("수호강석");
				dto.setCountData2(count*10);
				dto.setPriceData2(RecentPrice);
				dto.setTotalData2(count*RecentPrice);
			}
			if(temp.get("Name").toString().equals("경이로운 명예의 돌파석")) {
				double count = 7.0;
				double RecentPrice = Double.parseDouble(temp.get("RecentPrice").toString());
				dto.setNameData3("경이로운 명예의 돌파석");
				dto.setCountData3(count);
				dto.setPriceData3(RecentPrice);
				dto.setTotalData3(count*RecentPrice);
			}
		}
		dto.setTotalPrice(dto.getTotalData1()+dto.getTotalData2()+dto.getTotalData3());
		return dto;
	}
}
