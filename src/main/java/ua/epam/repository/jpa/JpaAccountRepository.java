package ua.epam.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ua.epam.domain.Account;
import ua.epam.repository.interfaces.AccountRepository;

@Repository
public class JpaAccountRepository implements AccountRepository {

	@PersistenceContext(name = "HibernateMySQL")
	EntityManager em;

	@Override
	@Transactional
	public void save(Account account) {
		em.persist(account);
	}

	@Override
	@Transactional
	public void update(Account account) {
		em.merge(account);
	}

	@Override
	public Account find(Long id) {
		return em.find(Account.class, id);
	}

	@Override
	public List<Account> findAll() {
		TypedQuery<Account> query = em.createNamedQuery("Account.findAll",
				Account.class);
		return query.getResultList();
	}

	@Override
	public List<Account> findAllIfActive(boolean active) {
		TypedQuery<Account> query = em.createNamedQuery(
				"Account.findAllActive", Account.class).setParameter("active",
				active);
		return query.getResultList();
	}

	@Override
	public Account findByUsername(String username) {
		TypedQuery<Account> query = em.createNamedQuery("Account.findByUsername", Account.class).setParameter("username", username);
		return query.getSingleResult();
	}

}
