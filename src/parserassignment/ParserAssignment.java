package parserassignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

public class ParserAssignment {

    static LinkedList<String> list = new LinkedList<>();
    static int pointer = 0;
    static LinkedList<String> l2 = new LinkedList<>();
    static String s = "";
    static OutputStream outputStream;
    static BufferedWriter writer;

    public static void readFromFileIntoList() {

        int i = 0;
        String line = "";
        try {

            InputStream inputStream = new FileInputStream("scanner_output.txt");
            BufferedReader buf = new BufferedReader(new InputStreamReader(inputStream));

            while ((line = buf.readLine()) != null) {
                int k = line.indexOf('_');
                list.add(i, line.substring(0, k));
                i++;
            }
            if (i == list.size()) {
                list.add("_____");
            }

            buf.close();

        } catch (IOException e) {
            System.out.println("Error of readFromFile Function");
        }

    }



    public static boolean accept(String s) {
        if (list.get(pointer).equals(s)) {
            pointer++;
            return true;
        }

        return false;
    }

    public static boolean expect(String s) {
        if (accept(s)) {
            return true;
        }
        System.out.println("unvalid syntax");
        s = "unvalid syntax";
        //l2.add(s);
        try {
            writer.write(s);
            writer.newLine();
            writer.close();

        } catch (Exception e) {

        }
        System.exit(0);

        return false;
    }

    public static void isFactor() {

        if (accept("(")) {

            if (pointer == list.size() - 1) {
                System.out.println("Factor Syntax Error");
                s = "Factor Syntax Error";
                //l2.add(s);
                try {
                    writer.write(s);
                    writer.newLine();
                    writer.close();

                } catch (Exception e) {

                }
                System.exit(0);

            }

            exp();

            if (pointer == list.size() - 1) {
                System.out.println("Factor Syntax Error");
                s = "Factor Syntax Error";

                //l2.add(s);
                try {
                    writer.write(s);
                    writer.newLine();
                    writer.close();

                } catch (Exception e) {

                }
                System.exit(0);

            }

            if (expect(")")) {
                System.out.println("Factor is Found");

                s = "Factor is Found";
                //l2.add(s);
                try {
                    writer.write(s);
                    writer.newLine();
                } catch (Exception e) {

                }

            }
        } else if (accept("number")) {
            System.out.println("Factor is Found");
            s = "Factor is Found";
            //l2.add(s);
            try {
                writer.write(s);
                writer.newLine();
            } catch (Exception e) {

            }

        } else if (accept("identifier")) {
            System.out.println("Factor is Found");
            s = "Factor is Found";
            //l2.add(s);

            try {
                writer.write(s);
                writer.newLine();
            } catch (Exception e) {

            }

        } else {
            if (pointer == (list.size() - 1)) {
                System.exit(0);
            } else {
                System.out.println("Factor Syntax Error");
                s = "Factor Syntax Error";
                //l2.add(s);

                try {
                    writer.write(s);
                    writer.newLine();
                    writer.close();

                } catch (Exception e) {

                }
                System.exit(0);

            }
        }

    }

    public static void term() {

        isFactor();

        try {

            while (accept("*") || accept("/")) {
                System.out.println("MulOp is Found");
                s = "MulOp is Found";
                //l2.add(s);
                try {
                    writer.write(s);
                    writer.newLine();
                } catch (Exception e) {

                }

                if (pointer == (list.size() - 1)) {
                    System.out.println("Term Syntax Error");
                    s = "Term Syntax Error";
                    //l2.add(s);

                    try {
                        writer.write(s);
                        writer.newLine();
                        writer.close();

                    } catch (Exception e) {

                    }

                    System.exit(0);

                }

                isFactor();
            }

        } catch (Exception e) {
        }

        System.out.println("Term is Found");
        s = "Term is Found";
        //l2.add(s);

        try {
            writer.write(s);
            writer.newLine();
        } catch (Exception e) {

        }

    }

    public static void simple_exp() {

        term();
        while (accept("+") || accept("-")) {
            System.out.println("AddOp is Found");
            s = "AddOp is Found";
            //l2.add(s);
            try {
                writer.write(s);
                writer.newLine();
            } catch (Exception e) {

            }

            if (pointer == (list.size() - 1)) {
                System.out.println("Simple Exp Syntax Error");
                s = "Simple Exp Syntax Error";
                //l2.add(s);
                try {
                    writer.write(s);
                    writer.newLine();
                    writer.close();

                } catch (Exception e) {

                }
                System.exit(0);

            }

            term();

        }
        System.out.println("Simple_Exp is Found");
        s = "Simple_Exp is Found";
        //l2.add(s);
        try {
            writer.write(s);
            writer.newLine();
        } catch (Exception e) {

        }
    }

