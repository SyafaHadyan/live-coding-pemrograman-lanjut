---
title: Peternakan Kambing Mas Fu'ad
---
classDiagram
    class Main{
    }
    class Goat{
        -String name
        -String food
        -int age
        -double milkProduction
        +setName(name)
        +setFood(food)
        +setAge(age)
        +getName()
        +getFood()
        +getAge()
        +getMilkProduction()
        +getInfo()
    }
    Goat <|-- Main
