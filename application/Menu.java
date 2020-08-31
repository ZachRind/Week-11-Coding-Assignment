package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.PokemonDao;
import entity.Pokemon;

public class Menu {
	
	private PokemonDao pokemonDao = new PokemonDao();
	private Scanner scanner = new Scanner(System.in);
	private List<String> options = Arrays.asList("Display Pokemon", "Create Pokemon", "Delete Pokemon");
	
	public void start() {
		String selection = "";
		
		do {
			printMenu();
			selection = scanner.nextLine();
			
		try {
			if (selection.equals("1")) {
				displayPokemon();
			} else if (selection.equals("2")) {
				createPokemon();
			} else if (selection.equals("3")) {
				deletePokemon();
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
			System.out.println("Press enter to continue...");
			scanner.nextLine();
		} while (!selection.contentEquals("-1"));
	}
	
	private void printMenu() {
		System.out.println("Select an Option:\n--------------------");
		for (int i = 0; i < options.size(); i++) {
			System.out.println(i + 1 + ") " + options.get(i));
		}
	}
	
	private void displayPokemon() throws SQLException {
		List<Pokemon> pokemons = pokemonDao.getPokemon();
		for (Pokemon pokemon : pokemons) {
			System.out.println(pokemon.getId() + ": " + pokemon.getName());
		}
	}
	
	private void createPokemon() throws SQLException {
		System.out.println("Enter new Pokemon name:");
		String pokemonName = scanner.nextLine();
		pokemonDao.createNewPokemon(pokemonName);
	}
	
	private void deletePokemon() throws SQLException {
		System.out.println("Enter Pokemon number to delete:");
		int id = Integer.parseInt(scanner.nextLine());
		pokemonDao.deletePokemonById(id);
	}
}

