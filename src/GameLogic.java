public class GameLogic {
    public String state = "start";
    private boolean endGame = false;
    GameDialog Dialog = new GameDialog();
    EnterAwareScanner scanner = new EnterAwareScanner(System.in);
    public void GameLoop(){
        Player Jogador = new Player("");
        do{
            while("start".equals(state)){
                StartMenu();
                state = "";
                int input = scanner.nextIntOrNull();
                if(input == 3){
                    System.out.println("Saindo do jogo...");
                    endGame = true;
                }
                if(input == 2){
                    state = "creditos";
                }
                if(input == 1){
                    state = "introduction";
                }
            }
            while("creditos".equals(state)){
                ShowCredits();
                state = "start";
            }
            while("introduction".equals(state)){
                Introduction();
                state = "";
            }

        }while (!endGame);
    }

    private void StartMenu(){
        String[] texts = {"Bem vindo ao Chinforinfola", "Selecione uma das opções abaixo: ", "(1)Jogar\n(2)Créditos\n(3)Sair do jogo"};
        Dialog.createDialog(texts);
    }
    private void ShowCredits(){
        String[] texts = {"Yan Batista Bueno de Oliveira / RA: R096AJ3\nGilmara"};
        Dialog.createDialog(texts);
    }

    private void Introduction(){
        String[] texts = {"No ano de 2137, o planeta Viridya havia finalmente encontrado equilíbrio.", "Após séculos de destruição ambiental no antigo planeta Terra, as nações agora haviam se unido para restaurar florestas, purificar os oceanos e abandonar combustíveis fósseis.", "Cidades verdes flutuavam sobre lagos cristalinos. O céu, antes um véu cinzento, agora era azul intenso. Por um tempo, a humanidade acreditou que havia vencido.", "Mas a paz não durou.", "Nas profundezas esquecidas dos oceanos e nos cantos ocultos das megacidades antigas, algo adormecido despertou.", "Era chamada de Névoa Cinza — uma entidade nascida dos séculos de poluição, ódio e descuido do antigo planeta.", "Não era apenas fumaça: era consciente. Alimentava-se de resíduos e da indiferença humana.", "E agora ela estava com fome novamente."};
        Dialog.createDialog(texts);
    }

    private void PlayerIntroduction(){
        String[] texts = {""};
    }

    private void PlayerBattleMenu(){
        
    }

    private void BattleLoop(Player player, Inimigo inimigo){
        int turno;
        do{

        }while(player.getVida() > 0 && inimigo.getVida() > 0);
    }
}
