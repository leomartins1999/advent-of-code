require_relative 'bingo_input_reader'

BINGO_SIZE = 5.freeze

# Solution for day 1 of Advent of Code 2021
class Day4

  def initialize
    @input_reader = BingoInputReader.new
  end

  def solve_first(input)
    game = @input_reader.parse_game(input)

    winner, last_number = game.play

    winner.get_final_score(last_number)
  end

  def solve_second(input)
    game = @input_reader.parse_game(input)

    loser, last_number = game.get_loser

    loser.get_final_score(last_number)
  end
end
