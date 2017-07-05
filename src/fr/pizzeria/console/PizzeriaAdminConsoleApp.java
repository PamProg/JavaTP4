package fr.pizzeria.console;

import java.util.Scanner;

/**
 * Classe unique et principale du projet.
 * Outil de gestion d'une pizzeria.
 * Permet le listage, l'ajout, la modification et la suppression de pizzas.
 * @author ETY15/Pierre-Antoine/Pam
 *
 */
public class PizzeriaAdminConsoleApp {

	// Déclaration des variables
	static String[][] pizzas;
	static Scanner input;
	
	public static void main(String[] args) {
		
		// Initialisations des variables/données
		pizzas = new String[100][4];
		initPizzas();
		input = new Scanner(System.in);
		int response = -1;
		
		// Afficher le menu tant qu'on ne sort pas (ie : response = 99)
		do {
			displayMenu();
			
			response = input.nextInt();
			
			switch (response) {
				case 1: 
					System.out.println("Liste des pizzas"); 
					displayPizzas();
					break; 
				case 2: 
					System.out.println("Ajout d'une nouvelle pizza"); 
					addPizza();
					break; 
				case 3: 
					System.out.println("Mise à jour d'une pizza");
					updatePizza();
					break; 
				case 4: 
					System.out.println("Suppression d'une pizza");
					deletePizza();
					break; 
				case 99: 
					System.out.println("Aurevoir ♪");
					break; 
//				default:
//					break;
			}
		} while (response != 99);
		
		input.close();
	}
	
	/**
	 * Permet la suppression d'une pizza en saisissant son code.
	 * La méthode "tasse" ensuite le tableau pour qu'il ne soit pas fragmenté
	 */
	private static void deletePizza() {
		displayPizzas();
		System.out.println("Veuillez choisir le code de la pizza à supprimer.");
		System.out.println("(99 pour abandonner)");
		
		String codeChosen = input.next();
		
		if(!codeChosen.equals("99")) {
			
			int indexPizzaDelete = -1;
			
			// Récupère l'indice de la pizza supprimée
			for(int i=0 ; i<pizzas.length ; i++) {
				if(pizzas[i][0] != null && pizzas[i][1].equals(codeChosen)) {
					indexPizzaDelete = i;
				}
			}
			
			// Permet de "tasser" le tableau après la suppression
			for(int i=indexPizzaDelete ; i<pizzas.length ; i++) {
				if(pizzas[i][0] != null) {
					pizzas[i][0] = pizzas[i+1][0];
					pizzas[i][1] = pizzas[i+1][1];
					pizzas[i][2] = pizzas[i+1][2];
					pizzas[i][3] = pizzas[i+1][3];
				}
			}
		}
	}

	/**
	 * Permet la modification d'une pizza en saisissant d'abord son code,
	 * puis le nouveau code, nom et prix.
	 */
	private static void updatePizza() {
		displayPizzas();
		System.out.println("Veuillez choisir le code de la pizza à modifier.");
		System.out.println("(99 pour abandonner)");
		
		String codeChosen = input.next();
		
		if(!codeChosen.equals("99")) {
		
			String codeString;
			String nomString;
			String prixString;
			
			System.out.println("Veuillez saisir le code");
			codeString = input.next();
			System.out.println("Veuillez saisir le nom (sans espace)");
			nomString = input.next();
			System.out.println("Veuillez saisir le prix");
			prixString = input.next();
			
			for(int i=0 ; i<pizzas.length ; i++) {
				if(pizzas[i][0] != null && pizzas[i][1].equals(codeChosen)) {
					pizzas[i][1] = codeString;
					pizzas[i][2] = nomString;
					pizzas[i][3] = prixString;
				}
			}
		}
	}

	/**
	 * Permet l'ajout d'une pizza en saisissant le code, le nom et le prix
	 */
	private static void addPizza() {
		
		String codeString;
		String nomString;
		String prixString;
		
		System.out.println("Veuillez saisir le code");
		codeString = input.next();
		System.out.println("Veuillez saisir le nom (sans espace)");
		nomString = input.next();
		System.out.println("Veuillez saisir le prix");
		prixString = input.next();
		
		int nbPizzas = 0;
		for(int i=0 ; i<pizzas.length ; i++) {
			if(pizzas[i][0] != null) {
				nbPizzas++;
			}
		}
		
		pizzas[nbPizzas] = new String[]{String.valueOf(nbPizzas),
										codeString,
										nomString,
										prixString};
	}

	/**
	 * Affiche toutes les pizzas
	 */
	private static void displayPizzas() {
		for(String[] s : pizzas) {
			if(s[0] != null) {
				System.out.println(s[1] + " -> " + s[2] + " (" + s[3] + " €)");
			}
		}
	}

	/**
	 * Affiche le menu
	 */
	public static void displayMenu() {
		System.out.println("***** Pizzeria Administration *****");
		System.out.println("1. Lister les pizzas");
		System.out.println("2. Ajouter une nouvelle pizza");
		System.out.println("3. Mettre à jour une pizza");
		System.out.println("4. Supprimer une pizza");
		System.out.println("99. Sortir");
	}
	
	/**
	 * Initialise les pizzas originelles
	 */
	private static void initPizzas() {
		pizzas[0] = new String[]{"0", "PEP", "Pépéroni", "12.50"};
		pizzas[1] = new String[]{"1", "MAR", "Margherita", "14.00"};
		pizzas[2] = new String[]{"2", "REI", "LA Reine", "11.50"};
		pizzas[3] = new String[]{"3", "FRO", "La 4 fromages", "12.00"};
		pizzas[4] = new String[]{"4", "CAN", "La cannibale", "12.50"};
		pizzas[5] = new String[]{"5", "SAV", "La savoyarde", "13.00"};
		pizzas[6] = new String[]{"6", "ORI", "L'orientale", "13.50"};
		pizzas[7] = new String[]{"7", "IND", "L'indienne", "14.00"};
	}

}
