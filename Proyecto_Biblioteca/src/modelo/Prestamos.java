package modelo;

import java.util.Calendar;
import java.util.Date;

public class Prestamos {
	
	private Calendar fecha_inicio;
	private Calendar fecha_fin;
	private String libro_asociado;
	private String socio_asocidado;
	
	
	
	
	public Prestamos(Calendar inicio,Calendar fin,String libro,String socio) {
		this.fecha_inicio=inicio;
		this.fecha_fin=fin;
		this.libro_asociado=libro;
		this.socio_asocidado=socio;
	}




	@Override
	public String toString() {
		return "Prestamos [fecha_inicio=" + fecha_inicio + ", fecha_fin=" + fecha_fin + ", libro_asociado="
				+ libro_asociado + ", socio_asocidado=" + socio_asocidado + "]";
	}




	public Calendar getFecha_inicio() {
		return fecha_inicio;
	}




	public void setFecha_inicio(Calendar fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}




	public Calendar getFecha_fin() {
		return fecha_fin;
	}




	public void setFecha_fin(Calendar fecha_fin) {
		this.fecha_fin = fecha_fin;
	}




	public String getLibro_asociado() {
		return libro_asociado;
	}




	public void setLibro_asociado(String libro_asociado) {
		this.libro_asociado = libro_asociado;
	}




	public String getSocio_asocidado() {
		return socio_asocidado;
	}




	public void setSocio_asocidado(String socio_asocidado) {
		this.socio_asocidado = socio_asocidado;
	}
	
	
	
	

}
