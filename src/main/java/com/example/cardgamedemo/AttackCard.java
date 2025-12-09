package com.example.cardgamedemo;

public class AttackCard extends Card {
    private final int damage;

    public AttackCard(String name, int damage){
        super(name);
        this.damage = damage;
    }
    public int getDamage(){
        return damage;
    }
    public void play(Player player, Enemy enemy){
        enemy.takeDamage(damage);
    }
    @Override
    public String toString(){
        return getName() +" ("+ damage + " dmg)";
    }
}
