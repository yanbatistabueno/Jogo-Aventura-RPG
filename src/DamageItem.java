public class DamageItem extends UsableItem {
    final private int dano; //-(Encapsulamento, final)
    public DamageItem(String nome, String desc, int dano){ //-(Método Construtor)
        super(nome, desc);
        this.dano = dano;
    }

    public int getDano(){
        return this.dano;
    } //-(Encapsulamento)
}
