package mz.com.manjate.controller;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Textbox;

import mz.com.manjate.model.Cliente;
import mz.com.manjate.model.ItemPedido;
import mz.com.manjate.model.Pedido;
import mz.com.manjate.model.Produto;
import mz.com.manjate.model.Vendedor;
import mz.manjate.jpa.ClienteJPA;
import mz.manjate.jpa.ItemPedidoJPA;
import mz.manjate.jpa.PedidoJPA;
import mz.manjate.jpa.ProdutoJPA;
import mz.manjate.jpa.VendedorJPA;

public class EfetuarVendaController extends GenericForwardComposer<Component> {
	private Textbox nomeCliente;
	private Textbox enderecoCliente;
	private Textbox descricaoProduto;
	private Textbox quantidadeProduto;
	private Combobox cmbxVendedor;
	private Listbox lbxProdutoSelecionado ;
	private Listbox lbxProdutosAdicionados;
	private Label valueCliente, valueClienteId;
	private Label numPedidoValue;
	private Label nomeClienteValue;
	private Label nomeVendedor;
	private Div conteudo, conteudoEfetuarVenda;
	private Menuitem cadastroVendedor;
	private Menuitem listarVendedor;
	private Menuitem cadastrarProduto;
	private Menuitem listarProduto;
	private Menuitem efetuarVenda;
    private Button btnAvancarLista;
    private Pedido pedidoGlobal;
	private Vendedor vendedorGlobal;
	private Cliente clienteGlobal;
	private Produto produtoGeral;
	private double totalFinal ;
	public void doAfterCompose(Component com) throws Exception {
		super.doAfterCompose(com);
	}

	public void onClick$btnCliente(Event e) {

		Cliente cliente = new Cliente();
		cliente.setCliente(nomeCliente.getValue());
		cliente.setEndereco(enderecoCliente.getValue());

		if (cliente.isValid()) {
			ClienteJPA.adicionar(cliente);
			clienteGlobal = cliente;
			valueCliente.setValue(cliente.getCliente());
			valueClienteId.setValue("" + cliente.getId_cliente());
			priencherVendedor();

			nomeCliente.setDisabled(true);
			enderecoCliente.setDisabled(true);
		} else {
			Clients.showNotification("O Cliente tem que ter nome e endereco");
		}

	}
private Cliente getClienteGlobal(){
	return clienteGlobal;
}
	public void priencherVendedor() {
		cmbxVendedor.getItems().clear();

		List<Vendedor> vendedores = VendedorJPA.listar();

		for (Vendedor v : vendedores) {

			Comboitem item = new Comboitem();
			item.setLabel(v.getVendedor());
			item.setValue(v);
			cmbxVendedor.appendChild(item);
		}

	}

	public void onSelect$cmbxVendedor(Event e) {
		vendedorGlobal = (Vendedor) cmbxVendedor.getSelectedItem().getValue();	
	}
	private Vendedor getVendedorGlobal(){
		return vendedorGlobal;
	}
	public Pedido onClick$btnInPedido(Event e) {
		Pedido pedido = new Pedido();
		
		Cliente cliente = getClienteGlobal();
		Vendedor vendedor = getVendedorGlobal();

		pedido.setCliente(cliente);
		pedido.setVendedor(vendedor);
		if (pedido.isValid()) {
			this.pedidoGlobal = pedido;
			PedidoJPA.adicionar(pedido);
			
			cmbxVendedor.setDisabled(true);
			numPedidoValue.setValue("" + pedido.getNum_pedido());
			nomeClienteValue.setValue(pedido.getCliente().getCliente());
			nomeVendedor.setValue(pedido.getVendedor().getVendedor());
			return pedido;
		} else {
			Clients.showNotification("O pededo tem que ter o Cliente e o Vendedor!!!");
			
			return pedido;
		}
		
	}

	private Pedido getPedidoGlobal(){
		return this.pedidoGlobal;
	}
	
	public void onClick$btnDescricaoPro(Event e) {

		String descricao = descricaoProduto.getValue();
		Produto produto = ProdutoJPA.getBayDescricao(descricao);
		produtoGeral = produto;
	}

