import java.util.Objects;
import java.util.ArrayList;
public class Player extends Entidade {
    public Item[] playerInventory = new Item[20];
    public Player(String nome){
        super(nome, 10);
    }

    public void getItem(Item itemReceveid){
        System.out.println("Você pegou o item: " + itemReceveid.getNome());
        if(playerInventory.length > 0){
            for(Item item : playerInventory){
                if(item != null && Objects.equals(item.getNome(), itemReceveid.getNome())){
                    item.setQuantitade(item.getQuantidade() + 1);
                    return;
                }
            }
        }
        for (int i = 0; i < playerInventory.length; i++) {
            if (playerInventory[i] == null) {
                playerInventory[i] = itemReceveid;
                break;
            }
        }
//        playerInventory[0] = itemReceveid;
    }

    public void useItem(Item item){
        if(item.use()){
            if (item instanceof healItem) {
                healItem healItem = (healItem) item;
                this.setVida(healItem.getHealAmmount());
            }
        }
    }

    public void useItem(Item item, Inimigo inimigo){
        if(item.use()){
            if (item instanceof DamageItem) {
                DamageItem DamageItem = (DamageItem) item;
                inimigo.tomarDano(DamageItem.getDano());
            }
        }
    }

    public void getInventory(){
        for(int i=0; i<= playerInventory.length - 1; i++){
            if(playerInventory[i] != null){
                Item item = playerInventory[i];
                if(item.getQuantidade() > 0){
                    System.out.println("(" + i + ") " + item.getNome() + ": " + item.getQuantidade());
                }
            }
        }
    }

    private Item[] getDamageItems(){
        Item[] damageItemsList = new Item[20];
        for(int i=0; i<= playerInventory.length - 1; i++){
            if(playerInventory[i] != null){
                Item item = playerInventory[i];
                if(item.getQuantidade() > 0){
                    if(item instanceof DamageItem){
                        for (Item damageItems : damageItemsList){
                            if(damageItems == null){
                                damageItems = item;
                            }
                        }
                    }
                }
            }
        }
        return  damageItemsList;
    }

    public void renderDamageItems(){
        Item[] damageItems = getDamageItems();
        System.out.println("Damage items lenght: " + damageItems.length);
        if(damageItems.length == 0){
            System.out.println("Sem itens de dano no inventário");
        }else{
            for(Item item : damageItems){
                if(item != null){
                    System.out.println(item.getNome());
                }
            }
        }
    }

    @Override
    public void morrer(){
        System.out.println("Você morreu.");
        System.out.println("Deseja continuar?");
    }
}
