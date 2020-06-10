package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

//import com.sun.tools.javac.util.Log;

import triviaMaze.Maze;
import triviaMaze.MazeFactory;
import triviaMaze.Player;
import triviaMaze.PlayerFactory;
import triviaMaze.Room;
import triviaMaze.TriviaDatabase;

import java.util.ArrayList;
import java.util.Scanner;

import TriviaMazeDevelopment.TriviaMaze;

/*Authors: Kayla Rubin, Victor Vargas, Caleb Stanley
Description: This class tests the ability of TriviaMaze class initialization and player 
			 movement/effects based on correct/incorrect answers*/

class TriviaMazeTest {
	
	private ByteArrayInputStream in;
	private Room[][] rooms;
	 
	@Test
	void gameInitialize() throws SQLException {
		in = new ByteArrayInputStream("bob".getBytes());
		System.setIn(in);
		TriviaMaze.gameInitialize();
		String name = TriviaMaze.player.getName();
		assertEquals("bob",name);
	}
	
	//Test menu direction 
	@Test
	void menuDirection() throws SQLException{
		TriviaMaze.adminMode = true;
		TriviaMaze.db.changeTable();
		
		//attempt move down and answer question correctly
		in = new ByteArrayInputStream("1".getBytes());
		System.setIn(in);
		TriviaMaze.resetScanner();
		rooms = TriviaMaze.triviaMaze.getMaze();
		
		
		int[] direction = TriviaMaze.menuDirection(rooms[1][1],1, 1, TriviaMaze.player, "down");
		int[] playerPosition = TriviaMaze.triviaMaze.getPlayer().getCoordinates();
		int row = playerPosition[0];
		int column = playerPosition[1];
		
		assertEquals(2,row);
		assertEquals(1,column);
		
		//--------------------------------------------------------------
		//attempt move down and answer incorrectly
		boolean locked;
		in = new ByteArrayInputStream("2".getBytes());
		System.setIn(in);
		TriviaMaze.resetScanner();
		rooms = TriviaMaze.triviaMaze.getMaze();
		
		direction = TriviaMaze.menuDirection(rooms[2][1],2, 1, TriviaMaze.player, "down");
		playerPosition = TriviaMaze.triviaMaze.getPlayer().getCoordinates();
		row = playerPosition[0];
		column = playerPosition[1];
		locked = rooms[2][1].bottomLocked();
		
		assertEquals(true,locked);
		assertEquals(2,row);
		assertEquals(1,column);
		
		//--------------------------------------------------------------
		//attempt move up and answer correctly
		in = new ByteArrayInputStream("1".getBytes());
		System.setIn(in);
		TriviaMaze.resetScanner();
		rooms = TriviaMaze.triviaMaze.getMaze();
		
		direction = TriviaMaze.menuDirection(rooms[2][1],2, 1, TriviaMaze.player, "up");
		playerPosition = TriviaMaze.triviaMaze.getPlayer().getCoordinates();
		row = playerPosition[0];
		column = playerPosition[1];
		
		assertEquals(1,row);
		assertEquals(1,column);
		
	}
	
	
	
	 
}
