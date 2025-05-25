import java.util.Objects;

public class Player extends Entidade {
    public Item[] playerInventory = new Item[20];
    public Player(String nome){
        super(nome, 10);
    }

    public void getItem(Item itemReceveid){
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

    @Override
    public void morrer(){
        System.out.println("Você morreu.");
        System.out.println("Deseja continuar?");
    }
}
