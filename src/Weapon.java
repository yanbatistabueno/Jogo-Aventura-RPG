public class Weapon extends DamageItem {
    private Attack ataqueDaArma;
    public Weapon(String nome, String desc, int dano, Attack ataque){ //-(Método Construtor)
        super(nome, desc, dano);
        this.ataqueDaArma = ataque;
    }

    public Attack getAtaqueDaArma(){
        return this.ataqueDaArma;
    }

}
