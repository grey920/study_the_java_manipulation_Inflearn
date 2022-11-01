package com.example.lombok;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.common.reflection.XMember;

/**
 * boiler plate code
 * getter, setter 같은 코드들
 */
@Getter @Setter @EqualsAndHashCode
public class Member {

    private String name;

    private int age;

    public boolean isSameAge( Member member ) {
        return this.age == member.age;
    }

}
