public class Inimigo extends Entidade{
    private int dano;
    public Inimigo(String nome, int vida, int dano){
        super(nome, vida);
        this.dano = dano;
    }

    public int getDano(){
        return this.dano;
    }

    @Override
    public void morrer(){
        System.out.println(this.getNome() + " morreu.");
    }
}
