package MH.IcePang.service;

import MH.IcePang.domain.items.ItemRepository;
import MH.IcePang.domain.items.Items;
import MH.IcePang.dto.ItemsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemsService {

	private final ItemRepository itemRepository;

	@Transactional
	public Items save(ItemsDto itemsDto){
		Items itemsEntity = itemRepository.save(itemsDto.toEntity());
		return itemsEntity;
	}
}
