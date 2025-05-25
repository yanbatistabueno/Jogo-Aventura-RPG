public class Item {
    private String nome;
    private int quantidade = 1;
    private String desc;

    private void setQuantitade(int quantidade){
        this.quantidade = quantidade;
    }

    public String getNome(String nome){
        return this.nome;
    }

    public boolean use(){
        if(this.quantidade > 0){
            System.out.println("Você usou o item: " + this.nome);
            setQuantitade(this.quantidade - 1);
            System.out.println("Quantidade restante: " + this.quantidade);
            return true;
        }
        System.out.println("Quantidade insuficiente.");
        return false;
    }


}
