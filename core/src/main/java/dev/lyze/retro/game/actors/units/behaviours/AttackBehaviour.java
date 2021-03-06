package dev.lyze.retro.game.actors.units.behaviours;

import com.github.czyzby.kiwi.log.Logger;
import com.github.czyzby.kiwi.log.LoggerService;
import dev.lyze.retro.game.actors.units.Unit;

public class AttackBehaviour extends Behaviour {
    private static final Logger logger = LoggerService.forClass(AttackBehaviour.class);

    private int damage;

    public AttackBehaviour(Unit unit, int damage) {
        super(unit);

        this.damage = damage;
    }

    @Override
    public void tick(float duration) {
        if (unit.getCurrentPoint() + 1 >= unit.getPathPoints().size())
            return;

        var hit = false;
        var nextPathPoint = unit.getPathPoints().get(unit.getCurrentPoint() + 1);
        for (Unit otherUnit : unit.getGame().getOtherPlayer(unit.getPlayer()).getRoundUnits()) {
            if (!otherUnit.isDead()) {
                if (unit.getGame().getMap().mapCoordsEqualsPixelCoords(nextPathPoint.getX(), nextPathPoint.getY(), (int) otherUnit.getX(), (int) otherUnit.getY())) {
                    unit.getGame().getAss().playRandomSound(unit.getGame().getAss().getMeleeSounds());

                    otherUnit.damage(damage + unit.getPlayer().getUpgrades().get(unit.getClass()));

                    hit = true;
                }
            }
        }

        if (hit) {
            bobUnit(duration);
        }
    }
}
