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


public abstract class Agenda {
    protected  String propietario;
    protected  String descripcion;
    protected  ArrayList<Turno> turnos ; 
    protected Map<Turno,String > mapa;

    public Agenda(String propietario, String descripcion) {
        this.propietario = propietario;
        this.descripcion = descripcion;
        turnos = new ArrayList<>();
        mapa = new HashMap<>();
    }

    public Agenda(String propietario, String descripcion, ArrayList<Turno> turnos) {
        this.propietario = propietario;
        this.descripcion = descripcion;
        this.turnos= new ArrayList<>();
        for(Turno turno : turnos){
             this.turnos.add(turno.clone());
        }
        this.mapa = new HashMap<>();
    }

    @Override
    protected Agenda clone()  {
       return null;
    }
    public int turnosLibres(LocalDate dia){
        int diponibles=0;
        for (Turno turno : turnos) {
            if(dia.equals(turno.getDia())){
                diponibles++;
            }
        }
        return diponibles;
    }

    @Override
    public String toString() {
        String retorno= "propietario : "+this.propietario+" descripcion : "+this.descripcion;
        for (Turno turno : turnos) {
            retorno+= "{ "+turno.toString()+" },";
        }
        for (Turno turno : turnos) {
            if(mapa.containsKey(turno)){
                retorno+= "{ key: "+turno.toString()+" clave : "+mapa.get(turno)+" }";
            }
        }
        return retorno;
    }
    
    public String getPropietario() {
        return propietario;
    }
    public boolean reservar(String usuario,Turno turno ){
        if(turnos.contains(turno)){
            if(!mapa.containsKey(turno)){
               mapa.put(turno,usuario);
               return true;
            }
        }
        return false;
    }
    protected abstract Turno elegirtunoL(String usuario);
    public String consultar(Turno turno){
        if(mapa.containsKey(turno)){
            return mapa.get(turno);
        }
        return null;
        
    }
    public boolean Cancelar(Turno turno, String usuario){
        if(mapa.containsKey(turno)){
            if(usuario.equals(mapa.get(turno))){
               mapa.remove(turno);
               return true; 
            }
        }
         return false;
    }
    public boolean isOcupado(Turno turno){
        return mapa.containsKey(turno);
    }
    public Turno turnoLibre(String usuario){
        Turno nuevo= elegirtunoL(usuario);
        if(nuevo!=null){
            reservar(usuario, nuevo);
            return nuevo;
        }
        return null;
    }
    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public boolean agregarTurno(LocalDate dia,String FranjaHoraria){
        Turno nuevo= new Turno(dia,FranjaHoraria);
        if(!turnos.contains(nuevo)){
            return turnos.add(nuevo);
        }else{
            return false;
        }
  

    }
    public ArrayList<Turno> getTurnos(){
        return this.turnos;
    }
    public void ajudarDias(int dias){
       if(dias>0){
            for(int i=0;i<turnos.size();i++){
                this.turnos.get(i).setDia(this.turnos.get(i).getDia().plusDays(dias));
            }
       }else if(dias<0){
           for(int i=0;i<turnos.size();i++){
                this.turnos.get(i).setDia(this.turnos.get(i).getDia().minusDays(-dias));
            }
       }
        
        
    }
    public ArrayList<Turno> dias(LocalDate dia){
        ArrayList<Turno> listaDias = new ArrayList<Turno>();
        for(int i=0;i<turnos.size();i++){
            if(dia.equals(turnos.get(i).getDia())){
                listaDias.add(turnos.get(i));
            }
                
        }
        return listaDias;
    }
    
    
}
