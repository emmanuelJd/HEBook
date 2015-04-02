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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Classe Entity qui map avec la base de donnée et représente la chambre d'un hotel. 
 * HEBOOKING_CHAMBRE est le nom de la table dans la base de données
 * @author Jean-vitus
 */
@Entity
@Table(name="HEBOOKING_CHAMBRE")
@XmlRootElement
public class Chambre implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CHAMBRE_ID")
    private Long idChambre;

    @Column(name="CHAMBRE_NUMERO")
    private int numero;
    
    @Column(name="CHAMBRE_ETAGE")
    private String etage;
    
    @Column(name="CHAMBRE_PRIX")
    private double prix;
    
    @Column(name="CHAMBRE_CATEGORIE")
    private String categorie;
    
    @ManyToOne(fetch= FetchType.EAGER,cascade = CascadeType.REFRESH)
    @JoinColumn(name="HOTEL_ID")
    protected Hotel hotel;

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
    
   
    @OneToMany(mappedBy="chambre")
    private Collection<Reservation> reservations;

    
    @XmlTransient
    @Transient
    public Collection<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Collection<Reservation> reservations) {
        this.reservations = reservations;
    }
    
 
    public Chambre() {
        super();
    }

    
    public Long getId() {
        return idChambre;
    }

    public void setId(Long id) {
        this.idChambre = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEtage() {
        return etage;
    }

    public void setEtage(String etage) {
        this.etage = etage;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idChambre != null ? idChambre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chambre)) {
            return false;
        }
        Chambre other = (Chambre) object;
        if ((this.idChambre == null && other.idChambre != null) || (this.idChambre != null && !this.idChambre.equals(other.idChambre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hebooking.modele.Chambre[ id=" + idChambre + " ]";
    }
    
}