    public static void exp() {

        simple_exp();

        if (accept("<") || accept(">") || accept("=")) {
            System.out.println("Comparsion Op is Found");
            s = "Comparsion Op is Found";
            //l2.add(s);
            try {
                writer.write(s);
                writer.newLine();
            } catch (Exception e) {

            }

            if (pointer == (list.size() - 1)) {
                System.out.println("Exp Syntax Error");
                s = "Exp Syntax Error";
                //l2.add(s);
                try {
                    writer.write(s);
                    writer.newLine();
                    writer.close();

                } catch (Exception e) {

                }
                System.exit(0);

            } else {
                simple_exp();
            }
        }

        System.out.println("Expression is Found");
        s = "Expression is Found";
        //l2.add(s);
        try {
            writer.write(s);
            writer.newLine();
        } catch (Exception e) {

        }

    }

    public static void write_stmt() {

        if (accept("write")) {
            System.out.println("Write Word is Found");
            s = "Write Word is Found";
            //l2.add(s);
            try {
                writer.write(s);
                writer.newLine();
            } catch (Exception e) {

            }

            if (pointer == list.size() - 1) {
                System.out.println("Write Statement Syntax Error");
                s = "Write Statement Syntax Error";
                //l2.add(s);
                try {
                    writer.write(s);
                    writer.newLine();

                    writer.close();
                } catch (Exception e) {

                }
                System.exit(0);

            }
            exp();
            System.out.println("Write Statement is Found");
            s = "Write Statement is Found";
            //l2.add(s);
            try {
                writer.write(s);
                writer.newLine();
            } catch (Exception e) {

            }

//           if(pointer==list.size()-1){
//               System.exit(0);
//           }
        }
    }

    public static void read_stmt() {

        if (accept("read")) {
            System.out.println("Read Word is Found");
            s = "Read Word is Found";
            l2.add(s);
            try {
                writer.write(s);
                writer.newLine();
            } catch (Exception e) {

            }

            if (pointer == list.size() - 1) {
                System.out.println("Read Statement Syntax Error");
                s = "Read Statement Syntax Error";
                l2.add(s);
                try {
                    writer.write(s);
                    writer.newLine();

                    writer.close();
                } catch (Exception e) {

                }

                System.exit(0);

            } else {
                if (expect("identifier")) {
                    System.out.println("Read Statement is Found");
                    s = "Read Statement is Found";
                    // l2.add(s);
                    try {
                        writer.write(s);
                        writer.newLine();
                    } catch (Exception e) {

                    }
                }
            }

//           if(pointer==list.size()-1){
//               System.exit(0);
//           }
        }
    }

    public static void assign_stmt() {

        if (accept("identifier")) {

            if (pointer == list.size() - 1) {

                System.out.println("Assignment Statement Syntax Error");
                s = "Assignment Statement Syntax Error";
                //l2.add(s);
                try {
                    writer.write(s);
                    writer.newLine();
                    writer.close();

                } catch (Exception e) {

                }

                System.exit(0);

            }

            System.out.println("Factor is Found");
            s = "Factor is Found";
            //l2.add(s);
            try {
                writer.write(s);
                writer.newLine();
            } catch (Exception e) {

            }

            if (expect(":=")) {

                System.out.println("Assignment Symbol is Found");
                s = "Assignment Symbol is Found";
                //l2.add(s);
                try {
                    writer.write(s);
                    writer.newLine();
                } catch (Exception e) {

                }

                if (pointer == list.size() - 1) {

                    System.out.println("Assignment Statement Syntax Error");
                    s = "Assignment Statement Syntax Error";
                    //l2.add(s);
                    try {
                        writer.write(s);
                        writer.newLine();
                        writer.close();

                    } catch (Exception e) {

                    }

                    System.exit(0);

                } else {
                    exp();
                    System.out.println("Assignment Statement is Found");
                    s = "Assignment Statement is Found";
                    // l2.add(s);
                    try {
                        writer.write(s);
                        writer.newLine();
                    } catch (Exception e) {

                    }

                }
//                if(pointer==list.size()-1){
//                    System.exit(0);
//                }
            }

        }

    }

    public static void repeat_stmt() {

        if (accept("repeat")) {
            System.out.println("repeat reserved word is Found");
            s = "repeat reserved word is Found";
            //l2.add(s);
            try {
                writer.write(s);
                writer.newLine();
            } catch (Exception e) {

            }

            if (pointer == list.size() - 1) {
                System.out.println("Repeat Statement Syntax Error");
                s = "Repeat Statement Syntax Error";
                //l2.add(s);
                try {
                    writer.write(s);
                    writer.newLine();
                    writer.close();

                } catch (Exception e) {

                }

                System.exit(0);

            }

            stmt_sequence();

            if (expect("until")) {
                System.out.println("until reserved word is found");
                s = "until reserved word is found";
                //l2.add(s);
                try {
                    writer.write(s);
                    writer.newLine();
                } catch (Exception e) {

                }

                if (pointer == list.size() - 1) {
                    System.out.println("Repeat Statement Syntax Error");
                    s = "Repeat Statement Syntax Error";
                    //l2.add(s);
                    try {
                        writer.write(s);
                        writer.newLine();
                        writer.close();

                    } catch (Exception e) {

                    }

                    System.exit(0);

                }
                exp();
                System.out.println("Repeat Statement is Found");

                s = "Repeat Statement is Found";
                // l2.add(s);
                try {
                    writer.write(s);
                    writer.newLine();
                } catch (Exception e) {

                }
//                  if(pointer==list.size()-1){
//                  System.exit(0);
//              }
            }
        }
    }

