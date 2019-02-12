package entities;

public class Personne {

    private String nom;
    private String adresse;
    private String téléphone;
    private String email;
    private String cin;


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTéléphone() {
        return téléphone;
    }

    public void setTéléphone(String téléphone) {
        this.téléphone = téléphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public Personne(String nom, String adresse, String téléphone, String email, String cin) {
        this.nom = nom;
        this.adresse = adresse;
        this.téléphone = téléphone;
        this.email = email;
        this.cin = cin;
    }
}
