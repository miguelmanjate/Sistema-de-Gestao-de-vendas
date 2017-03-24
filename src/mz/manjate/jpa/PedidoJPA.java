package mz.manjate.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mz.com.manjate.model.Cliente;
import mz.com.manjate.model.Pedido;
import mz.com.manjate.model.Produto;

public class PedidoJPA {
	
	public static void adicionar(Pedido pedido){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("modelJPA");
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		manager.persist(pedido);
		manager.getTransaction().commit();
		
		manager.close();
		
	}
	
	public static List<Pedido> listar(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("modelJPA");
		EntityManager manager = factory.createEntityManager();
		
		List<Pedido> pedidos = manager.createQuery("select p from Pedido as p").getResultList();
		manager.close();
		return pedidos;
		
	}
	public static Pedido getBayId(int id){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("modelJPA");
		EntityManager manager = factory.createEntityManager();
		
		Pedido pedido = manager.find(Pedido.class, id);
		manager.close();
		return pedido;
		
	}
	public static void remover(Pedido p){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("modelJPA");
		EntityManager manager = factory.createEntityManager();
		
		Pedido pedido = manager.find(Pedido.class, p.getNum_pedido());
		manager.getTransaction().begin();
		manager.remove(pedido);
		manager.getTransaction().commit();
		manager.close();
		
	}
public static void atualizar(Pedido p){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("modelJPA");
		EntityManager manager = factory.createEntityManager();
				
		manager.getTransaction().begin();
		manager.merge(p);
		manager.getTransaction().commit();
		
		
	}

}
