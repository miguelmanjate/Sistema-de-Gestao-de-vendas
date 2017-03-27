package mz.com.manjate.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import mz.manjate.jpa.PedidoJPA;

public class GeraTabelas {

	public static void main(String[] args) throws ParseException {

		//Calendar c = Calendar.getInstance();
	Pedido p = PedidoJPA.getBayId(56);
	DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	
	System.out.println(p.getCliente().getCliente()+"  "+p.getVendedor().getVendedor()+"  "+df.format(p.getDataHora()));
		
	}

}
