import java.util.concurrent.Semaphore;
import java.util.*;
// classe per la gestione dell'area sosta tramite i semafori
public class AreaSosta {
    private final Semaphore semaforo = new Semaphore(3);
    // usiamo la syncrhonized lyst al fine di non creare potenziali problemi con la risorsa usata dai thread
    private final List<Aereo> aereiPresenti = Collections.synchronizedList(new ArrayList<>());
    //funzione per bloccare un posto ( tramite semaforo e primitiva P(acquire ))
    // entrambi questi lavorano con la lista syncronizzata per far si che venga stampato l'aereo che esce
    public void entra(Aereo aereo) {
        try {
            semaforo.acquire(); 
            aereiPresenti.add(aereo);
            System.out.println(aereo.getCodiceVolo() + " è entrato in area sosta");
        } catch (InterruptedException e) {
            System.out.println("Errore area sosta: " + e.getMessage());
        }
    }
    // funzione per sbloccare il posto ( tramite semaforo e la primitiva V(release))
    public void esci(Aereo aereo) {
        aereiPresenti.remove(aereo);
        semaforo.release(); 
        System.out.println(aereo.getCodiceVolo() + " è uscito dall'area sosta");
    }
}

