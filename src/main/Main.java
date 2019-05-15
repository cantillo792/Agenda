/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.time.LocalDate;
import java.util.ArrayList;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Agenda_basica agenda_basica= new Agenda_basica("Enrique", "tutorias");
        agenda_basica.agregarTurno(LocalDate.of(2018, 12, 12),"9:30-10:00");
        agenda_basica.agregarTurno(LocalDate.of(2018, 12, 12),"10:30-11:00");
        agenda_basica.agregarTurno(LocalDate.of(2018, 12, 13),"9:30-10:00");
        Agenda_balanceada agenda_balanceada= new Agenda_balanceada("Enrique", "Revision de examen");
        agenda_balanceada.agregarTurno(LocalDate.of(2018, 12, 12),"12:00-12:30");
        agenda_balanceada.agregarTurno(LocalDate.of(2018, 12, 12),"13:30-14:00");
        agenda_balanceada.agregarTurno(LocalDate.of(2018, 12, 13),"11:00-11:30");
        agenda_balanceada.agregarTurno(LocalDate.of(2018, 12, 13),"12:30-13:00");
        agenda_balanceada.agregarTurno(LocalDate.of(2018, 12, 13),"13:00-13:30");
        ArrayList<Agenda> lista= new ArrayList<>();
        lista.add(agenda_basica);
        lista.add(agenda_balanceada);
        for (Agenda agenda : lista) {
            System.out.println(agenda.getDescripcion());
            System.out.println(agenda.turnosLibres(LocalDate.of(2018, 12, 13)));
           
            
        }
        Turno tun=agenda_balanceada.turnoLibre("juan");
        System.out.println(tun.toString());
        System.out.println(agenda_basica.turnoLibre("juan").toString());
        for (Agenda agenda : lista) {
            for(Turno turno : agenda.dias(LocalDate.of(2018, 12, 13))){
                if(agenda.consultar(turno)!=null){
                    System.out.println(turno.toString());
                }
            }
        }
        agenda_balanceada.Cancelar(tun, "juan");
        System.out.println(agenda_balanceada.Maxdia());
        Agenda_balanceada copia= agenda_balanceada.clone();
        copia.ajudarDias(7);
        for (Turno turno : copia.getTurnos()) {
            System.out.println(turno.toString());
        }
        
        
    }
    
}
