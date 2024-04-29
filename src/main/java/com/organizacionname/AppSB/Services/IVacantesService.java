package com.organizacionname.AppSB.Services;

import java.util.List;


import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.organizacionname.AppSB.Models.VacantesModels;

@Service
public interface IVacantesService {
    List<VacantesModels> buscarTodas();
    VacantesModels  buscarPorId(Integer idVacante);
    void guardar(VacantesModels vacantesModels);
    List<VacantesModels> destacadasVacantes();
    void eliminar(Integer idVacante);
    List<VacantesModels> buscarByExample(Example<VacantesModels>  example);
    Page<VacantesModels> buscarTodas(Pageable page);
}
