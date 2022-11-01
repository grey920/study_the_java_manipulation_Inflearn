package com.example.lombok;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MemberTest {

    @Test
    public void getterSetter(){
        Member member = new Member();
        member.setName( "grey" );

        Assert.assertEquals( member.getName(), "grey" );
    }



}