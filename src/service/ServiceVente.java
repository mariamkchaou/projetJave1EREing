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
     * @param deal
     * @param client
     * @param quantité
     * @param clients
     * @param deals
     * @return
     */
    public static Vente creeat(LocalDate dateAchat, Deal deal, Client client, Float quantité, List<Client> clients, List<Deal> deals) throws ExceptionDeal {

        if (ServiceClient.chercheByCIN(client.getCin(), clients) == null) {
            throw new ClientNotFound();
        }
        if (!ServiceDeal.chercheDealEnCours(deals).contains(deal)) {
            throw new DealNotExiste();
        }
        Vente vente = new Vente(dateAchat, deal, client, quantité);
        System.out.println(" La vente a été réalisée avec succès, au prix : " + calculePrix(vente));

        return vente;
    }

    public static Float calculePrix(Vente vente) {
        return vente.getDeals().getPrixDeal() * vente.getQuantité();
    }

}
