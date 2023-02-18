import java.util.Scanner;

public class Calculator {
    private static int countOccurrences(String str, char ch) {
        int counter = 0;
        for (int i = 0; i < str.length(); i++)
        {
            if (str.charAt(i) == ch) {
                counter++;
            }
        }

        return counter;
    }
    public static void main(String[] args) {
        while (true) {
            Converter converter = new Converter();
            char[] actions = {'+', '-', '/', '*'};
            String[] regexActions = {"\\+", "-", "/", "\\*"};
            Scanner scn = new Scanner(System.in);
            System.out.print("Введите выражение: ");
            String exp = scn.nextLine();
            //Определяем арифметическое действие:
            int actionIndex = -1, counter = 0;
            for (int i = 0; i < actions.length; i++) {
                int occurences = countOccurrences(exp, actions[i]);
                counter += occurences;
                if (counter > 1) {
                    System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                    return;
                }
                if (occurences == 1) {
                    actionIndex = i;
                }
            }
            if (actionIndex == -1) {
                System.out.println("throws Exception //т.к. строка не является математической операцией");
                return;
            }
            //Делим строчку по найденному арифметическому знаку


            String[] data = exp.split(regexActions[actionIndex]);
            data[0] = data[0].trim();
            data[1] = data[1].trim();
            //Определяем, находятся ли числа в одном формате (оба римские или оба арабские)
            if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {
                int a, b, c;
                //Определяем, римские ли это числа
                boolean isRoman = converter.isRoman(data[0]);
                if (isRoman) {
                    //если римские, то конвертируем их в арабские
                    a = converter.romanToInt(data[0]);
                    b = converter.romanToInt(data[1]);
                    //System.out.println("throws Exception //т.к. в римской системе нет отрицательных чисел");

                } else {
                    //если арабские, конвертируем их из строки в число
                    a = Integer.parseInt(data[0]);
                    b = Integer.parseInt(data[1]);
                }
                //выполняем с числами арифметическое действие
                int result;
                switch (actions[actionIndex]) {
                    case '+':
                        result = a + b;
                        break;
                    case '-':
                        result = a - b;
                        break;
                    case '*':
                        result = a * b;
                        break;
                    default:
                        result = a / b;
                        break;
                }
                //15->XV
                if (isRoman) {
                    //если числа были римские, возвращаем результат в римском числе
                if (result < 0)
                    System.out.println("throws Exception //т.ifк. в римской системе нет отрицательных чисел");
                else {
                    System.out.println(converter.intToRoman(result));
                }
                } else {
                    //если числа были арабские, возвращаем результат в арабском числе
                    System.out.println(result);
                }
            } else {
                System.out.println("throws Exception //т.к. используются одновременно разные системы счисления");
            }


        }
    }
    }