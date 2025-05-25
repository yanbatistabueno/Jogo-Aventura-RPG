public class Item {
    private String nome;
    private int quantidade = 1;
    private String desc;

    public Item(String itemNome, String itemDesc){
        this.nome = itemNome;
        this.desc = itemDesc;
    }

    public void setQuantitade(int quantidade){
        this.quantidade = quantidade;
    }

    public boolean verifyQuantidade(){
        if(this.quantidade > 0){
            System.out.println("Você usou o item: " + this.nome);
            setQuantitade(this.quantidade - 1);
            System.out.println("Quantidade restante: " + this.quantidade);
            return true;
        }
        System.out.println("Quantidade insuficiente.");
        return false;
    }
    public  int getQuantidade(){
        return this.quantidade;
    }
    public String getNome(){
        return this.nome;
    }

    public boolean use(){
        if(verifyQuantidade()){
            return true;
        }
        return false;
    }



}
