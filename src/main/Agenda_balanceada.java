/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Agenda_balanceada extends Agenda{
    private Map<LocalDate,Integer> balanceador;

    public Agenda_balanceada( String propietario, String descripcion) {
        super(propietario, descripcion);
        this.balanceador = new HashMap<>();
    }
    public Agenda_balanceada(String propietario, String descripcion, ArrayList<Turno> turnos) {
        super(propietario,descripcion,turnos);
        this.balanceador = new HashMap<>();
    }

    @Override
    protected Agenda_balanceada clone() {
       Agenda_balanceada nueva = new Agenda_balanceada(this.propietario, this.descripcion, this.turnos);
       return nueva;
    }

    @Override
    public String toString() {
        LocalDate dia=  LocalDate.of(9999, 12, 1);
        String retorno= super.toString(); 
        for (int i = 0; i < turnos.size(); i++) {
            if (balanceador.containsKey(turnos.get(i).getDia())) {
                if(!dia.equals(turnos.get(i).getDia())){
                    retorno+= "{ key dia: "+turnos.get(i).getDia().toString()+" clave : "+balanceador.get(turnos.get(i).getDia())+" }";
                    dia=turnos.get(i).getDia();
                }
                
            }
        }
        return retorno;
    }
    
    
    
    @Override
    public boolean Cancelar(Turno turno, String usuario){
        if(mapa.containsKey(turno)){
            if(usuario.equals(mapa.get(turno))){
               mapa.remove(turno);
               actualizarMapa();
               return true; 
            }
        }
         return false;
    }
    @Override
     public boolean agregarTurno(LocalDate dia,String FranjaHoraria){
        Turno nuevo= new Turno(dia,FranjaHoraria);
        if(!turnos.contains(nuevo)){
            
            if(turnos.add(nuevo)){
                actualizarMapa();
                return true;
            }
        }
            return false;
        
  

    }
    
    public void actualizarMapa(){
        int aux=0;
        balanceador.clear();
        
        for (int i = 0; i < turnos.size(); i++) {
            if(!mapa.containsKey(turnos.get(i))){
               if(balanceador.containsKey(turnos.get(i).getDia())){
                aux=balanceador.get(turnos.get(i).getDia());
                aux++;
                
                balanceador.put(turnos.get(i).getDia(), aux);
               }else{
                balanceador.put(turnos.get(i).getDia(), 1);
               }
                
            }
             
        }
        
    }
    public LocalDate Maxdia(){
        int aux=0;
        LocalDate dia=null;
        actualizarMapa();
        for (Turno turno : turnos){
            if( aux < balanceador.get(turno.getDia())){
                aux=balanceador.get(turno.getDia());
                dia=turno.getDia();
            }
        }
        return dia;
    }
    
    @Override
    protected Turno elegirtunoL(String usuario) {
        int aux=0;
        LocalDate dia=null;
        actualizarMapa();
        for (Turno turno : turnos){
            if( aux < balanceador.get(turno.getDia())){
                aux=balanceador.get(turno.getDia());
                dia=turno.getDia();
            }
        }
        for (Turno turno : turnos) {
            if(dia!=null){
            if(turno.getDia().equals(dia)){
                if(!mapa.containsKey(turno)){
                    aux=balanceador.get(dia);
                    aux--;
                    balanceador.remove(dia);
                    balanceador.put(dia, aux);
                    return turno;
                }
            }
            }
        }
        return null;
    }
    
    
    
}
