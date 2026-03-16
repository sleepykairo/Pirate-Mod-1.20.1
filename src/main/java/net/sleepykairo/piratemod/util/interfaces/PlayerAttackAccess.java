package net.sleepykairo.piratemod.util.interfaces;

public interface PlayerAttackAccess {
    void setPreviousAttackCooldown(float prevAttackCooldown);

    float getPreviousAttackCooldown();

    void setAttackDamage(float attackDmg);

    float getAttackDamage();
}
