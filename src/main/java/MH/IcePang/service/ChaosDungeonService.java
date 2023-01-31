package MH.IcePang.service;

import MH.IcePang.domain.contents.ChaosDungeon;
import MH.IcePang.domain.contents.ChaosDungeonRepository;
import MH.IcePang.domain.items.ItemRepository;
import MH.IcePang.dto.ChaosDungeonItemsDto;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChaosDungeonService {

	private final ChaosDungeonRepository chaosDungeonRepository;
	private final ItemRepository itemRepository;
	private final ApiService apiService;

	public List<ChaosDungeon> getChaosDungeonData() {
		return chaosDungeonRepository.findAll();
	}

	public List<ChaosDungeon> getChaosDungeonStatistics() {
		List<ChaosDungeon> chaosDungeonData = chaosDungeonRepository.chaosDungeonStatisticsList();
		return chaosDungeonRepository.chaosDungeonStatisticsList();
	}

	public ArrayList<ChaosDungeonItemsDto> getChaosDungeonGold(List<ChaosDungeon> chaosDungeonStatistics) {
		NumberFormat formatter = new DecimalFormat("0.##");
		ArrayList<ChaosDungeonItemsDto> chaosDungeonItemsDtos = new ArrayList<>();
		JSONArray jewelryArray = (JSONArray) apiService.AuctionItems(210000, "1레벨").get("Items");
		JSONObject jewelryObject = (JSONObject) jewelryArray.get(0);
		JSONObject jewelry = (JSONObject) jewelryObject.get("AuctionInfo");
		chaosDungeonStatistics.forEach((data) -> {
			ChaosDungeonItemsDto dto = new ChaosDungeonItemsDto();
			dto.setLevel(data.getLevel());
			if(data.getLevel() < 1490) {
				dto.setDestructionStone(data.getDestructionStone() * itemRepository.findByName("파괴석 결정").getYDayAvgPrice()/10);
				dto.setGuardianStone(data.getGuardianStone() * itemRepository.findByName("수호석 결정").getYDayAvgPrice()/10);
			} else if (data.getLevel() >= 1580) {
				dto.setDestructionStone(data.getDestructionStone() * itemRepository.findByName("정제된 파괴강석").getYDayAvgPrice()/10);
				dto.setGuardianStone(data.getGuardianStone() * itemRepository.findByName("정제된 수호강석").getYDayAvgPrice()/10);
			} else {
				dto.setDestructionStone(data.getDestructionStone() * itemRepository.findByName("파괴강석").getYDayAvgPrice()/10);
				dto.setGuardianStone(data.getGuardianStone() * itemRepository.findByName("수호강석").getYDayAvgPrice()/10);
			}
			dto.setGold(data.getGold());
			dto.setJewelry(data.getJewelry() * Double.parseDouble(jewelry.get("BuyPrice").toString()));
			//소수점 제거 및 합산
			dto.setDestructionStone(Double.parseDouble(formatter.format(dto.getDestructionStone())));
			dto.setGuardianStone(Double.parseDouble(formatter.format(dto.getGuardianStone())));
			dto.setJewelry(Double.parseDouble(formatter.format(dto.getJewelry())));
			dto.setTotal(Double.parseDouble(formatter.format(dto.getDestructionStone()+dto.getGuardianStone()+dto.getJewelry()+dto.getGold())));
			chaosDungeonItemsDtos.add(dto);
		});
		return chaosDungeonItemsDtos;
	}
}
