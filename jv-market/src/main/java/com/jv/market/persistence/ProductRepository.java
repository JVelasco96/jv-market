package com.jv.market.persistence;

import com.jv.market.persistence.crud.ProductoCrudRepository;
import com.jv.market.persistence.entity.Producto;

import java.util.List;

public class ProductRepository {

    private ProductoCrudRepository productoCrudRepository;


    public List<Producto> getAll(){
        return (List<Producto>) productoCrudRepository.findAll();
    }

    public List<Producto> getByCategoria( int idCategoria ){
        return productoCrudRepository.findByIdCategoriaOrderByNombreAsc( idCategoria );
    }

    public Optional<List<Producto>> getEscasos(int cantidad) {
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true);
    }
}
