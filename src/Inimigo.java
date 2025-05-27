public class Inimigo extends Entidade{
    public Inimigo(String nome, int vida, int forca, int inteli, int agi){
        super(nome, vida, forca, inteli, agi);
    }


    @Override
    public void morrer(){
        System.out.println(this.getNome() + " morreu.");
    }
}
