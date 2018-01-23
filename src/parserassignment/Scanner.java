
package parserassignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;


public class Scanner {
    
    
    public static void main(String[] args) throws Exception {

        LinkedList<String> list = new LinkedList<String>();
        LinkedList<String> l1 = new LinkedList<String>();
        
        String writeToFile = "";

        int state = 0;
        String token = "";
        int pointer = 0;

        //read from file
        InputStream is = new FileInputStream("tiny_sample_code.txt");
        
        
        OutputStream outputStream = new FileOutputStream("scanner_output.txt");
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

        String line = buf.readLine();
        StringBuilder sb = new StringBuilder();
        

        while (line != null) {
            sb.append(line);
            sb.append(" ");
            line = buf.readLine();
            l1.add(line);
        }
        
        

       String input = sb.toString()+" ";

       
        while (pointer <input.length()) {
            char c = input.charAt(pointer);
            switch (state) {
                case 0:

                    if (c == ' ') {
                        state = 0;
                        token = "";
                        pointer++;
                    } else if (Character.isWhitespace(c)) {
                        state = 5;
                        pointer++;
                    } else if (Character.isDigit(c)) {
                        state = 1;
                        token += c;
                        pointer++;
                    } else if (Character.isAlphabetic(c)) {
                        state = 2;
                        token += c;
                        pointer++;

                        if ("if".equals(token) || "then".equals(token) || "else".equals(token) || "end".equals(token)
                                || "repeat".equals(token) || "until".equals(token) || "read".equals(token) || "write".equals(token)) {
                            writeToFile=token+"_ReservedWord";
                            list.add(writeToFile);
                            System.out.println(writeToFile);
                            token="";
                        }
                    } else if (c == ':') {
                        state = 3;
                        token += c;
                        pointer++;
                    } else if (c == '{') {
                        state = 4;
                        pointer++;
                    } else if (c != ' ') {
                        state = 0;//5
                       // writeToFile = "Special Symbol " + c;
                        writeToFile = c +"_Special Symbol ";
                        list.add(writeToFile);

                        System.out.println("Special Character " + c);
                        pointer++;
                    } else {
                        state = 0;
                        pointer++;
                    }

                    break;

                case 1:

                    if (Character.isDigit(c)) {
                        state = 1;
                        pointer++;
                        token += c;
                    } else {
                        state = 0; //5
                      //  writeToFile = "Digit " + token;
                        writeToFile ="number_"+token;
                        list.add(writeToFile);

                        System.out.println("Digit " + token);
                        token = "";
                    }

                    break;

                case 2:

                    if (Character.isAlphabetic(c)) {
                        state = 2;
                        pointer++;
                        token += c;
                    } else {
                        if ("if".equals(token) || "then".equals(token) || "else".equals(token) || "end".equals(token)|| "repeat".equals(token)|| "until".equals(token) || "read".equals(token) || "write".equals(token)) {
                           // writeToFile = "Reserved Word " + token;
                            writeToFile = token+"_Reserved Word";
                            list.add(writeToFile);
                            System.out.println("Reserved Word " + token);
                            token = "";
                            state = 0;
                        } else {
                            state = 0;
                            writeToFile = "identifier_" + token;
                            list.add(writeToFile);
                            System.out.println("Identifier " + token);
                            token = "";
                        }
                    }

                    break;

                case 3:

                    if (c == '=') {
                        token += c;
                        if (":=".equals(token)) {
                           // writeToFile = "Assignment " + token;
                            writeToFile =token+"_ Assignment";
                            list.add(writeToFile);
                            System.out.println("Assignment " + token);
                            token = "";
                            state = 0;

                        }
                        pointer++;

                    } else {
                        state = 0;
                        token = "";
                    }

                    break;

                case 4:

                    if (c == '}') {
                        state = 0;
                        pointer++;
                    } else {
                        state = 4;
                        pointer++;
                    }

                    break;
            }

        }

        //File newFile = new File("scanner_output.txt");

        try {

            for (int i = 0; i < list.size(); i++) {

                writer.write(list.get(i));
                writer.newLine();

            }
            writer.close();
            System.out.println("File Written");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


