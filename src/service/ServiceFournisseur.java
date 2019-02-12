package service;

import entities.Fournisseur;
import error.exception.*;

public class ServiceFournisseur {

    public static Fournisseur creat(String nom, String adresse, String téléphone,
                                    String email, String cin, Float horaireTravail, Integer code) throws ExceptionDeal {
        if (nom == null || adresse == null || téléphone == null || email == null || cin == null || horaireTravail == null) {
            throw new ChampsObligatoireExceptionn();
        }
        try {

            if (!(cin.length() == 8)) {
                throw new CINException();
            }
            Integer.parseInt(cin);
        } catch (NumberFormatException nfe) {
            throw new CINException();
        }

        try {
            Integer.parseInt(téléphone);
        } catch (NumberFormatException nfe) {
            throw new NumeroTelException();
        }

        if (!email.contains("@")) {
            throw new EmailFormatException();
        }
        Fournisseur fournisseur = new Fournisseur(nom, adresse, téléphone, email, cin, horaireTravail, 123);
        return fournisseur;
        /**
         * todo
         */

    }

}
