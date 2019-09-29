package com.curtain.proficient._8compilercheck;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;
/**
 * @author Curtain
 * @date 2019/1/31 10:33
 */
//用*表示支持所有的Annotation
@SupportedAnnotationTypes("*")
//只支持JDK1.8的java代码
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class NameCheckProcessor extends AbstractProcessor{

    private NameChecker nameChecker;

    /**
     * 初始化名称检查插件
     * @param processingEnv
     */
    @Override
    public void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        nameChecker = new NameChecker(processingEnv);
    }

    /**
     * 对输入的语法树的各个节点进行名称检查
     * @param annotations
     * @param roundEnv
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
       if (!roundEnv.processingOver()){
           for (Element element:roundEnv.getRootElements()){
               nameChecker.checkNames(element);
           }
       }
        return false;
    }
}
