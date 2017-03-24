package mz.com.manjate.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.zkoss.zul.Listcell;

import com.sun.java_cup.internal.runtime.Scanner;

import mz.manjate.jpa.ClienteJPA;
import mz.manjate.jpa.ItemPedidoJPA;
import mz.manjate.jpa.PedidoJPA;
import mz.manjate.jpa.ProdutoJPA;
import mz.manjate.jpa.VendedorJPA;

public class GeraTabelas {

	public static void main(String[] args) {

	Pedido p1 = PedidoJPA.getBayId(11);
////	
//	Produto pr1 = ProdutoJPA.getBayId(4);
////	
//	ItemPedido item = new ItemPedido();
//	item.setPedido(p1);
//	item.setProduto(pr1);
//	item.setQuantidade(7);
//	
//	ItemPedidoJPA.adicionar(item);
		//ItemPedido item = ItemPedidoJPA.getBayId(1);
		
	for(ItemPedido i : p1.getItemPedidos()){	
		
	System.out.println(" "+i.getId_item_pedido()+"  "+i.getPedido().getCliente().getCliente()+"  "+i.getProduto().getDescricao()+" "+i.getQuantidade());
	}
	}


}
