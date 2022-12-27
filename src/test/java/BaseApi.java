import jdk.jfr.Description;
import org.apache.commons.lang3.RandomStringUtils;

public class BaseApi {

    @Description("Creating random string")
    public static String randomString(Integer length){
        return RandomStringUtils.random(length, true, false);}

    @Description("Creating random numbers")
    public static Integer randomNumbers(Integer length){
        return Integer.parseInt(RandomStringUtils.random(length, false, true));}

}
