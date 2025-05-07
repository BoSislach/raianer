public class Aereo extends Thread {
    private String codiceVolo;
    private String costruttore;
    private int maxPasseggeri;
    private int passeggeri;
    private int maxBagagli;
    private int bagagli;
    private boolean solaAndata;

    private Pista[] piste;
    private AreaSosta areaSosta;
    private Hangar hangar;

    public Aereo(String codiceVolo, String costruttore, int maxPasseggeri, int maxBagagli, boolean solaAndata,
                 Pista[] piste, AreaSosta areaSosta, Hangar hangar) {
        this.codiceVolo = codiceVolo;
        this.costruttore = costruttore;
        this.maxPasseggeri = maxPasseggeri;
        this.maxBagagli = maxBagagli;
        this.solaAndata = solaAndata;
        this.piste = piste;
        this.areaSosta = areaSosta;
        this.hangar = hangar;
    }

    public String getCodiceVolo() {
        return codiceVolo;
    }

    public void run() {
        hangar.esci(this);
        areaSosta.entra(this);

        VeicoloRifornimento.faiRifornimento(this);
        VeicoloBagagli.caricaBagagli(this);

        passeggeri = maxPasseggeri;
        bagagli = maxBagagli;

        areaSosta.esci(this);
        usaPista("Decollo");

        // Simula volo
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}

        usaPista("Atterraggio");

        areaSosta.entra(this);
        VeicoloBagagli.scaricaBagagli(this);

        if (solaAndata) {
            areaSosta.esci(this);
            hangar.entra(this);
        }
    }

    private void usaPista(String azione) {
        for (Pista pista : piste) {
            pista.usa(this, azione);
            return;
        }
    }
}

