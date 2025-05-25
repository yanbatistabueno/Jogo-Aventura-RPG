public class Player extends Entidade {

    public Player(String nome){
        super(nome, 10);
    }

    @Override
    public void morrer(){
        System.out.println("Você morreu.");
        System.out.println("Deseja continuar?");
    }
}
