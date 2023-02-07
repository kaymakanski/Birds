package com.test;

import java.util.ArrayList;
import java.util.List;

public class DataSource {
    public static ArrayList<Bird> createBirdList(){
        ArrayList<Bird> birds = new ArrayList<>();
        birds.add(new Bird("Crow", "https://en.wikipedia.org/wiki/Crow", "https://i.pinimg.com/originals/f9/48/d0/f948d02eca408e039a2c421f22d81fc1.png"));
        birds.add(new Bird("Dove", "https://en.wikipedia.org/wiki/Dove", "https://w1.pngwing.com/pngs/500/150/png-transparent-dove-bird-pigeons-and-doves-release-dove-mourning-dove-peace-symbols-beak-feather-wing-thumbnail.png"));
        birds.add(new Bird("Parrot", "https://en.wikipedia.org/wiki/Parrot", "https://www.freepnglogos.com/uploads/parrot-png/parrot-png-transparent-parrot-images-pluspng-1.png"));
        birds.add(new Bird("Sparrow", "https://en.wikipedia.org/wiki/Sparrow", "https://pngimg.com/uploads/sparrow/sparrow_PNG2.png"));
        return birds;
    }
}
