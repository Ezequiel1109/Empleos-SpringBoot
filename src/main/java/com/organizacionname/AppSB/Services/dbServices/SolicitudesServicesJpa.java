package com.organizacionname.AppSB.Services.dbServices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.organizacionname.AppSB.Models.SolicitudesModels;
import com.organizacionname.AppSB.Repository.SolicitudesRepository;
import com.organizacionname.AppSB.Services.ISolicitudesServices;

@Service
public class SolicitudesServicesJpa implements ISolicitudesServices{

    @Autowired
    private  SolicitudesRepository soliRepo;

    @Override
    public List<SolicitudesModels> buscarTodas() {
        return soliRepo.findAll();
    }

    @Override
    public SolicitudesModels buscarPorId(Integer idSolicitud) {
        Optional<SolicitudesModels> optional =  soliRepo.findById(idSolicitud);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public void guardar(SolicitudesModels solicitudModels) {
        soliRepo.save(solicitudModels);
    }

    @Override
    public void eliminar(Integer idSolicitud) {
       soliRepo.deleteById(idSolicitud);
    }

    @Override
    public Page<SolicitudesModels> buscarTodas(Pageable page) {
        return soliRepo.findAll(page);
    }

}
