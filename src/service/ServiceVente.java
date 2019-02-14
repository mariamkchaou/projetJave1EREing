package service;

import entities.Categories;
import entities.Client;
import entities.Deal;
import entities.Vente;
import error.exception.ClientNotFound;
import error.exception.DealNotExiste;
import error.exception.ExceptionDeal;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServiceVente {

    public static String fileUrl = "vente.txt";

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
     */
    public static Vente creat(LocalDate dateAchat, String codeDeal, String cin, Float quantité, List<Client> clients, Integer id, List<Deal> deals, List<Vente> ventes) throws ExceptionDeal, IOException {

        Client client = ServiceClient.chercheByCIN(cin, clients);
        if (client == null) {
            throw new ClientNotFound();
        }

        Deal deal = ServiceDeal.chercheDealEnCoursByCode(deals, codeDeal);

        if (deal == null) {
            throw new DealNotExiste();
        }
        Vente vente = new Vente(dateAchat, deal, client, quantité, id);
        ventes.add(vente);
        saveventesFile(ventes);
        /**
         * update Client
         */
        ServiceClient.AddVenteToClient(clients, vente, client);
        ServiceClient.saveClientFile(clients);
        /**
         * update Deal
         */
        ServiceDeal.addVenteToDeal(deals, vente, deal);
        ServiceDeal.saveDealFile(deals);
        System.out.println(" La vente a été réalisée avec succès, au prix : " + calculePrix(vente));

        return vente;

    }

    public static Float calculePrix(Vente vente) {
        return vente.getDeals().getPrixDeal() * vente.getQuantité();
    }

    /**
     * 0venteList.get(i).getId())+';'+ 1venteList.get(i).getDeals().getCode()+";"
     * + 2venteList.get(i).getClient().getCin()+";"
     * + 3 String.valueOf(venteList.get(i).getQuantité())+";"
     * + 4String.valueOf(venteList.get(i).getDateAchat()+";");
     * <p>
     * LocalDate dateAchat, String codeDeal, String cin,
     * Float quantité, List<Client> clients,Integer id, List<Deal> deals,List<Vente> ventes
     *
     * @return
     * @throws IOException
     */
    public static List<Vente> loadVentesFile(List<Client> clients, List<Deal> deals) throws IOException, ExceptionDeal {
        List<Vente> ventes = new ArrayList<Vente>();
        FileReader fileReader = new FileReader(fileUrl);
        BufferedReader ReadFileBuffer = new BufferedReader(fileReader);
        String line = ReadFileBuffer.readLine();
        while (line != null) {
            String[] attributs = line.split(";");
            Vente vente = creat(LocalDate.parse(attributs[4]), attributs[1], attributs[2], Float.parseFloat(attributs[3]), clients, Integer.valueOf(attributs[0]), deals, ventes);
            line = ReadFileBuffer.readLine();
            if (line != null)
                System.out.println(line);
        }
        return ventes;

    }

    /**
     * @param venteList
     * @throws IOException
     */
    public static void saveventesFile(List<Vente> venteList) throws IOException {
        FileWriter fileWriter = new FileWriter(fileUrl);
        BufferedWriter WriteFileBuffer = new BufferedWriter(fileWriter);
        int i = 0;
        while (i < venteList.size()) {
            String line = String.valueOf(venteList.get(i).getId()) + ';' + venteList.get(i).getDeals().getCode() + ";"
                    + venteList.get(i).getClient().getCin() + ";"
                    + String.valueOf(venteList.get(i).getQuantité()) + ";" + String.valueOf(venteList.get(i).getDateAchat() + ";");
            WriteFileBuffer.write(line);
            WriteFileBuffer.newLine();
            i++;
        }
        WriteFileBuffer.close();

    }

}
