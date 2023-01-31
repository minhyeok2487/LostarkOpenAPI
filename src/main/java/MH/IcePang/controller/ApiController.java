package MH.IcePang.controller;

import MH.IcePang.domain.items.Items;
import MH.IcePang.dto.DataDto;
import MH.IcePang.dto.ItemsDto;
import MH.IcePang.service.ApiService;
import MH.IcePang.service.CompareService;
import MH.IcePang.service.ItemsService;
import MH.IcePang.service.MarketApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
@Slf4j
public class ApiController {

	private final ApiService apiService;
	private final CompareService compareService;
	private final MarketApiService marketApiService;
	private final ItemsService itemsService;

	@PostMapping("/api/find")
	public String CallAuctionsItemsApi(DataDto dataDto, Model model) {
		log.info(dataDto.toString());
		JSONObject result = apiService.각인찾기(dataDto);
		log.info(result.toString());
		model.addAttribute("result", result.get("Items"));
		return "find";
	}

	@ResponseBody
	@GetMapping("/api/saveCode50000")
	public JSONArray SaveCode50000() {
		JSONArray result = marketApiService.CallMarketCategories(50000);
		result.forEach((data) -> {
			ItemsDto itemsDto = new ItemsDto((JSONObject) data);
			Items save = itemsService.save(itemsDto);
		});
		return result;
	}
}
