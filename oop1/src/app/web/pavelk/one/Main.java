package app.web.pavelk.one;

public class Main {
    public static void main(String[] args) {
        Person person = Person.builder().firstName("firstName").lastName("lastName").middleName("middleName")
                .country("country").address("address").phone("phone").age(298).gender("gender").build();
        System.out.println(person);
        System.out.println(Person.builder().address("add").build());
        System.out.println(Person.builder().lastName("lastName").build());
    }
}

