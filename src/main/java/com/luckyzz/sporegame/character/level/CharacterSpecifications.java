package com.luckyzz.sporegame.character.level;

import com.luckyzz.sporegame.character.CharacterDirection;
import com.luckyzz.sporegame.character.level.amplifier.SpecificationAmplifier;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.cristalix.core.util.UtilEntity;

public class CharacterSpecifications implements Cloneable {

    public static final CharacterSpecifications SPAWN_DEFAULT = new CharacterSpecifications(-1.0, 0.2, 20, 0);

    protected double scale, speed, health, damage;

    public CharacterSpecifications(final double scale, final double speed, final double health, final double damage) {
        this.scale = scale;
        this.speed = speed;
        this.health = health;
        this.damage = damage;
    }

    public double getScale() {
        return scale;
    }

    public double getSpeed() {
        return speed;
    }

    public double getHealth() {
        return health;
    }

    public double getDamage() {
        return damage;
    }

    public void applyTo(@NotNull final LivingEntity entity) {
        UtilEntity.setScale(entity, scale, scale, scale);

        final AttributeInstance maxHealth = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        if(maxHealth != null) {
            maxHealth.setBaseValue(health);
            entity.setHealth(health);
        }

        final AttributeInstance movementSpeed = entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        if(movementSpeed != null) {
            movementSpeed.setBaseValue(speed);

            if(entity instanceof Player) {
                final Player player = (Player) entity;
                player.setWalkSpeed((float) speed);
            }
        }

        final AttributeInstance attackDamage = entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        if(attackDamage != null) {
            attackDamage.setBaseValue(damage);
        }
    }

    public @NotNull CharacterSpecifications amplify(@NotNull final SpecificationAmplifier amplifier, @NotNull final CharacterDirection direction) {
        final CharacterSpecifications specifications = amplifier.getSpecifications(direction);
        scale += specifications.getScale();
        speed += specifications.getSpeed();
        health += specifications.getHealth();
        damage += specifications.getDamage();
        return this;
    }

    @Override
    public @NotNull CharacterSpecifications clone() {
        return new CharacterSpecifications(scale, speed, health, damage);
    }

    @Override
    public boolean equals(@Nullable final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final CharacterSpecifications that = (CharacterSpecifications) o;
        return new EqualsBuilder()
                .append(scale, that.scale)
                .append(speed, that.speed)
                .append(health, that.health)
                .append(damage, that.damage)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(scale)
                .append(speed)
                .append(health)
                .append(damage)
                .toHashCode();
    }
}
