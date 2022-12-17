package MH.IcePang.controller;

import MH.IcePang.service.CompareService;
import MH.IcePang.service.MarketApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CompareController {

	private final CompareService compareService;
	private final MarketApiService marketApiService;

	@GetMapping("/api/fishing")
	public String CompareFishing(Model model) {
		JSONObject result = compareService.CompareFishing();
		double makeHighestGrade = compareService.GetMakePrice(result, "최상급");
		double makeAdvancedGrade = compareService.GetMakePrice(result, "상급");
		double makeIntermediateGrade = compareService.GetMakePrice(result, "중급");
		double highestGrade = marketApiService.GetMarketItem("6861011")*15;
		double advancedGrade = marketApiService.GetMarketItem("6861009")*20;
		double intermediateGrade = marketApiService.GetMarketItem("6861008")*30;
		model.addAttribute("result",result.get("Items"));
		model.addAttribute("makeHighestGrade", makeHighestGrade);
		model.addAttribute("makeAdvancedGrade", makeAdvancedGrade);
		model.addAttribute("makeIntermediateGrade", makeIntermediateGrade);
		model.addAttribute("highestGrade", highestGrade);
		model.addAttribute("advancedGrade", advancedGrade);
		model.addAttribute("intermediateGrade", intermediateGrade);
		model.addAttribute("highestValue", Math.round((highestGrade - makeHighestGrade) * 100.0) / 100.0);
		model.addAttribute("advancedValue", Math.round((advancedGrade-makeAdvancedGrade) * 100.0) / 100.0);
		model.addAttribute("intermediateValue", Math.round(intermediateGrade-makeIntermediateGrade) * 100.0 / 100.0);
		return "fishing";
	}
}
