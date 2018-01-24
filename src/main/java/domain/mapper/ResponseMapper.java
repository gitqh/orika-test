package domain.mapper;

import com.google.common.collect.ImmutableList;
import domain.model.Account;
import domain.model.Address;
import domain.model.AddressDetail;
import domain.model.PersonDestination;
import domain.model.PersonSource;
import domain.model.PersonSourceA;
import domain.model.PersonSourceB;
import java.lang.reflect.Field;
import java.util.List;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * @author hqu
 */
public class ResponseMapper {

    private static MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    public static void registryAll(Class a, Class b) {
        mapperFactory.classMap(a, b)
                .field("personSourceA.id", "uid")
                .field("personSourceA.name", "uname")
                .field("personSourceB.score", "uscore")
                .field("personSourceB.sex", "usex")
                .field("account.sum", "usum")
                .field("account.accountName", "uaccountName")
                .byDefault()
                .register();
    }

    public static void registryList(Class a, Class b) {
        mapperFactory.classMap(a, b)
                .field("line", "lineDetail")
                .byDefault()
                .register();
    }


    public static void registry(Class a, Class b) {
        mapperFactory.classMap(a, b)
                .field("id", "uid")
                .field("name", "uname")
                .byDefault()
                .register();
    }

    public static void main(String[] args) {
//        testOneToOne();
        testManyToOne();

    }

    private static void testOneToOne() {
        registry(PersonSource.class, PersonDestination.class);

        PersonSourceA personSourceA = new PersonSourceA(1L,"wang");

        PersonDestination personDestination = mapperFactory.getMapperFacade()
                .map(personSourceA, PersonDestination.class);

        System.out.println("--------------------------");
        System.out.println(personDestination.toString());
    }

    private static void testManyToOne() {
        registryAll(PersonSource.class, PersonDestination.class);
        registryList(Address.class, AddressDetail.class);

        PersonSourceA personSourceA = new PersonSourceA(1L,"wang");
        PersonSourceB personSourceB = new PersonSourceB("90", "male");

        PersonSource personSource = new PersonSource(personSourceA, personSourceB,
                ImmutableList.of(new Address("China"), new Address("Japan")),
                new Account(10, "u201112"));



        PersonDestination personDestination = mapperFactory.getMapperFacade()
                .map(personSource, PersonDestination.class);

        List<AddressDetail> addressDetails = mapperFactory.getMapperFacade()
                .mapAsList(personSource.getAddresses(), AddressDetail.class);

        personDestination.setUaddressDetails(addressDetails);

        System.out.println("--------------------------");
        System.out.println(personDestination.toString());
    }


}
