package MH.IcePang.service;

import MH.IcePang.domain.contents.ChaosDungeon;
import MH.IcePang.domain.contents.ChaosDungeonRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChaosDungeonService {

	private final ChaosDungeonRepository chaosDungeonRepository;

	public List<ChaosDungeon> getChaosDungeonData() {
		return chaosDungeonRepository.findAll();
	}

	public List<ChaosDungeon> getChaosDungeonStatistics() {
		List<ChaosDungeon> chaosDungeonData = chaosDungeonRepository.chaosDungeonStatisticsList();
		return chaosDungeonRepository.chaosDungeonStatisticsList();
	}
}
