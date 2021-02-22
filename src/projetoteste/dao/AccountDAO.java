package projetoteste.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import projetoteste.entity.Account;
import projetoteste.entity.Client;

@Stateless
public class AccountDAO {

	private EntityManager entityManager;
	private static AccountDAO instance;
	
	//Singleton
	public static AccountDAO getInstance() {
		if(instance == null) {
			instance = new AccountDAO();
		}
		return instance;
	}
	
	private AccountDAO() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
	
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("testePU");
		if(entityManager == null) {
			entityManager = factory.createEntityManager();
		}
		return entityManager;
	}
	
	
	public Account getById(int id) {
		return entityManager.find(Account.class, id);
	}
	
	public Account getById(final String id) {
		return getById(Integer.parseInt(id));
	}
	
	@SuppressWarnings("unchecked")
	public List<Account> getContas(final int id) {
		return entityManager.createQuery("FROM " + Account.class.getName() + " WHERE client_id = :client_id").setParameter("client_id", id).getResultList();		
	}
	
	@SuppressWarnings("unchecked")
	public List<Account> getAll() {
		return entityManager.createQuery("FROM " + Account.class.getName()).getResultList();
	}
	
	//CRUD
	public void insert(Account account) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(account);
			entityManager.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	public void update(Account account) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(account);
			entityManager.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	public void remove(Account account) {
		try {
			entityManager.getTransaction().begin();
			account = entityManager.find(Account.class, account.getId());
			entityManager.remove(account);
			entityManager.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	public void removeById(final int id) {
		try {
			Account account = getById(id);
			remove(account);
		
		} catch(Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
}