package mz.com.manjate.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue
	private int id_produto;
	private String descricao;
	private double precoUnitario;
	private int quantidadeProduto;
	@Transient
	private int quantidadePedida;
//	@ManyToMany(mappedBy="produtos", fetch = FetchType.EAGER)
//	private List<Pedido> pedido;
//	
//	
//	
//	public List<Pedido> getPedido() {
//		return pedido;
//	}
//	public void setPedido(List<Pedido> pedido) {
//		this.pedido = pedido;
//	}
	public int getQuantidadePedida() {
		return quantidadePedida;
	}
	public void setQuantidadePedida(int quantidadePedida) {
		this.quantidadePedida = quantidadePedida;
	}
	public int getId_produto() {
		return id_produto;
	}
	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getPrecoUnitario() {
		return precoUnitario;
	}
	public void setPrecoUnitario(double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
	public int getQuantidadeProduto() {
		return quantidadeProduto;
	}
	public void setQuantidadeProduto(int quantidadeProduto) {
		this.quantidadeProduto = quantidadeProduto;
	}
	
	

}
