# CS441_Sudoku

## Sudoku Puzzle App
The goal of this project was to create custom classes and views to act as a sudoku puzzle, and I've made that in a simplest fashion. 0 Represents empty spaces in the puzzle, and cells can be edited by the input buttons on the bottom of the puzzle screen. The Home screen contains a spinner where he player can choose the difficulty of the puzzle they want to solve. Pressing the generate button will then GET a puzzle from the heroku sudoku api with the displayed difficulty. The player then can solve the puzzle on the puzzle screen and have their move count posted online when they correctly solve the puzzle.

## Production Log
11/30/19: Added feature for posting scores online once a puzzle is completed with a customizable player name. Scores are separated by difficulty

11/25/19: Added buttons to check the solve of a puzzle and to display the score of a player as they progress through the puzzle

11/20/19: Spinner properly integrates with puzzle and displays it properly on the puzzle screen

11/18/19: Finished API calls to get sudokus from online

11/15/19: Began setting up to make API calls to get puzzles from the internet

11/12/19: The puzzle can be edited with buttons at the bottom of the screen, the cell to be edited gets highlighted in yellow

11/11/19: Added buttons that will end up modifying the puzzle

11/10/19: Figured out how to get the entire puzzle board to render on screen

11/8/19: Got custom view to render, but only one group of 9 cells renders in each row of the table that's supposed to be the whole puzzle

11/7/19: Still playing around with custom view classes, now trying to extend from viewgroup to render child views properly

11/6/19: Learning about custom views, realized how fragments work a bit so now I'm making a custom view for the puzzle cells, not fragments

11/5/19: Trying to get custom sudoku fragments to render on screen, struggling

11/4/19: Began building custom fragments for sudoku cells so that I can easily place several cells on screen at once

11/1/19: Started project, added logic to check if a puzzle is solved, to be used later
