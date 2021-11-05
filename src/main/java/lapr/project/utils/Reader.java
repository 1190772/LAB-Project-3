package lapr.project.utils;

import java.io.*;

public class Reader {
    public static void main(String[] args) throws IOException {

        BST btree = new BST();

        try{
            BufferedReader imput = new BufferedReader(new FileReader("sships.csv"));

            String word = imput.readLine();
            while(word!= null){
                btree.insert(word);
                btree.inOrder();
                word= imput.readLine();
                System.out.println(word);
            }

        }
        catch (FileNotFoundException fileNotFoundException){
            System.out.println("File not found.");
        }
        catch(IOException ioException){
            System.out.println("File imput error.");
        }



    }
}

