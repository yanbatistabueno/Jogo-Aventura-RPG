import java.util.List;
public class GameLogic {
    public String state = "start";
    private String oldState;
    private int playerIntel;
    private int playerForca;
    private int playerAgil;
    private int playerMaxVida;
    private Inimigo currentInimigo;
    private int poluicao = 25;
    private String playerName;
    private boolean endGame = false;
    GameDialog Dialog = new GameDialog();
    EnterAwareScanner scanner = new EnterAwareScanner(System.in);
    Player Jogador = null;
    healItem pocaoComum = new healItem("Poção de vida comum", "Uma poção que restaura a saúde de forma levemente eficaz", 5);
    UsableItem identidade = new UsableItem("Identidade Falsa", "Uma identidade false usada em um planeta específico");
    UsableItem radioGuarda = new UsableItem("Rádio do Guarda", "Um rádio de um guarda que você acabou nocauteando. Talvez usar ele dê alguma informação extra");
    public void GameLoop(){

        do{
            while("player inventory".equals(state)){
                showPlayerInventory();
                break;
            }

            while("player damage items".equals(state)){

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
                    state = "player choose stats";
                }
            }

            while ("player choose stats".equals(state)){
                PlayerChooseStats();
                System.out.println("(0)Inteligente: mais pontos de inteligência");
                System.out.println("(1)Forte: mais pontos de força");
                System.out.println("(2)Ágil: mais pontos de agilidade");
                System.out.println("(3)Resistente: mais pontos de vida");
                System.out.println("(4)Um pouco de tudo: pontos equilibrados");
                int input = scanner.nextIntOrDefault(-1);
                if(input == -1 || (input < 0 && input > 4)){
                    System.out.println("Ação inválida");
                    state = "player choose stats";
                    break;
                }
                if(input == 0){
                    playerIntel = 8;
                    playerForca = 3;
                    playerAgil = 3;
                    playerMaxVida = 10;
                }
                if(input == 1){
                    playerIntel = 3;
                    playerForca = 8;
                    playerAgil = 3;
                    playerMaxVida = 10;
                }
                if(input == 2){
                    playerIntel = 3;
                    playerForca = 3;
                    playerAgil = 8;
                    playerMaxVida = 10;
                }
                if(input == 3){
                    playerIntel = 4;
                    playerForca = 4;
                    playerAgil = 4;
                    playerMaxVida = 16;
                }
                if(input == 4){
                    playerIntel = 5;
                    playerForca = 5;
                    playerAgil = 5;
                    playerMaxVida = 12;
                }
                Jogador = new Player(playerName, playerMaxVida, playerForca, playerIntel, playerAgil);
                state = "player greetings";
                break;
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
                String[] choices = {"(2) Prosseguir"};
                oldState = "entering the planet";
                int playerChoice = showPlayerInGameOptions(choices);
                if(playerChoice == -1){
                    System.out.println("Ação inválida.");
                    state = "entering the planet";
                }
                if(playerChoice == 2){
                    state = "encounter guard 1";
                }
            }
            while("encounter guard 1".equals(state)){
                encounterGuard1();
                String[] choices = {"(2) Mentir", "(3) Fugir", "(4) Atacar guarda"};
                oldState = "encounter guard 1";
                int playerChoice = showPlayerInGameOptions(choices);
                if(playerChoice == -1){
                    System.out.println("Ação inválida.");
                    state = "encounter guard 1";
                }
                if(playerChoice > 4 || playerChoice < 0){
                    System.out.println("Ação inválida.");
                    state = "encounter guard 1";
                }
                if(playerChoice == 4){
                    BattleLoop(Jogador, currentInimigo);
                    if(endGame){
                        state = "";
                    }else{
                        oldState = "takedown guard1";
                        state = "takedown guard1";
                    }
                }
                if(playerChoice == 2){
                    state = "player lie guard1";
                }
            }
            while("takedown guard1".equals(state)){
                takedownGuard1();
                Jogador.getItem(radioGuarda);
            }
            while("player lie guard1".equals(state)){
                oldState = "takedown guard1";
                playerLieGuard1();
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

    private void PlayerChooseStats(){
        String[] texts = {"Como você se considera como pesoa?", "Inteligente, forte, ágil, resistente ou um pouco de cada?"};
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
        currentInimigo = new Inimigo("Guarda", 10, 3, 2, 2);
        String[] texts = {"Você acaba andando alguns metros, porém, sua nave chamou muita atenção, e um guarda começa a se aproximar de você.", "-Guarda: Ei, quem é você? Você por algum acaso está envolvido nesse show todo?"};
        Dialog.createDialog(texts);
    }

    private void takedownGuard1(){
        String[] texts = {"Você acaba noucateando o guarda, e, em seu bolso, você encontra um rádio.", "Você decide pegar o rádio, assim conseguindo escutar quaisquer informações adicionais."};
        Dialog.createDialog(texts);
    }

    private void playerLieGuard1(){
        String playerLie = Jogador.getNome() + ": Não estou envolvido em nada, senhor. Sou apenas um cidadão comum de passagem.";
        String[] texts = {playerLie,"Guarda: Ah é mesmo? Me deixe ver sua identidade então, cidadão."};
        Dialog.createDialog(texts);
        if(!Jogador.getBoolItemByName("Identidade Falsa")){
            playerLie = Jogador.getNome() + ": Ah... então ,eu não tenho isso.";
            String[] texts2 = {playerLie, "Guarda: Você está mentindo então. Todo cidadão desse planeta precisa andar com sua documentação. Irei imobilizar você!"};
            Dialog.createDialog(texts2);
            state = "player damage items";
        }
    }


    private void showPlanetStatus(){
        System.out.println("Status do planeta: " + this.poluicao);
        state = oldState;
    }

    private int showPlayerInGameOptions(String[] choices){
        System.out.println("Selecione uma opção");
        System.out.println("(0) Ver status de poluição do planeta\n(1) Acessar inventário");
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


    private void PlayerBattleMenu(Player player, Inimigo inimigo){
        boolean action = false;
        do{
            System.out.println("Selecione as opções");
            System.out.println("(0)Atacar\n(1)Ver iventário");
            int input = scanner.nextIntOrDefault(-1);
            if(input == -1 || (input < 0 && input > 1)){
                System.out.println("Ação inválida");
            }
            if(input == 0){
                System.out.println("Selecione um ataque");
                List<Attack> atackList = player.getAttacksList();
                int attackIndex = 0;
                for (Attack attack : atackList){
                    System.out.println(attackIndex + ": " + attack.getNome() + " - " + ((attack.getDano() + player.getForca()) / 2) + " de dano.");

                }
                int attackInput = scanner.nextIntOrDefault(-1);
                if(attackInput == -1 && (attackInput < 0 && attackInput > attackIndex)){ //Verifica se o usuário pegou um ataque
                    System.out.println("Ataque inválido.");
                }else{
                    Attack currentAttack = atackList.get(attackInput);
                    player.useAttack(player, inimigo, currentAttack);
                    action = true; //Para o loop
                }

            }
            if(input == 1){
                List<Item> jogadorInventory = Jogador.getInventory();
                if(jogadorInventory.toArray().length > 0){
                    System.out.println("Selecione um item");
                    int itemIndex = 0;
                    for (Item item : jogadorInventory){
                        if((item instanceof Weapon)){ //Remove armas
                            jogadorInventory.remove(item);
                        }

                        if(!(item instanceof UsableItem)){ //Remove items que não seu utilizáveis
                            jogadorInventory.remove(item);
                        }else{
                            UsableItem displayUsable = (UsableItem) item;
                            System.out.println(itemIndex + ": " + displayUsable.getNome() +  " x " + displayUsable.getQuantidade());
                            itemIndex ++;
                        }

                    }
                    int itemInput = scanner.nextIntOrDefault(-1);
                    if(itemInput == -1 && (itemInput < 0 && itemInput > itemIndex)){ //Verifica se o usuário pegou um ataque
                        System.out.println("Item inválido.");
                    }else{
                        Item currentItem = jogadorInventory.get(itemInput);
                        UsableItem usable = (UsableItem) currentItem;
                        if(currentItem instanceof DamageItem){
                            player.useItem(usable, inimigo);
                        }else{
                            player.useItem(usable);
                        }
                        action = true; //Para o loop
                    }
                }else{
                    System.out.println("Você não tem items.");
                }
            }
        }while(!action);
    }

    public void BattleLoop(Player player, Inimigo inimigo){
        boolean battleEnded = false;
        Entidade[] priorityList = new Entidade[2];
        int turno = 0;
        if(player.getAgi() > inimigo.getAgi()){
            priorityList[0] = player;
            priorityList[1] = inimigo;
        }
        if(player.getAgi() < inimigo.getAgi()){
            priorityList[0] = inimigo;
            priorityList[1] = player;
        }
        System.out.println("Você iniciou um duelo com: " + inimigo.getNome());
        do{
            Entidade currentEntidade = priorityList[turno % 2];
            System.out.println("Turno de: " + currentEntidade.getNome());
            if(currentEntidade.getNome().equals(player.getNome())){
                PlayerBattleMenu(player, inimigo);
            }else{
                inimigo.useAttack(inimigo, player, inimigo.getAttacksList().get(0));
            }

            turno++;
            if(player.getVida() <= 0){
                System.out.println("Sua missão falhou.");
                battleEnded = true;
                endGame = true;

            }
            if(inimigo.getVida() <= 0){
                System.out.println("Você derrotou " + inimigo.getNome());
                battleEnded = true;
            }

        }while(!battleEnded);
    }
}
