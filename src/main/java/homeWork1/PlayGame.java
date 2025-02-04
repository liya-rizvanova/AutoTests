package homeWork1;

/**
 * Класс для запуска симуляции
 */
public class PlayGame {
    public static void main(String[] args) {
        int switchWins = 0;
        int stayWins = 0;
        int totalGames = 1000;

        for (int i = 0; i < totalGames; i++) {
            Player playerStay = new Player("Игрок (без смены)", false);
            Player playerSwitch = new Player("Игрок (со сменой)", true);

            Game gameStay = new Game(playerStay);
            Game gameSwitch = new Game(playerSwitch);

            if (gameStay.play(0)) stayWins++;
            if (gameSwitch.play(0)) switchWins++;
        }

        System.out.println("Игрок НЕ меняет дверь: " + stayWins + " побед из " + totalGames);
        System.out.println("Игрок МЕНЯЕТ дверь: " + switchWins + " побед из " + totalGames);
    }
}
