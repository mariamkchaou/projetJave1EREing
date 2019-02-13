package entities;

import java.util.List;

public class Fournisseur  extends  Personne{

    private Float horaireTravail;
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Float getHoraireTravail() {
        return horaireTravail;
    }

    public void setHoraireTravail(Float horaireTravail) {
        this.horaireTravail = horaireTravail;
    }

    public Fournisseur(String nom, String adresse, String téléphone,
                       String email, String cin, Float horaireTravail,Integer code) {
        super(nom, adresse, téléphone, email, cin);
        this.horaireTravail = horaireTravail;
        this.code = code;
    }
}
