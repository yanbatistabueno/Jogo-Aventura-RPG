public class Attack {
    private String nome;
    private int dano;
    private int custo;

    public Attack(String nome, int dano){
        this.nome = nome;
        this.dano = dano;
    }

    public String getNome(){
        return this.nome;
    }

    public int getDano(){
        return this.dano;
    }
}
