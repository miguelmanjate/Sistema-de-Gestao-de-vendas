package mz.com.manjate.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.zkoss.zul.Listcell;

import com.sun.java_cup.internal.runtime.Scanner;

import mz.manjate.jpa.ClienteJPA;
import mz.manjate.jpa.PedidoJPA;
import mz.manjate.jpa.ProdutoJPA;
import mz.manjate.jpa.VendedorJPA;

public class GeraTabelas {

	public static void main(String[] args) {
//		List produtos = new ArrayList<>();
//		Produto p1 = ProdutoJPA.getBayId(2);
//		Produto p2 = ProdutoJPA.getBayId(4);
//	
//		produtos.add(p1);
//		produtos.add(p2);
//		Produto produto = ProdutoJPA.getBayId(4);
//		produtos.add(produto);
//		Pedido pedido = new Pedido();
//		
//		Cliente c1 = ClienteJPA.getBayId(44);
//		Vendedor v1 = VendedorJPA.getBayId(3);
//		
//		pedido.setCliente(c1);
//		pedido.setVendedor(v1);
//		pedido.setProdutos(produtos);
//		PedidoJPA.adicionar(pedido);
//		
//		Pedido pedido = PedidoJPA.getBayId(12);
//		
//		System.out.println(""+pedido.getNum_pedido()+"  "+pedido.getCliente().getCliente()+"  "+pedido.getVendedor().getVendedor());
//		for(Produto p : pedido.getProdutos()){
//		
//		System.out.println(""+p.getDescricao()+"  "+p.getQuantidadePedida());
//		}
//		
//		Produto agua = ProdutoJPA.getBayId(4);
//		
//		System.out.println(""+agua.getDescricao());
//		
//		for(Pedido pedido : agua.getPedido()){
//			System.out.println("numero de pedidos :"+pedido.getNum_pedido()+" Nome do cliente  : "+pedido.getCliente().getCliente()+" Quem vendeu ? "+pedido.getVendedor().getVendedor());
//		}
//		
//
//	Cliente c1 = ClienteJPA.getBayId(58);
//	    Vendedor v1 = VendedorJPA.getBayId(5);
//		Pedido p1 = new Pedido();
////		
//	p1.setCliente(c1);
//		p1.setVendedor(v1);
//	PedidoJPA.adicionar(p1);
//		for(Pedido p : c1.getPedidos()){
//			System.out.println(" "+p.getNum_pedido()+" : "+ p.getCliente().getCliente()+" "+p.getVendedor().getVendedor());
//		}
		
		List<Pedido> pedidos = PedidoJPA.listar();
		
		for(Pedido pedido : pedidos){
			System.out.println(" "+pedido.getNum_pedido()+" "
					+ " "+ pedido.getCliente().getCliente()+"  "+" "
							+ " "+pedido.getVendedor().getVendedor());
			for (Produto produto : pedido.getProdutos()) {
				
			       System.out.print(" "+produto.getDescricao());;
			}
		}
	}


}
