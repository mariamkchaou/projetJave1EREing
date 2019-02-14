package entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Deal {

    private String description;
    private String nom;
    private String code;
    private float prixInitial;
    private float prixDeal;
    private LocalDate dateDébut, dateFin;
    private Fournisseur localisation;
    private Categories categories;
    private List<Vente> ventes;


    public Deal(String description, float prixInitial, float prixDeal, LocalDate dateDébut,
                LocalDate dateFin, Fournisseur localisation, Categories categories, List<Vente> ventes, String nom, String code) {
        this.description = description;
        this.prixInitial = prixInitial;
        this.prixDeal = prixDeal;
        this.dateDébut = dateDébut;
        this.dateFin = dateFin;
        this.localisation = localisation;
        this.categories = categories;
        this.ventes = ventes;
        this.code = code;
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Vente> getVentes() {
        return ventes;
    }

    public void setVentes(List<Vente> ventes) {
        this.ventes = ventes;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrixInitial() {
        return prixInitial;
    }

    public void setPrixInitial(float prixInitial) {
        this.prixInitial = prixInitial;
    }

    public float getPrixDeal() {
        return prixDeal;
    }

    public void setPrixDeal(float prixDeal) {
        this.prixDeal = prixDeal;
    }

    public LocalDate getDateDébut() {
        return dateDébut;
    }

    public void setDateDébut(LocalDate dateDébut) {
        this.dateDébut = dateDébut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Fournisseur getLocalisation() {
        return localisation;
    }

    public void setLocalisation(Fournisseur localisation) {
        this.localisation = localisation;
    }

    @Override
    public String toString() {
        return "Deal : " +
                " nom='" + nom + " \n" +
                " code='" + code + " \n" +
                " description='" + description + " \n" +
                " prixInitial=" + prixInitial + " \n" +
                " prixDeal=" + prixDeal + " \n" +
                " dateDébut=" + dateDébut + " \n" +
                " dateFin=" + dateFin + " \n" +
                " localisation=" + localisation .toString()+ " \n" +
                " categories=" + categories.toString() + " \n";
    }
}
