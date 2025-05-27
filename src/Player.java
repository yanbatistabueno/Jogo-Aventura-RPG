import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Player extends Entidade {
    private List<Item> playerInventory = new ArrayList<>();
    public Player(String nome, int maxVida, int forca, int inteli, int agi) { //-(Método Construtor)
        super(nome, maxVida, forca, inteli, agi);
    }

    public void getItem(Item itemReceived) {
//        // Verifica se já existe no inventário
        if(getBoolItemByName(itemReceived.getNome())){
            if(itemReceived instanceof UsableItem){
                UsableItem usable = (UsableItem) itemReceived;
                usable.setQuantitade(usable.getQuantidade() + 1);
                System.out.println("Você pegou o item: " + usable.getNome() + usable.getQuantidade());
            }else{
                System.out.println("Já possui esse item");
            }
        }else{
            System.out.println("Você pegou o item: " + itemReceived.getNome());
            if(itemReceived instanceof UsableItem){
                UsableItem usable = (UsableItem) itemReceived;
                usable.setQuantitade(usable.getQuantidade() + 1);
            }
            playerInventory.add(itemReceived);
        }
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
    } //-(Sobrecarga)

    public void useItem(UsableItem item) {
        if (item.use()) {
            if (item instanceof healItem) {
                healItem heal = (healItem) item;
                this.setVida(heal.getHealAmmount());
            }
        }
    } //-(Sobrecarga)

    public void useItem(UsableItem item, Inimigo inimigo) {
        if (item.use()) {
            if (item instanceof DamageItem) {
                DamageItem dmg = (DamageItem) item;
                inimigo.tomarDano(dmg.getDano());
            }
        }
    } //-(Sobrecarga)


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
    public void equipWeapon(Weapon weapon){
        super.equipWeapon(weapon);
        setAttacks(weapon.getAtaqueDaArma());
    }

    @Override //-(Sobrescrita)
    public void morrer() {
        System.out.println("Você morreu.");
    }
}
