package MH.IcePang.domain.contents;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChaosDungeon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략을 데이터베이스를 따라간다.
	private int id;

	@Column(nullable = false)
	@ColumnDefault("0")
	private int level;

	@Column(nullable = false)
	@ColumnDefault("0")
	private int counts;

	@Column(nullable = false)
	@ColumnDefault("0")
	private double shilling;

	@ColumnDefault("0")
	private double gold;

	@Column(nullable = false)
	@ColumnDefault("0")
	private double honorShard; //명예의 파편

	@Column(nullable = false)
	@ColumnDefault("0")
	private double leapstone; //돌파석

	@Column(nullable = false)
	@ColumnDefault("0")
	private double destructionStone; //파괴석

	@Column(nullable = false)
	@ColumnDefault("0")
	private double guardianStone; //수호석

	@Column(nullable = false)
	@ColumnDefault("0")
	private double jewelry; //1레벨 보석

	@ColumnDefault("0")
	private double cubeTicket; //큐브 티켓

	@ColumnDefault("0")
	private double hallTicket; //회랑 티켓

	@ColumnDefault("0")
	private double goldRoom; //골드방

	@ColumnDefault("0")
	private double bossRoom; //보스방
}
