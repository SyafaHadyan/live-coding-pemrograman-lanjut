import java.util.NoSuchElementException;
import java.util.Scanner;

class InputStruct {
    String type;
    String name;
    int healthPoint;
    int attackPoint;
    int defensePoint;
}

abstract class Monster {
    public static int MAX_SPECIAL_ATTACK = 3;

    private int specialAttackRemaining = MAX_SPECIAL_ATTACK;

    private String name;
    private int healthPoint;
    private int attackPoint;
    private int defensePoint;
    private int fireMonsterEffectRemain;
    private int waterMonsterEffectRemain;
    private int iceMonsterEffectRemain;

    public Monster(
            String name, int healthPoint,
            int attackPoint, int defensePoint) {
        setName(name);
        setHealthPoint(healthPoint);
        setAttackPoint(attackPoint);
        setDefensePoint(defensePoint);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public void setAttackPoint(int attackPoint) {
        this.attackPoint = attackPoint;
    }

    public void setDefensePoint(int defensePoint) {
        this.defensePoint = defensePoint;
    }

    public void setFireMonsterEffectRemain(int fireMonsterEffectRemain) {
        this.fireMonsterEffectRemain = fireMonsterEffectRemain;
    }

    public void setWaterMonsterEffectRemain(int waterMonsterEffectRemain) {
        this.waterMonsterEffectRemain = waterMonsterEffectRemain;
    }

    public void setIceMonsterEffectRemain(int iceMonsterEffectRemain) {
        this.iceMonsterEffectRemain = iceMonsterEffectRemain;
    }

    public String getName() {
        return this.name;
    }

    public int getHealthPoint() {
        return this.healthPoint;
    }

    public int getAttackPoint() {
        return this.attackPoint;
    }

    public int getDefensePoint() {
        return this.defensePoint;
    }

    public int getFireMonsterEffectRemain() {
        return this.fireMonsterEffectRemain;
    }

    public int getWaterMonsterEffectRemain() {
        return this.waterMonsterEffectRemain;
    }

    public int getIceMonsterEffectRemain() {
        return this.iceMonsterEffectRemain;
    }

    public int getSpecialAttackRemaining() {
        return this.specialAttackRemaining;
    }

    public void useSpecialAttack() throws IllegalArgumentException {
        if (this.specialAttackRemaining <= 0) {
            throw new IllegalArgumentException();
        }

        --this.specialAttackRemaining;
    }

    public boolean checkSpecialAttack() {
        if (getFireMonsterEffectRemain() <= 0 || getIceMonsterEffectRemain() <= 0
                || getWaterMonsterEffectRemain() <= 0) {
            return false;
        }

        return true;
    }

    public int attack(Monster other) {
        int damagePoint = getAttackPoint() - (other.getDefensePoint() / 2);

        if (this.getClass().getSimpleName().equals(other.getClass().getSimpleName())) {
        } else if (isStronger(other)) {
            damagePoint *= 2;
        } else {
            damagePoint /= 2;
        }

        if (damagePoint < 0) {
            damagePoint = 0;
        }

        other.receiveAttack(damagePoint);

        return damagePoint;
    }

    public void receiveAttack(int damagePoint) {
        setHealthPoint(getHealthPoint() - damagePoint);

        if (getHealthPoint() <= 0) {
            setHealthPoint(0);
        }
    }

    public void resetStatus() {
        setHealthPoint(100);
        setFireMonsterEffectRemain(0);
        setWaterMonsterEffectRemain(0);
        setIceMonsterEffectRemain(0);
        this.specialAttackRemaining = MAX_SPECIAL_ATTACK;
    }

    public void receiveSpecialAttack(Monster other) {
        if (other instanceof FireMonster) {
            setFireMonsterEffectRemain(2);
            setWaterMonsterEffectRemain(0);
            setIceMonsterEffectRemain(0);
        } else if (other instanceof WaterMonster) {
            if (getFireMonsterEffectRemain() >= 1) {
                return;
            }

            setFireMonsterEffectRemain(0);
            setWaterMonsterEffectRemain(0);
            setIceMonsterEffectRemain(0);
        } else if (other instanceof IceMonster) {
            if (getFireMonsterEffectRemain() >= 1) {
                return;
            }

            setFireMonsterEffectRemain(0);
            setWaterMonsterEffectRemain(0);
            setIceMonsterEffectRemain(1);
        }

        if (getHealthPoint() <= 0) {
            setHealthPoint(0);
        }
    }

    public abstract String getSpecialAttackName();

    public abstract void specialAttack(Monster other);

    public abstract boolean isStronger(Monster other);
}

class FireMonster extends Monster {
    private static final String SPECIAL_ATTACK_NAME = "Flame Burst";

    public FireMonster(
            String name, int healthPoint,
            int attackPoint, int defensePoint) {
        super(name, healthPoint, attackPoint, defensePoint);
    }

