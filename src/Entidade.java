import java.util.ArrayList;
import java.util.List;
public abstract class Entidade { //-(Classe Abstrata)
    private int vida; //-(Encapsulamento)
    private int maxVida; //-(Encapsulamento)

    private int forca; //-(Encapsulamento)

    private int inteli; //-(Encapsulamento)

    private int stamina; //-(Encapsulamento)

    private int agi; //-(Encapsulamento)

    Attack soco = new Attack("Soco", 1);

    private List<Attack> attacksList = new ArrayList<>(); //-(Encapsulamento)

    final private String nome; //-(Encapsulamento, final)

    private Weapon currentWeapon; //-(Encapsulamento)

    public Entidade(String nome, int maxVida, int forca, int inteli, int agi){
        this.nome = nome;
        this.maxVida = maxVida;
        this.vida = maxVida;
        this.forca = forca;
        this.inteli = inteli;
        this.agi = agi;
        attacksList.add(soco);
    } //-(Método Construtor)

    public String getNome(){
        return this.nome;
    } //-(Herança, Encapsulamento)
    public int getVida(){
        return this.vida;
    } //-(Herança, Encapsulamento)

    public int getAgi(){
        return this.agi;
    } //-(Herança, Encapsulamento)

    public int getForca(){ //-(Herança, Encapsulamento)
        return this.forca;
    }
    public void setVida(int vida){
        if(this.vida + vida > this.maxVida){
            this.vida = maxVida;
            System.out.println(this.nome + " curou toda a vida.");
        }else{
            this.vida = this.vida + vida;
            System.out.println(this.nome + " curou: " + this.vida + " de vida.");
        }
        System.out.println("Vida total de " + this.nome + ": " + this.vida);
    } //-(Herança)
    public void tomarDano(int dano){
        this.vida -= dano;
        System.out.println(getNome() + " tomou: " + dano + " de dano.");
        if(this.vida <= 0){
            morrer();
        }else{
            System.out.println("Vida de " + getNome() + ": " + this.vida);
        }
    } //-(Herança)

    public void setAttacks(Attack[] attacks){
        for (Attack attack : attacks){
            attacksList.add(attack);
        }
    } //-(Herança)

    public void setAttacks(Attack attack){
        attacksList.add(attack);
    } //-(Herança)

    public void useAttack(Entidade entidade, Entidade alvo, Attack attack){ //-(Herança)
        int dano = (attack.getDano() + entidade.getForca()) / 2;
        System.out.println(entidade.getNome() + " usou " + attack.getNome());
        alvo.tomarDano(dano);
    }
    public List<Attack> getAttacksList() {
        return attacksList;
    } //-(Herança, Encapsulamento)

    public void equipWeapon(Weapon weapon){
        if(currentWeapon == null || !weapon.getNome().equals(weapon.getNome())){
            currentWeapon = weapon;
            System.out.println("Você equipou a arma: " + weapon.getNome());
            this.forca = this.forca + weapon.getDano();
        }else{
            this.forca = this.forca - currentWeapon.getDano();
            currentWeapon = null;
            System.out.println("Você desequipou a arma");
        }
    }

    public int getInteli(){
        return this.inteli;
    }

    public abstract void morrer(); //-(Método Abstrato)
}
