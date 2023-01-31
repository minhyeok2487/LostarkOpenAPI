package MH.IcePang.domain.items;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Items {

	@Id
	private int Id;

	private String Name;

	private String Grade;

	private String Icon;

	private int BundleCount;

	private int TradeRemainCount;

	private double YDayAvgPrice;

	private int RecentPrice;

	private int CurrentMinPrice;

}
