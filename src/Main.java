// класс для языков
class Language {
    private String name; // название языка
    private int speed; // скорость кол-во символов в минуту
    private int count; // количество напечатанных символов
    private int time; // суммарное время печати
    public Language(){ // конструктов
        this.name = "ru"; // название языка, изначально русский
        this.speed = 100; // скорость, изначально 100 сим/мин
        this.count = 0; // количество символов
        this.time = 0; // время
    }

    public Language(String name, int speed){
        this.name = name;
        this.speed = speed;
        this.count = 0;
        this.time = 0;
    }
    // геттеры
    public String getName(){
        return name;
    }

    public int getSpeed(){
        return speed;
    }

    public int getCount(){
        return count;
    }

    public int getTime(){
        return time;
    }
    // печать определенного количества символов
    public void typeSign(int newCount){
        this.time += newCount / speed;
        this.count += newCount;

    }
    // печать по времени
    public int typeTime(int newTime){
        int newCount = newTime * speed;
        this.count += newCount;
        this.time += newTime;
        return newCount;
    }
    // изменение скорости по определенному правилу
    public void increaseSpeed(int numChars) {
        // Каждые 1000 символов увеличивают скорость на 5 символов в минуту
        int increments = numChars / 1000;
        this.speed += increments * 5;
    }
    // вывод информации о текущем языке
    @Override
    public String toString(){
        return String.format("Язык: %s\nСкорость: %d символов в минуту\nКоличество символов: %d\nВремя: %d мин.\n", name, speed, count, time);
    }

}
// класс скоропечатание
class RapidPrint{
    private Language[] languages; // массив для хранения языков
    public int langCount; // количество языков
    private final int maxCount = 5; // максимальное количество поддерживаемых языков

    public RapidPrint(){
        languages = new Language[maxCount]; // создаем массив для хранения языков
        languages[0] = new Language(); // добавляем первый язык
        langCount = 1; // обновили счетчик
    }
    // вывод информации о всех языках
    public void printInfo(){
        int allTime = 0; // переменная для хранения общего времени
        int allCount = 0; // переменная для хранения общего количества символов
        String result = ""; // строка в которую накапливаем всю информацию
        System.out.print("\nВывод информации:\n");
        System.out.print("---------------------------\n");
        for (int i = 0; i < langCount; i++){
            result += String.format("Язык: %s\nСкорость: %d символов/мин\nКоличество символов: %d\nВремя: %d мин." +
                            "\n---------------------------\n", languages[i].getName(),languages[i].getSpeed(),
                    languages[i].getCount(), languages[i].getTime());
            allTime += languages[i].getTime(); // увеличиваем общее время
            allCount += languages[i].getCount(); // увеличиваем общее количество символов
        }
        System.out.print(result);
        System.out.printf("Суммарное время печати: %d мин.\n", allTime);
        System.out.printf("Общее количество символов: %d\n\n", allCount);

    }
    // печать определенного количества символов
    public void typing(String language, int countSign){
        Language l = findLanguage(language); // проверяем есть ли такой язык
        if (l != null){
            l.typeSign(countSign); // печатаем определенное количество символов
            l.increaseSpeed(l.getCount()); // увеличиваем скорость
            System.out.printf("Напечатано %d символов на языке %s\n", countSign, language);
        }
        else{
            System.out.println("Язык не найден!");
        }
    }
    // печать по времени
    public void typingByTime(String language, int time){
        Language l = findLanguage(language); // проверяем есть ли такой язык
        if (l != null){
            int count = l.typeTime(time); // печатаем по времени
            l.increaseSpeed(l.getCount()); // увеличиваем скорость
            System.out.printf("За %d мин. было напечатано %d сим. на языке %s\n", time, count, language);
        }
        else{
            System.out.println("Язык не найден!");
        }
    }
    // получение текущей скорости языка
    public void gettingSpeed(String language){
        Language l = findLanguage(language); // проверяем есть ли такой язык
        if (l != null){
            System.out.printf("Текущая скорость на языке %s: %d\n", language, l.getSpeed());
        }
        else{
            System.out.println("Язык не найден!");
        }

    }
    // изучение нового языка
    public void LearningNewLanguage(String language){
        if (langCount < maxCount){ // проверяем не превышен ли лимит языков
            // определяем минимальную скорость среди всех языков
            int minSpeed = languages[0].getSpeed();
            for (int i = 1; i < langCount; i++){
                minSpeed = Math.min(minSpeed, languages[i].getSpeed());
            }
            int speed = Math.max(100, (minSpeed / 2)); // получаем новую скорость
            languages[langCount] = new Language(language, speed); // добавляем новый элемент в массив
            langCount++; // увеличиваем счетчик языков
            System.out.printf("Язык %s успешно добавлен!\n", language);

        }
        else {
            System.out.println("Количество языков превышает лимит! Программа поддерживает до пяти языков!");
        }
    }

    // определение самого активного языка
    public String mostActive(){
        int maxTime = languages[0].getTime();
        int indexLang = 0;
        // ищем минимальную скорость среди всех языков
        for (int i = 1; i < langCount; i++){
            if (maxTime < languages[i].getTime()) {
                indexLang = i;
                maxTime = languages[i].getTime();
            }
        }
        return languages[indexLang].getName();
    }

    public void compareSpeed() {
        if (langCount == 1) // если только один язык, то сравнение не требуется
            return;
        Language fast = languages[0];
        Language slow = languages[0];
        for (int i = 1; i < langCount; i++) {
            if (languages[i].getSpeed() > fast.getSpeed()) { // определяем самый быстрый язык
                fast = languages[i];
            }
            if (languages[i].getSpeed() < slow.getSpeed()) { // определяем самый медленный язык
                slow = languages[i];
            }
        }
        // сравниваем скорости
        if (fast.getSpeed() > slow.getSpeed()) {
            int sign = 0;
            int slowSpeed = slow.getSpeed();
            while (slowSpeed < fast.getSpeed()) { // пока минимальная скорость не достигла максимальной
                slowSpeed += 5; // скорость увеличиваем на 5
                sign += 1000; // количество символов увеличиваем на 1000
            }
            System.out.printf("Необходимо напечатать %d символов на языке %s, чтобы его скорость сравнялась с самым быстрым языком %s.\n", sign, slow.getName(), fast.getName());
        } else {
            System.out.println("Скорости языков одинаковы или ошибка данных!");
        }
    }
    // проверяем изучается ли данный язык
    private Language findLanguage(String language) {
        for (int i = 0; i < langCount; i++) {
            if (languages[i].getName().equals(language)) {
                return languages[i];
            }
        }
        return null;
    }
}

public class Main {
    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;
    public static void main(String[] args) throws IOException {
        //Создаем новый объект скоропечатания
        RapidPrint child = new RapidPrint();

        // Печатаем 10000 символов на языке "ru"
        child.typing("ru", 10000);

        //Печатаем 120 минут на языке "ru"
        child.typingByTime("ru", 120);

        //Получаем текущую скорость на языке "ru"
        int speed = child.gettingSpeed("ru");

        //Изучаем новые языки
        child.LearningNewLanguage("eng");
        child.LearningNewLanguage("kz");

        //Получаем самый активный язык
        String active = child.mostActive();
        out.printf("Самый активный язык: %s\n", active);

        //Рассчитываем количество символов для сравнения скорости
        child.compareSpeed();

        //Выводим информацию
        child.printInfo();
    }
}

