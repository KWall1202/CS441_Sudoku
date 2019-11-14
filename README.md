# CS441_Sudoku

## Sudoku Puzzle App
The goal of this project was to create custom classes and views to act as a sudoku puzzle, and I've made that in a simplest fashion. -1 Represents empty spaces in the puzzle, and cells can be edited by the input buttons on the bottom of the puzzle screen. The other two screens were meant to be a difficulty select/generate new puzzle screen, and a solver on the other screen. I didn't get to implement those because I faced a lot of troubles with getting custom views to render properly, I learned a lot about context along the way though!

## Production Log
11/12/19: The puzzle can be edited with buttons at the bottom of the screen, the cell to be edited gets highlighted in yellow

11/11/19: Added buttons that will end up modifying the puzzle

11/10/19: Figured out how to get the entire puzzle board to render on screen

11/8/19: Got custom view to render, but only one group of 9 cells renders in each row of the table that's supposed to be the whole puzzle

11/7/19: Still playing around with custom view classes, now trying to extend from viewgroup to render child views properly

11/6/19: Learning about custom views, realized how fragments work a bit so now I'm making a custom view for the puzzle cells, not fragments

11/5/19: Trying to get custom sudoku fragments to render on screen, struggling

11/4/19: Began building custom fragments for sudoku cells so that I can easily place several cells on screen at once

11/1/19: Started project, added logic to check if a puzzle is solved, to be used later
