import java.util.Random;

public class Aereo extends Thread {
    private String codiceVolo;
    private String costruttore;
    private int maxPasseggeri;
    private int passeggeri;
    private int pesoMaxBagagli;
    private int bagagli;
    private boolean solaAndata;

    private Pista[] piste;
    private AreaSosta areaSosta;
    private Hangar hangar;

    public Aereo(String codiceVolo, String costruttore, int maxPasseggeri, int pesoMaxBagagli, boolean solaAndata,
                 Pista[] piste, AreaSosta areaSosta, Hangar hangar) {
        this.codiceVolo = codiceVolo;
        this.costruttore = costruttore;
        this.maxPasseggeri = maxPasseggeri;
        this.pesoMaxBagagli = pesoMaxBagagli;
        this.solaAndata = solaAndata;
        this.piste = piste;
        this.areaSosta = areaSosta;
        this.hangar = hangar;
    }
    //getter per identificare l'aereo 
    public String getCodiceVolo() {
        return codiceVolo;
    }
    // il vero e proprio codice del thread, contiene tutte le azione e le condizioni che l'aereo fa 
    // è chiamato run perchè è la keyword per far eseguire al thread questo determinato codice quando lo chiamimao nel main 
    public void run() {
        hangar.esci(this);
        areaSosta.entra(this);

        VeicoloRifornimento.faiRifornimento(this);
        VeicoloBagagli.caricaBagagli(this);

        passeggeri = maxPasseggeri;
        bagagli = pesoMaxBagagli;

        areaSosta.esci(this);
        usaPista("Decollo");

        // bisogna per forza  utilizzare un eccezzione per catturare l'errore in caso avvega 
        try { Thread.sleep(2000); }
         catch (InterruptedException ignored) {}
        
        usaPista("Atterraggio");

        areaSosta.entra(this);
        VeicoloBagagli.scaricaBagagli(this);

        if (solaAndata) {
            areaSosta.esci(this);
            hangar.entra(this);
        }
    }
    // questo metodo lavora col semaforo della pista per consentire l'accesso a solamente 3 aerei in concomitanza 
    private void usaPista(String azione) {
        for (Pista pista : piste) {
            pista.usa(this, azione);
            return;
        }
    }
}

