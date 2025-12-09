package com.example.cardgamedemo;

public class AttackCard {
    private final String name;
    private int damage;

    public AttackCard(String name, int damage){
        this.name = name;
        this.damage = damage;
    }
    public String getName(){
        return name;
    }
    public int getDamage(){
        return damage;
    }
    public void play(Player player, Enemy enemy){
        enemy.takeDamage(damage);
    }
    @Override
    public String toString(){
        return name+" ("+ damage + " dmg)";
    }
}
