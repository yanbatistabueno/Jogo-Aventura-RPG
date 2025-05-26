public class Inimigo extends Entidade{
    private int dano;
    public Inimigo(String nome, int vida, int forca, int inteli, int agi){
        super(nome, vida, forca, inteli, agi);
    }

    public int getDano(){
        return this.dano;
    }

    @Override
    public void morrer(){
        System.out.println(this.getNome() + " morreu.");
    }
}
