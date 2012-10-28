/**
 * Created with IntelliJ IDEA.
 * User: 51ibm
 * Date: 12-10-18
 * Time: 下午1:05
 * To change this template use File | Settings | File Templates.
 */
public class  Caculator {
    /// <summary>
    /// Constructor
    /// </summary>
    public Caculator()
    {
        EOE = "\0";
    }

    /// <summary>
    /// Threse are the token types
    /// </summary>
    private enum TokenTypes
    {
        /// <summary>
        /// Not defined type of token
        /// </summary>
        None,

        /// <summary>
        /// Delimiter token type
        /// </summary>
        Delimiter,

        /// <summary>
        /// Variable token type
        /// </summary>
        Variable,

        /// <summary>
        /// Number token type
        /// </summary>
        Number,
    }

    /// <summary>
    /// These are the types of syntax errors
    /// </summary>
    private enum SyntaxErrors
    {
        /// <summary>
        /// All syntax error 
        /// </summary>
        Synatx,

        /// <summary>
        /// Bracket error
        /// </summary>
        UnBalparens,

        /// <summary>
        /// No expression error
        /// </summary>
        NoExpression,

        /// <summary>
        /// Divide by zero error
        /// </summary>
        DivByZero
    }

    /// <summary>
    /// This token indicates end-of-expression
    /// </summary>
    private String EOE;
    /// <summary>
    /// refers to expression String
    /// </summary>
    private String exp;
    /// <summary>
    /// Current index into the expression
    /// </summary>
    private int expIdx;
    /// <summary>
    /// Holds current token
    /// </summary>
    private String token;
    /// <summary>
    /// Holds current token's type
    /// </summary>
    private TokenTypes tokType;




    /// <summary>
    /// Parsor entry point
    /// </summary>
    /// <param name="expstr">expression String</param>
    /// <returns>result of the expression</returns>
    public double Evaluate(String expstr)   throws ExpressionParserException
    {
        double result = 0.0;
        exp = expstr;
        expIdx = 0;

        GetToken();
        if (token == EOE)
        {
            HandleError(SyntaxErrors.NoExpression);    //No expression present
        }

        //Parse and evaluate the expression
        result = EvalExp2();
        if (token != EOE)
        {
            HandleError(SyntaxErrors.Synatx);
        }

        return result;
    }


    /// <summary>
    ///  二元一级运算 +  -
    /// </summary>
    /// <returns>Evaluated result</returns>
    private double EvalExp2() throws ExpressionParserException
    {
        char op;
        double result;
        double partialResult;

        result = EvalExp3();

        while (((op = token.charAt(0))) == '+' || op == '-')
        {
            GetToken();
            partialResult = EvalExp3();
            switch (op)
            {
                case '+':
                    result += partialResult;
                    break;
                case '-':
                    result -= partialResult;
                    break;
            }
        }

        return result;
    }

    /// <summary>
    /// 二元二级运算 *   /   %
    /// </summary>
    /// <returns>Evaluated result</returns>
    private double EvalExp3()   throws ExpressionParserException
    {
        char op;
        double result;
        double partialResult;

        result = EvalExp4();

        while ((op = token.charAt(0)) == '*' || op == '/' || op == '%')
        {
            GetToken();
            partialResult = EvalExp4();
            switch (op)
            {
                case '*':
                    result *= partialResult;
                    break;
                case '/':
                    if (partialResult == 0.0)
                    {
                        HandleError(SyntaxErrors.DivByZero);
                    }
                    result /= partialResult;
                    break;
                case '%':
                    if (partialResult == 0.0)
                    {
                        HandleError(SyntaxErrors.DivByZero);
                    }
                    result %= partialResult;
                    break;
            }
        }

        return result;

    }

    /// <summary>
    /// 二元三级运算 ^
    /// </summary>
    /// <returns></returns>
    private double EvalExp4()  throws ExpressionParserException
    {
        double result;
        double partialResult;
        double ex;
        int t;

        result = EvalExp5();

        if (token == "^")
        {
            GetToken();
            partialResult = EvalExp4();
            ex = result;
            if (partialResult == 0.0)
            {
                result = 1.0;
            }
            else
            {
                for (t = (int)partialResult - 1; t > 0; t--)
                {
                    result *= ex;
                }
            }
        }

        return result;
    }

