package MH.IcePang.controller;

import MH.IcePang.domain.contents.ChaosDungeon;
import MH.IcePang.dto.ChaosDungeonItemsDto;
import MH.IcePang.service.ChaosDungeonService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ContentsController {

	private final ChaosDungeonService chaosDungeonService;

	@GetMapping("/contents/ChaosDungeon/data")
	public String chaosDungeonData(Model model) {
		List<ChaosDungeon> chaosDungeonData = chaosDungeonService.getChaosDungeonData();
		log.info(chaosDungeonData.get(0).toString());
		model.addAttribute("chaosDungeonData", chaosDungeonData);
		return "contents/ChaosDungeon/data";
	}

	@GetMapping("/contents/ChaosDungeon/statistics")
	public String chaosDungeonStatistics(Model model) {
		List<ChaosDungeon> chaosDungeonStatistics = chaosDungeonService.getChaosDungeonStatistics();
		ArrayList<ChaosDungeonItemsDto> chaosDungeonGold = chaosDungeonService.getChaosDungeonGold(chaosDungeonStatistics);
		model.addAttribute("chaosDungeonStatistics", chaosDungeonStatistics);
		model.addAttribute("chaosDungeonGold", chaosDungeonGold);
		return "contents/ChaosDungeon/statistics";
	}
}
