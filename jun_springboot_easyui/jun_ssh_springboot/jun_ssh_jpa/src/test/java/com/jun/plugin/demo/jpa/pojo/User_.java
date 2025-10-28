package com.jun.plugin.demo.jpa.pojo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-10-03T04:36:03.532+0800")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, Integer> userId;
	public static volatile SingularAttribute<User, String> account;
	public static volatile SingularAttribute<User, Logrole> logrole;
	public static volatile SingularAttribute<User, String> userName;
	public static volatile SingularAttribute<User, String> password;
}
