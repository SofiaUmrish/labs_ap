package com.knight.performance;
import com.knight.armor_actions.AactionsOnArmor;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AactionsOnArmor knight = new AactionsOnArmor();
        Menu menu = new Menu(scanner);

        menu.addCommand(1, new AddArmorCommand(knight, scanner));
        menu.addCommand(2, new ShowArmoryCommand(knight));
        menu.addCommand(3, new CalculateTotalPriceCommand(knight));
        menu.addCommand(4, new SortArmorByWeightCommand(knight));
        menu.addCommand(5, new FindArmorByPriceCommand(knight, scanner));
        menu.addCommand(6, new SaveArmoryToFileCommand(knight));
        menu.addCommand(7, new LoadArmoryFromFileCommand(knight));
        menu.addCommand(8, new HelpCommand());
        menu.addCommand(9, new RemoveArmorByNameAndPriceCommand(knight,scanner));
        menu.addCommand(10, new ExitCommand());

        menu.run();
    }
}
