package co.estudianteApp.servicio;

import co.estudianteApp.modelo.Estudiante;

import java.util.List;

public interface IEstudianteService {

    public List<Estudiante> listarEstudiantes();

    public Estudiante findEstudianteById(Integer idEstudiante);

    public void guardarEstudiante(Estudiante estudiante);

    public void eliminarEstudiante(Estudiante estudiante);
}
