/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hebook.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;
import javax.persistence.Table;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Classe Entity qui map avec la base de donnée et représente l'Excursion proposé par un hotel. 
 * HEBOOKING_EXCURSION est le nom de la table dans la base de données
 * @author Jean-vitus
 */
@Entity
@Table(name="HEBOOKING_EXCURSION")
@XmlRootElement
public class Excursion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="EXCURSION_ID")
    private Long idExcursion;

    @Column(name="EXCURSION_PRIX")
    private double prix;
    
    @Column(name="EXCURSION_DATE_DEBUT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebut;
    
    @Column(name="EXCURSION_DATE_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFin;
    
    @Column(name="EXCURSION_THEME")
    private String theme;
    
    @Column(name="EXCURSION_NOMBRE_PLACE")
    private int nombrePlace;
    
    
    public Excursion() {
        super();
    }
    
    @ManyToOne(fetch=FetchType.EAGER,cascade = CascadeType.REFRESH)
    @JoinColumn(name="HOTEL_ID")
    protected Hotel hotel;    

    
    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @ManyToMany(mappedBy="excursions")
    protected Collection<Reservation> reservations;
    
    
    @XmlTransient
    @Transient
    public Collection<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Collection<Reservation> reservations) {
        this.reservations = reservations;
    }
    
    
    
    public Long getId() {
        return idExcursion;
    }

    public void setId(Long id) {
        this.idExcursion = id;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
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

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idExcursion != null ? idExcursion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Excursion)) {
            return false;
        }
        Excursion other = (Excursion) object;
        if ((this.idExcursion == null && other.idExcursion != null) || (this.idExcursion != null && !this.idExcursion.equals(other.idExcursion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hebooking.modele.Excursion[ id=" + idExcursion + " ]";
    }
    
}
