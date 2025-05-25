public class GameLogic {
    public String state = "start";
    private String oldState;

    private Inimigo currentInimigo;
    private int poluicao = 25;
    private String playerName;
    private boolean endGame = false;
    GameDialog Dialog = new GameDialog();
    EnterAwareScanner scanner = new EnterAwareScanner(System.in);
    Player Jogador = new Player("");
    healItem pocaoComum = new healItem("Poção de vida comum", "Uma poção que restaura a saúde de forma levemente eficaz", 5);
    public void GameLoop(){

        do{
            while("player inventory".equals(state)){
                showPlayerInventory();
                break;
            }

            while("player damage items".equals(state)){
                showPlayeDamageItems();
                break;
            }

            while("show planet status".equals(state)){
                showPlanetStatus();
                break;
            }

            while("start".equals(state)){
                StartMenu();
                state = "";
                int input = scanner.nextIntOrDefault(-1);
                if(input == -1){
                    System.out.println("Ação inválida");
                    state = "start";
                    break;
                }
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
                state = "player introduction";
            }
            while("player introduction".equals(state)){
                PlayerIntroduction();
                String input = scanner.nextLine();
                playerName = input;
                System.out.println("Seu nome é realmente " + playerName + " ?");
                System.out.println("(0) Sim");
                System.out.println(("(1) Não"));
                int choice = scanner.nextIntOrDefault(-1);
                if(choice == 1){
                    state = "player introduction";
                }
                if(choice == 0){
                    Jogador.setNome(playerName);
                    state = "player greetings";
                }
            }
            while("player greetings".equals(state)){
                PlayerGreetings();
                state = "before leaving";
            }
            while("before leaving".equals(state)){
                BeforeLeaving();
                System.out.println("(0)Sim");
                System.out.println("(1)Não, prosseguir direto com a missão.");
                int choice = scanner.nextIntOrDefault(-1);
                if(choice == 0){
                    state = "enter bedroom";
                }
                if(choice == 1){
                    state = "leaving the planet";
                }
                if(choice == -1){
                    System.out.println("Escolha uma opção válida");
                    state = "before leaving";
                }
            }
            while("enter bedroom".equals(state)){
                EnterBedroom();
                Jogador.getItem(pocaoComum);
                state = "leaving the planet";
            }
            while("leaving the planet".equals(state)){
                LeavingThePlanet();
                state = "entering the planet";
            }
            while("entering the planet".equals(state)){
                enteringThePlanet();
                String[] choices = {"(3) Prosseguir"};
                oldState = "entering the planet";
                int playerChoice = showPlayerInGameOptions(choices);
                if(playerChoice == -1){
                    System.out.println("Ação inválida.");
                    state = "entering the planet";
                }
                if(playerChoice == 3){
                    state = "encounter guard 1";
                }
            }
            while("encounter guard 1".equals(state)){
                encounterGuard1();
                String[] choices = {"(3) Mentir", "(4) Fugir", "(5) Atacar guarda"};
                oldState = "encounter guard 1";
                int playerChoice = showPlayerInGameOptions(choices);
                if(playerChoice == -1){
                    System.out.println("Ação inválida.");
                    state = "encounter guard 1";
                }
                if(playerChoice > 5 || playerChoice < 0){
                    currentInimigo = new Inimigo("Guarda", 2);
                    System.out.println("Ação inválida.");
                    state = "encounter guard 1";
                }
                if(playerChoice == 5){
                    state = "player damage items";

                }
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
        String[] texts = {"Vamos começar conhecendo você.", "Me diga seu nome:"};
        Dialog.createDialog(texts);
    }

    private void PlayerGreetings(){
        String playerGreet = "Bem vindo " + Jogador.getNome();
        String[] texts = {playerGreet, "Você agora é o mais novo membro dos Guardiões Verdes.", "Sua primeira missão é averiguar a situação do planeta Viridya.", "Aparentemente há um surto letal de poluição lá.", "Sua missão é descobrir a causa dessa contaminação em massa e acabar com o problema.", "Falhar não é uma opção."};
        Dialog.createDialog(texts);
    }

    private void BeforeLeaving(){
        String[] texts = {"*Antes de sair você tem a opção de ir para o seu quarto, ver se você quer pegar alguma coisa com você.*", "*Você deseja vasculhar seu quarto antes de prosseguir?*"};
        Dialog.createDialog(texts);
    }


    private void EnterBedroom(){
        String[] texts = {"Você entra em seu quarto, e pega algumas coisas com você antes de partir."};
        Dialog.createDialog(texts);
    }

    private void LeavingThePlanet(){
        String[] texts = {"Você entra na nave e parte em direção ao planeta Viridya", "Você é informado que irá sozinho nessa missão, e que é para tomar cuidado, pois o planeta está sobre um regime ditatorial muito rigoroso."};
        Dialog.createDialog(texts);
    }

    private void enteringThePlanet(){
        String[] texts = {"Você acaba de aterriçar no planeta, e vê um cenário totalmente apocalíptico.", "Você olha para o seu pulso e lembra, que você está equipado com um aparelho que é capaz de medir a quantidade de poluição do planeta em que você está.", "Você se lembra também que, se o nível chegar a 100, os danos serão irreversíveis, logo sua missão irá ao fracasso."};
        Dialog.createDialog(texts);
    }

    private void encounterGuard1(){
        String[] texts = {"Você acaba andando alguns metros, porém, sua nave chamou muita atenção, e um guarda começa a se aproximar de você.", "-Guarda: Ei, quem é você? Você por algum acaso está envolvido nesse show todo?"};
        Dialog.createDialog(texts);
    }


    private void showPlanetStatus(){
        System.out.println("Status do planeta: " + this.poluicao);
        state = oldState;
    }

    private int showPlayerInGameOptions(String[] choices){
        System.out.println("Selecione uma opção");
        System.out.println("(0) Ver status de poluição do planeta\n(1) Acessar inventário\n");
        if(choices.length > 0){
            for(String choice : choices){
                System.out.println(choice);
            }
        }
        int choice = scanner.nextIntOrDefault(-1);
        if(choice == 0){
            state = "player inventory";
        }
        if(choice == 1){
            state = "show planet status";
        }
        return choice;
    }

    private void showPlayerInventory(){
        Jogador.getInventory();
        state = oldState;
    }

    private void showPlayeDamageItems(){
        Jogador.renderDamageItems();
    }

    private void PlayerBattleMenu(){
        
    }

    private void BattleLoop(Player player, Inimigo inimigo){
        int turno;
        do{

        }while(player.getVida() > 0 && inimigo.getVida() > 0);
    }
}
