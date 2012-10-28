import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: 51ibm
 * Date: 12-10-21
 * Time: 下午4:12
 * To change this template use File | Settings | File Templates.
 */
public class GuessGame {

    private String CompareNum = "";
    public GuessGame(String s) {
        CompareNum = s;
    }

    public String Guess(String s) throws RepeatNumException,NumCountException {

        int ANum =0;
        int BNum =0;
        if(s.length() != 4)
        {
            throw new   NumCountException  ("长度不满足要求！");
        }
        for(int i=0;i<s.length();i++)
        {
            if(i!=s.lastIndexOf(s.charAt(i)))
                throw new   RepeatNumException("数字重复！");
            if(CompareNum.charAt(i) == s.charAt(i))
            {
                ANum++;
            }
            else if(CompareNum.indexOf(s.charAt(i) ) !=-1 )
           {
               BNum++;
           }

        }
        return ANum+"A"+BNum+"B";
    }
}
