package com.github.orsonbraines.rpgcb.core;

/**
 *
 * @author Orson Baines
 */
public class Character {
    private int hp,mana,baseHP,baseMana;

    public Character(int hp, int mana, int baseHP, int baseMana) {
        this.hp = hp;
        this.mana = mana;
        this.baseHP = baseHP;
        this.baseMana = baseMana;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getBaseHP() {
        return baseHP;
    }

    public void setBaseHP(int baseHP) {
        this.baseHP = baseHP;
    }

    public int getBaseMana() {
        return baseMana;
    }

    public void setBaseMana(int baseMana) {
        this.baseMana = baseMana;
    }
}
