package ua.epam.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Payments extends BaseEntity {
	private Double amount;
	@Temporal(TemporalType.TIMESTAMP)
	private Date paymentDate;
	
	//private Account payerAccount;
}
