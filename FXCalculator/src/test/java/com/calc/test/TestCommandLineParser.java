package com.calc.test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.InputMismatchException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.calc.common.CommandLineParser;

public class TestCommandLineParser {
	
	CommandLineParser commandLineParser;
	
	
	@Before
	public void setup() {
		commandLineParser = new CommandLineParser();
	}
	
	@Test
	public void testParse() {
		String[] args  = {"AUD", "100.0", "in", "USD"};
		commandLineParser.parse(args);
		assertEquals("Base currency should be same","AUD", commandLineParser.getBaseCurrency());
		assertEquals("Terms currency should be same","USD", commandLineParser.getTermsCurrency());
		assertEquals("Amount should be same",new Double(100.0), commandLineParser.getAmount());
		
	}
	
	@Test(expected=InputMismatchException.class)
	public void testParseErrorInvalidArgumentsNumber() {
		String[] args  = {"AUD", "100.0", "USD"};
		commandLineParser.parse(args);
	}
	
	@Test(expected=InputMismatchException.class)
	public void testParseErrorInvalidArgumentsType() {
		String[] args  = {"AUD", "abc", "in", "USD"};
		commandLineParser.parse(args);
	}
	
	@Test
	public void testUserInput() {
		String input = "EUR 10.8 in EUR";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
		commandLineParser.parse(null);
	    assertEquals("EUR",commandLineParser.getBaseCurrency());
	    assertEquals("EUR",commandLineParser.getTermsCurrency());
	    assertEquals(new Double(10.8),commandLineParser.getAmount());
	}
	
	
	
	@After
	public void tearDown() {
		commandLineParser = null;
	}

}
