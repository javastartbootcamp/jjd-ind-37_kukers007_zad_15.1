package pl.javastart.task;

import java.util.Comparator;

class PlayerComparator implements Comparator<Player> {
    private int sortBy;
    private int sortDirection;

    public PlayerComparator(int sortBy, int sortDirection) {
        this.sortBy = sortBy;
        this.sortDirection = sortDirection;
    }

    @Override
    public int compare(Player player1, Player player2) {
        int result = 0;
        switch (sortBy) {
            case 1 -> result = player1.getFirstName().compareTo(player2.getFirstName());
            case 2 -> //
                    result = player1.getLastName().compareTo(player2.getLastName());
            case 3 -> result = Integer.compare(player1.getScore(), player2.getScore());
            default -> System.out.println("Niepoprawny parametr");
        }
        if (sortDirection == 2) {
            result *= -1;
        }
        return result;
    }
}
