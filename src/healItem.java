public final class healItem extends UsableItem { //-(Classe final)
    final private int healAmmount; //-(Encapsulamento, final)
    public healItem(String itemNome, String itemDesc, int healAmmount){ //-(Método Construtor)
        super(itemNome, itemDesc);
        this.healAmmount = healAmmount;
    }

    public int getHealAmmount(){
        return  this.healAmmount;
    }
}
