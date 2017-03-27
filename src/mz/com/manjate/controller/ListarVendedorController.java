package mz.com.manjate.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.zkoss.zhtml.Text;
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
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		lbxVendedor.getItems().clear();
		List<Vendedor> vendedores = VendedorJPA.listar();
		
		for(Vendedor vendedor : vendedores){
			
			Listitem item = new Listitem();
			
			Listcell cell1 = new Listcell(""+vendedor.getId_vendedor());
			Listcell cell2 = new Listcell(vendedor.getVendedor());
			Listcell cell3 = new Listcell(""+vendedor.getSalario());
			
			Listcell cell4 = new Listcell();
			
			if(vendedor.getDataNascimento()!= null){
				Text t = new Text(df.format(vendedor.getDataNascimento()));
				cell4.appendChild(t);
			}
			
			item.appendChild(cell1);
			item.appendChild(cell2);
			item.appendChild(cell3);
			item.appendChild(cell4);
			
			item.setValue(vendedor);
			
			lbxVendedor.appendChild(item);
				
		}
	}

}
