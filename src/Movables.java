public class Movables {
    int x;
    int energy;
    int tileAmount;
    int moved = 0;
    final static int MOVE_ENERGY = 1;
    final static int JUMP_ENERGY = 3;

    boolean moveRight(){
        if (!(x == tileAmount/2)){
            x += 1;
            useEnergy(MOVE_ENERGY);
            return true;
        } else {
            System.out.println("You can't go over the border.");
            return false;
        }
    }
    boolean moveLeft(){
        if (!(x == -tileAmount/2)){
            x += -1;
            useEnergy(MOVE_ENERGY);
            return true;
        } else {
            System.out.println("You can't go over the border.");
            return false;
        }
    }
    boolean jumpRight(){
        if(energy >= 2) {
            if (!(x == (tileAmount/2) - 1)){
                x += 2;
                useEnergy(JUMP_ENERGY);
                return true;
            } else {
                System.out.println("You can't go over the border.");
                return false;
            }
        } else {
            System.out.println("You don't have enough energy to jump.");
            return false;
        }
    }
    boolean jumpLeft(){
        if(energy >= 2) {
            if (!(x == -(tileAmount/2) - 1)){
                x += -2;
                useEnergy(JUMP_ENERGY);
                return true;
            } else {
                System.out.println("You can't go over the border.");
                return false;
            }
        } else {
            System.out.println("You don't have enough energy to jump.");
            return false;
        }
    }

    void setEnergy(int toSet){
        energy = toSet;
    }

    void setX(int toSet){
        x = toSet;
    }

    void setTileAmount(int toUse){
        tileAmount = toUse;
    }

    void useEnergy(int toUse){
        energy -= toUse;
        moved += 1;
    }
}
