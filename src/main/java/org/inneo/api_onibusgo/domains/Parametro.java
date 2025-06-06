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
import jakarta.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PARAMETROS", uniqueConstraints = {
@UniqueConstraint(name = "KEY_CODIGO", columnNames = { "codigo", "descricao", "parametro" })
})
public class Parametro {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parametros_seq")
	@SequenceGenerator(name = "parametros_seq", sequenceName = "PARAMETROS_SEQ", allocationSize = 1)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "cod_parametro")
	@NotBlank(message = "Campo obrigatório!")
	private String codigo;
	
	@Lob
    @Column(name = "desc_parametro")
	private String descricao;
	
    @Column(name = "param_parametro")
	private String parametro;	
}
