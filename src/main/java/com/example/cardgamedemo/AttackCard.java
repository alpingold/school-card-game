package com.example.cardgamedemo;

public class AttackCard extends Card {
    private final int damage;

    public AttackCard(String name, String description, int energyCost, int damage) {
        super(name, description, energyCost);
        this.damage = damage;
    }
    public int getDamage(){
        return damage;
    }
    //somehow integrate status effects. probably separate card classes
    @Override
    public void play(Player player, Enemy enemy){
        enemy.takeDamage(damage);
    }
    public void play(Enemy enemy, Player player){
        player.takeDamage(damage);
    }
    @Override
    public String toString(){
        return getName() +" ("+ damage + " dmg)";
    }
}
