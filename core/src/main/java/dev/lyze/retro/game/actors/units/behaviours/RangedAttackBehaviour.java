package dev.lyze.retro.game.actors.units.behaviours;

import com.github.czyzby.kiwi.log.Logger;
import com.github.czyzby.kiwi.log.LoggerService;
import dev.lyze.retro.game.actors.units.Unit;

public class RangedAttackBehaviour extends Behaviour {
    private static final Logger logger = LoggerService.forClass(RangedAttackBehaviour.class);

    private int damage, fields;

    private int ticks;

    public RangedAttackBehaviour(Unit unit, int damage, int fields) {
        super(unit);

        this.damage = damage;
        this.fields = fields;
    }

    @Override
    public void tick(float duration) {
        boolean hit = false;

        if (ticks++ % 2 == 0)
            return;

        for (Unit otherUnit : unit.getGame().getOtherPlayer(unit.getPlayer()).getRoundUnits()) {
            if (!otherUnit.isDead()) {
                for (int i = 1; i <= fields; i++) {
                    if (unit.getCurrentPoint() + i >= unit.getPathPoints().size())
                        continue;

                    var nextPathPoint = unit.getPathPoints().get(unit.getCurrentPoint() + i);
                    if (unit.getGame().getMap().mapCoordsEqualsPixelCoords(nextPathPoint.getX(), nextPathPoint.getY(), (int) otherUnit.getX(), (int) otherUnit.getY())) {
                        unit.getGame().getAss().playRandomSound(unit.getGame().getAss().getRangedSounds());

                        otherUnit.damage(damage + unit.getPlayer().getUpgrades().get(unit.getClass()));

                        hit = true;
                    }
                }
            }
        }

        if (hit) {
            bobUnit(duration);

            for (int i = 1; i <= fields; i++) {
                var nextPathPoint = unit.getGame().getMap().convertToPixelCoords(unit.getPathPoints().get(unit.getCurrentPoint() + i));
                unit.getGame().spawnParticle(unit.getGame().getAss().getRangedAttackParticle(), nextPathPoint.getX(),nextPathPoint.getY(), duration * 0.2f);
            }
        }
    }
}
