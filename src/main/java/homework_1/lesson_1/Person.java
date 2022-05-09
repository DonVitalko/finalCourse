package homework_1.lesson_1;

public class Person {
    private String firstName;
    private String lastName;
    private String middleName;
    private String country;
    private String address;
    private String phone;
    private int age;
    private String gender;

    private Person() {

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getCountry() {
        return country;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public static Builder newBuilder(){
        return new Person().new Builder();
    }

    public class Builder {
        private Builder() {

        }

        public Builder firstName(String name) {
            Person.this.firstName = name;
            return this;
        }

        public Builder lastName(String name) {
            Person.this.lastName = name;
            return this;
        }

        public Builder middleName(String name) {
            Person.this.middleName = name;
            return this;
        }

        public Builder country(String country) {
            Person.this.country = country;
            return this;
        }

        public Builder address(String address) {
            Person.this.address = address;
            return this;
        }

        public Builder phone(String phone) {
            Person.this.phone = phone;
            return this;
        }

        public Builder age(int age) {
            Person.this.age = age;
            return this;
        }

        public Builder gender(String gender) {
            Person.this.gender = gender;
            return this;
        }

        public Person build(){
            return Person.this;
        }
    }
}