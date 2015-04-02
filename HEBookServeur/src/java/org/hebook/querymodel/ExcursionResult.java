/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hebook.querymodel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe qui représente l'Excursion sans référence direct sur un hotel
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

    /**
     *
     * @return
     */
    public Long getIdExcursion() {
        return idExcursion;
    }

    /**
     *
     * @param idExcursion
     */
    public void setIdExcursion(Long idExcursion) {
        this.idExcursion = idExcursion;
    }

    /**
     *
     * @return
     */
    public double getPrix() {
        return prix;
    }

    /**
     *
     * @param prix
     */
    public void setPrix(double prix) {
        this.prix = prix;
    }

    /**
     *
     * @return
     */
    public Date getDateDebut(){
        //Date dateDeb = new Date((long)(dateDebut.getTime()));
        //System.out.println(" Fin : "+dateDeb.toString());
        return dateDebut;// new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(dateDebut);
    }

    /**
     *
     * @param dateDebut
     */
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     *
     * @return
     */
    public Date getDateFin(){
        //Date dateF = new Date((long)(dateFin.getTime())); 
        //System.out.println(" Fin : "+dateF.toString());
        return dateFin;
        //return dateFin;//new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(dateFin);
    }

    /**
     *
     * @param dateFin
     */
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    /**
     *
     * @return
     */
    public String getTheme() {
        return theme;
    }

    /**
     *
     * @param theme
     */
    public void setTheme(String theme) {
        this.theme = theme;
    }

    /**
     *
     * @return
     */
    public int getNombrePlace() {
        return nombrePlace;
    }

    /**
     *
     * @param nombrePlace
     */
    public void setNombrePlace(int nombrePlace) {
        this.nombrePlace = nombrePlace;
    }

    /**
     *
     * @return
     */
    public Long getIdHotel() {
        return idHotel;
    }

    /**
     *
     * @param idHotel
     */
    public void setIdHotel(Long idHotel) {
        this.idHotel = idHotel;
    }

    /**
     *
     * @return
     */
    public List<Long> getIdReservations() {
        return idReservations;
    }

    /**
     *
     * @param idReservations
     */
    public void setIdReservations(List<Long> idReservations) {
        this.idReservations = idReservations;
    }
    
    
}
