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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Classe Entity qui map avec la base de donnée et représente le Client. 
 * HEBOOKING_CLIENT est le nom de la table dans la base de données
 * @author Jean-vitus
 */
@Entity
@Table(name="HEBOOKING_CLIENT")
@XmlRootElement
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CLIENT_ID")
    private Long idClient;

    @Column(name="CLIENT_NOM")
    private String nomClient;
    
    @Column(name="CLIENT_PRENOM")
    private String prenomClient;
    
    @Column(name="CLIENT_MOT_DE_PASSE")
    private String motdepasse;
    
    @OneToMany(cascade=CascadeType.ALL,mappedBy="client")
    private Collection<Reservation> reservations;

    
    
    @XmlTransient
    @Transient
    public Collection<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Collection<Reservation> reservations) {
        this.reservations = reservations;
    }
    
    
    
    public Client(){
        super();
    }
    
    public Long getId() {
        return idClient;
    }

    public void setId(Long id) {
        this.idClient = id;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClient != null ? idClient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.idClient == null && other.idClient != null) || (this.idClient != null && !this.idClient.equals(other.idClient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.hebooking.modele.Client[ id=" + idClient + " ]";
    }
    
}
