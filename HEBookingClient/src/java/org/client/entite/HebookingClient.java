/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.client.entite;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jean-vitus
 */
@Entity
@Table(name = "HEBOOKING_CLIENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HebookingClient.findAll", query = "SELECT h FROM HebookingClient h"),
    @NamedQuery(name = "HebookingClient.findByClientId", query = "SELECT h FROM HebookingClient h WHERE h.clientId = :clientId"),
    @NamedQuery(name = "HebookingClient.findByClientNom", query = "SELECT h FROM HebookingClient h WHERE h.clientNom = :clientNom"),
    @NamedQuery(name = "HebookingClient.findByClientMotDePasse", query = "SELECT h FROM HebookingClient h WHERE h.clientMotDePasse = :clientMotDePasse"),
    @NamedQuery(name = "HebookingClient.findByClientPrenom", query = "SELECT h FROM HebookingClient h WHERE h.clientPrenom = :clientPrenom")})
public class HebookingClient implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CLIENT_ID")
    private Long clientId;
    @Size(max = 255)
    @Column(name = "CLIENT_NOM")
    private String clientNom;
    @Size(max = 255)
    @Column(name = "CLIENT_MOT_DE_PASSE")
    private String clientMotDePasse;
    @Size(max = 255)
    @Column(name = "CLIENT_PRENOM")
    private String clientPrenom;
    @OneToMany(mappedBy = "clientId")
    private Collection<HebookingReservation> hebookingReservationCollection;

    public HebookingClient() {
    }

    public HebookingClient(Long clientId) {
        this.clientId = clientId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getClientNom() {
        return clientNom;
    }

    public void setClientNom(String clientNom) {
        this.clientNom = clientNom;
    }

    public String getClientMotDePasse() {
        return clientMotDePasse;
    }

    public void setClientMotDePasse(String clientMotDePasse) {
        this.clientMotDePasse = clientMotDePasse;
    }

    public String getClientPrenom() {
        return clientPrenom;
    }

    public void setClientPrenom(String clientPrenom) {
        this.clientPrenom = clientPrenom;
    }

    @XmlTransient
    public Collection<HebookingReservation> getHebookingReservationCollection() {
        return hebookingReservationCollection;
    }

    public void setHebookingReservationCollection(Collection<HebookingReservation> hebookingReservationCollection) {
        this.hebookingReservationCollection = hebookingReservationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clientId != null ? clientId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HebookingClient)) {
            return false;
        }
        HebookingClient other = (HebookingClient) object;
        if ((this.clientId == null && other.clientId != null) || (this.clientId != null && !this.clientId.equals(other.clientId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.client.entite.HebookingClient[ clientId=" + clientId + " ]";
    }
    
}
