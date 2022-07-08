package ir.ac.kntu.gameplay.events.dead;

import ir.ac.kntu.engine.core.Entity;
import ir.ac.kntu.engine.core.Event;
import ir.ac.kntu.engine.core.EventManager;

/**
 * Event allowing to display the image of the dead red Pacman
 */
public class EventPowerfulPacDie extends Event {
    private final Entity entityOwned;

    protected EventPowerfulPacDie(Entity entityOwned, Entity entity, int time) {
        super(entity, time);
        this.entityOwned = entityOwned;
    }

    @Override
    public void handle() {
        entityOwned.getGraphicsComponent().getAnimationManager().setCurrentAnimation("dead");
        EventManager.getEventManager().addEvent(new EventDeadAnim(entityOwned, entity, 100));
    }
}
