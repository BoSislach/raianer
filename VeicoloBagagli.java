import java.util.concurrent.Semaphore;
// classe del veicolo dei bagagli 
// utilizza un solo semaforo, ha due metodi, in questo caso a differenza della pista abbiamo implementato sia quello dello scarico che del carico dei bagagli 
// usiamo lo sleep in entrambe le funzione per simulare il "tempo" che ci impiegherebbero
public class VeicoloBagagli {
    private static final Semaphore semaforo = new Semaphore(1);
    // codice per caricare
    public static void caricaBagagli(Aereo aereo) {
        try {
            semaforo.acquire(); 
            System.out.println("Caricamento bagagli per " + aereo.getCodiceVolo());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Errore carico bagagli: " + e.getMessage());
        } finally {
            semaforo.release();
        }
    }
    // codice per scaricare

    public static void scaricaBagagli(Aereo aereo) {
        try {
            semaforo.acquire(); // P
            System.out.println("Scaricamento bagagli per " + aereo.getCodiceVolo());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Errore scarico bagagli: " + e.getMessage());
        } finally {
            semaforo.release(); // V
        }
    }
    // sono molto  simili i due metodi con la sola differenza delle stringhe , entrambi usano le primitive dei semafori 
}
