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
 * Classe qui représente la réservation sans référence direct sur un client, une chambre et une liste d'excursion
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

    /**
     *
     * @return
     */
    public Long getIdReservation() {
        return idReservation;
    }

    /**
     *
     * @param idReservation
     */
    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    /**
     *
     * @return
     */
    public Long getDateDebut() {
        return dateDebut;
    }

    /**
     *
     * @param dateDebut
     */
    public void setDateDebut(Long dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     *
     * @return
     */
    public Long getDateFin() {
        return dateFin;
    }

    /**
     *
     * @param dateFin
     */
    public void setDateFin(Long dateFin) {
        this.dateFin = dateFin;
    }

    /**
     *
     * @return
     */
    public String getNumeroCarteBleu() {
        return numeroCarteBleu;
    }

    /**
     *
     * @param numeroCarteBleu
     */
    public void setNumeroCarteBleu(String numeroCarteBleu) {
        this.numeroCarteBleu = numeroCarteBleu;
    }

    /**
     *
     * @return
     */
    public Long getIdClient() {
        return idClient;
    }

    /**
     *
     * @param idClient
     */
    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    /**
     *
     * @return
     */
    public Long getIdChambre() {
        return idChambre;
    }

    /**
     *
     * @param idChambre
     */
    public void setIdChambre(Long idChambre) {
        this.idChambre = idChambre;
    }

    /**
     *
     * @return
     */
    public List<Long> getIdExcursions() {
        return idExcursions;
    }

    /**
     *
     * @param idExcursions
     */
    public void setIdExcursions(List<Long> idExcursions) {
        this.idExcursions = idExcursions;
    }

    
}
