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
    public void playerPlay(Player player, Enemy enemy){
        //change this to refelct cards
        enemy.takeDamage(damage);
    }
    public void enemyPlay(Enemy enemy, Player player){
        player.takeDamage(enemy.getDamage());
    }
    @Override
    public String toString(){
        return getName() +" ("+ damage + " dmg)";
    }
}
