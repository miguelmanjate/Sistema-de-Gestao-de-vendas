package mz.manjate.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mz.com.manjate.model.Cliente;

public class ClienteJPA {
	
	public static void adicionar(Cliente cliente){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("modelJPA");
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		manager.persist(cliente);
		manager.getTransaction().commit();
		
		manager.close();
		factory.close();
	}
	
	public static List<Cliente> listar(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("modelJPA");
		EntityManager manager = factory.createEntityManager();
		
		List<Cliente> clientes = manager.createQuery("select c from Cliente as c").getResultList();
		manager.close();
		return clientes;
		
	}
	public static Cliente getBayId(int id){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("modelJPA");
		EntityManager manager = factory.createEntityManager();
		
		Cliente cliente = manager.find(Cliente.class, id);
		manager.close();
		return cliente;
		
	}
	public static void remover(Cliente c){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("modelJPA");
		EntityManager manager = factory.createEntityManager();
		
		Cliente cliente = manager.find(Cliente.class, c.getId_cliente());
		manager.getTransaction().begin();
		manager.remove(cliente);
		manager.getTransaction().commit();
		manager.close();
		
	}

}
