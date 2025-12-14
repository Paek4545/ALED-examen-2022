package es.upm.aled.fichero;

	import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

	public class Paciente implements Serializable {

		private static final long serialVersionUID = -7040445160832391615L;
		
		private String nombre;
		private String apellido;
		private String dni;
		
		public Paciente(String nombre, String apellido, String dni){
			this.nombre = nombre;
			this.apellido = apellido;
			this.dni = dni;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getApellido() {
			return apellido;
		}

		public void setApellido(String apellido) {
			this.apellido = apellido;
		}

		public String getDni() {
			return dni;
		}

		public void setDni(String dni) {
			this.dni = dni;
		}
		
		public String toString() {
			return nombre + ";" + apellido + ";" + dni;
		}
		
		public List<Paciente> leerPacientes(String f1) throws Exception {
			FileInputStream f = new FileInputStream(f1);
			InputStreamReader reader1 = new InputStreamReader(f);
			BufferedReader html = new BufferedReader(reader1);
			
			String[] pacientes; 
			String linea;
			List<Paciente> p = new ArrayList<Paciente>();
			while((linea = html.readLine()) != null) {
				pacientes = linea.split(",");
				p.add(new Paciente(pacientes[0], pacientes[1], pacientes[2])); // Siendo pacientes[0] nombre, pacientes[1] apellidos y paciente[2] el DNI
			}
			f.close();
			html.close();
			return p;
		}

		public void escribirPacientes(List<Paciente> l, String f1) throws Exception{
			FileOutputStream f = new FileOutputStream(f1);
			PrintWriter out = new PrintWriter(f);
			for(Paciente p : l) {
				out.println(p.getNombre()+","+p.getApellido()+","+p.getDni());
				out.close();
				f.close();
			}
		}
	}


