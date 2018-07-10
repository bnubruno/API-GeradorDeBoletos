package br.com.desafio.boleto.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.desafio.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankSlip implements AbstractEntity<String> {

	@Id
	private String id;

	@Column(nullable = false)
	@NotNull
	private LocalDate dueDate;

	@Column(nullable = false, scale = 16, precision = 0)
	@NotNull
	private BigDecimal totalInCents;

	@Column(nullable = false)
	@NotNull
	private Customer customer;

	@Column(nullable = false, length = 1)
	@NotNull
	private BankSlipStatus status;

	public BigDecimal getFine(LocalDate actualDate) {
		return Fine.of(this.totalInCents, this.dueDate, actualDate);
	}

}
