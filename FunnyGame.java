/**
 * Created by Lauramv21 on 5/3/16.
 */

import java.util.Scanner;
import java.util.ArrayList;

public class FunnyGame {
    public static ArrayList<ArrayList<Integer>> jugadas = new ArrayList<ArrayList<Integer>>();
    public static void main(String args[]) {
        boolean gana = false;
        Scanner input = new Scanner(System.in);

        String firstLine = input.nextLine();
        int nAeropuertos = Integer.parseInt(""+firstLine.charAt(0));
        int aeropuertoInicial = Integer.parseInt(""+firstLine.charAt(2));
    
        ArrayList<ArrayList<Integer>> aeropuertos = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < nAeropuertos+1; i++) {
            aeropuertos.add(i,new ArrayList<Integer>());
        }
        
        for(int i=0; i<nAeropuertos-1; i++){
            
            String line = input.nextLine();
            int origen = Integer.parseInt(""+line.charAt(0));
            int destino = Integer.parseInt(""+line.charAt(2));
            aeropuertos.get(origen).add(destino);
        }

        int[] marcas = new int[aeropuertos.size()];

        for (int i = 0; i < marcas.length; i++) {
            marcas[i] = 0;
        }
        
        ArrayList<Integer> dfs = play(aeropuertos, aeropuertoInicial, marcas);

        boolean gano=false;
        for(int i=0;i<jugadas.size();i++){
            if(jugadas.get(i).size()%2==0&&!gano){
                System.out.println("First player wins flying to airport "+ jugadas.get(i).get(1));
                gano=true;
            }
        }
        if(!gano){
            System.out.println("First player loses");
        }
    }

    static ArrayList<Integer> play(ArrayList<ArrayList<Integer>> aeropuerto, int actual, int[] marcas) {

        ArrayList<Integer> vuelos = aeropuerto.get(actual);
        ArrayList<Integer> output = new ArrayList<Integer>();
        if(marcas[actual]==1){
            return output;
        }
        else{
            marcas[actual] = 1;
            output.add(actual);
            for (int i = 0; i < vuelos.size(); i++) {
                ArrayList<Integer> recursiveOutput = play(aeropuerto, vuelos.get(i), marcas);
                //jugadas.add(recursiveOutput);
                output.addAll(recursiveOutput);
            }
            jugadas.add(output);
            //System.out.println(output);
            return output;
        }
    }
}