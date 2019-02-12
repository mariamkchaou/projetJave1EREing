package service;

import entities.*;
import error.exception.ChampsObligatoireExceptionn;
import error.exception.ClientNotHaveDeal;
import error.exception.DealNotVend;
import error.exception.ExceptionDeal;
import responce.ListClientByDeal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ServiceDeal {

    public static LocalDate currentDate = LocalDate.now();

    public static Deal creat(String description, String nom, Float prixInitial, Float prixDeal,
                             LocalDate dateDébut, LocalDate dateFin, Fournisseur localisation, Categories categories) throws ExceptionDeal {
        if (nom == null || description == null || prixInitial == null || prixDeal == null || dateDébut == null || dateFin == null
                || localisation == null || categories == null) {
            throw new ChampsObligatoireExceptionn();
        }

        Deal deal = new Deal(description, prixInitial, prixDeal, dateDébut,
                dateFin, localisation, categories, null, nom);
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

            if (currentDate.isBefore(deals.get(i).getDateDébut()) && currentDate.isAfter(deals.get(i).getDateFin()) &&
                    deals.get(i).getCategories().equals(categorie)) {
                dealList.add(deals.get(i));
            }
            i++;

        }

        return dealList;
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
    private static List<Client> chercheClientByDeal(Deal deal) throws ExceptionDeal {

        List<Vente> ventes = deal.getVentes();
        List<Client> clients = new ArrayList<>();
        if (ventes != null) {
            int i = 0;
            while (i < ventes.size()) {
                clients.add(ventes.get(i).getClient());
            }
            return clients;
        } else {
            throw new DealNotVend();
        }
    }

    /**
     * recherche tous la liste des clients par  deals
     *
     * @param deals
     * @return
     */
    private static List<ListClientByDeal> chercheListClientByDeal(List<Deal> deals) throws ExceptionDeal {

        List<ListClientByDeal> listClientByDeals = new ArrayList<>();
        ListClientByDeal listClientByDeal = new ListClientByDeal();
        int i = 0;
        while (i < deals.size()) {
            try {
                listClientByDeal.setClients(chercheClientByDeal(deals.get(i)));
                listClientByDeal.setNonDeal(deals.get(i).getNom());
                listClientByDeals.add(listClientByDeal);
            }catch(DealNotVend e){
                System.out.println();
            continue;
        }
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
     * affiche tous la liste des clients par  deals
     * @param deal
     */
    public static void afficheClientByDeal(Deal deal) throws ExceptionDeal {

        int i = 0;
        List<Client> clients = chercheClientByDeal(deal);
        while (i < clients.size()) {
            System.out.println(clients.get(i).toString());

        }


    }


}
