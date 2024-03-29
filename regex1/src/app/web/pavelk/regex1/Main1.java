package app.web.pavelk.regex1;

import java.util.regex.Pattern;

//(?U) - модификатор Pattern.UNICODE_CHARACTER_CLASS (не используется в Android!), который в данном выражении позволяет
// \s находить все пробельные символы Юникода, не только ASCII
//[^ - начало исключающего (отрицающего) символьного класса, он находит любой символ, отличый от заданных в классе:
//\p{L} - любая буква Юникода
//\p{N} - любая цифра Юникода
//\s - благодаря (?U) находит любой пробельный символ Юникода
//]+ - конец символьного класса, 1 и более повторов (для более быстрого удаления).
public class Main1 {
    public static void main(String[] args) {
        String regex1 = "[0-9]{6}";
        String regex2 = "[^\\p{L}\\p{N}]+";
        System.out.println(Pattern.matches(regex1, "234234234242.234234.23432"));
        System.out.println(Pattern.matches(regex2, "234234234242.234234.23432"));
        System.out.println("234234234242.234234.23432".replaceAll(regex2, ""));
    }
}

//Содержит ли строка хоть одну букву.
//^ - начало строки
//$ - конец строки
//. - любой символ
//X* - ноль или более раз
//X+ - один или несколько раз
//(?=X) - с помощью положительного обзора нулевой ширины
//\p{L} - любая буква Юникода
class Main2 {
    public static void main(String[] args) {
        System.out.println("...1".matches("^(?=.*[a-zA-Z]).+$"));
        System.out.println("..w.1".matches("^(?=.*[a-zA-Z]).+$"));
        System.out.println("...12@.1".matches("^(?=.*[\\p{L}]).+$"));
        System.out.println(".ф..1".matches("^(?=.*[\\p{L}]).+$"));
        System.out.println(".@./@#2..1н".matches("^(?=.*[\\p{L}]).+$"));
    }
}
