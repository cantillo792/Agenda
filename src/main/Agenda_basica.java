/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;


public class Agenda_basica extends Agenda{
    private ArrayList<String> usuarios;
    public Agenda_basica(String propietario, String descripcion) {
        super(propietario, descripcion);
        this.usuarios = new ArrayList<>();
    }
    public Agenda_basica(String propietario, String descripcion, ArrayList<Turno> turnos) {
        super(propietario,descripcion,turnos);
        this.usuarios = new ArrayList<>();
    }

    @Override
    public String toString() {
       String retorno = super.toString(); 
        retorno+= " usuarios[ ";
        for (String usuario : usuarios) {
            retorno+= usuario+",";
        }
        return retorno;
    }
    

    @Override
    protected Agenda clone() {
       Agenda_basica nueva= new Agenda_basica(this.propietario, this.descripcion,this.turnos);
       return nueva;

    }
    
    
    
    
    
    @Override
    protected Turno elegirtunoL(String usuario) {
        for (Turno turno : turnos) {
            if(!mapa.containsKey(turno)){
                if(!usuarios.contains(usuario)){
                    usuarios.add(usuario);
                    return turno;
                }
            }
        }
        return null;
    }
    
}
