package ar.com.ada.api.empleadas.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.empleadas.entities.Categoria;
import ar.com.ada.api.empleadas.entities.Empleada;
import ar.com.ada.api.empleadas.entities.Empleada.EstadoEmpleadaEnum;
import ar.com.ada.api.empleadas.repos.EmpleadaRepository;

@Service
public class EmpleadaService {

    @Autowired
    EmpleadaRepository repository;

    @Autowired
    CategoriaService categoriaService;

    public void crearEmpleada(Empleada empleada) {
        repository.save(empleada);
    }

    public List<Empleada> traerEmpleadas() {
        return repository.findAll();
    }

    public Empleada buscarEmpleada(Integer empleadaId) {
        Optional<Empleada> resultado = repository.findById(empleadaId);

        if (resultado.isPresent())
            return resultado.get();

        return null;
    }

    public void bajaEmpleadaPorId(Integer id) {
        Empleada empleada = this.buscarEmpleada(id);

        empleada.setEstado(EstadoEmpleadaEnum.BAJA);
        empleada.setFechaBaja(new Date());

        repository.save(empleada);

    }

    public List<Empleada> traerEmpleadaPorCategoria(Integer catId) {

        Categoria categoria = categoriaService.buscarCategoria(catId);

        return categoria.getEmpleadas();
    }

    public void guardar(Empleada empleada) {
        repository.save(empleada);
    }
}
