import java.util.concurrent.Semaphore;
import java.util.*;
// classe per la gestione dell'hangar
// utilizza un semaforo e la sinchronized list per lo stesso motivo dell'area sosta, ovvero restituire il nome dell'aereo prevendendo conflitti 
// due metodi che printano una stringa, qundo entra viene chiamata la primitiva p per bloccare di un posto , quando esce viene chiamato v oer rilasciare
public class Hangar {
    private final Semaphore semaforo = new Semaphore(5);
    private final List<Aereo> aereiPresenti = Collections.synchronizedList(new ArrayList<>());
// il try c'è solo nell acquire perche sbloccare la risorsa non può portare a conflitti 
    public void entra(Aereo aereo) {
        try {
            semaforo.acquire();
            aereiPresenti.add(aereo);
            System.out.println(aereo.getCodiceVolo() + " è entrato nell hangar");
        } catch (InterruptedException e) {
            System.out.println("Errore hangar: " + e.getMessage());
        }
    }

    public void esci(Aereo aereo) {
        aereiPresenti.remove(aereo);
        semaforo.release();
        System.out.println(aereo.getCodiceVolo() + " è uscito dall'hangar");
    }
}

