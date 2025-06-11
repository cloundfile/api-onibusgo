package org.inneo.api_onibusgo.domains;

import lombok.Setter;
import lombok.Getter;
import jakarta.persistence.Id;

import jakarta.persistence.Lob;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.UniqueConstraint;

import jakarta.persistence.SequenceGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PARAMETER", uniqueConstraints = {
@UniqueConstraint(name = "KEY_PAGE", columnNames = { "page", "description", "parameter" })
})
public class Parameter {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parameter_seq")
	@SequenceGenerator(name = "parameter_seq", sequenceName = "PARAMETER_SEQ", allocationSize = 1)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "page")
	private String page;
	
	@Column(name = "field")
	private String field;
	
	@Column(name = "icon")
	private String icon;
	
	@Lob
    @Column(name = "description")
	private String description;
	
    @Column(name = "parameter")
	private String parameter;	
}
