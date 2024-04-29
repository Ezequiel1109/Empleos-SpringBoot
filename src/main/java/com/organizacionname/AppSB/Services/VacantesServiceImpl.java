package com.organizacionname.AppSB.Services;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.organizacionname.AppSB.Models.VacantesModels;
@Service
public class VacantesServiceImpl implements IVacantesService {
    
    private List<VacantesModels> lst = null;

    public VacantesServiceImpl(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        lst = new LinkedList<VacantesModels>();
        try {
            VacantesModels v1 = new VacantesModels();
            v1.setId(1);
            v1.setNombre("Desarrollador Java");
            v1.setDescripcion("Urgente");
            v1.setFecha(sdf.parse("08-02-2020"));
            v1.setSalario(500.0);
            v1.setDestacado(1);
            v1.setImagen("emp1.png");

            VacantesModels v2 = new VacantesModels();
            v2.setId(2);
            v2.setNombre("Desarrollador PHP");
            v2.setDescripcion("Urgente");
            v2.setFecha(sdf.parse("08-02-2032"));
            v2.setSalario(50500.0);
            v2.setDestacado(0);
            v2.setImagen("emp2.png");

            VacantesModels v3 = new VacantesModels();
            v3.setId(3);
            v3.setNombre("Desarrollador SP");
            v3.setDescripcion("Urgente");
            v3.setFecha(sdf.parse("08-02-3134"));
            v3.setSalario(50560.0);
            v3.setDestacado(1);
            v3.setImagen("sin-imagen.png");

            VacantesModels v4 = new VacantesModels();
            v4.setId(4);
            v4.setNombre("Desarrollador Angular");
            v4.setDescripcion("Urgente");
            v4.setFecha(sdf.parse("08-02-3134"));
            v4.setSalario(5000.0);
            v4.setDestacado(0);
            v4.setImagen("emp4.png");


            /*
             * se agrega los objetos de la lista
            */
            lst.add(v1);
            lst.add(v2);
            lst.add(v3);
            lst.add(v4);
        } catch (Exception e) {
            System.out.println("Error al cargar vacantes" + e);
        }
    }
    @Override
    public List<VacantesModels> buscarTodas(){
        return lst;
    }
    @Override
    public VacantesModels buscarPorId(Integer idVacante) {
        for(VacantesModels v : lst){
            if (v.getId()==idVacante) {
                return v;
            }
        }
        return null;
    }
    @Override
    public void guardar(VacantesModels vacante) {
        lst.add(vacante);
    }
    @Override
    public List<VacantesModels> destacadasVacantes() {
        throw new UnsupportedOperationException("Unimplemented method 'destacadasVacantes'");
    }
    @Override
    public void eliminar(Integer idVacante) {
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }
    @Override
    public List<VacantesModels> buscarByExample(Example<VacantesModels> example) {
        throw new UnsupportedOperationException("Unimplemented method 'buscarByExample'");
    }
    @Override
    public Page<VacantesModels> buscarTodas(Pageable page) {
        throw new UnsupportedOperationException("Unimplemented method 'buscarTodas'");
    }
 
    
}
