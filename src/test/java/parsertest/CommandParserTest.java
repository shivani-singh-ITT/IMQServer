package parsertest;

import static org.junit.Assert.*;
import org.junit.Test;

import parser.CommandParser;

public class CommandParserTest {
	String[] keywords = new String[3]; 
	CommandParser parser=new CommandParser();
 
	@Test
	public void parse1Test() { 
		keywords=parser.parse("xyz");
		assertEquals("restart",keywords[0]);
	}
	
	@Test
	public void parse2Test() {
		keywords=parser.parse("imq help");
		assertEquals("restart",keywords[0]);
	}
	
	@Test
	public void parse3Test() {
		keywords=parser.parse("imq login -shivani");
		assertEquals("login",keywords[0]);
	}
	
	@Test
	public void parse4Test() {
		keywords=parser.parse("imq newtopic");
		assertEquals("restart",keywords[0]);
	}
}
