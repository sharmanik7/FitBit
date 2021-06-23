package com.example.fitbit.Diet;

public class DietModel {
    String Name;
        String Image;
            float Proteins,Fats,Calories,Fibre,Carbohydrates;
    public DietModel(){

    }
    public DietModel(String name, float proteins, float fats, float calories, float fibre, float carbohydrates, String  image) {
        Name = name;
        Proteins = proteins;
        Fats = fats;
        Calories = calories;
        Fibre = fibre;
        Carbohydrates = carbohydrates;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public float getProteins() {
        return Proteins;
    }

    public void setProteins(float proteins) {
        Proteins = proteins;
    }

    public float getFats() {
        return Fats;
    }

    public void setFats(float fats) {
        Fats = fats;
    }

    public float getCalories() {
        return Calories;
    }

    public void setCalories(float calories) {
        Calories = calories;
    }

    public float getFibre() {
        return Fibre;
    }

    public void setFibre(float fibre) {
        Fibre = fibre;
    }

    public float getCarbohydrates() {
        return Carbohydrates;
    }

    public void setCarbohydrates(float carbohydrates) {
        Carbohydrates = carbohydrates;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
