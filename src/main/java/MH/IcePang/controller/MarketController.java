package MH.IcePang.controller;

import MH.IcePang.service.MarketApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MarketController {

	private final MarketApiService marketApiService;

	@GetMapping("/api/markets")
	public String CallMarketsOptions(Model model) {
		JSONObject result = marketApiService.GetMarketsOptions();
		model.addAttribute("result",result);
		return "markets/options";
	}

	@PostMapping("/api/markets/categories")
	public String CallMarketCategories(int categories, Model model) {
		JSONArray result = marketApiService.CallMarketCategories(categories);

		model.addAttribute("result",result);
		return "markets/items";
	}
}
