import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player extends Entidade {
    private List<Item> playerInventory = new ArrayList<>();

    public Player(String nome, int maxVida, int forca, int inteli, int agi) {
        super(nome, maxVida, forca, inteli, agi);
    }

    public void getItem(Item itemReceived) {
        // Verifica se já existe no inventário
        for (Item item : playerInventory) {
            if (Objects.equals(item.getNome(), itemReceived.getNome())) {
                if(item instanceof UsableItem){ //Itens usáveis podem ser pegos mais de uma vez
                    UsableItem usable = (UsableItem) item;
                    usable.setQuantitade(usable.getQuantidade() + 1);
                }else{
                    System.out.println("Você já possuí esse item: " + item.getNome());
                    return;
                }
                return;
            }
        }
        System.out.println("Você pegou o item: " + itemReceived.getNome());
        // Se não existe, adiciona ao inventário
        playerInventory.add(itemReceived);
    }

    public void useItem(Item item, boolean useCase){
        if(getBoolItemByName(item.getNome())){ //Se o usuário tiver com o item no inventário
            String useText = "Você usou o item " + item.getNome();
            if(useCase){
                System.out.println(useText);
            }
            if(!useCase){
                System.out.println(useText + ", mas nada surtiu efeito.");
            }
        }
    }

    public void useItem(UsableItem item) {
        if (item.use()) {
            if (item instanceof healItem) {
                healItem heal = (healItem) item;
                this.setVida(heal.getHealAmmount());
            }
        }
    }

    public void useItem(UsableItem item, Inimigo inimigo) {
        if (item.use()) {
            if (item instanceof DamageItem) {
                DamageItem dmg = (DamageItem) item;
                inimigo.tomarDano(dmg.getDano());
            }
        }
    }


    public List<Item> getInventory() {
        return this.playerInventory;
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
