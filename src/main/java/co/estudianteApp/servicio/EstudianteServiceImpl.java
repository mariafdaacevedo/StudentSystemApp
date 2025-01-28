package co.estudianteApp.servicio;

import co.estudianteApp.modelo.Estudiante;
import co.estudianteApp.repositorio.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstudianteServiceImpl implements IEstudianteService{

    @Autowired
    private EstudianteRepository estudianteRepository;
    @Override
    public List<Estudiante> listarEstudiantes() {
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        if (!estudiantes.isEmpty()) return estudiantes;
        return new ArrayList<>();
    }

    @Override
    public Estudiante findEstudianteById(Integer idEstudiante) {
        Estudiante estudiante = estudianteRepository.findById(idEstudiante).orElse(null);
        return estudiante;
    }

    @Override
    public void guardarEstudiante(Estudiante estudiante) {
        estudianteRepository.save(estudiante);
    }

    @Override
    public void eliminarEstudiante(Estudiante estudiante) {
        estudianteRepository.delete(estudiante);
    }
}
