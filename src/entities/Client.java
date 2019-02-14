package entities;

import java.util.List;

public class Client extends Personne {
    private List<Vente> ventes;

    public List<Vente> getVentes() {
        return ventes;
    }

    public void setVentes(List<Vente> ventes) {
        this.ventes = ventes;
    }


    public Client(String nom, String adresse, String téléphone, String email, String cin, List<Vente> ventes) {
        super(nom, adresse, téléphone, email, cin);
        this.ventes = ventes;
    }

    @Override
    public String toString() {
        return "Client: " + " \n" +
                "nom:" + getNom() + " \n" +
                "adresse: " + getAdresse() + " \n" +
                "téléphone:" + getTéléphone() + " \n" +
                "email:" + getEmail() + " \n" +
                "cin:" + getCin();


    }
}
