package org.inneo.api_onibusgo.specs;

import jakarta.persistence.criteria.*;
import org.inneo.api_onibusgo.domains.Rota;
import org.inneo.api_onibusgo.domains.Schedule;
import org.springframework.data.jpa.domain.Specification;

public class ScheduleSpec {		
	public static Specification<Schedule> daRota(Long idRota) {
		return (root, query, builder) -> {
            if(idRota != null) {
                query.distinct(true);
                Join<Schedule, Rota> rota = root.join("rota");
                return builder.equal(rota.get("id"), idRota);
            }
            return builder.and(new Predicate[0]);
        };
	}
}