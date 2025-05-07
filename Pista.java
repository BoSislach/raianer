import java.util.concurrent.Semaphore;
// classe per la gestione della pista 
// utilizza solo un semaforo, non come nell'area sosta che serve una syncronized list perche in questo caso la usiamo in un contesto 
// è bloccabile
public class Pista {
    private final Semaphore semaforo = new Semaphore(1);
    // unica funzione che funge sia per il decollo sia per l'atterraggio in qiesto caso 
    // anche in questo caso è obbligatorio mettere un eccezione per gestire possibili errori 
    public void usa(Aereo aereo, String azione) {
        try {
            semaforo.acquire(); // P
            System.out.println(aereo.getCodiceVolo() + " " + azione + " su pista");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Errore pista: " + e.getMessage());
        } finally {
            semaforo.release(); // V
        }
    }
}
