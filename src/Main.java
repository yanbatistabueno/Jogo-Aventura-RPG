public class Main {
    public static void main(String[] args) {

        GameLogic Game = new GameLogic();
        Player jogador = new Player("Yan");
        Inimigo inimigo = new Inimigo("Zorde", 5, 2);
//        Game.GameLoop();
        Game.BattleLoop();
    }
}