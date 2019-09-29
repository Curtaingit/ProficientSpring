package com.curtain.proficient._8compilercheck;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.*;
import javax.lang.model.util.ElementScanner8;
import javax.tools.Diagnostic.Kind;
import java.util.EnumSet;


/**
 * 程序名称规范编译插件
 * 如果程序命名不和规范，将会输出一个编译器的WARNING信息
 *
 * @author Curtain
 * @date 2019/1/31 10:38
 */
public class NameChecker {
    private final Messager messager;

    NameCheckScanner nameCheckScanner = new NameCheckScanner();

    NameChecker(ProcessingEnvironment processingEnvironment) {
        this.messager = processingEnvironment.getMessager();
    }

    /**
     * 对Java程序命名进行检查
     * 类或接口
     * 方法
     * 字段
     * 实例变量
     * 常量
     *
     * @param element
     */
    public void checkNames(Element element) {
        nameCheckScanner.scan(element);
    }

    /**
     * 名称检查器实现类
     */
    private class NameCheckScanner extends ElementScanner8<Void, Void> {

        /**
         * 检查Java类
         *
         * @param e
         * @param p
         * @return
         */
        @Override
        public Void visitType(TypeElement e, Void p) {
            scan(e.getTypeParameters(), p);
            checkCamelCase(e, true);
            super.visitType(e, p);
            return null;
        }

        /**
         * 检查方法名是否合法
         *
         * @param e
         * @param p
         * @return
         */
        @Override
        public Void visitExecutable(ExecutableElement e, Void p) {
            if (e.getKind() == ElementKind.METHOD) {
                Name name = e.getSimpleName();
                if (name.contentEquals(e.getEnclosingElement().getSimpleName())) {
                    messager.printMessage(Kind.MANDATORY_WARNING, "一个普通方法" + name + "不应当与类名重复，避免与构造函数产生混淆", e);
                }
                checkCamelCase(e, false);
            }
            super.visitExecutable(e, p);
            return null;
        }

        /**
         * 检查变量名是否合法
         *
         * @param e
         * @param p
         * @return
         */
        @Override
        public Void visitVariable(VariableElement e, Void p) {
            //如果这个Variable是枚举或常量，则大写命名解场，否则驼式命名法规则检查
            if (e.getKind() == ElementKind.ENUM_CONSTANT || e.getConstantValue() != null || heuristicallyConstant(e)) {
                checkALLCaps(e);
            } else {
                checkCamelCase(e, false);
            }
            return null;
        }

        private boolean heuristicallyConstant(VariableElement e) {
            if (e.getEnclosingElement().getKind() == ElementKind.INTERFACE) {
                return true;
            } else if (e.getKind() == ElementKind.FIELD && e.getModifiers().containsAll(EnumSet.of(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL))) {
                return true;
            } else {
                return false;
            }
        }

        /**
         * 检查传入的Element是否符合驼式命名法，不符合，给出警告信息
         *
         * @param e
         * @param initialCaps
         */
        private void checkCamelCase(Element e, boolean initialCaps) {
            String name = e.getSimpleName().toString();
            boolean previousUpper = false;
            boolean conventional = true;
            int firsCodePoint = name.codePointAt(0);

            if (Character.isUpperCase(firsCodePoint)) {
                previousUpper = true;
                if (!initialCaps) {
                    messager.printMessage(Kind.WARNING, "名称" + name + "应当以小写字母开头", e);
                    return;
                }
            } else if (Character.isLowerCase(firsCodePoint)) {
                if (initialCaps) {
                    messager.printMessage(Kind.WARNING, "名称" + name + "应当以大写字母开头", e);
                }
            } else {
                conventional = false;
            }

            if (conventional) {
                int cp = firsCodePoint;
                for (int i = Character.charCount(cp); i < name.length(); i += Character.charCount(cp)) {
                    cp = name.codePointAt(i);
                    if (Character.isUpperCase(cp)) {
                        if (previousUpper) {
                            conventional = false;
                            break;
                        }
                        previousUpper = true;
                    } else {
                        previousUpper = false;
                    }
                }
            }

            if (!conventional) {
                messager.printMessage(Kind.WARNING, "名称" + name + "应当以驼式命名法开头", e);
            }

        }

        /**
         * 大写字母检查，要求第一个字母必须是大写的英文字母，其余部分可以是下划线或大写字母
         *
         * @param e
         */
        private void checkALLCaps(Element e) {
            String name = e.getSimpleName().toString();

            boolean convertional = true;
            int firstCodePione = name.codePointAt(0);
            if (!Character.isUpperCase(firstCodePione)) {
                convertional = false;
            } else {
                boolean previousUnderscore = false;
                int cp = firstCodePione;
                for (int i = Character.charCount(cp); i < name.length(); i += Character.charCount(cp)) {
                    cp = name.codePointAt(i);
                    if (cp == (int) '_') {
                        if (previousUnderscore) {
                            convertional = false;
                            break;
                        }
                        previousUnderscore = true;
                    } else {
                        previousUnderscore = false;
                        if (!Character.isUpperCase(cp) && !Character.isDigit(cp)) {
                            convertional = false;
                            break;
                        }
                    }
                }
            }

            if (!convertional) {
                messager.printMessage(Kind.WARNING, "常量" + name + "应当全部以大写或下划线组成，并且以字母开头", e);
            }

        }


    }


}
