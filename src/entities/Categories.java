package entities;

public class Categories {
    private Long id;
    private String categorie;

    public Categories(Long id, String categorie) {
        this.id = id;
        this.categorie = categorie;
    }

    public Categories() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCcategorie() {
        return categorie;
    }

    public void setCateorie(String cateorie) {
        this.categorie = cateorie;
    }
}
