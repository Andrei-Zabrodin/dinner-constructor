package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DinnerConstructor {

    // хранилище блюд: ключ — тип блюда (например, "Суп"), значение — список названий блюд этого типа
    HashMap<String, ArrayList<String>> dinnersByType = new HashMap<>();
    Random random = new Random();

    //добавляем компонент в хранилище блюд
    public void addNewDish(String dishType, String dishName) {
        ArrayList<String> dishesForType; //переменая для списка блюд

        if (dinnersByType.containsKey(dishType)) { //проверяем, содержит ли хранилище такое блюдо
            dishesForType = dinnersByType.get(dishType); //если да — используем существующий список
        } else {
            dishesForType = new ArrayList<>(); //если нет — создаём пустой список
            dinnersByType.put(dishType, dishesForType); //запоминаем новый список в хранилище
        }

        dishesForType.add(dishName); //добавляем блюдо в список
    }

    //метод для генерирования вариантов комбинации блюд
    public ArrayList<ArrayList<String>> generateCombos(int comboNumber, ArrayList<String> dishTypes) {
        ArrayList<ArrayList<String>> combos = new ArrayList<>(); //пустой список для хранения получившихся комбинаций

        for (int i = 1; i <= comboNumber; i++) {
            ArrayList<String> combo = generateCombo(dishTypes); //одна комбинация блюд генерируется в отдельном методе
            combos.add(combo);
        }
        return combos;
    }

    //метод для генерирования одной комбинации блюд
    private ArrayList<String> generateCombo(ArrayList<String> dishTypes) {
        ArrayList<String> selectedDishes = new ArrayList<>(); //пустой список для хранения одной комбинации

        for (String dishType: dishTypes) {
            ArrayList<String> availableDishes = dinnersByType.get(dishType); //достаём из хранилища варианты блюд по типу
            String selectedDish = getRandomDish(availableDishes); //получаем произвольное блюдо отдельным методом
            selectedDishes.add(selectedDish); //добавляем блюдо в подборку комбинацию
        }
        return selectedDishes;
    }

    //метод для генерирования случайного блюда из списка
    private String getRandomDish(ArrayList<String> availableDishes) {
        int numberOfDishesForType = availableDishes.size(); //получаем общее количество доступных блюд этого типа
        int dishIndex = random.nextInt(numberOfDishesForType); //генерируем случайное число от 0 до (кол-во блюд - 1)
        String selectedDish = availableDishes.get(dishIndex); //выбираем произвольное блюдо по индексу
        return selectedDish;
    }

    //метод для проверки дубликатов блюд
    public boolean checkType(String type) {
        return dinnersByType.containsKey(type); //если хранилище уже содержит такое блюдо - вернём true
    }
}
