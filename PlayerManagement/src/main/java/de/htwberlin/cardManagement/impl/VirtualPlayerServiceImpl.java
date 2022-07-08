package de.htwberlin.cardManagement.impl;

import de.htwberlin.cardManagement.entity.Player;
import de.htwberlin.cardManagement.export.Card;
import de.htwberlin.cardManagement.export.VirtualPlayerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class VirtualPlayerServiceImpl implements VirtualPlayerService {

    private static final Logger logger = LogManager.getLogger(PlayerServiceImpl.class);

    private static Random random;

    public VirtualPlayerServiceImpl(Random random) {
        this.random = random;
    }

    /**
     * create a new virtual player
     *
     * @return initialized and configured virtual player
     */
    @Override
    public Optional<Player> createVirtualPlayer() {

        String[] firstNames = {"Bob", "Stefan", "Augustus", "Jan", "Winfried", "Martha", "Sabine", "Else", "Olga", "Hilda"};
        String[] lastNames = {"König", "Müller", "Winter", "Hansemann", "Sommer", "Neumann", "Haas", "Kiefer", "Fischer", "Jäger"};

        int indexFirstName = random.nextInt(firstNames.length);
        int indexLastName = random.nextInt(lastNames.length);
        String virtualPlayerName = firstNames[indexFirstName] + " " + lastNames[indexLastName];

        Player player = new Player(0, virtualPlayerName, new ArrayList<Card>(), false, true);
        logger.info("Virtual player created with the random name {}", virtualPlayerName);
        return Optional.of(player);
    }

    /**
     * Generate random int which dictates the move.
     *
     * @param min the min value
     * @param max the max value
     * @return the generated int
     */
    @Override
    public int generateRandomMove(int min, int max) {
        int randInt = random.nextInt(max - min) + min;
        logger.info("A random Move was generated for the virtual Player in form of the decimal: {}", randInt);
        return randInt;
    }

}
