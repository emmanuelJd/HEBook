/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hebook.querymodel;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe qui représente l'hotel sans référence direct sur les chambres et les excursions
 * @author Emmanuel
 */
@XmlRootElement
public class HotelResult implements Serializable{
    
    private Long idHotel;
    
    private String nomHotel;
    
    private String ville;
    
    private String imageHotel;
    
    private String description;
    
    private int nombreEtoile;
    
    private int nombreChambre;
    
    private List<Long> idChambres;

    private List<Long> idExcursions;

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
    
    
    
    /**
     *
     * @return
     */
    public List<Long> getIdChambres() {
        return idChambres;
    }

    /**
     *
     * @param idChambres
     */
    public void setIdChambres(List<Long> idChambres) {
        this.idChambres = idChambres;
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
    public String getNomHotel() {
        return nomHotel;
    }

    /**
     *
     * @return
     */
    public String getImageHotel() {
        return imageHotel;
    }

    /**
     *
     * @param imageHotel
     */
    public void setImageHotel(String imageHotel) {
        this.imageHotel = imageHotel;
    }

    
    
    /**
     *
     * @param nomHotel
     */
    public void setNomHotel(String nomHotel) {
        this.nomHotel = nomHotel;
    }

    /**
     *
     * @return
     */
    public String getVille() {
        return ville;
    }

    /**
     *
     * @param ville
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public int getNombreEtoile() {
        return nombreEtoile;
    }

    /**
     *
     * @param nombreEtoile
     */
    public void setNombreEtoile(int nombreEtoile) {
        this.nombreEtoile = nombreEtoile;
    }

    /**
     *
     * @return
     */
    public int getNombreChambre() {
        return nombreChambre;
    }

    /**
     *
     * @param nombreChambre
     */
    public void setNombreChambre(int nombreChambre) {
        this.nombreChambre = nombreChambre;
    }
    
    
}
