package com.daxue.studyandroid;


import android.content.Context;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorTest {
    private static final String FAKE_STRING = "CalculatorTest";

    @Mock
    Context context;

    @Test
    public void testAdd() throws Exception {
        Calculator calculator = new Calculator();
        int sum = calculator.add(1, 2);
        assertEquals(3, sum);
    }

    @Test
    public void readStringFromContext_LocalizedString() {

        when(context.getString(R.string.app_name)).thenReturn(FAKE_STRING);
        assertEquals(context.getString(R.string.app_name), FAKE_STRING);

        when(context.getPackageName()).thenReturn("com.android.application");
        System.out.println(context.getPackageName());

    }


}
