package designpatterns.builder;

/**
 * created by strugkail on 2018/8/8 0008
 *
 * @author strugkail
 */
public class BuilderTest {
    public static void main(String[] args){

        PersonDirector pd = new PersonDirector();
        Person person = pd.constructPerson(new ManBuilder());
        System.out.println(person.getBody());
        System.out.println(person.getFoot());
        System.out.println(person.getHead());

    }
}
