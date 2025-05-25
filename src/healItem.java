public class healItem extends Item{
    private int healAmmount;
    public healItem(String itemNome, String itemDesc, int healAmmount){
        super(itemNome, itemDesc);
        this.healAmmount = healAmmount;
    }

    public int getHealAmmount(){
        return  this.healAmmount;
    }
}
