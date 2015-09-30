package ua.epam.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ua.epam.domain.CreditCard;
import ua.epam.repository.interfaces.CreditCardRepository;

@Repository
public class JpaCreditCardRepository implements CreditCardRepository {
	@PersistenceContext
	EntityManager em;

	@Override
	@Transactional
	public void save(CreditCard creditCard) {
		em.persist(creditCard);
	}

	@Override
	@Transactional
	public void update(CreditCard creditCard) {
		em.merge(creditCard);
	}

	@Override
	public CreditCard find(Long id) {
		return em.find(CreditCard.class, id);
	}

	@Override
	public List<CreditCard> findAll() {
		TypedQuery<CreditCard> query = em.createNamedQuery(
				"CreditCard.findAll", CreditCard.class);
		return query.getResultList();
	}

	@Override
	public boolean isCardNumberUniq(String cardNumber) {
		TypedQuery<CreditCard> query = em.createNamedQuery(
				"CreditCard.findByCardNumber", CreditCard.class)
				.setParameter("cardNumber", cardNumber);
		List<CreditCard> creditCards = query.getResultList();
		return creditCards.isEmpty();
	}

	@Override
	public Long getAmountForAccount(Long accountId) {
		TypedQuery<Long> query = em.createNamedQuery(
				"CreditCard.getAmountForAccount", Long.class).setParameter("accountId", accountId);
		return query.getSingleResult();
	}

	@Override
	public List<CreditCard> findAllForAccount(Long accountId) {
		TypedQuery<CreditCard> query = em.createNamedQuery(
				"CreditCard.findAllForAccount", CreditCard.class).setParameter("accountId", accountId);
		return query.getResultList();
	}

}
