package mz.com.manjate.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Vendedor {
	
	@Id
	@GeneratedValue
	private int id_vendedor;
	private String vendedor;
	private double salario;
	
	public int getId_vendedor() {
		return id_vendedor;
	}
	public void setId_vendedor(int id_vendedor) {
		this.id_vendedor = id_vendedor;
	}
	public String getVendedor() {
		return vendedor;
	}
	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	
	public boolean isValid(){
		return !vendedor.isEmpty() && salario != 0.0 ;
		
	}

}
