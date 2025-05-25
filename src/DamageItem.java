public class DamageItem extends Item {
    private int dano;
    public DamageItem(String nome, String desc, int dano){
        super(nome, desc);
        this.dano = dano;
    }

    public int getDano(){
        return this.dano;
    }
}
