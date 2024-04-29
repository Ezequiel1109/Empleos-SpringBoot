package com.organizacionname.AppSB.Services.dbServices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;
import com.organizacionname.AppSB.Models.VacantesModels;
import com.organizacionname.AppSB.Repository.VacantesRepository;
import com.organizacionname.AppSB.Services.IVacantesService;

@Service
@Primary
public class VacantesServicesJpa implements  IVacantesService {

    @Autowired
    private VacantesRepository vacRepo;

    @Override
    public List<VacantesModels> buscarTodas() {
        return vacRepo.findAll();
    }

    @Override
    public VacantesModels buscarPorId(Integer idVacante) {
        Optional<VacantesModels> optional =  vacRepo.findById(idVacante);
        if(optional.isPresent()){
            return optional.get();
        }else{
            throw new RuntimeException("No se encontro la vacante con el ID: " + idVacante);
        }
    }

    @Override
    public void guardar(VacantesModels vacantesModels) {
        vacRepo.save(vacantesModels);
    }

    @Override
    public List<VacantesModels> destacadasVacantes() {
        return  vacRepo.findByDestacadoAndEstatusOrderByIdDesc(1, "Aprobada");
    }

    @Override
    public void eliminar(Integer idVacante) {
        vacRepo.deleteById(idVacante);
    }

    @Override
    public List<VacantesModels> buscarByExample(Example<VacantesModels> example) {
        return vacRepo.findAll(example);
    }

    @Override
    public Page<VacantesModels> buscarTodas(Pageable page) {
        return vacRepo.findAll(page);
    }

}