    public static void if_stmt_without_else() {

        if (accept("if")) {
            System.out.println("(if) reserved word is found");
            s = "(if) reserved word is found";
            //l2.add(s);
            try {
                writer.write(s);
                writer.newLine();
            } catch (Exception e) {

            }

            if (pointer == list.size() - 1) {
                System.out.println("If Statement Syntax Error");
                s = "If Statement Syntax Error";
                //l2.add(s);
                try {
                    writer.write(s);
                    writer.newLine();
                    writer.close();

                } catch (Exception e) {

                }

                System.exit(0);

            }
            exp();

            if (pointer == list.size() - 1) {
                System.out.println("If Statement Syntax Error");
                s = "If Statement Syntax Error";
                // l2.add(s);
                try {
                    writer.write(s);
                    writer.newLine();
                    writer.close();

                } catch (Exception e) {

                }

                System.exit(0);

            }

            if (expect("then")) {
                System.out.println("(then) reserved word is found");
                s = "(then) reserved word is found";
                //l2.add(s);
                try {
                    writer.write(s);
                    writer.newLine();
                } catch (Exception e) {

                }

                if (pointer == list.size() - 1) {
                    System.out.println("If Statement Syntax Error");
                    s = "If Statement Syntax Error";
                    //l2.add(s);
                    try {
                        writer.write(s);
                        writer.newLine();
                        writer.close();

                    } catch (Exception e) {

                    }

                    System.exit(0);

                }

                stmt_sequence();

                if (pointer == list.size() - 1) {
                    System.out.println("If Statement Syntax Error");
                    s = "If Statement Syntax Error";
                    //l2.add(s);
                    try {
                        writer.write(s);
                        writer.newLine();
                        writer.close();

                    } catch (Exception e) {

                    }
                    System.exit(0);

                }

                if (expect("end")) {
                    System.out.println("If Statement is Found");
                    s = "If Statement is Found";
                    //l2.add(s);
                    try {
                        writer.write(s);
                        writer.newLine();
                    } catch (Exception e) {

                    }
//
                }

//              if(pointer==list.size()-1){
//                  System.exit(0);
//              }
            }

        }
    }

    public static void if_stmt() {///////////////////////////////////////

        if_stmt_without_else();

        if (accept("else")) {
            System.out.println("(else) reserved word is found");
            s = "(else) reserved word is found";
            //l2.add(s);
            try {
                writer.write(s);
                writer.newLine();
            } catch (Exception e) {

            }

            if (pointer == list.size() - 1) {
                System.out.println("If Statement Syntax Error");
                s = "If Statement Syntax Error";
                //l2.add(s);
                try {
                    writer.write(s);
                    writer.newLine();
                    writer.close();

                } catch (Exception e) {

                }

                System.exit(0);

            }

            stmt_sequence();

            if (expect("end")) {
                System.out.println("If Statement is Found");
                s = "If Statement is Found";
                //l2.add(s);
                try {
                    writer.write(s);
                    writer.newLine();
                } catch (Exception e) {

                }
            }

        }
    }

    public static void statement() {

        if (accept("if")) {
            pointer--;
            if_stmt();
        } else if (accept("repeat")) {
            pointer--;
            repeat_stmt();
        } else if (accept("write")) {
            pointer--;
            write_stmt();
        } else if (accept("read")) {
            pointer--;
            read_stmt();
        } else if (accept("identifier")) {
            pointer--;
            assign_stmt();
        } else {
            System.out.println("Statement Syntax Error");
            s = "Statement Syntax Error";
            //l2.add(s);
            try {
                writer.write(s);
                writer.newLine();
                writer.close();

            } catch (Exception e) {

            }

            System.exit(0);

        }

    }

    public static void stmt_sequence() {

        statement();

        while (accept(";")) {
            System.out.println("semicolon is found");
            s = "semicolon is found";
            //l2.add(s);
            try {
                writer.write(s);
                writer.newLine();
            } catch (Exception e) {

            }

            statement();

        }

        System.out.println("Statement Sequence is Found");
        s = "Statement Sequence is Found";
        //l2.add(s);
        try {
            writer.write(s);
            writer.newLine();
        } catch (Exception e) {

        }
    }

    public static void program() {

        stmt_sequence();

        System.out.println("Program is Found");
        s = "Program is Found";

        //l2.add(s);
        try {
            writer.write(s);
            writer.newLine();
        } catch (Exception e) {

        }

    }

    public static void main(String[] args) throws Exception {

        readFromFileIntoList();

       

            outputStream = new FileOutputStream("parser_output.txt");
            writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            

        while (pointer < list.size() - 1) {

            program();
            
        }
        
        writer.close();
        System.out.println("____________________________________");

    }
}
