package MH.IcePang.dto;

import MH.IcePang.domain.items.Items;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemsDto {
	private int Id;

	private String Name;

	private String Grade;

	private String Icon;

	private int BundleCount;

	private int TradeRemainCount;

	private double YDayAvgPrice;

	private int RecentPrice;

	private int CurrentMinPrice;

	public ItemsDto(JSONObject testData) {
		this.Id = Integer.parseInt(testData.get("Id").toString());
		this.Name = testData.get("Name").toString();
		this.Grade = testData.get("Grade").toString();
		this.Icon = testData.get("Icon").toString();
		this.BundleCount = Integer.parseInt(testData.get("BundleCount").toString());
		if(testData.get("TradeRemainCount") != null) {
			this.TradeRemainCount = Integer.parseInt(testData.get("TradeRemainCount").toString());
		}
		this.YDayAvgPrice = Double.parseDouble(testData.get("YDayAvgPrice").toString());
		this.RecentPrice = Integer.parseInt(testData.get("RecentPrice").toString());
		this.CurrentMinPrice = Integer.parseInt(testData.get("CurrentMinPrice").toString());
	}


	public Items toEntity() {
		return Items.builder()
			.id(Id)
			.name(Name)
			.grade(Grade)
			.icon(Icon)
			.bundleCount(BundleCount)
			.tradeRemainCount(TradeRemainCount)
			.yDayAvgPrice(YDayAvgPrice)
			.recentPrice(RecentPrice)
			.currentMinPrice(CurrentMinPrice)
			.build();
	}
}
