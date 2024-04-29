package com.organizacionname.AppSB.Services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.organizacionname.AppSB.Models.CategoriasModels;

@Service
public class CategoriasServiceImpl  implements ICategoriasService {

    private List<CategoriasModels> lista = null;
	
	public CategoriasServiceImpl() {
		lista = new LinkedList<CategoriasModels>();
		
		// Creamos algunas Categorias para poblar la lista ...
		
		// Categoria 1
		CategoriasModels cat1 = new CategoriasModels();
		cat1.setId(1);
		cat1.setNombre("Contabilidad");
		cat1.setDescripcion("Descripcion de la categoria Contabilidad");
		
		// Categoria 2
		CategoriasModels cat2 = new CategoriasModels();
		cat2.setId(2);
		cat2.setNombre("Ventas");
		cat2.setDescripcion("Trabajos relacionados con Ventas");
		
					
		// Categoria 3
		CategoriasModels cat3 = new CategoriasModels();
		cat3.setId(3);
		cat3.setNombre("Comunicaciones");
		cat3.setDescripcion("Trabajos relacionados con Comunicaciones");
		
		// Categoria 4
		CategoriasModels cat4 = new CategoriasModels();
		cat4.setId(4);
		cat4.setNombre("Arquitectura");
		cat4.setDescripcion("Trabajos de Arquitectura");
		
		// Categoria 5
		CategoriasModels cat5 = new CategoriasModels();
		cat5.setId(5);
		cat5.setNombre("Educacion");
		cat5.setDescripcion("Maestros, tutores, etc");
		
		/**
		 * Agregamos los 5 objetos de tipo Categoria a la lista ...
		 */
		lista.add(cat1);			
		lista.add(cat2);
		lista.add(cat3);
		lista.add(cat4);
		lista.add(cat5);

	}

    @Override
    public List<CategoriasModels> buscarTodas() {
        return lista;
    }

    @Override 
    public CategoriasModels buscarPorId(Integer idCategoria) {
        for (CategoriasModels cat : lista){
            if(cat.getId()==idCategoria){
                return cat;
            }
        }
        return null;
    }

    @Override
    public void guardar(CategoriasModels categoriasModels) {
        lista.add(categoriasModels);
    }

	@Override
	public void eliminar(Integer idCategoria) {
		throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
	}

	@Override
	public Page<CategoriasModels> buscarTodas(Pageable page) {
		throw new UnsupportedOperationException("Unimplemented method 'buscarPage'");
	}
    
}
