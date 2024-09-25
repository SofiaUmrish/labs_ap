
import java.util.Scanner;
public class Main {
    //Створення масиву домів

    static House[] houses = {
            new House("H001", 101, 80, 5, 3, "Main Street"),
            new House("H002", 202, 120, 10, 4, "Elm Street"),
            new House("H003", 305, 90, 7, 2, "Pine Street"),
            new House("H004", 405, 110, 8, 3, "Maple Avenue"),
            new House("H005", 506, 150, 12, 5, "Oak Street"),
            new House("H006", 607, 95, 6, 2, "Birch Lane")
    };

    //Метод для списку квартир, які мають задане число кімнат roomsNumber
   public static void roomsNumber(House[] house,int number_of_rooms)
   {
       for (int i = 0; i < houses.length; i++) {
           if (houses[i].getNumber_of_rooms() == number_of_rooms) {
               System.out.println(houses[i].toString());
           }
       }
   }

// Метод для списку квартир, які мають задане число кімнат та
// розташовані на поверсі, який знаходиться в заданому проміжку floorInInterval
    public static void floorInInterval(House[] house, int number_of_rooms, int start,int end){

            for (int i = 0; i < houses.length; i++) {
                if (houses[i].getNumber_of_rooms() == number_of_rooms) {
                    if (houses[i].getFloor() >= start && houses[i].getFloor() <= end) {
                        System.out.println(houses[i].toString());
                    }
                }
            }
        }


// Метод для спискук квартир, які мають площу, що перевищує задану largeSquare
    public static void largeSquare(House[] house, int square){
        for (int i = 0; i < houses.length; i++){
            if(houses[i].getSquare() > square){
                System.out.println(houses[i].toString());
            }
        }
    }
public static void main(String[] args) {

    for (int i = 0; i < houses.length; i++)
    {
        System.out.println("\n"+houses[i].toString());
    }
    System.out.println("Input rooms number:");
    Scanner scanner = new Scanner(System.in);
    int numb = scanner.nextInt();
    roomsNumber(houses,numb);

    System.out.println("Input rooms number:");
    int numb_2 = scanner.nextInt();

    System.out.println("Input the beginning of the floor space:");
    int start = scanner.nextInt();
    System.out.println("Input the end of the floor space:");
    int end = scanner.nextInt();
    floorInInterval(houses,numb_2,start,end);

    System.out.println("Input square:");
    int square = scanner.nextInt();
    largeSquare(houses,square);

}
}
