package com.slj.pojo;

import com.slj.pojo.Order.META;
import com.slj.pojo.Order.META.COLUMS;
import java.io.Serializable;
import work.myfavs.framework.orm.meta.annotation.Column;
import work.myfavs.framework.orm.meta.annotation.PrimaryKey;
import work.myfavs.framework.orm.meta.annotation.Table;
import work.myfavs.framework.orm.meta.enumeration.GenerationType;

@Table(value = META.TABLE, strategy = GenerationType.SNOW_FLAKE)
public class Order implements Serializable {
    @PrimaryKey
    @Column(value = COLUMS.userName)
    String userName;
    @Column(value = COLUMS.age)
    String age;

    public Order() {
        this.userName = userName;
        this.age      = age;
    }

    public enum META {
        ;
        public static final String TABLE ="orderr";

        public interface COLUMS{
            String userName="user_name";
            String age="age";
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Order{" +
            "userName='" + userName + '\'' +
            ", age='" + age + '\'' +
            '}';
    }
}
