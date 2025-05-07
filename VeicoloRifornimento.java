import java.util.concurrent.Semaphore;
// classe del veicolo del rifornimento 
// usa un semaforo per regolare se sta venendo usato o meno
// dispone di un solo metodo che è quello di rifornire l'aereo con un leggero delay, durante il qualche non può essere usato dagli altri aerei 
public class VeicoloRifornimento {
    private static final Semaphore semaforo = new Semaphore(1);

    public static void faiRifornimento(Aereo aereo) {
        try {
            semaforo.acquire();
            System.out.println("Rifornimento per " + aereo.getCodiceVolo());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Errore rifornimento: " + e.getMessage());
        } finally {
            semaforo.release();
        }
    }
}

