public class Main {
    // classe main per gestire gli aerei 
    public static void main(String[] args) {
        //array di piste per averne 3 
        Pista[] piste = {new Pista(), new Pista(), new Pista()};
        AreaSosta areaSosta = new AreaSosta();
        Hangar hangar = new Hangar();
        // ciclo for per simulare 
        // il metodo start chiama autonomamente la funzione run ( in override) del thread in questo caso dell aereo 
        // assegnamo il booleano se ha o meno la l'andata e il ritorno tramite la i
        for (int i = 0; i < 5; i++) {
            Aereo a = new Aereo("FLT" + i, "boing", 180, 800, i % 2 == 0, piste, areaSosta, hangar);
            hangar.entra(a);
            a.start();
        }
    }
}

