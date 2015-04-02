/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.client.servlet;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Emmanuel
 */
@XmlRootElement
public class ExcursionResult implements Serializable{
    
    private Long idExcursion;
    private double prix;
    private Date dateDebut;
    private Date dateFin;
    private String theme;
    private int nombrePlace;
    private Long idHotel;    

    private List<Long> idReservations;

    public Long getIdExcursion() {
        return idExcursion;
    }

    public void setIdExcursion(Long idExcursion) {
        this.idExcursion = idExcursion;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Date getDateDebut(){
        //Date dateDeb = new Date((long)(dateDebut.getTime()));
        //System.out.println(" Fin : "+dateDeb.toString());
        return dateDebut;// new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(dateDebut);
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin(){
        //Date dateF = new Date((long)(dateFin.getTime())); 
        //System.out.println(" Fin : "+dateF.toString());
        return dateFin;
        //return dateFin;//new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(dateFin);
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public int getNombrePlace() {
        return nombrePlace;
    }

    public void setNombrePlace(int nombrePlace) {
        this.nombrePlace = nombrePlace;
    }

    public Long getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(Long idHotel) {
        this.idHotel = idHotel;
    }

    public List<Long> getIdReservations() {
        return idReservations;
    }

    public void setIdReservations(List<Long> idReservations) {
        this.idReservations = idReservations;
    }
    
    
}
