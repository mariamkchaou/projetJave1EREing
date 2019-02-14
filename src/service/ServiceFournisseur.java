package service;

import entities.Fournisseur;
import error.exception.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceFournisseur {
    static String fileUrl = "fournisseur.txt";

    public static Fournisseur creat(String nom, String adresse, String téléphone,
                                    String email, String cin, Float horaireTravail, Integer code, List<Fournisseur> fournisseurs) throws ExceptionDeal, IOException {
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
        Fournisseur fournisseur = new Fournisseur(nom, adresse, téléphone, email, cin, horaireTravail, code);
        fournisseurs.add(fournisseur);
        saveFournisseurFile(fournisseurs);
        return fournisseur;

    }

    /**
     * @param code
     * @param fournisseur
     * @return
     */
    public static Fournisseur getFournisseurCode(Integer code, List<Fournisseur> fournisseur) {
        int i = 0;
        while (i < fournisseur.size()) {

            if (fournisseur.get(i).getCode().equals(code)) {
                return fournisseur.get(i);
            }
            i++;
        }

        return null;
    }


    public static List<Fournisseur> loadFournisseurFile() throws IOException {
        ArrayList<Fournisseur> fournisseurs = new ArrayList<Fournisseur>();
        FileReader fileReader = new FileReader(fileUrl);
        BufferedReader ReadFileBuffer = new BufferedReader(fileReader);
        String line = ReadFileBuffer.readLine();
        while (line != null) {
            String[] attributs = line.split(";");
            try {
                Fournisseur fournisseur = creat(attributs[0], attributs[1], attributs[2], attributs[3], attributs[4],
                        Float.parseFloat(attributs[5]), Integer.parseInt(attributs[6]), fournisseurs);
            } catch (ExceptionDeal exceptionDeal) {
                exceptionDeal.printStackTrace();
            }
            line = ReadFileBuffer.readLine();

        }
        return fournisseurs;

    }

    /**
     * @param fournisseurs
     * @throws IOException
     */
    public static void saveFournisseurFile(List<Fournisseur> fournisseurs) throws IOException {
        FileWriter fileWriter = new FileWriter(fileUrl);
        BufferedWriter WriteFileBuffer = new BufferedWriter(fileWriter);
        int i = 0;
        while (i < fournisseurs.size()) {
            String line = fournisseurs.get(i).getNom() + ';' + fournisseurs.get(i).getAdresse() + ';' + fournisseurs.get(i).getTéléphone() + ';'
                    + fournisseurs.get(i).getEmail() + ';' + fournisseurs.get(i).getCin() + ';' + fournisseurs.get(i).getHoraireTravail() +
                    ';' + fournisseurs.get(i).getCode() + ";";
            WriteFileBuffer.write(line);
            WriteFileBuffer.newLine();
            i++;
        }
        WriteFileBuffer.close();

    }

}
