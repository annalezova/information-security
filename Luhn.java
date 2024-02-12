import java.util.Scanner;

public class Luhn {
    //описание алгоритма Луна:
    //1. Проссумировать все цифры на четных позициях справа налево
    //2. Прибавить к полученному значению сумму всех цифр на нечетных позициях умноженных на 2
    // При этом, если произведение таких чисел больше 9, то вычитаем из него 9
    //3. Если полученная сумма делится на 10 без остатка, значит начальное чисдо верно

    public static void main(String[] args) {
        //введем строку из 16 цифр:
        Scanner in = new Scanner(System.in);
        System.out.print("Введите номер банковской карты: \n");
        String number = in.nextLine();

        //переворачиваем строку:
        number = new StringBuilder(number).reverse().toString();

        //перебираем все цифры в строке:
        int summ = 0;
        for (int i = 0; i < number.length(); i++) {
           int digit = Character.getNumericValue(number.charAt(i));
            if (i%2 > 0){//если нечетная позиция
                digit *= 2;
                if (digit > 9){
                    digit -= 9;
                }
            }
            summ += digit;
        }

        //проверяем, делится ли на 10 без остатка:
        if (summ % 10 == 0){
            System.out.println("Сумма равна "+ summ + "\n Номер карты введен верно");
        } else {
            System.out.println("Сумма равна "+ summ + "\n Номер карты введен неверно!!!");
        }
    }
}
