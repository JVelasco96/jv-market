package com.jv.market.web.controller;

import com.jv.market.domain.Purchase;
import com.jv.market.domain.service.PurchaseService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/all")
    @ApiOperation("Obtener todas las compras")
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<Purchase>> getAll(){
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/client/{clientId}")
    @ApiOperation("Obtener las compra de un cliente por su ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "NOT FOUND")
    })
    public ResponseEntity<List<Purchase>> getByClientId(@ApiParam(value = "ID del cliente", required = true, example = "2") @PathVariable("clientId") String clientId){
       return purchaseService.getByClient(clientId)
               .map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
               .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    @ApiOperation("Registrar un compra")
    @ApiResponse( code = 201, message = "CREATED")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
    }
}
