public class Inimigo extends Entidade{

    public Inimigo(String nome, int vida){
        super(nome, vida);
    }
    @Override
    public void morrer(){
        System.out.println(this.getNome() + " morreu.");
    }
}
