package mz.com.manjate.controller;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Menuitem;

import mz.com.manjate.model.Vendedor;
import mz.manjate.jpa.VendedorJPA;

public class MenuCrontroller extends GenericForwardComposer<Component> {
	
	
	private Div conteudo;
	private Menuitem cadastroVendedor;
	private Menuitem listarVendedor;
	private Menuitem cadastrarProduto;
	private Menuitem listarProduto;
	private Menuitem efetuarVenda;
	private Menuitem listarVendas;
	
	public void onClick$cadastroVendedor(Event e){
	    
		conteudo.getChildren().clear();
		
		cadastroVendedor.setDisabled(true);
		listarVendedor.setDisabled(false);
		cadastrarProduto.setDisabled(false);
		listarProduto.setDisabled(false);
		efetuarVenda.setDisabled(false);
		listarVendas.setDisabled(false);
		
		Executions.createComponents("vendedor.zul", conteudo, null);
	}
	
	public void onClick$cadastrarProduto(Event e){
		
		conteudo.getChildren().clear();
		
		cadastrarProduto.setDisabled(true);
		listarVendedor.setDisabled(false);
		cadastroVendedor.setDisabled(false);
		listarProduto.setDisabled(false);
		efetuarVenda.setDisabled(false);
		listarVendas.setDisabled(false);
		
		Executions.createComponents("produto.zul", conteudo, null);
		
	}
	
	public void onClick$efetuarVenda(Event e){
		conteudo.getChildren().clear();
		
		efetuarVenda.setDisabled(true);
		cadastrarProduto.setDisabled(false);
		listarVendedor.setDisabled(false);
		cadastroVendedor.setDisabled(false);
		listarProduto.setDisabled(false);
		listarVendas.setDisabled(false);
		
		Executions.createComponents("efetuarVenda.zul", conteudo, null);
		
	}
	
	public void onClick$listarVendedor(Event e){
		
		conteudo.getChildren().clear();
		
		listarVendedor.setDisabled(true);
		cadastroVendedor.setDisabled(false);
		cadastrarProduto.setDisabled(false);
		listarProduto.setDisabled(false);
		efetuarVenda.setDisabled(false);
		listarVendas.setDisabled(false);
		
		Executions.createComponents("listarVendedor.zul", conteudo, null);
	}
	
	public void onClick$listarProduto(Event e){
		conteudo.getChildren().clear();
		
		cadastroVendedor.setDisabled(false);
		listarVendedor.setDisabled(false);
		cadastrarProduto.setDisabled(false);
		efetuarVenda.setDisabled(false);
		listarProduto.setDisabled(true);
		listarVendas.setDisabled(false);
		
		Executions.createComponents("listarProduto.zul", conteudo, null);
		
	}
	public void onClick$listarVendas(Event e){
		conteudo.getChildren().clear();
		
		listarVendas.setDisabled(true);
		listarVendedor.setDisabled(false);
		cadastroVendedor.setDisabled(false);
		cadastrarProduto.setDisabled(false);
		listarProduto.setDisabled(false);
		efetuarVenda.setDisabled(false);
		Executions.createComponents("listaVendas.zul", conteudo, null);
	}

}
