public class Weapon extends Item {
    private int dano;
    public Weapon(String nome, String desc, int dano){
        super(nome, desc);
        this.dano = dano;
    }
}
