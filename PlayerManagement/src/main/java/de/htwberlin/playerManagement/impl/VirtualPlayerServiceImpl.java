package de.htwberlin.playerManagement.impl;

import de.htwberlin.playerManagement.entity.Player;
import de.htwberlin.cardManagement.entity.Card;
import de.htwberlin.playerManagement.export.PlayerCreationFailedException;
import de.htwberlin.playerManagement.export.VirtualPlayerService;
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

    @Override
    public Player createVirtualPlayer() throws PlayerCreationFailedException {
        try {
            String[] firstNames = {"Bob", "Stefan", "Augustus", "Jan", "Winfried", "Martha", "Sabine", "Else", "Olga", "Hilda"};
            String[] lastNames = {"König", "Müller", "Winter", "Hansemann", "Sommer", "Neumann", "Haas", "Kiefer", "Fischer", "Jäger"};

            int indexFirstName = random.nextInt(firstNames.length);
            int indexLastName = random.nextInt(lastNames.length);
            String virtualPlayerName = firstNames[indexFirstName] + " " + lastNames[indexLastName];

            Player player = new Player(virtualPlayerName, new ArrayList<Card>(), false, true);
            logger.info("Virtual player created with the random name {}", virtualPlayerName);
            return player;
        }catch (Exception e){
            throw new PlayerCreationFailedException(String.format("Player couldn't be created: %s", e));
        }

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
