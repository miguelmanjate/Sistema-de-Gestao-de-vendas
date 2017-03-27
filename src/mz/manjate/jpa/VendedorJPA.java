package mz.manjate.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mz.com.manjate.model.Produto;
import mz.com.manjate.model.Vendedor;



public class VendedorJPA {
	
	public static void adicionar(Vendedor vendedor){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("modelJPA");
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		manager.persist(vendedor);
		manager.getTransaction().commit();
		
		manager.close();
		
	}
	
	public static List<Vendedor> listar(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("modelJPA");
		EntityManager manager = factory.createEntityManager();
		
		List<Vendedor> vendedors = manager.createQuery("select v from Vendedor v").getResultList();
		manager.close();
		return vendedors;
		
	}
	public static Vendedor getBayId(int id){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("modelJPA");
		EntityManager manager = factory.createEntityManager();
		
		Vendedor vendedor = manager.find(Vendedor.class, id);
		manager.close();
		return vendedor;
		
	}
public static void atualizar(Vendedor vendedor){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("modelJPA");
		EntityManager manager = factory.createEntityManager();
				
		manager.getTransaction().begin();
		manager.merge(vendedor);
		manager.getTransaction().commit();
		
		
	}
	


}
