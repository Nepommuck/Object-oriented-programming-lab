package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine {
    private IWorldMap map;
    private List<Animal> animals;
    private MoveDirection[] commands;

    public SimulationEngine(MoveDirection[] moves, IWorldMap worldMap, Vector2d[] animalsPositions) {
        map = worldMap;
        animals = new ArrayList<>();
        commands = moves;

        for(Vector2d pos:animalsPositions) {
            Animal newAnimal = new Animal(map, pos);
            if(map.place(newAnimal)) {
                animals.add(newAnimal);
                newAnimal.addObserver((IPositionChangeObserver) map);
            }
        }
    }

    @Override
    public void run() {
        int numberOfAnimals = animals.size();
        int i = 0;
        for(MoveDirection command:commands) {
            animals.get(i).move(command);
            i = (i+1) % numberOfAnimals;
        }
    }
}
