package MH.IcePang.controller;

import MH.IcePang.dto.SuppressionDto;
import MH.IcePang.service.MarketApiService;
import MH.IcePang.service.SuppressionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SuppressionController {

	private final MarketApiService marketApiService;
	private final SuppressionService suppressionService;

	@GetMapping("/api/suppression")
	public String CallSuppression(Model model) {
		JSONArray data = marketApiService.CallMarketCategories(50000);
		SuppressionDto sonavel = suppressionService.GetSonavel(data);
		SuppressionDto hanumatan = suppressionService.Gethanumatan(data);
		double distance = sonavel.getTotalData1()+sonavel.getTotalData3()-hanumatan.getTotalData1()-hanumatan.getTotalData3();
		model.addAttribute("sonavel", sonavel);
		model.addAttribute("hanumatan", hanumatan);
		model.addAttribute("distance", distance);
		return "suppression";
	}
}
