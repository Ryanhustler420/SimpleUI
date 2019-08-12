package com.example.crap.store;

import com.example.crap.model.Destination;

import java.util.ArrayList;

public class DestinationCollection {

    private ArrayList<Destination> destinations = new ArrayList<>();

    public DestinationCollection() {
        destinations.add(new Destination("Bistupur", 15));
        destinations.add(new Destination("Adityapur", 10));
        destinations.add(new Destination("Beco More", 5));
        destinations.add(new Destination("Imlee Chock", 10));
        destinations.add(new Destination("AIR", 10));
    }

    public ArrayList<Destination> getDestinations() {
        return destinations;
    }

}
