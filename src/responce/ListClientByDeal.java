package responce;

import entities.Client;

import java.util.List;

public class ListClientByDeal {
    String nonDeal;
    List<Client> clients;

    public ListClientByDeal(String nonDeal, List<Client> clients) {
        this.nonDeal = nonDeal;
        this.clients = clients;

    }
    public ListClientByDeal( ) {

    }

    public String getNonDeal() {
        return nonDeal;
    }

    public void setNonDeal(String nonDeal) {
        this.nonDeal = nonDeal;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        String affiche= "ListClientByDeal{" +
                "Deal='" + nonDeal +"\n" ;
        int i =0;
        if(clients!=null){
        while (i < clients.size()) {
            affiche+=   clients.get(i).toString()+"\n" ;

        }}
        return  affiche;
    }
}
