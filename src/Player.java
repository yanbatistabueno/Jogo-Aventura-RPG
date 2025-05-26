import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player extends Entidade {
    private List<Item> playerInventory = new ArrayList<>();

    public Player(String nome) {
        super(nome, 10);
    }

    public void getItem(Item itemReceived) {
        System.out.println("Você pegou o item: " + itemReceived.getNome());

        // Verifica se já existe no inventário
        for (Item item : playerInventory) {
            if (Objects.equals(item.getNome(), itemReceived.getNome())) {
                item.setQuantitade(item.getQuantidade() + 1);
                return;
            }
        }

        // Se não existe, adiciona ao inventário
        playerInventory.add(itemReceived);
    }

    /**
     * Usa um item de cura se for instancia de HealItem
     */
    public void useItem(Item item) {
        if (item.use()) {
            if (item instanceof healItem) {
                healItem heal = (healItem) item;
                this.setVida(heal.getHealAmmount());
            }
        }
    }

    public void useItem(Item item, Inimigo inimigo) {
        if (item.use()) {
            if (item instanceof DamageItem) {
                DamageItem dmg = (DamageItem) item;
                inimigo.tomarDano(dmg.getDano());
            }
        }
    }

    public void getInventory() {
        for (int i = 0; i < playerInventory.size(); i++) {
            Item item = playerInventory.get(i);
            if (item.getQuantidade() > 0) {
                System.out.println("(" + i + ") " + item.getNome() + ": " + item.getQuantidade());
            }
        }
    }

    public List<Item> getDamageItems() {
        List<Item> damageItemsList = new ArrayList<>();
        for (Item item : playerInventory) {
            if (item.getQuantidade() > 0 && item instanceof DamageItem) {
                damageItemsList.add(item);
            }
        }
        return damageItemsList;
    }

    public boolean getBoolItemByName(String itemName){
        for (Item item : playerInventory){
            if(item.getNome().equals(itemName)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void morrer() {
        System.out.println("Você morreu.");
    }
}
