package com.organizacionname.AppSB.Services;

import java.util.List;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.organizacionname.AppSB.Models.SolicitudesModels;

@Service
public interface ISolicitudesServices {
    List<SolicitudesModels> buscarTodas();
    SolicitudesModels  buscarPorId(Integer idSolicitud);
    void guardar(SolicitudesModels  solicitudModels);
    void eliminar(Integer idSolicitud);
    Page<SolicitudesModels> buscarTodas(Pageable page);
}
