package MH.IcePang.domain.items;

import javax.persistence.Column;
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
	private int id;

	private String name;

	private String grade;

	private String icon;

	private int bundleCount;

	private int tradeRemainCount;

	@Column(name = "y_day_avg_price")
	private double yDayAvgPrice;

	private int recentPrice;

	private int currentMinPrice;

}