    public FireMonster(InputStruct inputStruct) {
        super(
                inputStruct.name,
                inputStruct.healthPoint,
                inputStruct.attackPoint,
                inputStruct.defensePoint);
    }

    @Override
    public String getSpecialAttackName() {
        return SPECIAL_ATTACK_NAME;
    }

    @Override
    public boolean isStronger(Monster other) {
        if (other instanceof IceMonster) {
            return true;
        }

        return false;
    }

    @Override
    public void specialAttack(Monster other) {
        useSpecialAttack();
        other.receiveSpecialAttack(this);

        System.out.printf("%s is burned (2 turns)\n", other.getName());
    }

}

class WaterMonster extends Monster {
    private static final String SPECIAL_ATTACK_NAME = "Healing Wave";

    public WaterMonster(
            String name, int healthPoint,
            int attackPoint, int defensePoint) {
        super(name, healthPoint, attackPoint, defensePoint);
    }

    public WaterMonster(InputStruct inputStruct) {
        super(
                inputStruct.name,
                inputStruct.healthPoint,
                inputStruct.attackPoint,
                inputStruct.defensePoint);
    }

    @Override
    public String getSpecialAttackName() {
        return SPECIAL_ATTACK_NAME;
    }

    @Override
    public boolean isStronger(Monster other) {
        if (other instanceof FireMonster) {
            return true;
        }

        return false;
    }

    @Override
    public void specialAttack(Monster other) {
        useSpecialAttack();
        other.receiveSpecialAttack(this);
        setHealthPoint(getHealthPoint() + 15);

        System.out.println("HP + 15");
    }
}

class IceMonster extends Monster {
    private static final String SPECIAL_ATTACK_NAME = "Frost Strike";

    public IceMonster(
            String name, int healthPoint,
            int attackPoint, int defensePoint) {
        super(name, healthPoint, attackPoint, defensePoint);
    }

    public IceMonster(InputStruct inputStruct) {
        super(
                inputStruct.name,
                inputStruct.healthPoint,
                inputStruct.attackPoint,
                inputStruct.defensePoint);
    }

    @Override
    public String getSpecialAttackName() {
        return SPECIAL_ATTACK_NAME;
    }

    @Override
    public boolean isStronger(Monster other) {
        if (other instanceof WaterMonster) {
            return true;
        }

        return false;
    }

    @Override
    public void specialAttack(Monster other) {
        useSpecialAttack();
        other.receiveSpecialAttack(this);

        System.out.printf("%s is frozen (1 turn)\n", other.getName());
    }
}

public class MCA {
    private static final int MAX_MONSTER = 128;

    private static Scanner scanner = new Scanner(System.in);
    private static Monster[] monsters = new Monster[MAX_MONSTER];
    private static int monsterCount;
    private static int fightCount;

    private static void initialize() {
        monsterCount = scanner.nextInt();
        scanner.nextLine();
    }

    private static void createMonsterObject() {
        InputStruct inputStruct = new InputStruct();

        for (int i = 0; i < monsterCount; i++) {
            inputStruct.type = scanner.next();
            inputStruct.name = scanner.next();
            inputStruct.healthPoint = scanner.nextInt();
            inputStruct.attackPoint = scanner.nextInt();
            inputStruct.defensePoint = scanner.nextInt();

            switch (inputStruct.type.toLowerCase()) {
                case "fire":
                    monsters[i] = new FireMonster(inputStruct);
                    break;
                case "water":
                    monsters[i] = new WaterMonster(inputStruct);
                    break;
                case "ice":
                    monsters[i] = new IceMonster(inputStruct);
                    break;
            }

            // Fix EOF. DO NOT REMOVE.
            try {
                scanner.nextLine();
            } catch (NoSuchElementException e) {
            }
        }
    }

    private static void setFightCount() {
        fightCount = scanner.nextInt();
        scanner.nextLine();
    }

    private static void startFightRoutine() {
        boolean singleFight;

        if (fightCount == 1) {
            singleFight = true;
        } else {
            singleFight = false;
        }

        System.out.printf("Total monsters created: %d\n", monsterCount);

        for (int i = 0; i < fightCount; i++) {
            System.out.printf("\nBattle %d: ", i + 1);
            fight(singleFight);
        }
    }

