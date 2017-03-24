package mz.com.manjate.controller;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;

import mz.com.manjate.model.Vendedor;
import mz.manjate.jpa.VendedorJPA;

public class ListarVendedorController extends GenericForwardComposer<Component> {
	
	private Listbox lbxVendedor;
	
	public void doAfterCompose(Component com) throws Exception{
		super.doAfterCompose(com);
		
		listar();
	}
	
	public void listar(){
		lbxVendedor.getItems().clear();
		List<Vendedor> vendedores = VendedorJPA.listar();
		
		for(Vendedor vendedor : vendedores){
			
			Listitem item = new Listitem();
			
			Listcell cell1 = new Listcell(""+vendedor.getId_vendedor());
			Listcell cell2 = new Listcell(vendedor.getVendedor());
			Listcell cell3 = new Listcell(""+vendedor.getSalario());
			
			item.appendChild(cell1);
			item.appendChild(cell2);
			item.appendChild(cell3);
			
			item.setValue(vendedor);
			
			lbxVendedor.appendChild(item);
				
		}
	}

}
