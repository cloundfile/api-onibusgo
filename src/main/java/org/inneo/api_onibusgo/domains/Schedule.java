package org.inneo.api_onibusgo.domains;

import lombok.Setter;
import lombok.Getter;
import jakarta.persistence.Id;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Table;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.JoinColumn;
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
@Table(name = "SCHEDULE", uniqueConstraints = {
@UniqueConstraint(name = "KEY_ROTA", columnNames = { "rota", "partida", "retorno" })
})
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "schedule_seq")
	@SequenceGenerator(name = "schedule_seq", sequenceName = "SCHEDULE_SEQ", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "rota", nullable = false)
	private Rota rota;

    @NotBlank(message = "Campo obrigatório!")
    @Column(name = "partida", nullable = false)
    private String partida;

    @NotBlank(message = "Campo obrigatório!")
    @Column(name = "destino", nullable = false)
    private String destino;

    @NotBlank(message = "Campo obrigatório!")
    @Column(name = "retorno", nullable = false)
    private String retorno;

    @NotBlank(message = "Campo obrigatório!")
    @Column(name = "chegada", nullable = false)
    private String chegada;
    
}
