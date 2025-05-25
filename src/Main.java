public class Main {
    public static void main(String[] args) {

        GameLogic Game = new GameLogic();
        Player player = new Player("Yan");

//        Game.GameLoop();
        healItem pocao = new healItem("Poção de cura", "Uma poção de cura", 5);
        Item item = new Item("Chave", "Apenas uma chave comum");
        player.getItem(pocao);
        player.getItem(item);
        player.useItem(item);
    }
}