package entities;

public class Categories {
    private Integer id;
    private String categorie;

    public Categories(Integer id, String categorie) {
        this.id = id;
        this.categorie = categorie;
    }

    public Categories() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCcategorie() {
        return categorie;
    }

    public void setCateorie(String cateorie) {
        this.categorie = cateorie;
    }
}
