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
import javax.persistence.JoinColumn;
import javax.persistence.Transient;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Classe Entity qui map avec la base de donnée et représente une réservation d'un client. 
 * HEBOOKING_RESERVATION est le nom de la table dans la base de données
 * @author Jean-vitus
 */
@Entity
@Table(name="HEBOOKING_RESERVATION")
@XmlRootElement
public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="RESERVATION_ID")
    private Long idReservation;

    @Column(name="RESERVATION_DEBUT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebut;
    
    @Column(name="RESERVATION_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFin;
    
    @Column(name="RESERVATION_NUMERO_CARTE_BlEU")
    private String numeroCarteBleu;

    
    @ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH)
    @JoinColumn(name="CLIENT_ID")
    protected Client client;
    
    @XmlTransient
    @Transient
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
    @ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH)
    @JoinColumn(name="CHAMBRE_ID")
    private Chambre chambre;
    
    @XmlTransient
    @Transient
    public Chambre getChambre() {
        return chambre;
    }

    public void setChambre(Chambre chambre) {
        this.chambre = chambre;
    }
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="EXCURSION_RESERVATION",
                joinColumns=@JoinColumn(name="RESERVATION_ID"),
                inverseJoinColumns=@JoinColumn(name="EXCURSION_ID"))
    private Collection<Excursion> excursions;

    @Transient
    @XmlTransient
    public Collection<Excursion> getExcursions() {
        return excursions;
    }

    public void setExcursions(Collection<Excursion> excursions) {
        this.excursions = excursions;
    }

    public Reservation() {
        super();
    }

   
    
    public Long getId() {
        return idReservation;
    }

    public void setId(Long id) {
        this.idReservation = id;
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

    public String getNumeroCarteBleu() {
        return numeroCarteBleu;
    }

    public void setNumeroCarteBleu(String numeroCarteBleu) {
        this.numeroCarteBleu = numeroCarteBleu;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReservation != null ? idReservation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.idReservation == null && other.idReservation != null) || (this.idReservation != null && !this.idReservation.equals(other.idReservation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hebooking.modele.Reservation[ id=" + idReservation + " ]";
    }
    
}
