package co.estudianteApp;

import co.estudianteApp.modelo.Estudiante;
import co.estudianteApp.servicio.EstudianteServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class EstudianteAppApplication implements CommandLineRunner {

	@Autowired
	private EstudianteServiceImpl estudianteService;

	//herramienta utilizada para registrar mensajes en la consola o en archivos de registro
	private static final Logger logger=
			LoggerFactory.getLogger(EstudianteAppApplication.class);
	// salto de linea
	String nl = System.lineSeparator();

	public static void main(String[] args) {
		//imprimir con logger
		logger.info("Iniciando la aplicacion...");
		//levantar fabrica de spring
		SpringApplication.run(EstudianteAppApplication.class, args);

		logger.info("Aplicación finalizada...");
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info(nl + "Ejecutando metodo run de Spring..." + nl);

		var salir = false;
		var consola = new Scanner(System.in);
		while(!salir){
			mostrarMenu();
			salir = ejecutarOpciones(consola);
			logger.info(nl);
		}
	}

	private void mostrarMenu(){
		logger.info(nl);
		logger.info("""
    			***Sistema de Estudiantes***
    			
    			1.Listar estudiantes
    			2.Buscar estudiante
    			3.Agregar estudiante
    			4.Modificar estudiante
    			5.Eliminar estudiante
    			6.Salir
    			
    			Elige una opción:""");
	}

	private boolean ejecutarOpciones(Scanner consola){
		var opcion = Integer.parseInt(consola.nextLine());
		var salir = false;
		switch (opcion){
			case 1 -> { //Buscar estudiante
				logger.info(nl+ "Listado estudiantes" + nl);
				List<Estudiante> estudiantes = estudianteService.listarEstudiantes();
				estudiantes.forEach(estudiante -> logger.info(estudiante.toString()+ nl));
			}
			case 2 -> { // buscar estudiante
				logger.info("Ingrese el id del estudiante: ");
				var idEstudiante = Integer.parseInt(consola.nextLine());
				Estudiante estudiante = estudianteService.findEstudianteById(idEstudiante);
				if ( estudiante != null)
					logger.info("Estudiante encontrado" + nl + estudiante + nl );
				else
					logger.info("El estudiante con id " + idEstudiante+ " no fue encontrado" + nl);
			}
			case 3 -> { // agregar un nuevo estudiante
				logger.info("Agregar estudiante:" + nl);
				logger.info("Nombre: ");
				var nombre = consola.nextLine();
				logger.info("Apellido: ");
				var apellido = consola.nextLine();
				logger.info("Telefono: ");
				var telefono = consola.nextLine();
				logger.info("Email: ");
				var email = consola.nextLine();

				var estudiante = new Estudiante();
				estudiante.setNombre(nombre);
				estudiante.setApellido(apellido);
				estudiante.setTelefono(telefono);
				estudiante.setEmail(email);
				estudianteService.guardarEstudiante(estudiante);
				logger.info("Estudiante guardado con exito: " + nl + estudiante + nl);
			}
			case 4 -> { // modificar un estudiante
				logger.info("Modificar estudiante:" + nl);
				logger.info("Id estudiante: ");
				var idEstudiante = Integer.parseInt(consola.nextLine());
				Estudiante estudiante = estudianteService.findEstudianteById(idEstudiante);
				if( estudiante != null) {
					logger.info("Nombre: ");
					var nombre = consola.nextLine();
					logger.info("Apellido: ");
					var apellido = consola.nextLine();
					logger.info("Telefono: ");
					var telefono = consola.nextLine();
					logger.info("Email: ");
					var email = consola.nextLine();

					estudiante.setNombre(nombre);
					estudiante.setApellido(apellido);
					estudiante.setTelefono(telefono);
					estudiante.setEmail(email);
					estudianteService.guardarEstudiante(estudiante);
					logger.info("Estudiante modificado con exito: " + nl + estudiante + nl);
				}else
					logger.info("Estudiante con id "+ idEstudiante+" no fue encontrado."+nl);
			}
			case 5 -> {
				// eliminar un estudiante
				logger.info("Modificar estudiante:" + nl);
				logger.info("Id estudiante: ");
				var idEstudiante = Integer.parseInt(consola.nextLine());
				Estudiante estudiante = estudianteService.findEstudianteById(idEstudiante);
				if( estudiante != null) {
					estudianteService.eliminarEstudiante(estudiante);
					logger.info("Estudiante eliminado con éxito"+nl);
				}else
				logger.info("El estudiante con id "+ idEstudiante+" no existe" +nl);
			}
			case 6 ->{
				logger.info("Hasta pronto ... "+nl+nl);
				salir = true;
			}
			default -> logger.info("Opcion "+opcion+" no reconocida." + nl);
		}
		return salir;
	}
}
