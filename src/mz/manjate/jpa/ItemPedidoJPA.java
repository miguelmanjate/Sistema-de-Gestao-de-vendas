package mz.manjate.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mz.com.manjate.model.ItemPedido;


public class ItemPedidoJPA {
	
	public static void adicionar(ItemPedido itemPedido){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("modelJPA");
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		manager.persist(itemPedido);
		manager.getTransaction().commit();
		
		manager.close();
		factory.close();
	}
	
	public static List<ItemPedido> listar(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("modelJPA");
		EntityManager manager = factory.createEntityManager();
		
		List<ItemPedido> itemPedidos = manager.createQuery("select i from item_pedido as i").getResultList();
		manager.close();
		return itemPedidos;
		
	}
	public static ItemPedido getBayId(int id){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("modelJPA");
		EntityManager manager = factory.createEntityManager();
		
		ItemPedido itemPedido = manager.find(ItemPedido.class, id);
		manager.close();
		return itemPedido;
		
	}
	public static void remover(ItemPedido i){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("modelJPA");
		EntityManager manager = factory.createEntityManager();
		
		ItemPedido itemPedido = manager.find(ItemPedido.class, i.getId_item_pedido());
		manager.getTransaction().begin();
		manager.remove(itemPedido);
		manager.getTransaction().commit();
		manager.close();
		
	}


}
