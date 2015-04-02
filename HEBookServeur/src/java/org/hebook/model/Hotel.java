/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hebook.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Classe Entity qui map avec la base de donnée et représente le Hotel. 
 * HEBOOKING_HOTEL est le nom de la table dans la base de données
 * @author Jean-vitus
 * 
 */
@Entity
@Table(name="HEBOOKING_HOTEL")
@XmlRootElement
public class Hotel implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HOTEL_ID")
    private Long idHotel;

    @Column(name = "HOTEL_NOM")
    private String nomHotel;
    
    @Column(name = "HOTEL_IMAGE")
    private String imageHotel;
    
    @Column(name = "HOTEL_VILLE")
    private String ville;
    
    @Column(name = "HOTEL_DESCRIPTION")
    private String description;
    
    @Column(name = "HOTEL_ETOILE")
    private int nombreEtoile;
    
    @Column(name = "HOTEL_NBRE_CHAMBRE")
    private int nombreChambre;

    @OneToMany(mappedBy="hotel",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Collection<Chambre> chambre;

    @XmlTransient
    @Transient
    public Collection<Chambre> getChambre() {
        return chambre;
    }

    public void setChambre(Collection<Chambre> chambre) {
        this.chambre = chambre;
    }
    
    @OneToMany(cascade= CascadeType.ALL,mappedBy="hotel",fetch = FetchType.EAGER)
    private Collection<Excursion> excursions;

    
    @XmlTransient
    @Transient
    public Collection<Excursion> getExcursions() {
        return excursions;
    }

    public void setExcursions(Collection<Excursion> excursions) {
        this.excursions = excursions;
    }
    
    public Hotel() {
        super();
    }

    public String getNomHotel() {
        return nomHotel;
    }

    public void setNomHotel(String nomHotel) {
        this.nomHotel = nomHotel;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNombreEtoile() {
        return nombreEtoile;
    }

    public void setNombreEtoile(int nombreEtoile) {
        this.nombreEtoile = nombreEtoile;
    }

    public int getNombreChambre() {
        return nombreChambre;
    }

    public void setNombreChambre(int nombreChambre) {
        this.nombreChambre = nombreChambre;
    }

    public String getImageHotel() {
        return imageHotel;
    }

    public void setImageHotel(String imageHotel) {
        this.imageHotel = imageHotel;
    }
    
    
    
    public Long getId() {
        return idHotel;
    }

    public void setId(Long id) {
        this.idHotel = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHotel != null ? idHotel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hotel)) {
            return false;
        }
        Hotel other = (Hotel) object;
        if ((this.idHotel == null && other.idHotel != null) || (this.idHotel != null && !this.idHotel.equals(other.idHotel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hebooking.modele.Hotel[ id=" + idHotel + " ]";
    }
    
}