package MH.IcePang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChaosDungeonItemsDto {
	private int level;

	private double destructionStone;

	private double guardianStone;

	private double jewelry;

	private double gold;

	private double total;
}
