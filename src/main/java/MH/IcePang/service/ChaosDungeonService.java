package MH.IcePang.service;

import MH.IcePang.domain.contents.ChaosDungeon;
import MH.IcePang.domain.contents.ChaosDungeonRepository;
import MH.IcePang.domain.items.ItemRepository;
import MH.IcePang.dto.ChaosDungeonItemsDto;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChaosDungeonService {

	private final ChaosDungeonRepository chaosDungeonRepository;
	private final ItemRepository itemRepository;

	public List<ChaosDungeon> getChaosDungeonData() {
		return chaosDungeonRepository.findAll();
	}

	public List<ChaosDungeon> getChaosDungeonStatistics() {
		List<ChaosDungeon> chaosDungeonData = chaosDungeonRepository.chaosDungeonStatisticsList();
		return chaosDungeonRepository.chaosDungeonStatisticsList();
	}

	public ArrayList<ChaosDungeonItemsDto> getChaosDungeonGold(List<ChaosDungeon> chaosDungeonStatistics) {
		ArrayList<ChaosDungeonItemsDto> chaosDungeonItemsDtos = new ArrayList<>();
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
			dto.setJewelry(data.getJewelry());
			chaosDungeonItemsDtos.add(dto);
		});
		return chaosDungeonItemsDtos;
	}
}