    private static void fight(boolean singleFight) {
        int firstMonsterIndex = scanner.nextInt();
        int secondMonsterIndex = scanner.nextInt();
        boolean healthDown = false;

        // Fix EOF. DO NOT REMOVE.
        try {
            scanner.nextLine();
        } catch (NoSuchElementException e) {
        }

        int roundCount = scanner.nextInt();

        // Fix EOF. DO NOT REMOVE.
        try {
            scanner.nextLine();
        } catch (NoSuchElementException e) {
        }

        int otherMonster;
        String winner = new String();

        System.out.printf(
                "%s vs %s\n",
                monsters[firstMonsterIndex].getName(),
                monsters[secondMonsterIndex].getName());

        if (!singleFight) {
            monsters[firstMonsterIndex].resetStatus();
            monsters[secondMonsterIndex].resetStatus();
        }

        for (int i = 0; i < roundCount; i++) {
            int monsterIndex = scanner.nextInt();
            String attackType = scanner.next();

            // Fix EOF. DO NOT REMOVE.
            try {
                scanner.nextLine();
            } catch (NoSuchElementException e) {
            }

            if (monsterIndex == firstMonsterIndex) {
                otherMonster = secondMonsterIndex;
            } else {
                otherMonster = firstMonsterIndex;
            }

            if (monsters[monsterIndex].getHealthPoint() <= 0 || monsters[otherMonster].getHealthPoint() <= 0) {
                healthDown = true;
            }

            if (healthDown) {
                continue;
            }

            System.err.printf(
                    "   %d %s\n",
                    monsters[firstMonsterIndex].getHealthPoint(),
                    monsters[firstMonsterIndex].getName());
            System.err.printf(
                    "   %d %s\n",
                    monsters[secondMonsterIndex].getHealthPoint(),
                    monsters[secondMonsterIndex].getName());

            System.out.printf("Turn %d: ", i + 1);

            if (monsters[otherMonster].getFireMonsterEffectRemain() > 0) {
                monsters[otherMonster].setHealthPoint(monsters[otherMonster].getHealthPoint() - 20);
                monsters[otherMonster]
                        .setFireMonsterEffectRemain(monsters[otherMonster].getFireMonsterEffectRemain() - 1);
            }

            if (monsters[monsterIndex].getFireMonsterEffectRemain() > 0) {
                monsters[monsterIndex].setHealthPoint(monsters[monsterIndex].getHealthPoint() - 20);
                monsters[monsterIndex]
                        .setFireMonsterEffectRemain(monsters[monsterIndex].getFireMonsterEffectRemain() - 1);
            }

            if (monsters[monsterIndex].getIceMonsterEffectRemain() > 0) {
                monsters[monsterIndex]
                        .setIceMonsterEffectRemain(monsters[monsterIndex].getIceMonsterEffectRemain() - 1);
                System.out.printf("%s is frozen and skips turn\n", monsters[monsterIndex].getName());
                continue;
            }

            if (attackType.equalsIgnoreCase("normal")) {
                int damage = monsters[monsterIndex].attack(monsters[otherMonster]);

                System.out.printf(
                        "%s uses normal attack -> damage: %d -> %s HP: %d\n",
                        monsters[monsterIndex].getName(),
                        damage,
                        monsters[otherMonster].getName(),
                        monsters[otherMonster].getHealthPoint());
            } else if (attackType.equalsIgnoreCase("special")) {
                try {
                    if (monsters[monsterIndex].getSpecialAttackRemaining() <= 0) {
                        System.out.println("Special attack failed: Maximum uses reached.");
                        continue;
                    }

                    System.out.printf(
                            "%s uses %s (special) -> ",
                            monsters[monsterIndex].getName(),
                            monsters[monsterIndex].getSpecialAttackName());
                    if (!healthDown) {
                        monsters[monsterIndex].specialAttack(monsters[otherMonster]);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Special attack failed: Maximum uses reached.");
                }
            }
        }

        if (healthDown) {
            if (monsters[firstMonsterIndex].getHealthPoint() <= 0) {
                winner = monsters[secondMonsterIndex].getName();
            } else if (monsters[secondMonsterIndex].getHealthPoint() <= 0) {
                winner = monsters[firstMonsterIndex].getName();
            }
        } else if (monsters[firstMonsterIndex].getHealthPoint() > monsters[secondMonsterIndex].getHealthPoint()) {
            winner = monsters[firstMonsterIndex].getName();
        } else if (monsters[firstMonsterIndex].getHealthPoint() < monsters[secondMonsterIndex].getHealthPoint()) {
            winner = monsters[secondMonsterIndex].getName();
        } else {
            int index = -1;

            if (firstMonsterIndex < secondMonsterIndex) {
                index = firstMonsterIndex;
            } else if (secondMonsterIndex > firstMonsterIndex) {
                index = secondMonsterIndex;
            }

            winner = monsters[index].getName();
        }

        System.out.printf(
                "-> Winner: %s\n",
                winner);

        monsters[firstMonsterIndex].resetStatus();
        monsters[secondMonsterIndex].resetStatus();
    }

    private static void close() {
        scanner.close();
    }

    public static void main(String[] args) {
        initialize();
        createMonsterObject();
        setFightCount();
        startFightRoutine();
        close();
    }
}
