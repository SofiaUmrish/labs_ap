package com.knight.armor_actions;

// броня
public abstract class Armor {
    private String name;
    private double weight;
    private String material;
    private double price;

    public Armor(String name, double weight,String material, double price) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.material = material;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }
    public String getMaterial() {
        return material;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " (Weight: " + weight + " kg, Material: "+material+", Price: $" + price + ")";
    }
}

// бронекостюм
class ArmorSuit extends Armor {
    public ArmorSuit(String name, double weight,String material, double price) {
        super(name, weight, material, price);
    }
}
// щит
class Shield extends Armor {
    public Shield(String name, double weight,String material, double price) {
        super(name, weight, material, price);
    }
}

// шолом
class Helmet extends Armor {
    public Helmet(String name, double weight,String material, double price) {
        super(name, weight, material, price);
    }
}
// меч
class Sword extends Armor {
    public Sword(String name, double weight,String material, double price) {
        super(name, weight, material, price);
    }
}
// спис
class Lance extends Armor {
    public Lance(String name, double weight,String material, double price) {
        super(name, weight, material, price);
    }
}
// лук
class Bow extends Armor {
    public Bow(String name, double weight,String material, double price) {
        super(name, weight, material, price);
    }
}
