import java.util.Scanner;

class InvalidAttackException extends Exception {
    public InvalidAttackException(String message) {
        super(message);
    }
}

class InvalidHealthException extends Exception {
    public InvalidHealthException(String message) {
        super(message);
    }
}

class MonsterIndex {
    static Monster firstMonster;
    static Monster secondMonster;
}

class MonsterStruct {
    int health;
    int attack;
    int defense;
}

class Monster {
    private int health;
    private int attack;
    private int defense;

    public Monster(int health, int attack, int defense) {
        setHealth(health);
        setAttack(attack);
        setDefense(defense);
    }

    public Monster(MonsterStruct monsterStruct) {
        setHealth(monsterStruct.health);
        setAttack(monsterStruct.attack);
        setDefense(monsterStruct.defense);
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getHealth() {
        return this.health;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getDefense() {
        return this.defense;
    }

    public void initializeAttackPoint(Monster monster) {
        if (getHealth() > monster.getHealth()) {
            System.err.println("first");
            setAttack(getAttack() / 2);
        } else if (getHealth() < monster.getHealth()) {
            System.err.println("second");
            setAttack(getAttack() * 2);
        } else {
            System.err.println("else");
            setAttack(getAttack() / 2);
        }
    }

    // public int getAttackPoint(Monster monster) {
    // if (getHealth() > monster.getHealth()) {
    // System.err.println("first");
    // return getAttack() / 2;
    // } else if (getHealth() < monster.getHealth()) {
    // System.err.println("second");
    // return getAttack() * 2;
    // } else {
    // System.err.println("else");
    // return getAttack() / 2;
    // }
    // }

    /*
     * Manusia menyerang Gajah.
     * Hp Manusia: 100
     * Hp Gajah: 95
     * Gajah menyerang Manusia.
     * Hp Manusia: 65
     * Hp Gajah: 95
     * Manusia menyerang Gajah.
     * Hp Manusia: 65
     * Hp Gajah: 90
     * Gajah menyerang Manusia.
     * Hp Manusia: 30
     * Hp Gajah: 90
     * Manusia menyerang Gajah.
     * Hp Manusia: 30
     * Hp Gajah: 85
     * Gajah menyerang Manusia.
     * Hp Manusia: 0
     * Hp Gajah: 85
     * Pertarungan dimenangkan Gajah.
     */

    public void attack(Monster monster)
            throws InvalidAttackException, InvalidHealthException {
        if (getClass().getSimpleName().equals(monster.getClass().getSimpleName())) {
            throw new InvalidAttackException("Cannot attack same monster");
        }

        int finalAttackPoint = getAttack() - monster.getDefense();

        if (finalAttackPoint < 0) {
            finalAttackPoint = 0;
        }

        if (monster.getHealth() <= finalAttackPoint) {
            monster.setHealth(0);
        } else {
            monster.setHealth(monster.getHealth() - finalAttackPoint);
        }

        System.out.printf("%s menyerang %s.\n" +
                "Hp %s: %d\nHp %s: %d\n",
                getClass().getSimpleName(),
                monster.getClass().getSimpleName(),
                MonsterIndex.firstMonster.getClass().getSimpleName(),
                MonsterIndex.firstMonster.getHealth(),
                MonsterIndex.secondMonster.getClass().getSimpleName(),
                MonsterIndex.secondMonster.getHealth());

        if (monster.getHealth() == 0) {
            throw new InvalidHealthException(
                    "Invalid health for monster " +
                            monster.getClass().getSimpleName());
        }
    }

    public String dump() {
        return String.format(
                "Monster: %s\nHealth: %d\nAttack: %d\nDefense: %d\n",
                getClass().getSimpleName(),
                getHealth(),
                getAttack(),
                getDefense());
    }
}

class Semut extends Monster {
    public Semut(int health, int attack, int defense) {
        super(health, attack, defense);
    }

    public Semut(MonsterStruct monsterStruct) {
        super(monsterStruct);
    }
}

class Gajah extends Monster {
    public Gajah(int health, int attack, int defense) {
        super(health, attack, defense);
    }

    public Gajah(MonsterStruct monsterStruct) {
        super(monsterStruct);
    }
}

class Manusia extends Monster {
    public Manusia(int health, int attack, int defense) {
        super(health, attack, defense);
    }

    public Manusia(MonsterStruct monsterStruct) {
        super(monsterStruct);
    }
}

public class GajahManusiaSemut {
    private static String DEFAULT_SEPARATOR = ";";
    private static int MAX_INPUT = 2;

    private static Scanner scanner = new Scanner(System.in);

    private static Monster[] newMonster(int count) {
        Monster[] monsters = new Monster[count];
        for (int i = 0; i < count; i++) {

            String[] monster = scanner.nextLine().split(DEFAULT_SEPARATOR);
            MonsterStruct monsterStruct = new MonsterStruct();

            monsterStruct.health = Integer.parseInt(monster[1]);
            monsterStruct.attack = Integer.parseInt(monster[2]);
            monsterStruct.defense = Integer.parseInt(monster[3]);

            switch (monster[0]) {
                case "Semut":
                    monsters[i] = new Semut(monsterStruct);
                    break;
                case "Gajah":
                    monsters[i] = new Gajah(monsterStruct);
                    break;
                case "Manusia":
                    monsters[i] = new Manusia(monsterStruct);
                    break;
                default:
                    System.exit(0);
            }
        }

        scanner.close();

        return monsters;
    }

    private static void fight() {
        int index = 0;
        int iteration = 0;

        int firstMonsterAttack = MonsterIndex.firstMonster.getAttack();
        int secondMonsterAttack = MonsterIndex.secondMonster.getAttack();

        if (firstMonsterAttack < 0) {
            firstMonsterAttack = 0;
        }
        if (secondMonsterAttack < 0) {
            secondMonsterAttack = 0;
        }

        // MonsterIndex.firstMonster.setAttack(firstMonsterAttack);
        // MonsterIndex.secondMonster.setAttack(secondMonsterAttack);

        /*
         * MonsterIndex.firstMonster.setAttack(
         * (MonsterIndex.firstMonster.getAttack() / 2) -
         * MonsterIndex.secondMonster.getDefense());
         * MonsterIndex.secondMonster.setAttack(
         * (MonsterIndex.secondMonster.getAttack() * 2) -
         * MonsterIndex.firstMonster.getDefense());
         */

        if (MonsterIndex.firstMonster.getAttack() != MonsterIndex.secondMonster.getAttack()) {
            if (MonsterIndex.firstMonster.getAttack() > MonsterIndex.secondMonster.getHealth()) {
                System.err.println("first");
                MonsterIndex.firstMonster.setAttack(MonsterIndex.firstMonster.getAttack() / 2);
                MonsterIndex.secondMonster.setAttack(MonsterIndex.secondMonster.getAttack() * 2);
            } else if (MonsterIndex.firstMonster.getAttack() < MonsterIndex.secondMonster.getHealth()) {
                System.err.println("second");
                MonsterIndex.firstMonster.setAttack(MonsterIndex.firstMonster.getAttack() * 2);
                MonsterIndex.secondMonster.setAttack(MonsterIndex.secondMonster.getAttack() / 2);
            } else {
                System.err.println("else");
                MonsterIndex.firstMonster.setAttack(MonsterIndex.firstMonster.getAttack() / 2);
                MonsterIndex.secondMonster.setAttack(MonsterIndex.secondMonster.getAttack() * 2);
            }
        } else if (MonsterIndex.firstMonster.getClass().getSimpleName().equals("Manusia") &&
                MonsterIndex.secondMonster.getClass().getSimpleName().equals("Gajah")) {
            MonsterIndex.firstMonster.setAttack(MonsterIndex.firstMonster.getAttack() / 2);
            MonsterIndex.secondMonster.setAttack(MonsterIndex.secondMonster.getAttack() * 2);
        } else if (MonsterIndex.firstMonster.getClass().getSimpleName().equals("Semut") &&
                MonsterIndex.secondMonster.getClass().getSimpleName().equals("Manusia")) {
            MonsterIndex.firstMonster.setAttack(MonsterIndex.firstMonster.getAttack() / 2);
            MonsterIndex.secondMonster.setAttack(MonsterIndex.secondMonster.getAttack() * 2);
        } else if (MonsterIndex.firstMonster.getClass().getSimpleName().equals("Gajah") &&
                MonsterIndex.secondMonster.getClass().getSimpleName().equals("Semut")) {
            MonsterIndex.firstMonster.setAttack(MonsterIndex.firstMonster.getAttack() / 2);
            MonsterIndex.secondMonster.setAttack(MonsterIndex.secondMonster.getAttack() * 2);
        } else {
            if (MonsterIndex.firstMonster.getHealth() > MonsterIndex.secondMonster.getHealth()) {
                System.err.println("first");
                MonsterIndex.firstMonster.setAttack(MonsterIndex.firstMonster.getAttack() * 2);
                MonsterIndex.secondMonster.setAttack(MonsterIndex.secondMonster.getAttack() / 2);
            } else if (MonsterIndex.firstMonster.getHealth() < MonsterIndex.secondMonster.getHealth()) {
                System.err.println("second");
                MonsterIndex.firstMonster.setAttack(MonsterIndex.firstMonster.getAttack() / 2);
                MonsterIndex.secondMonster.setAttack(MonsterIndex.secondMonster.getAttack() * 2);
            } else {
                System.err.println("else");
                MonsterIndex.firstMonster.setAttack(MonsterIndex.firstMonster.getAttack() * 2);
                MonsterIndex.secondMonster.setAttack(MonsterIndex.secondMonster.getAttack() / 2);
            }
        }

        System.err.println("DATA DUMP BEFORE FIGHT");
        System.err.println(MonsterIndex.firstMonster.dump());
        System.err.println(MonsterIndex.secondMonster.dump());

        while (true) {
            try {
                if (index == 0) {
                    MonsterIndex.firstMonster.attack(MonsterIndex.secondMonster);
                    ++index;
                } else {
                    MonsterIndex.secondMonster.attack(MonsterIndex.firstMonster);
                    --index;
                }
            } catch (InvalidHealthException e) {
                System.out.print("Pertarungan dimenangkan ");
                if (index == 0) {
                    System.out.print(MonsterIndex.firstMonster.getClass().getSimpleName());
                } else {
                    System.out.print(MonsterIndex.secondMonster.getClass().getSimpleName());
                }
                System.out.println(".");
                break;
            } catch (InvalidAttackException f) {
                System.out.printf(
                        "Tidak bisa menyerang monster dengan jenis yang sama: %s.\n",
                        MonsterIndex.firstMonster.getClass().getSimpleName());
                break;
            } finally {
                System.err.println("\nDATA DUMP ITERATION " + ++iteration + "\n");
                System.err.println(MonsterIndex.firstMonster.dump());
                System.err.println(MonsterIndex.secondMonster.dump());
            }
        }

        System.err.println("\nDATA DUMP AFTER FIGHT");
        System.err.println(MonsterIndex.firstMonster.dump());
        System.err.println(MonsterIndex.secondMonster.dump());
    }

    public static void main(String[] args) {
        Monster[] monsters = newMonster(MAX_INPUT);

        MonsterIndex.firstMonster = monsters[0];
        MonsterIndex.secondMonster = monsters[1];

        fight();
    }
}
