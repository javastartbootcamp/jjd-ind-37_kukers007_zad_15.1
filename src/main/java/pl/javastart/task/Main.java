package pl.javastart.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TournamentStats tournamentStats = new TournamentStats();
        Scanner scanner = new Scanner(System.in);
        List<Player> players = new ArrayList<>();
        tournamentStats.run(scanner);
    }
}
