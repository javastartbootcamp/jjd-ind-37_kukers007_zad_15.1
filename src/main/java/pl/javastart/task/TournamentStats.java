package pl.javastart.task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TournamentStats {

    void run(Scanner scanner) {
        List<Player> players = readPlayersData(scanner);
        if (!players.isEmpty()) {
            sortPlayers(scanner, players);
        } else {
            System.out.println("Brak danych do zapisania.");
        }
        savePlayersDataToFile(players);
    }

    private List<Player> readPlayersData(Scanner scanner) {
        List<Player> players = new ArrayList<>();
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
        return players;
    }

    private void sortPlayers(Scanner scanner, List<Player> players) {
        System.out.printf("Po jakim parametrze posortować? (%d - imię, %d - nazwisko, %d - wynik)\n",
                PlayerComparator.SORT_BY_FIRST_NAME, PlayerComparator.SORT_BY_LAST_NAME, PlayerComparator.SORT_BY_SCORE);
        int sortBy = scanner.nextInt();
        scanner.nextLine();
        System.out.printf("Wybierz kierunek sortowania (%d - rosnąco, %d - malejąco)\n",
                PlayerComparator.SORT_DESCEDING, PlayerComparator.SORT_ASCEDING);
        int sortDirection = scanner.nextInt();
        scanner.nextLine();
        PlayerComparator comparator = new PlayerComparator(sortBy, sortDirection);
        players.sort(comparator);
    }

    private void savePlayersDataToFile(List<Player> players) {
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