	public Produto getProdutoGeral(){
		return produtoGeral;
	}
	public void onClick$btnQuantidade(Event e) {
		onClick$btnDescricaoPro(e);
		lbxProdutoSelecionado.getItems().clear();
	    Produto produto = getProdutoGeral();
		if (!descricaoProduto.getValue().isEmpty()&& produto!=null) {

			int numeroProduto = Integer.parseInt(quantidadeProduto.getValue());
			
			if (numeroProduto <= produto.getQuantidadeProduto()) {
				produto.setQuantidadePedida(numeroProduto);
				produtoGeral = produto;
				listarProdutosSelecionados(produto);
			} else {
				Clients.showNotification("A quantidade de produtos selecionada eh maior do que a Disponivel");
			}
		}else{
			Clients.showNotification("Introdusa o produto Existente!!!!");
		}
	
	}

	public void  listarProdutosSelecionados(Produto produto) {
    
		lbxProdutoSelecionado.getItems().clear();
	
			Listitem item = new Listitem();

			Listcell cell1 = new Listcell("" + produto.getId_produto());
			Listcell cell2 = new Listcell(produto.getDescricao());
			Listcell cell3 = new Listcell(""+produto.getPrecoUnitario());
			Listcell cell4 = new Listcell("" +produto.getQuantidadeProduto());
		
			item.appendChild(cell1);
			item.appendChild(cell2);
			item.appendChild(cell3);
			item.appendChild(cell4);
			
			item.setValue(produto);
			lbxProdutoSelecionado.appendChild(item);

		
	}

	public void onClick$btnAvancarLista(Event e){
		
		if(!descricaoProduto.getValue().isEmpty() && !quantidadeProduto.getValue().isEmpty()&& !lbxProdutoSelecionado.getItems().isEmpty()){
		conteudoEfetuarVenda.getChildren().clear();
		Executions.createComponents("formularioProdutosSelecionados.zul", conteudoEfetuarVenda, null);
		btnAvancarLista.setDisabled(true);
		}else{
			Clients.showNotification("Escreva o Produto e a sua quantidade");
		}
	}
	public void onClick$btnAddProdutos(Event e){
		onClick$btnQuantidade(e);
		Produto produto =  getProdutoGeral();
		System.out.println("Ponto 1 "+produto.getQuantidadePedida());
		listaProdutosAddicionados(produto);
		
		descricaoProduto.setRawValue(null);
		quantidadeProduto.setRawValue(null);	
	}
	
	
	private void listaProdutosAddicionados(Produto produto) {
		double total = 0;
		Listitem item = new Listitem();

		Listcell cell1 = new Listcell(produto.getDescricao());
		Listcell cell2 = new Listcell(""+produto.getPrecoUnitario());
		Listcell cell3 = new Listcell("" +produto.getQuantidadePedida());
		double valor = produto.getPrecoUnitario() * produto.getQuantidadePedida();
		
		total = valor;
		totalFinal = totalFinal + total;
		Listcell cell4 = new Listcell("" +totalFinal);
		
		item.appendChild(cell1);
		item.appendChild(cell2);
		item.appendChild(cell3);
		item.appendChild(cell4);
		
		item.setValue(produto);
		lbxProdutosAdicionados.appendChild(item);

	}
	public void onClick$btnVender(Event e) {
		Produto produto;
	
	    List<Listitem> items =	lbxProdutosAdicionados.getItems();
	    
	    Pedido pedido = PedidoJPA.getBayId(Integer.parseInt(numPedidoValue.getValue()));
	for(Listitem i : items ){
		produto = (Produto) i.getValue(); 
		produto.setQuantidadeProduto(produto.getQuantidadeProduto() - produto.getQuantidadePedida());
		
		ProdutoJPA.atualizar(produto);
		ItemPedido item = new ItemPedido();
		item.setPedido(pedido);
		item.setProduto(produto);
		item.setQuantidade(produto.getQuantidadePedida());
	    ItemPedidoJPA.adicionar(item);
	}
		formularioEfetuarVendas();
	}

	public void onClick$btnCancelarPedido(Event e) {
	formularioEfetuarVendas();
	}
	public void formularioEfetuarVendas(){
		conteudo.getChildren().clear();

		efetuarVenda.setDisabled(false);
		cadastrarProduto.setDisabled(false);
		listarVendedor.setDisabled(false);
		cadastroVendedor.setDisabled(false);
		listarProduto.setDisabled(false);

		Executions.createComponents("efetuarVenda.zul", conteudo, null);
	}

}
