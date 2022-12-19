package Var2.Lab3_1;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Test2 {
    public static void main(String[] args) throws IOException {

        FileReader fileReader = new FileReader("C:\\Users\\dania\\IdeaProjects\\KATA\\src\\Labs\\var2\\Lab3_1\\Stih.txt");
        FileWriter fileWriter2 = new FileWriter("C:\\Users\\dania\\IdeaProjects\\KATA\\src\\Labs\\var2\\Lab3_1\\f2.txt");
        FileWriter fileWriter3 = new FileWriter("C:\\Users\\dania\\IdeaProjects\\KATA\\src\\Labs\\var2\\Lab3_1\\f3.txt");
        FileWriter fileWriter4 = new FileWriter("C:\\Users\\dania\\IdeaProjects\\KATA\\src\\Labs\\var2\\Lab3_1\\f4.txt");



        int counter = 0;

        //запись в f2
        while (fileReader.ready()) {
            counter++;
            int c = fileReader.read();
            if (c == 13) {
                String a = String.valueOf(counter);
                fileWriter2.write(a);
                fileWriter2.write('\n');
                counter = 0;
            }
            fileWriter2.flush();
        }

        counter = 0;
        fileReader.close();
        fileReader =  new FileReader("C:\\Users\\dania\\IdeaProjects\\KATA\\src\\Labs\\var2\\Lab3_1\\Stih.txt");


        //запись в f3
        while (fileReader.ready()) {
            char c = (char) fileReader.read();
            if (c == ' ') {
                counter++;
            } else if (c == '\n') {
                counter++;
                String a = String.valueOf(counter);
                fileWriter3.write(a);
                fileWriter3.write('\n');
                counter = 0;
            }
            fileWriter3.flush();
        }

        fileReader.close();
        fileReader =  new FileReader("C:\\Users\\dania\\IdeaProjects\\KATA\\src\\Labs\\var2\\Lab3_1\\Stih.txt");

        FileReader fileReader1 = new FileReader("C:\\Users\\dania\\IdeaProjects\\KATA\\src\\Labs\\var2\\Lab3_1\\f2.txt");
        FileReader fileReader2 = new FileReader("C:\\Users\\dania\\IdeaProjects\\KATA\\src\\Labs\\var2\\Lab3_1\\f3.txt");

        int p;

        //Запись и объединение в f4
        while (fileReader.ready()){
            char c = (char) fileReader.read();
            if(c == 13){
                fileWriter4.write(' ');
                while((char) (p = fileReader1.read()) != '\n'){
                    fileWriter4.write(p);
                }
                p =0;
                fileWriter4.write(' ');
                while ((char) (p = fileReader2.read()) != '\n') {
                    fileWriter4.write(p);
                }
            }
            fileWriter4.write(c);
            fileWriter4.flush();

        }


    }
}
