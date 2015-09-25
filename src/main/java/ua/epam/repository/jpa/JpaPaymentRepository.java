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
	@Transactional
	public void update(Payment payment) {
		em.merge(payment);
	}

	@Override
	public Payment find(Long id) {
		return em.find(Payment.class, id);
	}

	@Override
	public List<Payment> findAll() {
		TypedQuery<Payment> query = em.createNamedQuery("Payment.findAll",
				Payment.class);
		return query.getResultList();
	}

	@Override
	public Long getAmountForPayerAccount(Long accountId) {
		TypedQuery<Long> query = em.createNamedQuery(
				"Payment.getAmountForPayerAccount", Long.class).setParameter(
				"accountId", accountId);
		return query.getSingleResult();
	}

	@Override
	public List<Payment> findAllForPayerAccount(Long accountId) {
		TypedQuery<Payment> query = em.createNamedQuery(
				"Payment.findAllForPayerAccount", Payment.class).setParameter(
				"accountId", accountId);
		return query.getResultList();
	}

	@Override
	public Long getAmountForReceiverAccount(Long accountId) {
		TypedQuery<Long> query = em.createNamedQuery(
				"Payment.getAmountForReceiverAccount", Long.class)
				.setParameter("accountId", accountId);
		return query.getSingleResult();
	}

	@Override
	public List<Payment> findAllForReceiverAccount(Long accountId) {
		TypedQuery<Payment> query = em.createNamedQuery(
				"Payment.findAllForReceiverAccount", Payment.class)
				.setParameter("accountId", accountId);
		return query.getResultList();
	}
}
