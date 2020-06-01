package DefiningClassesExercises.PokemonTrainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trainer {
    private String name;
    private Map<String, List<Pokemon>> pokemons;
    private int badges;

    public Trainer(String name) {
        this.name = name;
        this.setPokemons();
        this.badges = 0;
    }
    private void setPokemons() {
        this.pokemons = new HashMap<>();
        this.pokemons.put("Fire", new ArrayList<>());
        this.pokemons.put("Water", new ArrayList<>());
        this.pokemons.put("Electricity", new ArrayList<>());
    }

    public void addPokemon(Pokemon pokemon) {
        this.pokemons.putIfAbsent(pokemon.getElement(), new ArrayList<>());
        this.pokemons.get(pokemon.getElement()).add(pokemon);
    }

    public boolean hasElement(String element) {
        return !this.pokemons.get(element).isEmpty();
    }

    public void increaseBadges(int value) {
        this.badges += value;
    }

    public void damagePokemons(int damage) {
        for (List<Pokemon> pokemonList : pokemons.values()) {
            for (Pokemon pokemon : pokemonList) {
                pokemon.takeDamage(damage);
            }
        }
        this.clearPokemons();
    }
    private void clearPokemons() {
        for (List<Pokemon> pokemonList : pokemons.values()) {
            pokemonList.removeIf(p -> p.getHealth() <= 0);
        }
    }

    public int getBadges() {
        return this.badges;
    }

    public int getPokemonsCount() {
        int size = 0;
        for (List<Pokemon> pokemonList : pokemons.values()) {
            size += pokemonList.size();
        }
        return size;
    }

    @Override
    public String toString() {
        return String.format("%s %d %d", this.name, this.badges, this.getPokemonsCount());
    }
}