    /// <summary>
    /// 一元一级运算 -
    /// </summary>
    /// <returns></returns>
    private double EvalExp5()  throws ExpressionParserException
    {
        double result;
        String op;

        op = "";

        if ((tokType == TokenTypes.Delimiter) && (token == "+" || token == "-"))
        {
            op = token;
            GetToken();
        }

        result = EvalExp6();

        if (op == "-") result = -result;

        return result;
    }
    /// <summary>
    /// 最高级运算 （）
    /// </summary>
    /// <returns></returns>
    private double EvalExp6() throws ExpressionParserException
    {
        double result;
        if (token.equals("("))
        {
            GetToken();
            result = EvalExp2();

            if (!token.equals(")"))
            {
                HandleError(SyntaxErrors.UnBalparens);
            }

            GetToken();
        }
        else
        {
            result = Atom();
        }

        return result;
    }

    /// <summary>
    /// Get the value of a number
    /// </summary>
    /// <returns></returns>
    private double Atom()     throws ExpressionParserException
    {
        double result = 0.0;

        switch (tokType)
        {
            case Number:
                try
                {
                    result = Double.parseDouble(token);
                }
                catch (Exception ex)
                {
                    HandleError(SyntaxErrors.Synatx);
                }

                GetToken();
                break;
            case Variable:
                try
                {
                    /*if (GetVariableValue != null)
                    {
                        result = GetVariableValue(token);
                    }*/
                }
                catch (Exception ex)                {
                    HandleError(SyntaxErrors.Synatx);
                }

                GetToken();
                break;
            default:
                HandleError(SyntaxErrors.Synatx);
                break;
        }

        return result;
    }

    /// <summary>
    /// Handle an error
    /// </summary>
    /// <param name="error">index of error</param>
    private void HandleError(SyntaxErrors error)   throws ExpressionParserException
    {
        String StrError = "";
        switch(error)
        {
            case  Synatx:
                StrError = "Syntax Error";
                break;
            case  UnBalparens:
                StrError = "Unabalanced Parentheses";
                break;
            case NoExpression:
                StrError = "No Expression Present";
                break;
            case DivByZero:
                StrError = "Division By Zero";
                break;
            default:
        }
        throw new ExpressionParserException(StrError);
    }
    /// <summary>
    /// Obtain the next token
    /// </summary>
    private void GetToken()
    {
        tokType = TokenTypes.None;
        token = "";

        //Check for end of expression.
        if (expIdx == exp.length())
        {
            token = EOE;
            return;
        }

        //Skip over white space
        while (expIdx < exp.length() && Character.isSpaceChar(exp.indexOf(expIdx)))
            ++expIdx;

        //Trailing whitespace ends expression.
        if (expIdx == exp.length())
        {
            token = EOE;
            return;
        }

        if (IsDelimiter(exp.charAt(expIdx)))  //Is operator
        {
            token += exp.charAt(expIdx);
            expIdx++;
            tokType = TokenTypes.Delimiter;
        }
        else if (Character.isLetter(exp.charAt(expIdx)))  //Is variable
        {
            while (!IsDelimiter(exp.charAt(expIdx)))
            {
                token += exp.charAt(expIdx);
                expIdx++;
                if (expIdx >= exp.length()) break;
            }
            tokType = TokenTypes.Variable;

        }
        else if (Character.isDigit(exp.charAt(expIdx))) //Is a number
        {
            while (!IsDelimiter(exp.charAt(expIdx)))
            {
                token += exp.charAt(expIdx);
                expIdx++;
                if (expIdx >= exp.length()) break;
            }
            tokType = TokenTypes.Number;
        }
        else
        {
            token = EOE;
            return;
        }
    }

    /// <summary>
    /// Check whether c is  a delimiter
    /// </summary>
    /// <param name="c">the checked char</param>
    /// <returns>Return true if c is a delimiter, else return false</returns>
    private static Boolean IsDelimiter(char c)
    {
        if ((" +-/*%^=()".indexOf(c) != -1))
        {
            return true;
        }

        return false;
    }

}
