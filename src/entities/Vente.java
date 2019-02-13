package entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Vente {

    private LocalDate dateAchat;
    private Deal deals;
    private Client client;
    private Float quantité;


    public Vente(LocalDate dateAchat, Deal deals, Client client, Float quantité) {
        this.dateAchat = dateAchat;
        this.deals = deals;
        this.client = client;
        this.quantité = quantité;
    }

    public Deal getDeals() {
        return deals;
    }

    public void setDeals(Deal deals) {
        this.deals = deals;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(LocalDate dateAchat) {
        this.dateAchat = dateAchat;
    }

    public Float getQuantité() {
        return quantité;
    }

    public void setQuantité(Float quantité) {
        this.quantité = quantité;
    }

    @Override
    public String toString() {
        return "Vente:" +
                "dateAchat=" + dateAchat +"\n"+
                ", deals=" + deals.toString() +"\n"+
                ", client=" + client.toString()+"\n"+
                ", quantité=" + quantité ;
    }
}
