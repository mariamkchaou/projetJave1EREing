package service;

import entities.Client;
import entities.Deal;
import entities.Vente;
import error.exception.ClientNotFound;
import error.exception.DealNotExiste;
import error.exception.ExceptionDeal;

import java.time.LocalDate;
import java.util.List;

public class ServiceVente {
    /**
     * ventes d’un deal existant à un client existant, puis afficher le Total à payer
     *
     * @param dateAchat
     * @param codeDeal
     * @param cin
     * @param quantité
     * @param clients
     * @param deals
     * @return
     * @throws ExceptionDeal
     * @return
     */
    public static Vente creeat(LocalDate dateAchat, String codeDeal, String cin, Float quantité, List<Client> clients, List<Deal> deals) throws ExceptionDeal {

        Client client= ServiceClient.chercheByCIN(cin, clients);
        if (client == null) {
            throw new ClientNotFound();
        }

        Deal deal=ServiceDeal.chercheDealEnCoursByCode(deals,codeDeal);

        if (deal==null) {
            throw new DealNotExiste();
        }
        Vente vente = new Vente(dateAchat, deal, client, quantité);

        System.out.println(" La vente a été réalisée avec succès, au prix : " + calculePrix(vente));
        client.getVentes().add(vente);
        deal.getVentes().add(vente);
        return vente;

    }

    public static Float calculePrix(Vente vente) {
        return vente.getDeals().getPrixDeal() * vente.getQuantité();
    }

}
