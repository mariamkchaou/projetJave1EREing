package service;

import entities.*;
import error.exception.ChampsObligatoireExceptionn;
import error.exception.ClientNotHaveDeal;
import error.exception.DealNotVend;
import error.exception.ExceptionDeal;
import responce.ListClientByDeal;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ServiceDeal {

    public static LocalDate currentDate = LocalDate.now();
    public static String fileUrl = "deal.txt";

    public static Deal creat(String description, String nom, Float prixInitial, Float prixDeal,
                             LocalDate dateDébut, LocalDate dateFin, Integer fournisseurCode, Integer idCategories, String code, List<Categories> categoriesList, List<Fournisseur> fournisseurs, List<Deal> deals) throws ExceptionDeal, IOException {
        if (nom == null || description == null || prixInitial == null || prixDeal == null || dateDébut == null || dateFin == null || code == null
                || fournisseurCode == null || idCategories == null) {
            throw new ChampsObligatoireExceptionn();
        }
        List<Vente> ventes = new ArrayList<>();
        Categories categories = ServiseCategorie.getCategorieById(idCategories, categoriesList);
        Fournisseur localisation = ServiceFournisseur.getFournisseurCode(fournisseurCode, fournisseurs);
        Deal deal = new Deal(description, prixInitial, prixDeal, dateDébut,
                dateFin, localisation, categories, ventes, nom, code);
        deals.add(deal);
        saveDealFile(deals);
        return deal;


    }

    public static void affiche(List<Deal> deals) {
        int i = 0;
        System.out.println("*******************\n");
        while (i < deals.size()) {
            System.out.println(deals.get(i).toString());
            System.out.println("*******************\n");
            i++;
        }

    }

    /**
     * Rechercher les deals en cours par catégorie
     *
     * @return
     */
    public static List<Deal> chercheDealEnCoursByCtagorie(List<Deal> deals, String categorie) {

        List<Deal> dealList = new ArrayList<>();
        int i = 0;
        while (i < deals.size()) {

            if (((currentDate.isBefore(deals.get(i).getDateDébut()) &&
                    currentDate.isAfter(deals.get(i).getDateFin())) ||
                    ( currentDate.isEqual(deals.get(i).getDateDébut())
                            || currentDate.isEqual(deals.get(i).getDateFin())) )&&
                    deals.get(i).getCategories().getCcategorie().equals(categorie)) {
                dealList.add(deals.get(i));
            }
            i++;

        }

        return dealList;
    }

    /**
     * Rechercher les deals en cours
     *
     * @return
     */
    public static Deal chercheDealEnCoursByCode(List<Deal> deals, String code) {

        List<Deal> dealList = new ArrayList<>();
        int i = 0;
        while (i < deals.size()) {

            if (((currentDate.isBefore(deals.get(i).getDateDébut()) && currentDate.isAfter(deals.get(i).getDateFin())) || currentDate.isEqual(deals.get(i).getDateDébut()) || currentDate.isEqual(deals.get(i).getDateFin())) &&
                    deals.get(i).getCode().equals(code)) {
                return deals.get(i);
            }
            i++;

        }

        return null;
    }

    /**
     * cherche  la liste des deals d’un client par ordre chronologique.
     */
    private static List<Deal> chercheDealByClientOrderChrono(Client client) throws ExceptionDeal {
        if (client.getVentes() != null) {
            List<Vente> ventes = client.getVentes();
            int i = 0;
            List<Deal> dealList = new ArrayList<>();
            while (i < ventes.size()) {
                dealList.add(ventes.get(i).getDeals());
            }
            dealList.sort(Comparator.comparing(a -> a.getDateDébut()));
            return dealList;
        } else {
            throw new ClientNotHaveDeal();
        }
    }

    /**
     * recherche  la liste des clients par un  deal
     */
    private static List<Client> chercheClientByDeal(Deal deal) {

        List<Vente> ventes = deal.getVentes();
        List<Client> clients = new ArrayList<>();
        if (!ventes.isEmpty()) {
            int i = 0;
            while (i < ventes.size()) {
                if(ventes.get(i).getClient()!=null){
                  clients.add(ventes.get(i).getClient());
                }
                i++;
            }

        }
        return clients;
    }

    /**
     * recherche tous la liste des clients par  deals
     *
     * @param deals
     * @return
     */
    public static List<ListClientByDeal> chercheListClientByDeal(List<Deal> deals) throws ExceptionDeal {

        List<ListClientByDeal> listClientByDeals = new ArrayList<>();
        ListClientByDeal listClientByDeal = new ListClientByDeal();
        int i = 0;

        while (i < deals.size()) {
                listClientByDeal.setClients(chercheClientByDeal(deals.get(i)));
                listClientByDeal.setNonDeal(deals.get(i).getNom());
                listClientByDeals.add(listClientByDeal);
                i++;
            }


        return listClientByDeals;
    }


    /**
     * affiche tous la liste des clients par  deals
     *
     * @param deals
     */
    public static void afficheListClientByDeal(List<Deal> deals) throws ExceptionDeal {
        List<ListClientByDeal> listClientByDeals = chercheListClientByDeal(deals);
        int i = 0;
        while (i < listClientByDeals.size()) {
            System.out.println(listClientByDeals.get(i).toString());
            i++;

        }
    }

    /**
     *
     * @param deals
     * @throws ExceptionDeal
     */
    public static void afficheListDealOrderChrono(List<Deal> deals ) throws ExceptionDeal {
        List<Deal> dealList = deals;
        dealList.sort(Comparator.comparing(a -> a.getDateDébut()));
        int i = 0;

        while (i < deals.size()) {
            System.out.println(deals.get(i).toString());
            i++;

        }
    }

    public static void addVenteToDeal(List<Deal> deals, Vente vente, Deal deal) {
        int i = 0;
        while (i < deals.size()) {
            if (deals.get(i).getCode().equals(deal.getCode())) {
                deals.get(i).getVentes().add(vente);
                break;
            }
            i++;

        }
    }

    /**
     * affiche tous la liste des clients par  deals
     *
     * @param deal
     */
    public static void afficheClientByDeal(Deal deal) throws ExceptionDeal {

        int i = 0;
        List<Client> clients = chercheClientByDeal(deal);
        while (i < clients.size()) {
            System.out.println(clients.get(i).toString());

        }


    }

    /**
     * @param deals
     * @param categorie
     * @throws ExceptionDeal
     */
    public static void afficheListDealByCategorie(List<Deal> deals, String categorie) throws ExceptionDeal {
        List<Deal> deal = chercheDealEnCoursByCtagorie(deals, categorie);
        int i = 0;

        while (i < deal.size()) {
            System.out.println(deal.get(i).toString());
            i++;

        }
    }

    /**
     * @param categoriesList
     * @param fournisseurs
     * @return
     * @throws IOException
     */

    public static List<Deal> loadDealFile(List<Categories> categoriesList, List<Fournisseur> fournisseurs, List<Deal> deals) throws IOException, ExceptionDeal {
        FileReader fileReader = new FileReader(fileUrl);
        BufferedReader ReadFileBuffer = new BufferedReader(fileReader);
        String line = ReadFileBuffer.readLine();
        while (line != null) {
            String[] attributs = line.split(";");
            Deal deal = creat(attributs[0], attributs[1], Float.valueOf(attributs[2]), Float.valueOf(attributs[3])
                    , LocalDate.parse(attributs[4]), LocalDate.parse(attributs[5]), Integer.valueOf(attributs[6]), Integer.valueOf(attributs[7]), attributs[8], categoriesList, fournisseurs, deals);
            line = ReadFileBuffer.readLine();

        }
        return deals;

    }

    /**
     * @throws IOException
     */
    public static void saveDealFile(List<Deal> deals) throws IOException {
        FileWriter fileWriter = new FileWriter(fileUrl);
        BufferedWriter WriteFileBuffer = new BufferedWriter(fileWriter);
        int i = 0;
        while (i < deals.size()) {
            String line = String.valueOf(deals.get(i).getDescription()) + ';' + deals.get(i).getNom() +
                    ";" + deals.get(i).getPrixInitial() + ";" + deals.get(i).getPrixDeal() + ";" + deals.get(i).getDateDébut() + ";" + deals.get(i).getDateFin() + ";" + deals.get(i).getLocalisation().getCode() +
                    ";" + deals.get(i).getCategories().getId() + ";" + deals.get(i).getCode() + ";";
            WriteFileBuffer.write(line);
            WriteFileBuffer.newLine();
            i++;
        }
        WriteFileBuffer.close();

    }


}
