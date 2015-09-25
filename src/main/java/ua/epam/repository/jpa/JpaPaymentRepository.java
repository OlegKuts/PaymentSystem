package ua.epam.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
		
		return null;
	}

	@Override
	public Long getAmountForAccount(Long accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Payment> findAllForAccount(Long accountId) {
		// TODO Auto-generated method stub
		return null;
	}

		
}
