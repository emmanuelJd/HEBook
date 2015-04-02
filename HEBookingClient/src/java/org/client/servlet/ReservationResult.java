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
public class ReservationResult implements Serializable{
    
    private Long idReservation;
    private Long dateDebut;
    private Long dateFin;
    private String numeroCarteBleu;
    private Long idClient;
    private Long idChambre;
    private List<Long> idExcursions;

    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public Long getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Long dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Long getDateFin() {
        return dateFin;
    }

    public void setDateFin(Long dateFin) {
        this.dateFin = dateFin;
    }

    public String getNumeroCarteBleu() {
        return numeroCarteBleu;
    }

    public void setNumeroCarteBleu(String numeroCarteBleu) {
        this.numeroCarteBleu = numeroCarteBleu;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public Long getIdChambre() {
        return idChambre;
    }

    public void setIdChambre(Long idChambre) {
        this.idChambre = idChambre;
    }

    public List<Long> getIdExcursions() {
        return idExcursions;
    }

    public void setIdExcursions(List<Long> idExcursions) {
        this.idExcursions = idExcursions;
    }

    
}
