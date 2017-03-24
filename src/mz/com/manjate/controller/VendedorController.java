package mz.com.manjate.controller;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;

import mz.com.manjate.model.Vendedor;
import mz.manjate.jpa.VendedorJPA;

public class VendedorController extends GenericForwardComposer<Component> {
	private Textbox nomeVendedor;
	private Textbox salarioVendedor;
	private Listbox lbxVendedor;
	
	public void doAfterCompose(Component com) throws Exception{
		super.doAfterCompose(com);
	}
	
	public void onClick$btnGravar(Event e){
		
		Vendedor vendedor = new Vendedor();
		
		String nome = nomeVendedor.getValue();
		double salario = Double.parseDouble(salarioVendedor.getValue());
		
		vendedor.setVendedor(nome);
		vendedor.setSalario(salario);
		if(vendedor.isValid()){
		VendedorJPA.adicionar(vendedor);
		listar(vendedor);
		}else{
			Clients.showNotification("O Vendedor tem que ter Nome e Salario");
		}
		
		limpar();
		
	}
	public void listar(Vendedor v){
		
		lbxVendedor.getItems().clear();
		
		Listitem item = new Listitem();
		
		Listcell cell1 = new Listcell(""+v.getId_vendedor());
		Listcell cell2 = new Listcell(v.getVendedor());
		
		item.appendChild(cell1);
		item.appendChild(cell2);
		
		item.setValue(v);
		lbxVendedor.appendChild(item);
		
	}
	
	public void limpar(){
		nomeVendedor.setRawValue(null);
		salarioVendedor.setRawValue(null);
		
	}

}
