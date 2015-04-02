/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hebook.querymodel;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe qui représente le couple d'attribut identifiant de l'hotel ainsi que le nombre de chambre associé
 * @author Emmanuel
 */
@XmlRootElement
public class ChambrePerHotel implements Serializable{
    
    private Long idHotel;
    private Integer NbreChambre;

    /**
     *
     * @return Long identifiant de l'hotel
     */
    public Long getIdHotel() {
        return idHotel;
    }

    /**
     *
     * @param idHotel identifiant de l'hotel
     */
    public void setIdHotel(Long idHotel) {
        this.idHotel = idHotel;
    }

    /**
     *
     * @return Integer nombre de chambre
     */
    public Integer getNbreChambre() {
        return NbreChambre;
    }

    /**
     *
     * @param NbreChambre modifie le nombre de chambre
     */
    public void setNbreChambre(Integer NbreChambre) {
        this.NbreChambre = NbreChambre;
    }    
}
