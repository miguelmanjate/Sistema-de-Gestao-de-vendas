package mz.com.manjate.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.zkoss.zhtml.Li;
import org.zkoss.zhtml.Text;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;

import mz.com.manjate.model.ItemPedido;
import mz.com.manjate.model.Pedido;
import mz.com.manjate.model.Produto;
import mz.manjate.jpa.PedidoJPA;

public class ListarVendasController extends GenericForwardComposer<Component> {

	private Listbox lbxVendas;

	public void doAfterCompose(Component com) throws Exception {
		super.doAfterCompose(com);
		listarVenda();
	}

	public void listarVenda() {
		DateFormat df = new SimpleDateFormat("HH:mm dd/MM/yyyy");
		
		List<Pedido> pedidos = PedidoJPA.listar();
		lbxVendas.getItems().clear();
		for (Pedido pedido : pedidos) {
			 int totalProduto = 0;
			 double totalPreco = 0;
			Listitem item = new Listitem();

			Listcell cell1 = new Listcell("" + pedido.getNum_pedido());
			Listcell cell2 = new Listcell(pedido.getCliente().getCliente());
			Listcell cell3 = new Listcell(pedido.getVendedor().getVendedor());
			Listcell cell4 = new Listcell();
			
			for (ItemPedido itemPedido : pedido.getItemPedidos()) {
				
			    Li li = new Li();
			    Text t = new Text(itemPedido.getProduto().getDescricao()+" : "+itemPedido.getQuantidade());
			    totalProduto += itemPedido.getQuantidade();
			    totalPreco += itemPedido.getProduto().getPrecoUnitario() * itemPedido.getQuantidade();
			    li.appendChild(t);
			    cell4.appendChild(li);
					
				}
			
			Listcell cell5 = new Listcell(""+totalProduto);
			Listcell cell6 = new Listcell(""+totalPreco+"0$ MT");
			Listcell cell7 = new Listcell();
			if(pedido.getDataHora()!= null){
				Text t = new Text(df.format(pedido.getDataHora()));
			cell7.appendChild(t);
			}
			item.appendChild(cell1);
			item.appendChild(cell2);
			item.appendChild(cell3);
			item.appendChild(cell4);
			item.appendChild(cell5);
			item.appendChild(cell6);
            item.appendChild(cell7);
			
			item.setValue(pedido);
			lbxVendas.appendChild(item);
		}

	}

}
