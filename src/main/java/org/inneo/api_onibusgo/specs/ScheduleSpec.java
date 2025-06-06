package org.inneo.api_onibusgo.specs;

import jakarta.persistence.criteria.*;
import org.springframework.util.StringUtils;
import org.inneo.api_onibusgo.domains.Schedule;
import org.springframework.data.jpa.domain.Specification;

public class ScheduleSpec {	
	public static Specification<Schedule> doFilter(String codigo){
        return (root, query, builder) -> {
            if(StringUtils.hasText(codigo)) {
            	Long id = null;
            	
            	if(codigo.matches("^\\d+$")) {
            		id = Long.parseLong(codigo);
            	}  
            	
            	Predicate predicateFieldMatricula = builder.equal(root.get("id"), id);
            	Predicate predicateFieldNome = builder.like(builder.upper(root.get("codigo")), "%" + codigo.toUpperCase() + "%");            	
            	return builder.or(predicateFieldMatricula, predicateFieldNome);
            }
            return builder.and(new Predicate[0]);
        };
    }
}