package com.app.wheaterapproshka.models.City;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.List;
public class CityData {

    @SuppressLint("NewApi")
    public List<City> cities = new ArrayList<>(
                                List.of(new City("Asuncion","-25.28646","-57.647"),
                                        new City("Ciudad del Este","-25.50972","-54.61111"),
                                        new City("Encarnacion","-27.33056","-55.86667"),
                                        new City("Loma Plata","-22.38333","-59.83333"),
                                        new City("Villarrica","-25.74946","-56.43518")
                                        ));
}
