public class UsableItem extends Item {

    private int quantidade = 0; //-(Encapsulamento)

    public UsableItem(String itemNome, String itemDesc){
        super(itemNome, itemDesc);
    } //-(Método Construtor)

    public void setQuantitade(int quantidade){
        this.quantidade = quantidade;
    } //-(Herança)

    public boolean verifyQuantidade(){
        if(this.quantidade > 0){
            System.out.println("Você usou o item: " + getNome());
            setQuantitade(this.quantidade - 1);
            System.out.println("Quantidade restante: " + this.quantidade);
            return true;
        }
        System.out.println("Quantidade insuficiente.");
        return false;
    } //-(Herança)
    public  int getQuantidade(){
        return this.quantidade;
    } //-(Herança)

    public boolean use(){
        if(verifyQuantidade()){
            return true;
        }
        return false;
    } //-(Herança)


}
