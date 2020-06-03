package modelo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class Prestamos implements Comparable<Prestamos>{

	private Date fecha_inicio;
	private Date fecha_fin;
	private int socio_asocidado;
	private int libro_asociado;




	public Prestamos(Date inicio,Date fin,int socio,int libro) {
		this.fecha_inicio=inicio;
		this.fecha_fin=fin;
		this.socio_asocidado=socio;
		this.libro_asociado=libro;

	}
	public Prestamos(Prestamos obj) {
		this.fecha_inicio=obj.getFecha_inicio();
		this.fecha_fin=obj.getFecha_fin();
		this.socio_asocidado=obj.getSocio_asocidado();
		this.libro_asociado=obj.libro_asociado;
	}
	public  void insertarPrestamo() throws SQLException, ClassNotFoundException {
		try {
		String insert=" Insert into prestamos values(?,?,?,?)";
		PreparedStatement st2=Conector.conectar().prepareStatement(insert);
		st2.setDate(1, this.getFecha_inicio());
		st2.setDate(2, this.getFecha_fin());
		st2.setInt(3, this.getSocio_asocidado());
		st2.setInt(4, this.getLibro_asociado());
		st2.executeUpdate();
		}finally {
			Conector.cerrar();
		}
		
	}






	@Override
	public int hashCode() {
		return Objects.hash(fecha_inicio,fecha_fin,socio_asocidado,libro_asociado);
	}







	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if(obj instanceof Prestamos) {
			Prestamos aux=(Prestamos)obj;
			if(this.fecha_inicio.equals(aux.getFecha_inicio())&&this.fecha_fin.equals(aux.getFecha_fin())&&
					this.socio_asocidado==aux.getSocio_asocidado()&&
					this.libro_asociado==aux.libro_asociado)
				return(true);
		}
		return false;
	}







	@Override
	public String toString() {
		return "Prestamos [fecha_inicio=" + fecha_inicio + ", fecha_fin=" + fecha_fin + ", libro_asociado="
				+ libro_asociado + ", socio_asocidado=" + socio_asocidado + "]";
	}




	public Date getFecha_inicio() {
		return fecha_inicio;
	}




	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}




	public Date getFecha_fin() {
		return fecha_fin;
	}




	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}




	public int getLibro_asociado() {
		return libro_asociado;
	}




	public void setLibro_asociado(int libro_asociado) {
		this.libro_asociado = libro_asociado;
	}




	public int getSocio_asocidado() {
		return socio_asocidado;
	}




	public void setSocio_asocidado(int socio_asocidado) {
		this.socio_asocidado = socio_asocidado;
	}
	@Override
	public int compareTo(Prestamos o) {
		return String.valueOf(this.libro_asociado).compareTo(String.valueOf(o.getLibro_asociado()));
	}





}
