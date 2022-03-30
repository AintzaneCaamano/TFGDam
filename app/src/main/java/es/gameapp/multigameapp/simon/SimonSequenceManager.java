package es.gameapp.multigameapp.simon;

import java.util.ArrayList;
import java.util.Random;

public class SimonSequenceManager {
    private ArrayList<Integer> sequence = new ArrayList<Integer>();
    private Random random = new Random();

    public void addValue(int repetitions){
        int num;
        for (int i=0; i<repetitions; i++){
            num = random.nextInt(4);
            sequence.add(num);
        }
    }

    public ArrayList<Integer> getSequence(){
        return sequence;
    }

    public boolean checkSequence(ArrayList<Integer> userSequence){
        boolean ret = true;
        for(int i=0; i<userSequence.size();i++){
            if (userSequence.get(i)!=sequence.get(i)){
                return false;
            }
        }
        return ret;
    }
}
