package mz.com.manjate.controller;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;

import mz.com.manjate.model.Produto;
import mz.manjate.jpa.ProdutoJPA;

public class ProdutoController extends GenericForwardComposer<Component> {
	
	private Textbox descricao;
	private Textbox precoUnitario;
	private Textbox quantidadeEstoque;
	
	private Listbox lbxProduto;
	
	public void doAfterCompose(Component com) throws Exception{
		super.doAfterCompose(com);	
	}
	
	public void onClick$btnGravar(Event e){
		
		Produto p = new Produto();
		
		double preco = Double.parseDouble(precoUnitario.getValue());
		int quantidade = Integer.parseInt(quantidadeEstoque.getValue());
		
		p.setDescricao(descricao.getValue());
		p.setPrecoUnitario(preco);
		p.setQuantidadeProduto(quantidade);
		
		ProdutoJPA.adicionar(p);
		listar(p);
		limpar();

	}
	
	public void listar(Produto p){
	
		lbxProduto.getItems().clear();
			
			Listitem item = new Listitem();
			
			Listcell cell1 = new Listcell(""+p.getId_produto());
			Listcell cell2 = new Listcell(p.getDescricao());
			Listcell cell3 = new Listcell(""+p.getPrecoUnitario());
			Listcell cell4 = new Listcell(""+p.getQuantidadeProduto());
			
			item.appendChild(cell1);
			item.appendChild(cell2);
			item.appendChild(cell3);
			item.appendChild(cell4);
			
			item.setValue(p);
			lbxProduto.appendChild(item);
			
		
	}
	
	public void limpar(){
	
		descricao.setRawValue(null);
		precoUnitario.setRawValue(null);
		quantidadeEstoque.setRawValue(null);
		
	}
	}
