import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

/**
 * Created with IntelliJ IDEA.
 * User: 51ibm
 * Date: 12-10-17
 * Time: 下午3:36
 * To change this template use File | Settings | File Templates.
 */

public class CaculatorTest {
    private Caculator  CaculatorObj;
    @Before
    public void Init(){
        CaculatorObj  = new Caculator();
    }
    private Object Run(String ExpressionStr)throws ExpressionParserException
    {
        return CaculatorObj.Evaluate(ExpressionStr) ;
    }

    @Test
    public void test1()  throws ExpressionParserException{
        Assert.assertEquals(3.0,Run("1+2"));
    }
    @Test
    public void test2() throws ExpressionParserException {
        Assert.assertEquals(-1.0,Run("1-2"));
    }
    public void test3() throws ExpressionParserException {
        Assert.assertEquals(4.0,Run("2*2"));
    }
    @Test
    public void test4() throws ExpressionParserException {
        Assert.assertEquals(2.0,Run("4/2"));
    }
    @Test
    public void test5() throws ExpressionParserException {
        Assert.assertEquals(1.0,Run("3%2"));
    }
    @Test
    public void test7() throws ExpressionParserException {
        Assert.assertEquals(9.0,Run("3*(1+2)"));
    }
    @Test(expected = ExpressionParserException.class)
    public void test6() throws ExpressionParserException {
        Assert.assertEquals(2.0,Run("3/0"));
       //throw new ExpressionParserException("ttt");
    }
    @Test(expected = ExpressionParserException.class)
    public void test8() throws ExpressionParserException {
        Assert.assertEquals(2.0,Run("3*(1+2"));
        //throw new ExpressionParserException("ttt");
    }
    @Test(expected = ExpressionParserException.class)
    public void test9() throws ExpressionParserException {
        Assert.assertEquals(2.0,Run("  "));
        //throw new ExpressionParserException("ttt");
    }
    @Test(expected = ExpressionParserException.class)
    public void test10() throws ExpressionParserException {
        Assert.assertEquals(2.0,Run("1+1+ "));
        //throw new ExpressionParserException("ttt");
    }
}
