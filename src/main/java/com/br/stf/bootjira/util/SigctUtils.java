package com.br.stf.bootjira.util;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class SigctUtils {

	public String currentInvoice() {
		
		LocalDate today = LocalDate.now();
		String[] splitDate = today.toString().split("-");
		
		MonthPtBr month;
		
		if(Integer.valueOf(splitDate[2]) > 21) {
			int currentMonth = Integer.valueOf(splitDate[1]) + 1;
			if(currentMonth > 12) {
				month = MonthPtBr.of(1);
			} else {
				month = MonthPtBr.of(currentMonth);
			}
		} else {
			month = MonthPtBr.of(Integer.valueOf(splitDate[1]));
		}
		
		Integer year;
		
		if(Integer.valueOf(splitDate[2]) > 21 && Integer.valueOf(splitDate[1]) == 12) {
			year = (Integer.valueOf(splitDate[0]) + 1);
		} else {
			year = (Integer.valueOf(splitDate[0]));
		}
		
		
		String a = month.getMonth().concat("/").concat(year.toString());
		return a;
	}
	
}
