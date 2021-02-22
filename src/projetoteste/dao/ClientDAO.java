package projetoteste.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import projetoteste.entity.Account;
import projetoteste.entity.Client;

@Stateless
public class ClientDAO {

	private EntityManager entityManager;
	private static ClientDAO instance;
	
	//Singleton
	public static ClientDAO getInstance() {
		if(instance == null) {
			instance = new ClientDAO();
		}
		return instance;
	}
	
	private ClientDAO() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
	
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("testePU");
		if(entityManager == null) {
			entityManager = factory.createEntityManager();
		}
		return entityManager;
	}
	
	
	public Client getById(final int id) {
		return entityManager.find(Client.class, id);
	}
	
	public Client getById(final String id) {
		return getById(Integer.parseInt(id));
	}
	
	@SuppressWarnings("unchecked")
	public List<Account> getContas(final int id) {
		return entityManager.createQuery("FROM " + Account.class.getName() + " WHERE cliente_id = :cliente_id").setParameter("cliente_id", id).getResultList();		
	}
	
	@SuppressWarnings("unchecked")
	public List<Client> getAll() {
		return entityManager.createQuery("FROM " + Client.class.getName()).getResultList();
	}
	
	//CRUD
	public void insert(Client client) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(client);
			entityManager.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	public void update(Client client) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(client);
			entityManager.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	public void remove(Client client) {
		try {
			entityManager.getTransaction().begin();
			client = entityManager.find(Client.class, client.getId());
			entityManager.remove(client);
			entityManager.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
	
	public void removeById(final int id) {
		try {
			Client client = getById(id);
			remove(client);
		
		} catch(Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}
}

