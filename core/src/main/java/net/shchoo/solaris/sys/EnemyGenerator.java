package net.shchoo.solaris.sys;

import net.shchoo.solaris.entity.Enemy;

public class EnemyGenerator {
    public Enemy generate() {
        Enemy enemy = new Enemy();
        enemy.health = 5;
        enemy.maxHealth = 5;
        return enemy;
    }
}
