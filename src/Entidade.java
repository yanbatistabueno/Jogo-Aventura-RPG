public abstract class Entidade {
    private int vida;
    private String nome;

    public Entidade(String nome, int vida){
        this.nome = nome;
        this.vida = vida;
    }

    public String getNome(){
        return this.nome;
    }
    public int getVida(){
        return this.vida;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public void tomarDano(int dano){
        this.vida -= dano;
        System.out.println(getNome() + " tomou: " + dano + " de dano.");
        if(this.vida <= 0){
            morrer();
        }
    }

    public abstract void morrer();
}
