package MH.IcePang.controller;

import MH.IcePang.service.MarketApiService;
import MH.IcePang.service.SuppressionService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SuppressionController {

	private final MarketApiService marketApiService;

	@GetMapping("/api/suppression")
	public String CallSuppression(Model model) {
		JSONArray result = marketApiService.CallMarketCategories(50000);
		//model.addAttribute("result", result);
		return "suppression";
	}
}
