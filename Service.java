import com.github.javafaker.Faker;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.tools.ant.taskdefs.Delete;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static org.apache.tools.ant.listener.BigProjectLogger.HEADER;

public class Service {

    private FileWriter out;
    public CSVPrinter printer;
    private List<CSVRecord> records;
    private CSVParser parser;

    private List<Person> people;

    private void initList() throws IOException {
        out = new FileWriter("People.csv");
        printer = new CSVPrinter(out, CSVFormat.DEFAULT
                .withHeader("Id","Name","Surname","BirthDate","Race","Number","Email"));
        for(Person person1 : people) {
            printer.printRecord(
                    person1.Id, person1.Name, person1.Surname, person1.BirthDate,
                    person1.Race, person1.Number, person1.Email
            );
        }
        printer.close();
    }

    public void add(Person person) throws IOException {
        if(people.contains(people.stream().filter(each -> person.Id == each.Id).findAny().get())){
            System.out.println("person id should be identical");
            return;
        }
        people.add(person);
        initList();
    }

    public void show(int id) throws IOException {
        String path = "./People.csv";
        Reader reader= Files.newBufferedReader(Paths.get(path));

        parser = new CSVParser(reader,CSVFormat.DEFAULT
                .withHeader("Id","Name","Surname","BirthDate","Race","Number","Email")
                .withIgnoreHeaderCase()
                .withTrim());


        records = parser.getRecords();
        for(CSVRecord record : records){

            if(record.get(0).equals(Integer.toString(id))) {
                System.out.print(record.get(1) + ", ");
                System.out.print(record.get(2) + ", ");
                System.out.print(record.get(3) + ", ");
                System.out.print(record.get(4) + ", ");
                System.out.print(record.get(5) + ", ");
                System.out.print(record.get(6) );
                System.out.println();
            }
        }
    }

    public void show() throws IOException {
        String path = "./People.csv";
        Reader reader= Files.newBufferedReader(Paths.get(path));

        parser = new CSVParser(reader,CSVFormat.DEFAULT
                .withHeader("Id","Name","Surname","BirthDate","Race","Number","Email")
                .withIgnoreHeaderCase()
                .withTrim());


        records = parser.getRecords();
        for(CSVRecord record : records){
                System.out.print(record.get(0) + ", ");
                System.out.print(record.get(1) + ", ");
                System.out.print(record.get(2) + ", ");
                System.out.print(record.get(3) + ", ");
                System.out.print(record.get(4) + ", ");
                System.out.print(record.get(5) + ", ");
                System.out.print(record.get(6) );
                System.out.println();
        }
    }

    public void update(int id, Person person) throws IOException {
        if(people.contains(people.stream().filter(each -> id == each.Id).findAny().get()))
            people.remove(people.stream().filter(each -> id == each.Id).findAny().get());
        people.add(person);

        initList();
    }

    public void delete(int id) throws IOException {
        if(!people.contains(people.stream().filter(each -> id == each.Id).findAny().get())){
            System.out.println("Person with inserted id does not exist");
            return;
        }

        people.remove(people.stream().filter(each -> id == each.Id).findAny().get());

        initList();
    }

    public String randomestring()
    {
        String generatedstring=RandomStringUtils.randomAlphabetic(8);
        return(generatedstring);
    }

    public Service() throws IOException {

        people = new ArrayList<>();

        String path = "./People.csv";
        Reader reader= Files.newBufferedReader(Paths.get(path));

        CSVParser csvParser = new CSVParser(reader,CSVFormat.DEFAULT
                .withHeader("Id","Name","Surname","BirthDate","Race","Number","Email")
                .withIgnoreHeaderCase()
                .withTrim());

        List<CSVRecord> csvRecords = csvParser.getRecords();
        if(csvRecords.size() != 0) {
            csvRecords.remove(0);
            for (CSVRecord record : csvRecords) {
                Person person = new Person(Integer.parseInt(record.get(0)),record.get(1),record.get(2),
                        record.get(3),record.get(4),record.get(5),record.get(6));
                people.add(person);
            }
        }

        out = new FileWriter("People.csv");
        printer = new CSVPrinter(out, CSVFormat.DEFAULT
                .withHeader("Id","Name","Surname","BirthDate","Race","Number","Email"));
        if(csvRecords.size() == 0) {

            for (int i = 0; i < 1000; i++) {
                Faker faker = new Faker();
                String name = faker.name().firstName();
                String surname = faker.name().lastName();
                String birthday = faker.date().birthday().toString();
                String race = faker.random().nextBoolean() == true ? "White" : "Black";
                String number = faker.phoneNumber().phoneNumber();
                String email = randomestring() + "@gmail.com";
                printer.printRecord(i,name,surname,birthday,race,number,email);
                people.add(new Person(i,name,surname,birthday,race,number,email));
            }
        }
    }
}
