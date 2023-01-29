package MH.IcePang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChaosDungeonStatisticsDto {

	private int id;

	private int totalCount;

	private long shilling;

	private long gold;

	private long honorShard; //명예의 파편

	private long leapstone; //돌파석

	private long destructionStone; //파괴석

	private long guardianStone; //수호석

	private long jewelry; //1레벨 보석

	private long cubeTicket; //큐브 티켓

	private long hallTicket; //회랑 티켓

	private long goldRoom; //골드방

	private long bossRoom; //보스방
}
