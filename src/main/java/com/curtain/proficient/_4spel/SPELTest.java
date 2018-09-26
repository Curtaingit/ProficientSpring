package com.curtain.proficient._4spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author Curtain
 * @date 2018/9/20 14:35
 */
public class SPELTest {

    public static void main(String[] args) {

//        test1();
        test2();
    }

    //简单使用spel  ExpressionParser
    private static void test1() {
        ExpressionParser parser = new SpelExpressionParser();

        Expression exp = parser.parseExpression("'HEOOL' + 'WORLD'");

        String message = (String) exp.getValue();

//        message = exp.getValue(String.class);

        System.out.println(message);

    }

    private static void test2(){
        ExpressionParser parser = new SpelExpressionParser();

        User user = new User("xm");

        EvaluationContext context = new StandardEvaluationContext(user);

        //如果不是public 是私有的还是拿不到。   或者提供public setName 方法
        context.setVariable("newName","xxx");
        parser.parseExpression("name=#newName").getValue(context);

        System.out.println(user.getName());

        //如果不是public 是私有的还是拿不到。   或者提供public getName 方法
        String name = (String) parser.parseExpression("name").getValue(context);

        System.out.println(name);
    }
}
