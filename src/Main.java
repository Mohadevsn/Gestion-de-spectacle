import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static int sectionA = 45;
    static int sectionB = 30;
    static int sectionC = 15;

    static int[] nbrPerSection = {0, 0, 0} ;
    static int nbrFacture = 1 ;
    static final double[] price = {16.79, 23.52, 30.50};

    // la fonction principal
    public static void main(String[] args) {
        // les variables

        String choice;
        Scanner scan = new Scanner(System.in);
        // La chaine enter aura son effet a la ligne #20
        String enter = " ";
        do {
            do {
                menuPrincipal();
                choice = scan.nextLine();

                if (goodChoiceVerification(choice)) {
                    do {
                        System.out.println("Mauvais choix !!");
                        System.out.println("[ Appuyer sur entree pour continuer ]");
                        enter = scan.nextLine();
                    } while (Objects.equals(enter, "\n"));
                }
            } while (goodChoiceVerification(choice));

            switch (choice.charAt(0)) {
                case '1': {
                    do{
                        command();
                    }while(!(command() == 'T'));
                    do {
                        printFacture();
                        System.out.println("[ Appuyer sur pour continuer ] ");
                        enter = scan.nextLine();
                    }while(Objects.equals(enter, "\n"));
                    break;
                }
                case '2': {
                    enter = " ";
                    do {
                        totalSelling();
                        System.out.println("[ Appuyer sur pour continuer ] ");
                        enter = scan.nextLine();
                    }while(Objects.equals(enter, "\n"));
                    break;
                }
                case '3': {
                    enter = " ";
                    do {
                        resetData();
                        System.out.println("[ Appuyer sur pour continuer ] ");
                        enter = scan.nextLine();
                    }while(Objects.equals(enter, "\n"));
                    break;
                }
                case '4': {
                    System.out.println("FIN NORMALE DU PROGRAMME");
                    break;
                }
                default:

            }

        }while (!choice.equals("4"));
    }
    public static void menuPrincipal(){
        clearScreen();
        System.out.println("THEATRE L'EXPERT MENTAL");
        System.out.println("Programme de gestion des ventes de billets");
        System.out.println("\n*******************************");
        System.out.println("GUICHET BILLETERIE");
        System.out.println("\n*******************************");
        System.out.println("1. Preparer une nouvelle facture");
        System.out.println("2. Consulter les ventes");
        System.out.println("3. Reinitialiser l'application");
        System.out.println("4. Quitter le programme");
        System.out.println("\nEntrez votre choix :");

    }
    public static boolean goodChoiceVerification(String choice) {
        char[] validChoice = {'1', '2', '3', '4'};

        for (char valid : validChoice) {
            if (choice.charAt(0) != valid) {
                return false;
            }
        }

        return true; //
    }

    public static void runOutPlace(){
        clearScreen();
        System.out.println("\n-------------------------------------");
        System.out.println("PREPARATION FACTURE ");
        System.out.println("-------------------------------------");
        System.out.println("\nToutes les section sont completes !");
        System.out.println("\n*** OPERATION ANNULEE ***");

    }

    public static void sectionChoosing(){
        clearScreen();
        System.out.println("SECTIONS");
        System.out.println("A. Section A [ " + sectionA + " place(s)]");
        System.out.println("B. Section B [ " + sectionB + " place(s)]");
        System.out.println("C. Section C [ " + sectionC + " place(s)]");
        System.out.println("\nEntrez la section pour les billets achetes (ou T pour terminer) : ");
    }


    public static void cancelOperation(){
        clearScreen();
        if ((sectionA ==45) && (sectionB == 30) && (sectionC == 15)){
            System.out.println("-------------------------------------");
            System.out.println("*** Aucune vente a ete realiser  ***");
            System.out.println("------------------------------------");
        }
        System.out.println("-----------------------------------");
        System.out.println("*** OPERATION ANNULEE ***");
        System.out.println("-----------------------------------");

    }
    public static char command() {
        Scanner scan = new Scanner(System.in);
        char section = ' ';
        boolean isEnough;
        if ((sectionA == 0 && sectionB == 0 && sectionC == 0)) {
            runOutPlace();
        } else {
            char[] goodSection = {'A', 'B', 'C'};
            boolean isGood = false;
            int i;
            // debut controle du choix de section
            clearScreen();
            do {
                sectionChoosing();
                section = Character.toUpperCase(scan.nextLine().charAt(0));
                for (char letter : goodSection) {
                    if (letter == section) {
                        isGood = true;
                        break;
                    }
                }

                if (section == 'T') {
                    return section;
                } else if (!isGood) {
                    System.out.println("Erreur, choix invalide... Recommencer !");
                }

            } while (!isGood);


            if (section == 'A') {
                i = 0;
            } else if (section == 'B') {
                i = 1;
            } else {
                i = 2;
            }



            // debut controle du choix du nombre de place
            do {
                System.out.println("Entrez le nombre de billet pour la section " + section + " (0 pour terminer)");
                nbrPerSection[i] = Integer.parseInt(scan.nextLine());
                if (nbrPerSection[i] < 0) {
                    System.out.println("Erreur, le nombre de billet doit etre superieur ou egual à 0 ... Recommencer !");
                } else if (nbrPerSection[i] == 0) {
                    cancelOperation();
                    break;
                }


                // verifions le nombre de place restant dans les sections
                // on declare un booleen qui gere si le nombre est bon
                isEnough = true;
                switch (i) {
                    case 0: {
                        if (nbrPerSection[i] > sectionA) {
                            System.out.println("Erreur, il n'y'a pas assez de place dans la section A");
                            nbrPerSection[i] = 0;
                            isEnough = false;
                        }
                        break;
                    }
                    case 1: {
                        if (nbrPerSection[i] > sectionB) {
                            System.out.println("Erreur, il n'y'a pas assez de place dans la section B");
                            nbrPerSection[i] = 0;

                            isEnough = false;

                        }
                        break;
                    }
                    case 2: {
                        if (nbrPerSection[i] > sectionC) {
                            System.out.println("Erreur, il n'y'a pas assez de place dans la section C");
                            nbrPerSection[i] = 0;
                            isEnough = false;

                        }
                        break;
                    }
                    default:

                }
                // apres avoir verifier on decrement le nombre de place correspondant

                if ((section == 'A') && (nbrPerSection[i] > 0)) {
                    sectionA -= nbrPerSection[0];
                } else if ((section == 'B') && (nbrPerSection[i] > 0)) {
                    sectionB -= nbrPerSection[1];
                } else if ((section == 'C') && (nbrPerSection[i] > 0)) {
                    sectionC -= nbrPerSection[2];
                }
                System.out.println("Section A :" + sectionA);
                System.out.println("Section B :" + sectionB);
                System.out.println("Section C :" + sectionC);


            } while (!isEnough);


        }
        return section;
    }

    public static void printFacture(){
        clearScreen();
        int i ;
        double sousTotal = 0, rabais = 0, total;
        int totalBuy = 0;
        final double TAUX_TPS = 0.05 , TAUX_TVQ = 0.09975;
        double tps, tvq;

        //on cree un objet pour le formatage histoire d'avoir deux chiffres apres la virgule

        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("FACTURE # "+ nbrFacture);
System.out.println("------------------------------------------------------------------------");
        System.out.println("\nQTE\t\t|\t\tSECTION\t\t\t|\t\t\tPRIX  ");
System.out.println("------------------------------------------------------------------------");
        for (i = 0; i < 3; i++){
            char section;
            if (i == 0) {
                section = 'A';
            } else if (i == 1) {
                section = 'B';
            } else {
                section = 'C';
            }
            if (nbrPerSection[i] > 0 ){
                System.out.println(nbrPerSection[i] + "\t\t|\t\t"+ section + "\t\t|\t\t\t"+ df.format(nbrPerSection[i] * price[i])+ " $");
            }
            sousTotal += nbrPerSection[i] * price[i];
            totalBuy += nbrPerSection[i] ;
        }


        System.out.println("===========================================================================");
        System.out.println("\t\tsous_total\t\t\t"+ df.format(sousTotal));
        if ((totalBuy >= 5) && (totalBuy <= 9)){
            rabais = sousTotal * 0.10;
            System.out.println("\t\trabais\t\t\t\t- "+ df.format(rabais)+ " $");
        }
        else if ((totalBuy > 9) && (totalBuy <25)) {
            rabais = sousTotal * 0.15;
            System.out.println("\t\trabais\t\t\t\t- "+ df.format(rabais)+ " $");

        } else if (totalBuy > 24) {
            rabais = sousTotal * 0.20;
            System.out.println("\t\trabais\t\t\t\t- "+ df.format(rabais)+ " $");
        }
        tps = sousTotal * TAUX_TPS;
        tvq = sousTotal * TAUX_TVQ;
        System.out.println("\t\tTPS\t\t\t\t\t"+ df.format(tps));
        System.out.println("\t\tTVQ\t\t\t\t\t"+ df.format(tvq));
        total = (sousTotal - rabais) + (tps + tvq);
        System.out.println("\n\t\tTOTAL\t\t\t\t\t\t"+ df.format(total));
        nbrFacture++;
        nbrPerSection = new int[]{0, 0, 0};

    }

    public static void totalSelling() {
    System.out.println("---------------------------------------------------------------");
    System.out.println("CONSULTATION DES VENTES");
    System.out.println("---------------------------------------------------------------");

        System.out.println("Statistiques sur les ventes depuis la derniere reinitialisation.");
        int resSecA = (45 - sectionA), resSecB = (30 - sectionB), resSecC = (15 - sectionC);
        int totalSell = resSecA + resSecB + resSecC ;
        System.out.println("\nNOMBRE DE BILLETS VENDUS : "+ totalSell);


        if (sectionA < 45){
            System.out.println("Section A\t\t\t|\t\t\tNombre de vente\t\t"+ resSecA);
        }
        if (sectionB < 30) {
            System.out.println("Section B\t\t\t|\t\t\tNombre de vente\t\t"+ resSecB);

        }
        if (sectionC < 15) {
            System.out.println("Section C\t\t\t|\t\t\tNombre de vente\t\t"+ resSecC);
        }
        else if((sectionA == 45)&&(sectionB == 30) && (sectionC == 15)){
            cancelOperation();
            return;
        }
        System.out.println("TOTAL DES VENTES : "+ (resSecA * 16.79 + resSecB * 23.52 + resSecC * 30.5)+ " $");
    }

    public static void clearScreen(){
        for(int i = 0; i < 50 ; i++) {
            System.out.println(" ");
        }
    }

    public  static void resetData(){
        sectionA = 45;
        sectionB = 30;
        sectionC = 15;

        nbrPerSection = new int[]{0, 0, 0};
        nbrFacture = 1 ;
        System.out.println("---------------------------------------------------------------");

        System.out.println("REINITIALISATION DE L'APPLICATION ");
        System.out.println("---------------------------------------------------------------");

        System.out.println("\nL'application a ete reinitialisee.");
    }


}