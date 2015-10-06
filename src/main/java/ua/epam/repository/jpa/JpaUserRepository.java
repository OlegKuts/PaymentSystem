package ua.epam.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ua.epam.domain.User;
import ua.epam.repository.interfaces.UserRepository;

@Repository
public class JpaUserRepository implements UserRepository {
	@PersistenceContext(name = "HiberanteMySQL")
	EntityManager em;

	@Override
	@Transactional
	public void save(User user) {
		em.persist(user);
	}

	@Override
	public User find(Long id) {
		return em.find(User.class, id);
	}

	public User findByUsername(String username) {
		TypedQuery<User> query = em.createNamedQuery("User.findByUsername",
				User.class).setParameter("username", username);
		return query.getSingleResult();
	}

	@Override
	public List<User> findAll() {
		TypedQuery<User> query = em
				.createNamedQuery("User.findAll", User.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void update(User user) {
		em.merge(user);
	}

	@Override
	public boolean isUsernameUniq(String username) {
		TypedQuery<User> query = em.createNamedQuery("User.findByUsername",
				User.class).setParameter("username", username);
		List<User> users = query.getResultList();
		return users.isEmpty();
	}

	@Override
	public List<User> findAllWithUserRole() {
		TypedQuery<User> query = em
				.createNamedQuery("User.findAllUserRole", User.class);
		return query.getResultList();
	}
}
