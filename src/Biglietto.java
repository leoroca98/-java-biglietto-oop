import java.math.BigDecimal;
import java.time.LocalDate;

public class Biglietto {
    private int km;
    private int eta;
    private LocalDate data;
    private boolean flessibile;
    private static final BigDecimal COSTO_CHILOMETRICO = new BigDecimal("0.21");
    private static final BigDecimal SCONTO_OVER_65 = new BigDecimal("0.40");
    private static final BigDecimal SCONTO_MINORENNI = new BigDecimal("0.20");
    private static final int DURATA_NORMALE = 30;
    private static final int DURATA_FLESSIBILE = 90;

    public Biglietto(int km, int eta, boolean flessibile) throws IllegalArgumentException {
        if (!isValidKm(km) || !isValidEta(eta)) {
            throw new IllegalArgumentException("Km e/o età non validi.");
        }
        this.km = km;
        this.eta = eta;
        this.data = LocalDate.now();
        this.flessibile = flessibile;
    }

    public Biglietto() {

    }

    public Biglietto(int km, int eta) {
    }

    private boolean isValidKm(int km) {
        return km > 0;
    }

    private boolean isValidEta(int eta) {
        return eta > 0;
    }

    private BigDecimal calcolaSconto() {
        if (eta >= 65) {
            return SCONTO_OVER_65;
        } else if (eta < 18) {
            return SCONTO_MINORENNI;
        } else {
            return BigDecimal.ZERO;
        }
    }

    private LocalDate calcolaDataScadenza() {
        int durata = flessibile ? DURATA_FLESSIBILE : DURATA_NORMALE;
        return data.plusDays(durata);
    }

    public BigDecimal calcolaPrezzo() {
        BigDecimal prezzoBase = COSTO_CHILOMETRICO.multiply(new BigDecimal(km));
        BigDecimal sconto = calcolaSconto();
        BigDecimal prezzoScontato = prezzoBase.subtract(prezzoBase.multiply(sconto));
        if (flessibile) {
            return prezzoScontato.multiply(new BigDecimal("1.10"));
        } else {
            return prezzoScontato;
        }
    }

    public LocalDate getDataScadenza() {
        return calcolaDataScadenza();
    }
}