package de.htwberlin.configuration;

import de.htwberlin.cardManagement.impl.CardDeckServiceImpl;
import de.htwberlin.cardManagement.impl.CardServiceImpl;
import de.htwberlin.gameManagement.impl.GameServiceImpl;
import de.htwberlin.persistGameManagement.impl.DAOServiceImpl;
import de.htwberlin.playerManagement.export.VirtualPlayerService;
import de.htwberlin.playerManagement.impl.PlayerServiceImpl;
import de.htwberlin.playerManagement.impl.VirtualPlayerServiceImpl;
import de.htwberlin.rulesetManagement.impl.GameRuleServiceImpl;
import export.MauMauUi;
import impl.MauMauUiController;
import impl.MauMauUiView;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.injectors.AnnotatedFieldInjection;

import java.util.Random;

public class Configuration {

    private static MutablePicoContainer container = new DefaultPicoContainer(
            new AnnotatedFieldInjection());

    public static void main(String[] args) {
        registerComponents();
        container.getComponent(MauMauUi.class).run();
    }

    private static void registerComponents() {
        container.addComponent(CardDeckServiceImpl.class);
        container.addComponent(CardServiceImpl.class);
        container.addComponent(GameServiceImpl.class);
        container.addComponent(PlayerServiceImpl.class);
        VirtualPlayerService virtualPlayerService = new VirtualPlayerServiceImpl(new Random());
        container.addComponent(virtualPlayerService);
        container.addComponent(GameRuleServiceImpl.class);
        container.addComponent(MauMauUiController.class);
        container.addComponent(MauMauUiView.class);
        container.addComponent(DAOServiceImpl.class);
    }


}
