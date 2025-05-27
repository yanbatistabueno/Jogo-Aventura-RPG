public class Item {
    final private String nome; //-(Encapsulamento)
    final private String desc; //-(Encapsulamento)

    public Item(String nome, String desc){//-(Método Construtor)
        this.nome = nome;
        this.desc = desc;
    }

    public String getNome(){
        return this.nome;
    }

}
