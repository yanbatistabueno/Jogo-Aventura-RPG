public class Attack {
    final private String nome; //-(Encapsulamento, final)
    final private int dano; //-(Encapsulamento, final)
    private int custo;

    public Attack(String nome, int dano){//-(Método Construtor)
        this.nome = nome;
        this.dano = dano;
    }

    public String getNome(){
        return this.nome;
    } //-(Encapsulamento)

    public int getDano(){
        return this.dano;
    } //-(Encapsulamento)
}
