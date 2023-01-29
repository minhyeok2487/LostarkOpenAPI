package MH.IcePang.service;

import MH.IcePang.domain.contents.ChaosDungeonData;
import MH.IcePang.domain.contents.ChaosDungeonRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChaosDungeonService {

	private final ChaosDungeonRepository chaosDungeonRepository;

	public List<ChaosDungeonData> getChaosDungeonData() {
		return chaosDungeonRepository.findAll();
	}

	public List<ChaosDungeonData> getChaosDungeonStatistics() {
		List<ChaosDungeonData> chaosDungeonData = chaosDungeonRepository.chaosDungeonStatisticsList();
		return chaosDungeonRepository.chaosDungeonStatisticsList();
	}
}
