package de.htwberlin.configuration;

import de.htwberlin.cardManagement.entity.Player;
import de.htwberlin.cardManagement.export.PlayerService;
import de.htwberlin.cardManagement.impl.CardDeckServiceImpl;
import de.htwberlin.cardManagement.impl.CardServiceImpl;
import de.htwberlin.cardManagement.impl.PlayerServiceImpl;
import de.htwberlin.gameManagement.export.GameService;
import de.htwberlin.gameManagement.impl.GameServiceImpl;
import de.htwberlin.rulesetManagement.impl.GameRuleServiceImpl;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.injectors.AnnotatedFieldInjection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Configuration {

    private static MutablePicoContainer container = new DefaultPicoContainer(
            new AnnotatedFieldInjection());

    public static void main(String[] args) {
        registerComponents();
        //container.getComponent(App.class).run();
    }

    private static void registerComponents() {
        container.addComponent(CardDeckServiceImpl.class);
        container.addComponent(CardServiceImpl.class);
        container.addComponent(GameServiceImpl.class);
        container.addComponent(PlayerServiceImpl.class);
        container.addComponent(GameRuleServiceImpl.class);
    }


}
