package mz.com.manjate.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.zkoss.zhtml.Text;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
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
	private Button btnGravar;
	private Datebox dataNascimento;
	private Vendedor vendedorGlobal;
	
	public void doAfterCompose(Component com) throws Exception{
		super.doAfterCompose(com);
		listar();
	}
	
	public void onClick$btnGravar(Event e){
		if(btnGravar.getLabel().equalsIgnoreCase("Gravar")){
		Vendedor vendedor = new Vendedor();
		
		String nome = nomeVendedor.getValue();
		double salario = Double.parseDouble(salarioVendedor.getValue());
		
		vendedor.setVendedor(nome);
		vendedor.setSalario(salario);
		vendedor.setDataNascimento(dataNascimento.getValue());
		if(vendedor.isValid()){
		VendedorJPA.adicionar(vendedor);
	
		Clients.showNotification("Vendedor gravado com sucesso", "info", lbxVendedor, "center", 30000);
		listar();
		}else{
			Clients.showNotification("O Vendedor tem que ter Nome e Salario");
		}
		
		limpar();
		} else if(btnGravar.getLabel().equalsIgnoreCase("Editar")){
			Vendedor v = vendedorGlobal;
			
			if(v.isValid()){
			
			v.setVendedor(nomeVendedor.getValue());
			v.setSalario(Double.parseDouble(salarioVendedor.getValue()));
			v.setDataNascimento(dataNascimento.getValue());
			
			VendedorJPA.atualizar(v);
			limpar();
			listar();
			Clients.showNotification("Vendedor atualizado com sucesso", "info", lbxVendedor, "center", 30000);
			btnGravar.setLabel("Gravar");
			}else{
				Clients.showNotification("Nao foi possivel atualisar o vendedor", "error", lbxVendedor, "center", 30000);
				
			}
		}
		
	}
	public void listar(){
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		List<Vendedor> vedidores = VendedorJPA.listar();
		
		lbxVendedor.getItems().clear();
		for(Vendedor v : vedidores){
		
		Listitem item = new Listitem();
		
		Listcell cell1 = new Listcell(""+v.getId_vendedor());
		Listcell cell2 = new Listcell(v.getVendedor());
		Listcell cell3 = new Listcell();
		
		if(v.getDataNascimento()!= null){
			Text t = new Text(df.format(v.getDataNascimento()));
			cell3.appendChild(t);
		}
		
		item.appendChild(cell1);
		item.appendChild(cell2);
		item.appendChild(cell3);
		
		item.setValue(v);
		lbxVendedor.appendChild(item);
		}
		
	}
	
	public Vendedor onSelect$lbxVendedor(Event e){
		Vendedor v = (Vendedor) lbxVendedor.getSelectedItem().getValue();
		
		nomeVendedor.setValue(v.getVendedor());
		salarioVendedor.setValue(""+v.getSalario());
		dataNascimento.setValue(v.getDataNascimento());
		
		btnGravar.setLabel("Editar");
		vendedorGlobal = v;
	 return v;
	}
	
	public void limpar(){
		nomeVendedor.setRawValue(null);
		salarioVendedor.setRawValue(null);
		dataNascimento.setRawValue(null);
	}

}
