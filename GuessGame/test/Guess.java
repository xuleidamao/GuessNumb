/**
 * Created with IntelliJ IDEA.
 * User: 51ibm
 * Date: 12-10-21
 * Time: 下午4:10
 * To change this template use File | Settings | File Templates.
 */
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import java.beans.Customizer;

public class Guess {
   private GuessGame GuessGameObj;
    @Before
    public void Init() throws RepeatNumException,NumCountException {
        GuessGameObj  = new GuessGame("1234");
    }
    @Test
    public void test1() throws RepeatNumException,NumCountException {
        Assert.assertEquals("4A0B",GuessGameObj.Guess("1234"));
    }
   /* @Test
    public void test2() throws RepeatNumException,NumCountException {
        Assert.assertEquals("3A1B",GuessGameObj.Guess("1235"));
    }  */
    @Test
    public void test3() throws RepeatNumException,NumCountException {
        Assert.assertEquals("2A2B",GuessGameObj.Guess("1243"));
    }
    @Test
    public void test4() throws RepeatNumException,NumCountException {
        Assert.assertEquals("1A3B",GuessGameObj.Guess("1423"));
    }
    @Test
    public void test5() throws RepeatNumException,NumCountException {
        Assert.assertEquals("0A4B",GuessGameObj.Guess("4321"));
    }
    @Test
    public void test11() throws RepeatNumException,NumCountException {
        Assert.assertEquals("3A0B",GuessGameObj.Guess("1235"));
    }
    @Test
    public void test12() throws RepeatNumException,NumCountException {
        Assert.assertEquals("2A1B",GuessGameObj.Guess("1253"));
    }
    @Test
    public void test13() throws RepeatNumException,NumCountException {
        Assert.assertEquals("1A2B",GuessGameObj.Guess("1325"));
    }
    @Test
    public void test14() throws RepeatNumException,NumCountException {
        Assert.assertEquals("0A3B",GuessGameObj.Guess("2345"));
    }
    @Test
    public void test22() throws RepeatNumException,NumCountException {
        Assert.assertEquals("2A0B",GuessGameObj.Guess("1267"));
    }
    @Test
    public void test23() throws RepeatNumException,NumCountException {
        Assert.assertEquals("1A1B",GuessGameObj.Guess("1378"));
    }
    @Test
    public void test24() throws RepeatNumException,NumCountException {
        Assert.assertEquals("0A2B",GuessGameObj.Guess("3467"));
    }
    @Test
    public void test31() throws RepeatNumException,NumCountException {
        Assert.assertEquals("1A0B",GuessGameObj.Guess("1567"));
    }
    @Test
    public void test32() throws RepeatNumException,NumCountException {
        Assert.assertEquals("0A1B",GuessGameObj.Guess("5671"));
    }
    @Test
    public void test41() throws RepeatNumException,NumCountException {
        Assert.assertEquals("0A0B",GuessGameObj.Guess("5678"));
    }
    @Test (expected = RepeatNumException.class)
    public void RepeatNumException() throws RepeatNumException,NumCountException {
        Assert.assertEquals("0A0B",GuessGameObj.Guess("1123"));
    }
    @Test (expected = NumCountException.class)
    public void NumCountException()  throws NumCountException ,RepeatNumException  {
        Assert.assertEquals("0A0B",GuessGameObj.Guess("12345"));
    }
}
