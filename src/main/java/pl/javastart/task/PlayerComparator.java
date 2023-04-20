package pl.javastart.task;

import java.util.Comparator;

class PlayerComparator implements Comparator<Player> {
    private int sortBy;
    private int sortDirection;
    static final int SORT_BY_FIRST_NAME = 1;
    static final int SORT_BY_LAST_NAME = 2;
    static final int SORT_BY_SCORE = 3;
    static final int SORT_DESCEDING = 1;
    static final int SORT_ASCEDING = 2;

    public PlayerComparator(int sortBy, int sortDirection) {
        this.sortBy = sortBy;
        this.sortDirection = sortDirection;
    }

    @Override
    public int compare(Player player1, Player player2) {
        int result = 0;
        switch (sortBy) {
            case SORT_BY_FIRST_NAME -> result = player1.getFirstName().compareTo(player2.getFirstName());
            case SORT_BY_LAST_NAME -> result = player1.getLastName().compareTo(player2.getLastName());
            case SORT_BY_SCORE -> result = Integer.compare(player1.getScore(), player2.getScore());
            default -> System.out.println("Niepoprawny parametr");
        }
        if (sortDirection == SORT_ASCEDING) {
            result *= -SORT_DESCEDING;
        }
        return result;
    }
}
