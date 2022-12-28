package MH.IcePang.dto;

import lombok.Data;

@Data
public class SuppressionDto {
	private String nameData1;
	private Double countData1;
	private Double priceData1;
	private Double totalData1;

	private String nameData2;
	private Double countData2;
	private Double priceData2;
	private Double totalData2;

	private String nameData3;
	private Double countData3;
	private Double priceData3;
	private Double totalData3;

	private Double totalPrice;


	public Double getPriceData1() {
		return Math.round(priceData1)*100.0/100.0;
	}

	public Double getTotalData1() {
		return Math.round(totalData1)*100.0/100.0;
	}

	public Double getPriceData2() {
		return Math.round(priceData2)*100.0/100.0;
	}

	public Double getTotalData2() {
		return Math.round(totalData2)*100.0/100.0;
	}


	public Double getPriceData3() {
		return Math.round(priceData3)*100.0/100.0;
	}

	public Double getTotalData3() {
		return Math.round(totalData3)*100.0/100.0;
	}

	public Double getTotalPrice() {
		return Math.round(totalPrice)*100.0/100.0;
	}
}
