package org.inneo.api_onibusgo.domains;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

import java.util.ArrayList;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.UniqueConstraint;

import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ROTA", uniqueConstraints = {
@UniqueConstraint(name = "KEY_CODIGO", columnNames = { "terminal_partida", "terminal_destino" })
})
public class Rota {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rotas_seq")
	@SequenceGenerator(name = "rotas_seq", sequenceName = "ROTAS_SEQ", allocationSize = 1)
	@Column(name = "id")
	private Long id;

    @NotBlank(message = "Campo obrigatório!")
    @Column(name = "terminal_partida", nullable = false)
    private String terminalPartida;
    
    @NotBlank(message = "Campo obrigatório!")
    @Column(name = "terminal_destino", nullable = false)
    private String terminalDestino;
    
    @JsonIgnore
    @OneToMany(mappedBy = "rota", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Schedule> schedules = new ArrayList<>();
}
