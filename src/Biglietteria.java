import java.util.InputMismatchException;
import java.util.Scanner;

public class Biglietteria {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Inserisci il numero di km: ");
            int km = scanner.nextInt();

            System.out.print("Inserisci l'età del passeggero: ");
            int eta = scanner.nextInt();

            Biglietto biglietto = new Biglietto(km, eta);

            System.out.println("Il prezzo del biglietto è: " + biglietto.calcolaPrezzo() + " €");
        } catch (IllegalArgumentException e) {
            System.out.println("Errore: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Errore: inserimento non valido.");
        } finally {
            scanner.close();
        }
    }
}