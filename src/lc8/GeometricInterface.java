import java.util.Scanner;

final class ShapeObjectStore {
    Shape[] oldShapes;
    Shape[] newShapes;
}

interface ShapeObject {
    public void setName(String name);

    public String getName();
}

interface ShapeProperty {
    public double getArea();
}

abstract class Shape implements ShapeObject {
    private String name;

    public Shape(String name) {
        setName(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public abstract double getArea();
}

class Circle extends Shape implements ShapeProperty {
    private double radius;

    public Circle(String name, double radius) {
        super(name);
        setRadius(radius);
    }

    public void setRadius(double radius) {
        if (radius > 0) {
            this.radius = radius;
        } else {
            this.radius = 0;
        }
    }

    public double getRadius() {
        return this.radius;
    }

    public double getArea() {
        return Math.PI * Math.pow(getRadius(), 2);
    }
}

class Rectangle extends Shape implements ShapeProperty {
    private double firstSide;
    private double secondSide;

    public Rectangle(String name, double firstSide, double secondSide) {
        super(name);
        setFirstSide(firstSide);
        setSecondSide(secondSide);
    }

    public void setFirstSide(double firstSide) {
        if (firstSide > 0) {
            this.firstSide = firstSide;
        } else {
            this.firstSide = 0;
        }
    }

    public void setSecondSide(double secondSide) {
        if (secondSide > 0) {
            this.secondSide = secondSide;
        } else {
            this.secondSide = 0;
        }
    }

    public double getFirstSide() {
        return this.firstSide;
    }

    public double getSecondSide() {
        return this.secondSide;
    }

    public double getArea() {
        return getFirstSide() * getSecondSide();
    }
}

class Triangle extends Shape implements ShapeProperty {
    private double bottom;
    private double side;

    public Triangle(String name, double bottom, double side) {
        super(name);
        setBottom(bottom);
        setSide(side);
    }

    public void setBottom(double bottom) {
        if (bottom > 0) {
            this.bottom = bottom;
        } else {
            this.bottom = 0;
        }
    }

    public void setSide(double side) {
        if (side > 0) {
            this.side = side;
        } else {
            this.side = 0;
        }
    }

    public double getBottom() {
        return this.bottom;
    }

    public double getSide() {
        return this.side;
    }

    public double getArea() {
        return getBottom() * getSide() / 2d;
    }
}

public class GeometricInterface {
    private static final int MAX_INPUT = 3;

    private static Scanner scanner = new Scanner(System.in);

    private static ShapeObjectStore shapeObjectOperation() {
        ShapeObjectStore shapeObjectStore = new ShapeObjectStore();
        Shape[] oldShapes = new Shape[MAX_INPUT];
        Shape[] newShapes = new Shape[MAX_INPUT];

        Circle circle = new Circle(
                "Lingkaran",
                Double.parseDouble(scanner.nextLine()));
        Rectangle rectangle = new Rectangle(
                "Persegi Panjang",
                Double.parseDouble(scanner.nextLine()),
                Double.parseDouble(scanner.nextLine()));
        Triangle triangle = new Triangle(
                "Segitiga",
                Double.parseDouble(scanner.nextLine()),
                Double.parseDouble(scanner.nextLine()));

        int index = 0;
        oldShapes[index++] = circle;
        oldShapes[index++] = rectangle;
        oldShapes[index++] = triangle;

        shapeObjectStore.oldShapes = oldShapes;

        Circle newCircle = new Circle(
                circle.getName(),
                circle.getRadius());
        Rectangle newRectangle = new Rectangle(
                rectangle.getName(),
                rectangle.getFirstSide(),
                rectangle.getSecondSide());
        Triangle newTriangle = new Triangle(
                triangle.getName(),
                triangle.getBottom(),
                triangle.getSide());

        newCircle.setRadius(Double.parseDouble(scanner.nextLine()));
        newRectangle.setFirstSide(Double.parseDouble(scanner.nextLine()));
        newRectangle.setSecondSide(Double.parseDouble(scanner.nextLine()));
        newTriangle.setBottom(Double.parseDouble(scanner.nextLine()));
        newTriangle.setSide(Double.parseDouble(scanner.nextLine()));

        index = 0;
        newShapes[index++] = newCircle;
        newShapes[index++] = newRectangle;
        newShapes[index++] = newTriangle;

        shapeObjectStore.newShapes = newShapes;

        return shapeObjectStore;
    }

    private static void displayInfo(ShapeObjectStore shapeObjectStore) {
        System.out.println("||| Sebelum Perubahan Ukuran |||");
        double oldShapeTotalArea = 0d;
        double newShapeTotalArea = 0d;

        for (int i = 0; i < shapeObjectStore.oldShapes.length; i++) {
            double tempArea = shapeObjectStore.oldShapes[i].getArea();
            System.out.printf(
                    "Nama Bentuk: %s\n",
                    shapeObjectStore.oldShapes[i].getName());
            System.out.printf(
                    "Luas: %.2f\n",
                    tempArea);

            oldShapeTotalArea += tempArea;
        }

        System.out.printf(
                "Total luas semua bentuk: %.2f\n",
                oldShapeTotalArea);

        System.out.println("\n||| Setelah Perubahan Ukuran |||");
        for (int i = 0; i < shapeObjectStore.newShapes.length; i++) {
            double tempArea = shapeObjectStore.newShapes[i].getArea();
            System.out.printf(
                    "Nama Bentuk: %s\n",
                    shapeObjectStore.newShapes[i].getName());
            System.out.printf(
                    "Luas: %.2f\n",
                    tempArea);

            newShapeTotalArea += tempArea;
        }

        System.out.printf(
                "Total luas semua bentuk: %.2f\n",
                newShapeTotalArea);
    }

    public static void main(String[] args) {
        displayInfo(shapeObjectOperation());
    }
}
