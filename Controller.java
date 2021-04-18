import java.io.IOException;
import java.util.Scanner;

public class Controller {

    private Service service;

    private Person createPerson(){
        Scanner scanner = new Scanner(System.in);
        Person person = new Person();

        System.out.println("insert person id");
        person.Id = scanner.nextInt();

        System.out.println("insert person Name");
        person.Name = scanner.next();

        System.out.println("insert person Surname");
        person.Surname = scanner.next();

        System.out.println("insert person Birthday");
        person.BirthDate = scanner.next();

        System.out.println("insert person Race");
        person.Race = scanner.next();

        System.out.println("insert person Number");
        person.Number = scanner.next();

        System.out.println("insert person Email");
        person.Email = scanner.next();

        return person;

    }

    public void scanner(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Press 1 to add a person");
            System.out.println("Press 2 to show a person");
            System.out.println("Press 3 to show all people");
            System.out.println("Press 4 to update a person");
            System.out.println("Press 5 to delete a person");
            System.out.println("Press 6 to Exit");

            try {
                switch (scanner.nextInt()) {
                    case 1:
                        service.add(createPerson());
                        break;
                    case 2:
                        System.out.println("insert person id you want to show");
                        service.show(scanner.nextInt());
                        break;
                    case 3:
                        service.show();
                        break;
                    case 4:
                        System.out.println("insert person id you want to update");
                        service.update(scanner.nextInt(), createPerson());
                        break;
                    case 5:
                        System.out.println("insert person id you want to delete");
                        service.delete(scanner.nextInt());
                        break;
                    case 6:

                        return;
                }
            }
            catch (Exception es){
                System.out.println(es.getLocalizedMessage());
                return;
            }

        }

    }

    public Controller(){
        try {
            service = new Service();
        }catch (IOException ex){
            System.out.println("Unable to create file");
            return;
        }
    }
}
