package ua.epam.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ua.epam.domain.Payment;
import ua.epam.repository.interfaces.PaymentRepository;

@Repository
public class JpaPaymentRepository implements PaymentRepository {

	@PersistenceContext
	EntityManager em;

	@Override
	@Transactional
	public void save(Payment payment) {
		em.persist(payment);
	}

	@Override
	public List<Payment> findAllForPayerAccount(Long accountId) {
		TypedQuery<Payment> query = em.createNamedQuery(
				"Payment.findAllForPayerAccount", Payment.class).setParameter(
				"accountId", accountId);
		return query.getResultList();
	}

	@Override
	public List<Payment> findAllForReceiverAccount(Long accountId) {
		TypedQuery<Payment> query = em.createNamedQuery(
				"Payment.findAllForReceiverAccount", Payment.class)
				.setParameter("accountId", accountId);
		return query.getResultList();
	}
}
