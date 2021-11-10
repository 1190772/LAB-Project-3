package lapr.project.utils;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Utils {

    static public String readLineFromConsole(String prompt)
    {
        try
        {
            System.out.println("\n" + prompt);

            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);

            return in.readLine();
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    static public int readIntegerFromConsole(String prompt)
    {
        do
        {
            try
            {
                String input = readLineFromConsole(prompt);

                int value = Integer.parseInt(input);

                return value;
            } catch (NumberFormatException ex)
            {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public double readDoubleFromConsole(String prompt)
    {
        do
        {
            try
            {
                String input = readLineFromConsole(prompt);

                double value = Double.parseDouble(input);

                return value;
            } catch (NumberFormatException ex)
            {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public Date readDateFromConsole(String prompt)
    {
        do
        {
            try
            {
                String strDate = readLineFromConsole(prompt);

                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

                Date date = df.parse(strDate);

                return date;
            } catch (ParseException ex)
            {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public boolean confirm(String message) {
        String input;
        do {
            input = Utils.readLineFromConsole("\n" + message + "\n");
        } while (!input.equalsIgnoreCase("s") && !input.equalsIgnoreCase("n"));

        return input.equalsIgnoreCase("s");
    }

    static public Object showAndSelectOne(List list, String header)
    {
        showList(list,header);
        return selectsObject(list);
    }
    static public int showAndSelectIndex(List list, String header)
    {
        showList(list,header);
        return selectsIndex(list);
    }
    static public void showList(List list, String header)
    {
        System.out.println(header);

        int index = 0;
        for (Object o : list)
        {
            index++;

            System.out.println(index + ". " + o.toString());
        }
        System.out.println("");
        System.out.println("0. Cancel");
    }

    static public Object selectsObject(List list)
    {
        String input;
        Integer value;
        do
        {
            input = Utils.readLineFromConsole("Type your option: ");
            value =  Integer.valueOf(input);
        } while (value < 0 || value > list.size());

        if (value == 0)
        {
            return null;
        } else
        {
            return list.get(value - 1);
        }
    }

    static public int selectsIndex(List list)
    {
        String input;
        Integer value;
        do
        {
            input = Utils.readLineFromConsole("Type your option: ");
            value =  Integer.valueOf(input);
        } while (value < 0 || value > list.size());

        return value - 1;
    }

    static public boolean confirm(){
        String resposta = Utils.readLineFromConsole("Confirm? (Yes or No)");
        if(resposta.equalsIgnoreCase("Yes") || resposta.equalsIgnoreCase("Y")){
            return true;
        }else{
            if(resposta.equalsIgnoreCase("No") || resposta.equalsIgnoreCase("N")){
                return false;
            }
        }
        return confirm();
    }

    public static boolean verifyDate(String dateStr) {
        DateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static Date localDateToDate(LocalDate d) {
        ZonedDateTime zdt = d.atStartOfDay(ZoneId.systemDefault());
        Instant instant = zdt.toInstant();
        return Date.from(instant);
    }

    public static void createFile(String name, String textToWrite) {
        try {
            File myObj = new File(name);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
                writeFile(name, textToWrite);
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void writeFile(String name, String textToWrite) {
        try {
            FileWriter myWriter = new FileWriter(name);
            myWriter.write(textToWrite);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static String[] convertDoubleToStringArr(double[] arr){
        String[] str = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            str[i] = String.valueOf(arr[i]);
        }
        return str;
    }
    public static String[][] convertDoubleToStringArr2(double[][] arr){
        String[][] str = new String[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            str[i][0] = String.valueOf(arr[i][0]);
            str[i][1] = String.valueOf(arr[i][1]);
        }
        return str;
    }


}
