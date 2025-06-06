package org.inneo.api_onibusgo.ferramentas;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class utils {
	public static boolean isWeekend() {
		LocalDate data = LocalDate.now();
        DayOfWeek hoje= data.getDayOfWeek();
        return hoje == DayOfWeek.SATURDAY || hoje == DayOfWeek.SUNDAY;
    }
}
