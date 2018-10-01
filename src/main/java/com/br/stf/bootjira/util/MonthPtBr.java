package com.br.stf.bootjira.util;

public enum MonthPtBr {
	
	JANUARY("Janeiro",1),
	FEBRUARY("Fevereiro",2),
	MARCH("Marco",3),
	APRIL("Abril",4),
	MAY("Maio",5),
	JUNE("Junho",6),
	JULY("Julho",7),
	AUGUST("Agosto",8),
	SEPTEMBER("Setembro",9),
	OCTOBER("Outubro",10),
	NOVEMBER("Novembro",11),
	DECEMBER("Dezembro",12);

	private String month;
	private Integer idMonth;
	
	private MonthPtBr(String month, Integer idMonth) {
		this.month = month;
		this.idMonth = idMonth;
	}
	
	public static MonthPtBr of(Integer id) {
		MonthPtBr month = null;
		for(MonthPtBr monthValue : MonthPtBr.values()) {
			if(monthValue.getIdMonth().equals(id)) {
				month = monthValue;
			}
		}
		return month;
	}

	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * @return the idMonth
	 */
	public Integer getIdMonth() {
		return idMonth;
	}

	/**
	 * @param idMonth the idMonth to set
	 */
	public void setIdMonth(Integer idMonth) {
		this.idMonth = idMonth;
	}
	
	
	
}
