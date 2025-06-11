package org.inneo.api_onibusgo.specs;

import jakarta.persistence.criteria.*;
import org.inneo.api_onibusgo.domains.Parameter;
import org.springframework.data.jpa.domain.Specification;

public class ParameterSpec {
	public static Specification<Parameter> doId(Long id){
        return (root, query, builder) -> {
            if(id != null) {            	
            	Predicate predicateId = builder.equal(root.get("id"), id);          	
            	return builder.or(predicateId);
            }
            return builder.and(new Predicate[0]);
        };
    };
	
	public static Specification<Parameter> doFilter(String page, String field){
        return (root, query, builder) -> {
            if(page != null && !page.isEmpty() && field != null && !field.isEmpty()) { 
            	Predicate PredicatePage  = builder.equal(builder.upper(root.get("page")),  page.toUpperCase());
                Predicate PredicateField = builder.equal(builder.upper(root.get("field")), field.toUpperCase());            	
            	return builder.or(PredicatePage, PredicateField);
            }
            return builder.and(new Predicate[0]);
        };
    }
}