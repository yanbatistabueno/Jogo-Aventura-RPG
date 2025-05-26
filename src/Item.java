public class Item {
    private String nome;
    private String desc;

    public Item(String nome, String desc){
        this.nome = nome;
        this.desc = desc;
    }

    public String getNome(){
        return this.nome;
    }
}
