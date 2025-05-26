import java.util.ArrayList;
import java.util.List;
public abstract class Entidade {
    private int vida;
    private int maxVida;


    private int forca;

    private int inteli;

    private int stamina;

    private int agi;

    Attack soco = new Attack("Soco", 1);

    private List<Attack> attacksList = new ArrayList<>();

    private String nome;





    public Entidade(String nome, int maxVida, int forca, int inteli, int agi){
        this.nome = nome;
        this.maxVida = maxVida;
        this.vida = maxVida;
        this.forca = forca;
        this.inteli = inteli;
        this.agi = agi;
        attacksList.add(soco);
    }

    public String getNome(){
        return this.nome;
    }
    public int getVida(){
        return this.vida;
    }

    public int getAgi(){
        return this.agi;
    }

    public int getForca(){
        return this.forca;
    }

    public void setNome(String nome){
        this.nome = nome;
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
    }
    public void tomarDano(int dano){
        this.vida -= dano;
        System.out.println(getNome() + " tomou: " + dano + " de dano.");
        if(this.vida <= 0){
            morrer();
        }else{
            System.out.println("Vida de " + getNome() + ": " + this.vida);
        }
    }

    public void setAttacks(Attack[] attacks){
        for (Attack attack : attacks){
            attacksList.add(attack);
        }
    }

    public void setAttacks(Attack attack){
        attacksList.add(attack);
    }

    public void useAttack(Entidade entidade, Entidade alvo, Attack attack){
        int dano = (attack.getDano() + entidade.getForca()) / 2;
        System.out.println(entidade.getNome() + " usou " + attack.getNome());
        alvo.tomarDano(dano);
    }

    public List<Attack> getAttacksList() {
        return attacksList;
    }

    public abstract void morrer();
}
