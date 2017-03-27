package mz.manjate.jpa;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import mz.com.manjate.model.Produto;

public class ProdutoJPA {
	
	public static void adicionar(Produto produto){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("modelJPA");
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		manager.persist(produto);
		manager.getTransaction().commit();
		
		manager.close();
		
	}
	
	public static List<Produto> listar(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("modelJPA");
		EntityManager manager = factory.createEntityManager();
		
		List<Produto> produtos = manager.createQuery("select p from Produto as p").getResultList();
		manager.close();
		return produtos;
		
	}
	public static Produto getBayId(int id){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("modelJPA");
		EntityManager manager = factory.createEntityManager();
		
		Produto produto = manager.find(Produto.class, id);
		manager.close();
		return produto;
		
	}
	public static Produto getBayDescricao(String produto)throws SQLException{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("modelJPA");
		EntityManager manager = factory.createEntityManager();
		
		Query query = manager.createQuery("SELECT p FROM Produto as p "+"where p.descricao = :paramDescricao ");
		query.setParameter("paramDescricao", produto);
		
		Produto produtoResultado =(Produto)  query.getSingleResult();
		return produtoResultado;
		
	}
	
	public static void atualizar(Produto produto){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("modelJPA");
		EntityManager manager = factory.createEntityManager();
				
		manager.getTransaction().begin();
		manager.merge(produto);
		manager.getTransaction().commit();
		
		
	}
	

}
