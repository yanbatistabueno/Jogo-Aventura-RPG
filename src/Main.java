public class Main {
    public static void main(String[] args) {

        GameLogic Game = new GameLogic();
        Player jogador = new Player("Yan", 5, 5, 5, 5);
        Inimigo inimigo = new Inimigo("Zorde", 5, 2, 2, 2);
        Game.GameLoop();
//        Game.BattleLoop(jogador, inimigo);


    }
}