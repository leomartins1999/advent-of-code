require_relative 'bingo_board'
require_relative 'bingo_game'

class BingoInputReader
  def parse_game(input)
    lines = read_lines(input)

    numbers = read_numbers(lines[1])

    boards = read_boards(lines.drop(2))

    BingoGame.new(numbers, boards)
  end

  private

  def read_lines(input)
    input.split(/\n+/)
  end

  def read_numbers(line)
    line
      .split(',')
      .map(&:to_i)
  end

  def read_boards(lines)
    lines
      .each_slice(BINGO_SIZE)
      .map { |chunk| BingoBoard.new(chunk) }
  end
end
