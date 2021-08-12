package com.sanri.tools.modules.core.dtos;

import lombok.Data;

import java.util.List;

/**
 * 简化版的类结构
 * 类部类不做处理,只处理最简单的 dto 结构
 */
@Data
public class ClassStruct {
    private List<Field> fields;
    private List<Method> methods;
    private String name;
    private String packageName;

    public ClassStruct() {
    }

    public ClassStruct(String name, String packageName) {
        this.name = name;
        this.packageName = packageName;
    }

    @Data
    public static abstract class Member{
        protected int modifys;
        protected String name;

        public Member(int modifys, String name) {
            this.modifys = modifys;
            this.name = name;
        }

        public Member() {
        }
    }

    @Data
    public static class Method extends Member{
        private String returnType;
        private List<Arg> args;

        public Method() {
        }

        public Method(int modifys, String name, String returnType, List<Arg> args) {
            super(modifys, name);
            this.returnType = returnType;
            this.args = args;
        }

        public Method(int modifys, String name, String returnType) {
            super(modifys, name);
            this.returnType = returnType;
        }
    }

    @Data
    public static class Arg{
        private String type;
        private String name;

        public Arg() {
        }

        public Arg(String type, String name) {
            this.type = type;
            this.name = name;
        }
    }

    @Data
    public static class Field extends Member{
        private String type;

        public Field() {
        }

        public Field(int modifys, String name, String type ) {
            super(modifys,name);
            this.type = type;
        }
    }
}
