/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.time.LocalDate;


public class Turno {
    private  LocalDate dia;
    private final String franjaHoraria;

    public Turno(LocalDate dia, String franjaHoraria) {
        this.dia = dia;
        this.franjaHoraria = franjaHoraria;
    }

    @Override
    public String toString() {
       String retorno = "dia [ "+this.dia.toString()+" ] franjaHoraria [ "+this.franjaHoraria+" ]";
       return retorno;
    }
    
    
    public LocalDate getDia() {
        return dia;
    }

    public String getFranjaHoraria() {
        return franjaHoraria;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    @Override
    protected Turno clone() {
        Turno nuevo = new Turno(this.dia,this.franjaHoraria);
        return nuevo;
    }
   
    
    public boolean equals(Turno comparar){
        if( this.dia.isEqual(comparar.getDia()) && this.franjaHoraria.equals(comparar.getFranjaHoraria())){
            return true;
        }
        return false;
    }
    
}
