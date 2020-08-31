package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Pokemon;

public class PokemonDao {
	
	private Connection connection;
	private final String GET_POKEMON = "SELECT * FROM pokemon";
	private final String GET_POKEMON_BY_ID = "SELECT * FROM pokemon WHERE id = ?";
	private final String CREATE_NEW_POKEMON = "INSERT INTO pokemon(name) VALUES(?)";
	private final String DELETE_POKEMON_BY_ID = "DELETE FROM pokemon WHERE id = ?";
	
	public PokemonDao() {
		connection = DBConnection.getConnection();
	}
	
	public List<Pokemon> getPokemon() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_POKEMON).executeQuery();
		List<Pokemon> pokemon = new ArrayList<Pokemon>();
		
		while (rs.next()) {
			pokemon.add(populatePokemon(rs.getInt(1), rs.getString(2)));
		}
		
		return pokemon;
	}

	public Pokemon getPokemonByID(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_POKEMON_BY_ID);
		ps.setInt(1,  id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populatePokemon(rs.getInt(1), rs.getString(2));
	}
	
	public void createNewPokemon(String pokemonName) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_POKEMON);
		ps.setString(1, pokemonName);
		ps.executeUpdate();
	}
	
	public void deletePokemonById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_POKEMON_BY_ID);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	
	private Pokemon populatePokemon(int id, String name) {
		return new Pokemon(id, name);
	}
	
	
}
