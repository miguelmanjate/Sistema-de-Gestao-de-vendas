package mz.com.manjate.controller;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;

import mz.com.manjate.model.Produto;
import mz.manjate.jpa.ProdutoJPA;

public class ListarProdutoController extends GenericForwardComposer<Component> {
	
	private Listbox lbxProduto;
	private Textbox descricaoProduto;
	
	public void doAfterCompose(Component com) throws Exception{
		super.doAfterCompose(com);
		listar();
	}
	
	public void listar(){
		List<Produto> produtos = ProdutoJPA.listar();
		lbxProduto.getItems().clear();
		for(Produto p : produtos){
			
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
		
	}
	/*
	public void listarProdutoSelecionado(){
		String descricao = descricaoProduto.getValue();
		System.out.println(": "+descricao);
		
		List<Produto> produtos = ProdutoJPA.getBayDescricao(descricao);
		lbxProduto.getItems().clear();
		for(Produto p : produtos){
			
			Listitem item = new Listitem();
			
			Listcell cell1 = new Listcell(""+p.getId_produto());
			Listcell cell2 = new Listcell(p.getDescricao());
			Listcell cell3 = new Listcell(p.getUnidade());
			Listcell cell4 = new Listcell(""+p.getPreco());
			Listcell cell5 = new Listcell(p.getCondicao());
			
			item.appendChild(cell1);
			item.appendChild(cell2);
			item.appendChild(cell3);
			item.appendChild(cell4);
			item.appendChild(cell5);
			
			item.setValue(p);
			lbxProduto.appendChild(item);
			
		}

}
*/
}
