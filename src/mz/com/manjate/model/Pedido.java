package mz.com.manjate.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.engine.internal.Cascade;

@Entity
public class Pedido {
	@Id
	@GeneratedValue
	private int num_pedido;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "id_vendedor")
	private Vendedor vendedor;
	
	@OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
	List<ItemPedido> itemPedidos; 
	
	
	
	public int getNum_pedido() {
		return num_pedido;
	}



	public void setNum_pedido(int num_pedido) {
		this.num_pedido = num_pedido;
	}



	public Cliente getCliente() {
		return cliente;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	public Vendedor getVendedor() {
		return vendedor;
	}



	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}



	public List<ItemPedido> getItemPedidos() {
		return itemPedidos;
	}



	public void setItemPedidos(List<ItemPedido> itemPedidos) {
		this.itemPedidos = itemPedidos;
	}



	public boolean isValid() {
		return cliente!= null && vendedor!= null ;
	}
	

}
