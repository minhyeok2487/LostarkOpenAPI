package MH.IcePang.controller;

import MH.IcePang.dto.DataDto;
import MH.IcePang.service.ApiService;
import MH.IcePang.service.CompareService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
@Slf4j
public class ApiController {

	private final ApiService apiService;
	private final CompareService compareService;

	@PostMapping("/api/find")
	public String CallAuctionsItemsApi(DataDto dataDto, Model model) {
		log.info(dataDto.toString());
		JSONObject result = apiService.각인찾기(dataDto);
		log.info(result.toString());
		model.addAttribute("result", result.get("Items"));
		return "find";
	}

}
