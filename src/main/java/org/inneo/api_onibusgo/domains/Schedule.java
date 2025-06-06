package org.inneo.api_onibusgo.domains;

import lombok.Setter;
import lombok.Getter;
import jakarta.persistence.Id;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SCHEDULES", uniqueConstraints = {
@UniqueConstraint(name = "KEY_CODIGO", columnNames = { "codigo", "partida", "retorno" })
})
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "schedule_seq")
	@SequenceGenerator(name = "schedule_seq", sequenceName = "SCHEDULE_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private Long id;

    @NotBlank(message = "Campo obrigatório!")
    @Column(name = "CODIGO", nullable = false)
    private String codigo;

    @NotBlank(message = "Campo obrigatório!")
    @Column(name = "PARTIDA", nullable = false)
    private String partida;

    @NotBlank(message = "Campo obrigatório!")
    @Column(name = "DESTINO", nullable = false)
    private String destino;

    @NotBlank(message = "Campo obrigatório!")
    @Column(name = "RETORNO", nullable = false)
    private String retorno;

    @NotBlank(message = "Campo obrigatório!")
    @Column(name = "CHEGADA", nullable = false)
    private String chegada;
    
}
