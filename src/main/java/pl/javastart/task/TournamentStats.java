package pl.javastart.task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TournamentStats {
    private List<Player> players = new ArrayList<>();
    private int sortBy;
    private int sortDirection;

    void run(Scanner scanner) {
        readPlayersData(scanner);

        if (!players.isEmpty()) {
            sortPlayers(scanner);
        } else {
            System.out.println("Brak danych do zapisania.");
        }

        PlayerComparator playerComparator = new PlayerComparator(sortBy, sortDirection);
        players.sort(playerComparator);
        savePlayersDataToFile(players, "stats.csv");
    }

    private void readPlayersData(Scanner scanner) {
        System.out.println("Podaj wynik kolejnego gracza (lub stop):");
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("stop")) {
            String[] data = input.split(" ");
            if (data.length == 3) {
                String firstName = data[0];
                String lastName = data[1];
                int score = Integer.parseInt(data[2]);
                Player player = new Player(firstName, lastName, score);
                players.add(player);
            } else {
                System.out.println("Błędny format danych. Poprawny format: imię nazwisko wynik");
            }
            System.out.println("Podaj wynik kolejnego gracza (lub stop):");
            input = scanner.nextLine();
        }
    }

    private void sortPlayers(Scanner scanner) {
        System.out.println("Po jakim parametrze posortować? (1 - imię, 2 - nazwisko, 3 - wynik)");
        sortBy = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Wybierz kierunek sortowania (1 - rosnąco, 2 - malejąco)");
        sortDirection = scanner.nextInt();
        scanner.nextLine();
        scanner.close();
        PlayerComparator comparator = new PlayerComparator(sortBy, sortDirection);
        players.sort(comparator);
    }

    private void savePlayersDataToFile(List<Player> players, String fileName) {
        try (FileWriter writer = new FileWriter("stats.csv")) {
            for (Player player : players) {
                writer.write(player.getFirstName() + " " + player.getLastName() + ";" + player.getScore() + "\n");
            }
            System.out.println("Dane posortowano i zapisano do pliku stats.csv.");
        } catch (IOException e) {
            System.out.println("Błąd podczas zapisu danych do pliku.");
        }
    }
}