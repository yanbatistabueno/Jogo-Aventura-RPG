public class GameDialog {
    private int currentDialogIndex = 0;
    private boolean ended = false;
    EnterAwareScanner scanner = new EnterAwareScanner(System.in);
    public void createDialog(String[] dialogs){
        currentDialogIndex = 0;
        do{
            if(scanner.isEnterPressed() || currentDialogIndex == 0){
                if(currentDialogIndex < dialogs.length){
                    ended = false;
                    System.out.println(dialogs[currentDialogIndex]);
                    if(currentDialogIndex < dialogs.length - 1){
                        scanner.nextLine();
                    }
                    currentDialogIndex ++;

                }else{
                    currentDialogIndex = 0;
//                    scanner.close();
                    ended = true;
                }

            }
            if(!scanner.isEnterPressed()){
                System.out.println("Somente a tecla Enter prossegue com o diálogo...");
                currentDialogIndex -= 1;
                scanner.nextLine();
            }
        }while (!ended);

    }
}
