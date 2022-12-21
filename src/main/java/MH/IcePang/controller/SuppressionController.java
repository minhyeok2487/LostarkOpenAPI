package MH.IcePang.controller;

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

	private static SuppressionService suppressionService;

	@GetMapping("/api/suppression")
	public String GetData(Model model) {
		JSONArray result = suppressionService.GetSonavel();
		model.addAttribute("result", result);
		return "suppression";
	}
}
